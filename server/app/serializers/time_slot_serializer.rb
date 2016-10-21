class TimeSlotSerializer < ActiveModel::Serializer
  attributes :id, :from, :to
  has_one :opinion_poll
end
