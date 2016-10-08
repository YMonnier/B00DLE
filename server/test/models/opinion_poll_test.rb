require 'test_helper'

class OpinionPollTest < ActiveSupport::TestCase
    test 'should save opinion poll' do
        opinionPoll = OpinionPoll.new
        opinionPoll.title = 'My Custom Opinon Poll'
        opinionPoll.description = 'My super description'
        opinionPoll.place = 'My super place'
        opinionPoll.administrator_id = 123
        assert opinionPoll.valid?
    end

    test 'should not save opinion poll with empty fields' do
        opinionPoll = OpinionPoll.new
        assert_not opinionPoll.valid?
    end

    test 'should not save opinion poll with no numeric administrator_id' do
        opinionPoll = OpinionPoll.new
        opinionPoll.title = 'My Custom Opinon Poll'
        opinionPoll.description = 'My super description'
        opinionPoll.place = 'My super place'
        opinionPoll.administrator_id = 'zedzed'
        assert_not opinionPoll.valid?
    end

    test 'should not save opinion poll without empty title' do
        opinionPoll = OpinionPoll.new
        opinionPoll.title = ''
        opinionPoll.description = 'My super description'
        opinionPoll.place = 'My super place'
        opinionPoll.administrator_id = 123
        assert_not opinionPoll.valid?
    end

    test 'should not save opinion poll without empty description' do
        opinionPoll = OpinionPoll.new
        opinionPoll.title = 'My Custom Opinon Poll'
        opinionPoll.description = ''
        opinionPoll.place = 'My super place'
        opinionPoll.administrator_id = 123
        assert_not opinionPoll.valid?
    end

    test 'should not save opinion poll without empty place' do
        opinionPoll = OpinionPoll.new
        opinionPoll.title = 'My Custom Opinon Poll'
        opinionPoll.description = 'My super description'
        opinionPoll.place = ''
        opinionPoll.administrator_id = 123
        assert_not opinionPoll.valid?
    end
end
