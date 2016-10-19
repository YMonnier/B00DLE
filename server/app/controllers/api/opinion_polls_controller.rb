class Api::OpinionPollsController < ApplicationController
  before_action :authenticate_user, :only => [:create, :delete, :update]

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
      end
      return ok_request @opinion_poll
    else
      return bad_request @opinion_poll.errors.messages
    end
  end

  private
  def opinion_params
    params.permit(:title, :description, :place)
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
      invit = Invitation.new(email: email)
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
      t = TimeSlot.new(from: time[:from], to: time[:to])
      bad_request t.errors.messages and return unless t.valid?
      time_models.append t
    end
    time_models
  end
end
