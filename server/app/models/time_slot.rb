class TimeSlot < ApplicationRecord
  belongs_to :opinion_poll
  has_many :answer_time_slots
  has_many :answers, :through => :answer_time_slots

  validates :from, presence: true
  validates :to, presence: true
  #validate :dates_at_is_valid_datetime


  def dates_at_is_valid_datetime
    errors.add(:from, 'must be a valid datetime ' + self.from.to_s) if ((DateTime.strptime(self.from, '%Y-%m-%d %H:%M:%S %z') rescue ArgumentError) == ArgumentError)
    errors.add(:to, 'must be a valid datetime ' + self.to.to_s) if ((DateTime.strptime(self.from, '%Y-%m-%d %H:%M:%S') rescue ArgumentError) == ArgumentError)
  end
end
