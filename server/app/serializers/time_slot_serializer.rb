class TimeSlotSerializer < ActiveModel::Serializer
  attributes :id, :from, :to
  has_one :opinion_poll
  has_many :answer_time_slots, dependent: :destroy
end
