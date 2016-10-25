class AnswerSerializer < ActiveModel::Serializer
  attributes :id, :app_id, :name
  has_one :opinion_poll
  has_many :time_slots, through: :answer_time_slots
end
