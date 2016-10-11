require 'rails_helper'

RSpec.describe Api::UsersController, :type => :controller do
  describe 'POST #create' do

    context 'when is successfully created' do
      before(:each) do
        @user_attr = FactoryGirl.attributes_for :user
        @user_attr[:password_confirmation] = @user_attr[:password]
        post :create, params: @user_attr
        @json = json_response
        @user_response = @json[:data]
      end

      it 'should be succeed' do
        expect(@json[:succeed]).to eql true
      end

      it 'should be the same email' do
        expect(@user_response[:email]).to eql @user_attr[:email]
      end

      it 'should be the same name' do
        expect(@user_response[:name]).to eql @user_attr[:name]
      end

      it { should respond_with 201 }
    end

    context 'when is failed without the same password confirmation' do
      before(:each) do
        @user_attr = FactoryGirl.attributes_for :user
        post :create, params: @user_attr
        @json = json_response
        @user_response = @json[:data]
      end

      it 'should not be same password' do
        expect(@json[:succeed]).to eql false
      end

      it { should respond_with 400 }
    end

    context 'when is failed without the email' do
      before(:each) do
        @user_attr = FactoryGirl.attributes_for :user
        @user_attr[:password_confirmation] = @user_attr[:password]
        @user_attr[:email] = ''
        post :create, params: @user_attr
        @json = json_response
        @user_response = @json[:data]
      end

      it 'should not be same password' do
        expect(@json[:succeed]).to eql false
      end

      it { should respond_with 400 }
    end

    context 'when is failed without the name' do
      before(:each) do
        @user_attr = FactoryGirl.attributes_for :user
        @user_attr[:password_confirmation] = @user_attr[:password]
        @user_attr[:name] = ''
        post :create, params: @user_attr
        @json = json_response
        @user_response = @json[:data]
      end

      it 'should not be same password' do
        expect(@json[:succeed]).to eql false
      end

      it { should respond_with 400 }
    end
  end
end
