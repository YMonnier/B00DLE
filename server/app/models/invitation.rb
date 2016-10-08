class Invitation < ApplicationRecord
    EMAIL_REGEX = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i
    has_one :opinionPoll

    validates :email,
            presence: true,
            uniqueness: true,
            format: EMAIL_REGEX,
            allow_blank: false
            
    validates :opinion_poll_id,
            presence: true,
            numericality: true,
            allow_blank: false
end
