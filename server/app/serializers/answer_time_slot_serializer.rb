class AnswerTimeSlotSerializer < ActiveModel::Serializer
  has_one :answer
  has_one :time_slot, dependent: :destroy
end
