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
    end
  end
end
