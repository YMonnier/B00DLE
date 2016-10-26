require 'jwt'
class Api::OpinionPollsController < ApplicationController
  before_action :authenticate_user, :only => [:create, :destroy, :update, :index]

  ##
  #
  # Create a new opinion_poll.
  # Header Authorize: json auth token
  # JSON Parameters:
  # {
  #    "title": "Mon sondage",
  #    "description": "Ma description",
  #    "place": "Mon lieu...",
  #    "emails": ["test@mail.com"],
  #    "time_slots": [{
  #                       "from": "2016-10-23 15:23",
  #                       "to": "2016-10-23 17:00"
  #    }]
  # }
  #
  ##
  def create
    @opinion_poll = OpinionPoll.new(opinion_params)
    @opinion_poll.user_id = current_user.id
    if @opinion_poll.valid?
      invitation_models = validate_emails params[:emails]; return if performed?
      time_models = validate_time_slots params[:time_slots]; return if performed?

      if @opinion_poll.save
        invitation_models.each do |invitation|
          invitation.opinion_poll_id = @opinion_poll.id
          invitation.save
        end

        time_models.each do |time_slot|
          time_slot.opinion_poll_id = @opinion_poll.id
          time_slot.save
        end
        created_request @opinion_poll
        #render json: @opinion_poll,
        #              root: :data,
        #              status: :created
      end
    else
      bad_request @opinion_poll.errors
    end
  end

  ##
  #
  # Get all opinion polls
  # from the current user.
  #
  ##
  def index
    ok_request current_user
  end


  ##
  #
  # Get a specific opinion poll
  # with an link or id given.
  # If the link is given, we decode it.
  #
  ##
  def show
    id = params[:id]
    unless is_number? id
      token = id
      decode_t = decode_link token
      id = decode_t[0]['id']
    end

    @opinion_poll = OpinionPoll.find(id)

    ok_request @opinion_poll, %w(user answers.time_slots)
  rescue ActiveRecord::RecordNotFound
    r = {opinion_poll: 'Record Not Found'}
    return not_found r
  end


  ##
  #
  # Update a specific opinion poll
  # with a link or id given.
  # If the link is given, we decode it.
  #
  ##
  def update
    id = params[:id]
    unless is_number? id
      token = id
      decode_t = decode_link token
      id = decode_t[0]['id']
    end

    @opinion_poll = OpinionPoll.find(id)

    if @opinion_poll.update(opinion_params)
      ok_request @opinion_poll
    else
      bad_request @opinion_poll.errors
    end

  rescue ActiveRecord::RecordNotFound
    r = {opinion_poll: 'Record Not Found'}
    return not_found r
  end

  ##
  #
  # Destroy a specific opinion poll
  # with an link or id given.
  # If the link is given, we decode it.
  #
  ##
  def destroy
    id = params[:id]
    unless is_number? id
      token = id
      decode_t = decode_link token
      id = decode_t[0]['id']
    end
    @opinion_poll = OpinionPoll.find(id)
    @opinion_poll.destroy

    return deleted_request

  rescue ActiveRecord::RecordNotFound
    r = {opinion_poll: 'Record Not Found'}
    return not_found r
  end

  private
  def opinion_params
    params.permit(:title, :description, :place, :close)
  end

  ##
  #
  # Check if the parameter is a Float/Integer.
  # Return true if string is a Float/Integer, otherwise false.
  #
  ##
  def is_number? string
    true if Float(string) rescue false
  end

  ##
  #
  # Check if all email for the invitations are valid.
  # * emails: array of email
  #
  ##
  def validate_emails emails
    invitation_models = []
    emails.each do |email|
      invit = Invitation.new(email: email, opinion_poll: @opinion_poll)
      bad_request invit.errors.messages and return unless invit.valid? #opinion_poll_id: @opinion_poll)
      invitation_models.append invit
    end
    invitation_models
  end

  ##
  #
  # Check if all datetime for the time_slot are valid.
  # * times: array of timeSlot object
  #
  ##
  def validate_time_slots times
    time_models = []
    times.each do |time|
      t = TimeSlot.new(from: time[:from], to: time[:to], opinion_poll: @opinion_poll)

      bad_request t.errors.messages and return unless t.valid?
      time_models.append t
    end
    time_models
  end

  ##
  #
  # Encode some data to create
  # unique link with JWT
  # * id: opinion_poll's id
  # * title: opinion_poll's title
  #
  ##
  def encode_link id, title
    payload = {
        :id => id,
        :title => title
    }
    JWT.encode payload, nil, 'none'
  end

  ##
  #
  # Decode the link generated by *encode_link*
  #
  ##
  def decode_link data
    JWT.decode data, nil, false
  end

end
