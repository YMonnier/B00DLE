class User < ActiveRecord::Base
  has_secure_password

  has_many :opinion_polls, dependent: :destroy

  validates :email,
            presence: true,
            uniqueness: true,
            format: {with: /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\z/i},
            allow_blank: false
  validates :name,
            presence: true,
            allow_blank: false
  validates :password,
            length: {minimum: 8},
            presence: true,
            allow_blank: false
end
