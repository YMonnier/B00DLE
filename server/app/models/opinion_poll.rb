class OpinionPoll < ApplicationRecord
  #has_many :answers
  has_many :invitations, dependent: :destroy
  has_many :time_slots, dependent: :destroy
  belongs_to :user

  validates :title,
            presence: true,
            allow_blank: false

  validates :description,
            presence: true,
            allow_blank: false

  validates :place,
            presence: true,
            allow_blank: false

  validates :user_id,
            presence: true,
            allow_blank: false
end
