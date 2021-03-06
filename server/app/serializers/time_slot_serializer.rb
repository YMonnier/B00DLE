class TimeSlotSerializer < ActiveModel::Serializer
  attributes :id, :from, :to
  belongs_to :opinion_poll
  has_many :answer_time_slots
  has_many :answers, :through => :answer_time_slots
end
