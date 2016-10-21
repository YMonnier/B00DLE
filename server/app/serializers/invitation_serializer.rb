class InvitationSerializer < ActiveModel::Serializer
  attributes :id, :email
  has_one :opinion_poll
end
