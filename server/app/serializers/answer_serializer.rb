class AnswerSerializer < ActiveModel::Serializer
  attributes :id, :app_id, :name
  belongs_to :opinion_poll
  has_many :time_slots, :through => :answer_time_slots
end
