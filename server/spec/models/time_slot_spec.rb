require 'rails_helper'
require 'pp'
RSpec.describe TimeSlot, :type => :model do
  before { @time = FactoryGirl.build(:time_slot) }
  subject { @time }


  it { should respond_to :id }
  it { should respond_to :from }
  it { should respond_to :to }
  it { should respond_to :opinion_poll_id }

  it { should be_valid }

  it { should_not allow_value('').for :from }
  it { should_not allow_value('').for :to }
end
