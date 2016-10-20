require 'rails_helper'
require 'pp'
RSpec.describe Api::OpinionPollsController, :type => :controller do
  describe 'POST #create' do

    context 'when is successfully created' do
      before do
        @user = FactoryGirl.create :user

        token = generate_token @user
        api_authorization_header(token)

        @opinion_poll = FactoryGirl.attributes_for :opinion_poll
        @time = FactoryGirl.attributes_for :time_slot
        @invitation = FactoryGirl.attributes_for :invitation

        parameters = {
            title: @opinion_poll[:title],
            description: @opinion_poll[:description],
            place: @opinion_poll[:place],
            emails: [@invitation[:email]],
            time_slots: [{from: @time[:from], to: @time[:to]}]
        }

        #@user_attr[:password_confirmation] = @user_attr[:password]
        post :create, params: parameters
        @json = json_response
        @data = json_response[:data]
        pp '-------------'
        pp @json
        @user_response = @json[:data]
      end


      it 'succeed should be succeed' do
        expect(@json[:succeed]).to eql true
      end

      it 'user id should be the same' do
        expect(@data[:user_id]).to eql @user.id
      end

      it 'title should be the same' do
        expect(@data[:title]).to eql @opinion_poll[:title]
      end

      it 'description should be the same' do
        expect(@data[:description]).to eql @opinion_poll[:description]
      end

      it { should respond_with 201 }
    end

    context 'when is not permit to create(Not authorize)' do
      before do
        @user = FactoryGirl.create :user

        @opinion_poll = FactoryGirl.attributes_for :opinion_poll
        @time = FactoryGirl.attributes_for :time_slot
        @invitation = FactoryGirl.attributes_for :invitation

        parameters = {
            title: @opinion_poll[:title],
            description: @opinion_poll[:description],
            place: @opinion_poll[:place],
            emails: [@invitation[:email]],
            time_slots: [{from: @time[:from], to: @time[:to]}]
        }
        post :create, params: parameters
      end

      it { should respond_with 401 }
    end


    context 'when is not successful - bad request - bad email parameters' do
      before do
        @user = FactoryGirl.create :user

        token = generate_token @user
        api_authorization_header(token)

        @opinion_poll = FactoryGirl.attributes_for :opinion_poll
        @time = FactoryGirl.attributes_for :time_slot

        @invitation = FactoryGirl.attributes_for :invitation
        @invitation[:email] = 'zedezd@zed'

        parameters = {
            title: @opinion_poll[:title],
            description: @opinion_poll[:description],
            place: @opinion_poll[:place],
            emails: [@invitation[:email]],
            time_slots: [{from: @time[:from], to: @time[:to]}]
        }

        #@user_attr[:password_confirmation] = @user_attr[:password]
        post :create, params: parameters
        @json = json_response
        @data = json_response[:data]
        pp '-------------'
        pp @json
        @user_response = @json[:data]
      end


      it 'succeed should be false' do
        expect(@json[:succeed]).to eql false
      end

      it { should respond_with 400 }
    end
  end
end
