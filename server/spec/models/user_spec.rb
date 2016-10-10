require 'rails_helper'

RSpec.describe User, :type => :model do
  before { @user = FactoryGirl.build(:user) }
  subject { @user }


  it { should respond_to :name }
  it { should respond_to :password }
  it { should have_secure_password }
  it { should respond_to :email }

  it { should be_valid }

  it { should validate_presence_of :name }
  it { should validate_presence_of :email }
  it { should validate_uniqueness_of :email }

  it { should allow_value('example@domain.com').for :email }
  it { should_not allow_value('example@').for :email }
  it { should_not allow_value('').for :email }
  it { should_not allow_value('').for :name }

end
