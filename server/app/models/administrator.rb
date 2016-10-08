class Administrator < ApplicationRecord
    #EMAIL_REGEX = /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\z/i
    has_many :opinionPolls
    validates :name,
            presence: true
    validates :email,
            presence: true,
            uniqueness: true,
            format: { with: /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\z/i, on: :create }
    validates :password, #password
                presence: true,
                length: { in: 8..72 },
                confirmation: true, #password_confirmation field
                allow_blank: false
end
