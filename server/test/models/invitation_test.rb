require 'test_helper'

class InvitationTest < ActiveSupport::TestCase
    test 'should save invitation mode' do
        invitation = Invitation.new
        invitation.opinion_poll_id = 12
        invitation.email = 'john@email.com'
        assert invitation.valid?
    end

    test 'should not save invitation mode with invalid email' do
        invitation = Invitation.new
        invitation.opinion_poll_id = 12
        invitation.email = 'john@'
        assert_not invitation.valid?
    end

    test 'should not save invitation mode with an empty poll id' do
        invitation = Invitation.new
        invitation.email = 'john@email.com'
        assert_not invitation.valid?
    end

    test 'should not save invitation mode with an empty invalid poll id(not numeric)' do
        invitation = Invitation.new
        invitation.opinion_poll_id = 'zedzedezd'
        invitation.email = 'john@email.com'
        assert_not invitation.valid?
    end

    test 'should not save invitation model with empty fields' do
        invitation = Invitation.new
        assert_not invitation.valid?
    end
end
