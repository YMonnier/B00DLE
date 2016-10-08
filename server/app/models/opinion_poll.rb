class OpinionPoll < ApplicationRecord
    has_many :answers
    has_many :invitations
    has_many :timeSlots
    has_one :administrator
end
