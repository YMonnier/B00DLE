require 'rails_helper'

RSpec.describe Invitation, :type => :model do
  before { @invitation = FactoryGirl.build(:invitation) }
  subject { @invitation }


  it { should respond_to :email }
  it { should respond_to :opinion_poll_id }

  it { should be_valid }

  it { should validate_presence_of :email }

  it { should allow_value('example@domain.com').for :email }
  it { should_not allow_value('example@').for :email }
  it { should_not allow_value('').for :email }
end
