require 'rails_helper'
require 'pp'
RSpec.describe Answer, :type => :model do
  before {
    @opinion_poll = FactoryGirl.create :opinion_poll
    @answer = FactoryGirl.build :answer, opinion_poll: @opinion_poll
  }
  subject { @answer }

  it { should respond_to :name }
  it { should respond_to :app_id }
  it { should respond_to :opinion_poll_id }

  it { should be_valid }

  it { should_not allow_value('').for :name }
  it { should_not allow_value(nil).for :name }
end
