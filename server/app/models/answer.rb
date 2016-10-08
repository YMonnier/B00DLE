class Answer < ApplicationRecord
    has_one :application
    has_one :opinionPoll
    has_many :timeSlots
end
