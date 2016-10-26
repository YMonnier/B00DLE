class AnswerTimeSlotSerializer < ActiveModel::Serializer
  belongs_to :answer
  belongs_to :time_slot
end
