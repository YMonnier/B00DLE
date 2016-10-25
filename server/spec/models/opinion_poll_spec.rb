require 'rails_helper'
require 'pp'
RSpec.describe OpinionPoll, :type => :model do
  before { @opinion_poll = FactoryGirl.build :opinion_poll }
  subject { @opinion_poll }


  it { should respond_to :title }
  it { should respond_to :description }
  it { should respond_to :place }
  it { should respond_to :close }
  it { should respond_to :user_id }

  it { should be_valid }

  it { should_not allow_value('').for :title }
  it { should_not allow_value('').for :description }
  it { should_not allow_value('').for :place}
  it { should_not allow_value(nil).for :user_id}
  it { should allow_value(true).for :close }
end
