require 'test_helper'

class TimeSlotTest < ActiveSupport::TestCase
    test 'should save time slot model' do
        timeSlot = TimeSlot.new
        timeSlot.from = '25-04-2016 08:15'
        timeSlot.to = '25-04-2016 09:15'
        timeSlot.opinion_poll_id = 123
        assert timeSlot.valid?
    end
end
