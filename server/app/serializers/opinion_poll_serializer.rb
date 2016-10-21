class OpinionPollSerializer < ActiveModel::Serializer
  has_one :user
  attributes :id, :title, :description, :place, :close
  has_many :invitations
  has_many :time_slots
end
