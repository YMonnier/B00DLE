class TimeSlot < ApplicationRecord
    has_one :opinionPoll

    validates :from, presence: true
    validates :to, presence: true
    validates :opinion_poll_id
                presence: true,
                numericality: true,
                allow_blank: false
end
