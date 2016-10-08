require 'test_helper'

class AnswerTest < ActiveSupport::TestCase
    test 'should save answer' do
        answer = Answer.new
        answer.name = 'John'
        answer.application_id = 1
        answer.opinion_poll_id = 1
        answer.time_slot_id = 1
        assert answer.valid?
    end

    test 'should not save answer without empty name' do
        answer = Answer.new
        answer.name = ''
        answer.application_id = 1
        answer.opinion_poll_id = 1
        answer.time_slot_id = 1
        assert_not answer.valid?
    end

    test 'should not save answer without empty application_id' do
        answer = Answer.new
        answer.name = 'John'
        answer.opinion_poll_id = 1
        answer.time_slot_id = 1
        assert_not answer.valid?
    end

    test 'should not save answer without empty opinion_poll_id' do
        answer = Answer.new
        answer.name = 'John'
        answer.application_id = 1
        answer.time_slot_id = 1
        assert_not answer.valid?
    end

    test 'should not save answer without empty time_slot_id' do
        answer = Answer.new
        answer.name = 'John'
        answer.application_id = 1
        answer.opinion_poll_id = 1
        assert_not answer.valid?
    end

    test 'should not save answer without interger ids' do
        answer = Answer.new
        answer.name = 'John'
        answer.application_id = '112AZ'
        answer.opinion_poll_id = '2FR'
        answer.time_slot_id = '1ZE23'
        assert_not answer.valid?
    end
end
