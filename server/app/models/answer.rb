class Answer < ApplicationRecord
    has_one :application
    has_one :opinionPoll
    has_many :timeSlots

    validates :name, presence: true
    validates :application_id,
                presence: true,
                numericality: true,
                allow_blank: false
                
    validates :opinion_poll_id,
                presence: true,
                numericality: true,
                allow_blank: false

    validates :time_slot_id,
                presence: true,
                numericality: true,
                allow_blank: false
end
