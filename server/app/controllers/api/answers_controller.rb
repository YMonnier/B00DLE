class Api::AnswersController < ApplicationController
  ##
  #
  # Create a new answer.
  # JSON Parameters:
  # {
  #    "app_id": "5E:FF:56:A2:AF:15",
  #    "name": "John",
  #    "opinion_poll_id": 1,
  #    "times": [1, 2, 3, 56, 12]
  # }
  #
  ##
  def create
    if params[:times]
      times = params[:times]
      valid_time_slots = validate_time_slot_ids times; return if performed?
      @answer = Answer.new(answer_params)
      if valid_time_slots
        if @answer.save
          times.each do |time_slot_id|
            AnswerTimeSlot.create(answer_id: @answer.id, time_slot_id: time_slot_id)
          end
          created_request @answer
        else
          bad_request @answer.errors
        end
      end
    else
      r = {times: 'should not be empty'}
      return bad_request r
    end
  end

  ##
  #
  # Update a specific answer.
  # URL_PARAM :id
  # JSON Parameters:
  # {
  #    "app_id": "5E:FF:56:A2:AF:15",
  #    "name": "John",
  #    "times": [1, 2, 3, 56, 12]
  # }
  #
  ##
  def update
    if params[:times]
      @answer = Answer.find_by! id: params[:id], app_id: params[:app_id]
      times = params[:times]
      valid_time_slots = validate_time_slot_ids times; return if performed?
      if valid_time_slots
        if @answer.update(answer_update_params)
          @answer.answer_time_slots.delete_all
          times.each do |time_slot_id|
            AnswerTimeSlot.create(answer_id: @answer.id, time_slot_id: time_slot_id)
          end
          ok_request @answer
        else
          bad_request @answer.errors
        end
      end
    else
      r = {times: 'should not be empty'}
      return bad_request r
    end

  rescue ActiveRecord::RecordNotFound
    r = {answer: 'Record Not Found'}
    return not_found r
  end


  def destroy
    @answer = Answer.find_by! id: params[:id], app_id: params[:app_id]
    @answer.destroy
    return deleted_request

  rescue ActiveRecord::RecordNotFound
    r = {answer: 'Record Not Found'}
    return not_found r
  end

  private
  def answer_params
    params.permit(:app_id, :name, :opinion_poll_id)
  end

  def answer_update_params
    params.permit(:app_id, :name)
  end

  ##
  #
  # Check if all time slot ids exist
  # * times: array of time slot id
  #
  ##
  def validate_time_slot_ids times

    empty_times if times.size == 0

    times.each do |id|
      time_slot = TimeSlot.find(id)
    end
    true

  rescue ActiveRecord::RecordNotFound
    r = {times: 'Record Not Found'}
    return not_found r
  end


  ##
  #
  # Exception when array times is empty.
  #
  ##
  def empty_times
    r = {times: 'should not be empty'}
    return not_found r
  end
end
