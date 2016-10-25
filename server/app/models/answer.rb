class Answer < ApplicationRecord
    #has_one :opinion_poll
    #has_many :answer_time_slots

    validates :name, presence: true, allow_blank: false

    validates :opinion_poll_id,
                presence: true,
                numericality: true,
                allow_blank: false
end
