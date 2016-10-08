class OpinionPoll < ApplicationRecord
    has_many :answers
    has_many :invitations
    has_many :timeSlots
    has_one :administrator

    validates :title,
                presence: true,
                allow_blank: false

    validates :description,
                presence: true,
                allow_blank: false

    validates :place,
                presence: true,
                allow_blank: false

    validates :administrator_id,
                presence: true,
                numericality: true,
                allow_blank: false

end
