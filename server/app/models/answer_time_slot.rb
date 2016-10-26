class AnswerTimeSlot < ApplicationRecord
  belongs_to :answer
  belongs_to :time_slot
end
