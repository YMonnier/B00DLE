require 'rails_helper'
require 'pp'
RSpec.describe Api::OpinionPollsController, :type => :controller do
  describe 'POST #create' do
    context 'when is successfully created' do
      before do
        @user = FactoryGirl.create :user

        token = generate_token @user
        api_authorization_header(token)

        @opinion_poll = FactoryGirl.attributes_for :opinion_poll, user: @user

        @time = FactoryGirl.attributes_for :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.attributes_for :invitation, opinion_poll: @opinion_poll

        parameters = {
            title: @opinion_poll[:title],
            description: @opinion_poll[:description],
            place: @opinion_poll[:place],
            emails: [@invitation[:email]],
            time_slots: [{from: @time[:from], to: @time[:to]}]
        }

        post :create, params: parameters
        @json = json_response
        @data = json_response[:data]
        @user_response = @json[:data]
      end


      it 'succeed should be succeed' do
        expect(@json[:succeed]).to eql true
      end

      it 'user id should be the same' do
        expect(@data[:user][:id]).to eql @user.id
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

        @opinion_poll = FactoryGirl.attributes_for :opinion_poll, user: @user

        @time = FactoryGirl.attributes_for :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.attributes_for :invitation, opinion_poll: @opinion_poll

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


        @opinion_poll = FactoryGirl.attributes_for :opinion_poll, user: @user

        @time = FactoryGirl.attributes_for :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.attributes_for :invitation, opinion_poll: @opinion_poll
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
        @user_response = @json[:data]
      end


      it 'succeed should be false' do
        expect(@json[:succeed]).to eql false
      end

      it { should respond_with 400 }
    end
  end

  describe 'GET #index' do

    context 'when is successfully to get all information' do
      before do
        @user = FactoryGirl.create :user
        token = generate_token @user
        api_authorization_header(token)

        @opinion_poll = FactoryGirl.attributes_for :opinion_poll, user: @user

        @time = FactoryGirl.attributes_for :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.attributes_for :invitation, opinion_poll: @opinion_poll

        parameters = {
            title: @opinion_poll[:title],
            description: @opinion_poll[:description],
            place: @opinion_poll[:place],
            emails: [@invitation[:email]],
            time_slots: [{from: @time[:from], to: @time[:to]}]
        }

        post :create, params: parameters
        get :index

        @json = json_response
        @data = json_response[:data]
      end


      it 'succeed should be true' do
        expect(@json[:succeed]).to eql true
      end

      it 'must be the same user' do
        expect(@data[:id]).to eql @user[:id]
      end

      it 'should be the same opinion poll' do
        expect(@data[:opinion_polls][0][:title]).to eql @opinion_poll[:title]
        expect(@data[:opinion_polls][0][:description]).to eql @opinion_poll[:description]
        expect(@data[:opinion_polls][0][:place]).to eql @opinion_poll[:place]
      end

      it { should respond_with 200 }
    end
  end

  describe 'GET #show' do

    context 'when is successfully to get specific opinion poll - ' do
      before do
        @user = FactoryGirl.create :user

        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user

        @time = FactoryGirl.attributes_for :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.attributes_for :invitation, opinion_poll: @opinion_poll

        pp '======== user'
        pp @user
        pp '======== opinion poll'
        pp @opinion_poll

        parameters = {
            id: @opinion_poll[:id],
        }

        get :show, params: parameters



        @json = json_response

        pp '======== JSON:'
        pp @json
        @data = json_response[:data]
      end


      it 'succeed should be true' do
        expect(@json[:succeed]).to eql true
      end

      it { should respond_with 200 }
    end
  end
end
