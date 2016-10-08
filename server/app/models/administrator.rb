class Administrator < ApplicationRecord
    EMAIL_REGEX = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i
    has_many :opinionPolls
    validates :name,
            presence: true
    validates :email,
            presence: true,
            uniqueness: true,
            format: EMAIL_REGEX
    validates :password, #password
                presence: true,
                length: { in: 8..72 },
                confirmation: true #password_confirmation field
                allow_blank: false
end
