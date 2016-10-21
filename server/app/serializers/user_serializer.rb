class UserSerializer < ActiveModel::Serializer
  has_many :opinion_polls
  attributes :id, :name, :email
end
