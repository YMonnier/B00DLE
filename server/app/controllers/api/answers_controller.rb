class Api::AnswersController < ApplicationController


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

  private
  def answer_params
    params.permit(:app_id, :name, :opinion_poll_id)
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
