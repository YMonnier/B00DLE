class UserSerializer < ActiveModel::Serializer
  has_many :opinion_polls
  #has_many :opinion_polls#, serializer: OpinionPollSerializer
  attributes :id, :name, :email
end
