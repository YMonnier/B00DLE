require 'rails_helper'
require 'pp'
RSpec.describe Api::UsersController, :type => :controller do
  describe 'POST #create' do

    context 'when is successfully created' do
      before(:each) do
        @user = FactoryGirl.build :user

        parameters = {
            name: @user.name,
            email: @user.email,
            password: @user.password,
            password_confirmation: @user.password
        }

        post :create, params: parameters
        @json = json_response
        @user_response = @json[:data]
      end

      it 'should be the same email' do
        expect(@user_response[:email]).to eql @user.email
      end

      it 'should be the same name' do
        expect(@user_response[:name]).to eql @user.name
      end

      it { should respond_with 201 }
    end

    context 'when is failed without the same password confirmation' do
      before(:each) do
        @user = FactoryGirl.build :user

        parameters = {
            name: @user.name,
            email: @user.email,
            password: @user.password,
            password_confirmation: 'ZEDZEF-&é"455SDF"'
        }

        post :create, params: parameters
        @json = json_response
        @user_response = @json[:data]
      end



      it { should respond_with 400 }
    end

    context 'when is failed without the email' do
      before(:each) do
        @user = FactoryGirl.build :user

        parameters = {
            name: @user.name,
            email: '',
            password: @user.password,
            password_confirmation: @user.password,
        }

        post :create, params: parameters

        @json = json_response
      end

      it 'should not be valid and no empty' do
        expect(@json[:errors][:email][0]).to eql 'can\'t be blank'
        expect(@json[:errors][:email][1]).to eql 'is invalid'
      end

      it { should respond_with 400 }
    end
  end
end
