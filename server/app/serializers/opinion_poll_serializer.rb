class OpinionPollSerializer < ActiveModel::Serializer
  has_one :user#, serializer: UserSerializer
  attributes :id, :title, :description, :place, :close
  #has_many :invitations#, serializer: InvitationSerializer
end
