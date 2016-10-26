class InvitationSerializer < ActiveModel::Serializer
  attributes :id, :email
  belongs_to :opinion_poll
end
