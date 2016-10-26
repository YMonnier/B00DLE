class Answer < ApplicationRecord
    belongs_to :opinion_poll
    has_many :time_slots, :through => :answer_time_slots

    validates :name, presence: true, allow_blank: false

    validates :opinion_poll_id,
                presence: true,
                numericality: true,
                allow_blank: false
end
