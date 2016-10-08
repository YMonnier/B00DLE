class Invitation < ApplicationRecord
    has_one :opinionPoll

    validates :email,
            presence: true,
            uniqueness: true,
            format: { with: /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\z/i, on: :create },
            allow_blank: false

    validates :opinion_poll_id,
            presence: true,
            numericality: true,
            allow_blank: false
end
