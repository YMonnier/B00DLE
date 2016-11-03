require 'rails_helper'
RSpec.describe Api::OpinionPollsController, :type => :controller do
  describe 'POST #create' do
    context 'when is successfully created' do
      before do
        @user = FactoryGirl.create :user
        token = generate_token @user
        api_authorization_header(token)

        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user
        @time = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.create :invitation, opinion_poll: @opinion_poll

        parameters = {
            title: @opinion_poll.title,
            description: @opinion_poll.description,
            place: @opinion_poll.place,
            invitations: [@invitation.email],
            time_slots: [{from: @time.from, to: @time.to}]
        }

        post :create, params: parameters
        @json = json_response
        @data = json_response[:data]
        @user_response = @json[:data]
      end

      it 'user id should be the same' do
        expect(@data[:user][:id]).to eql @user.id
      end

      it 'title should be the same' do
        expect(@data[:title]).to eql @opinion_poll.title
      end

      it 'description should be the same' do
        expect(@data[:description]).to eql @opinion_poll.description
      end

      it { should respond_with 201 }
    end

    context 'when is not permit to create(Not authorize)' do
      before do
        @user = FactoryGirl.create :user
        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user
        @time = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.create :invitation, opinion_poll: @opinion_poll

        parameters = {
            title: @opinion_poll.title,
            description: @opinion_poll.description,
            place: @opinion_poll.place,
            invitations: [@invitation.email],
            time_slots: [{from: @time.from, to: @time.to}]
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

        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user
        @time = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.create :invitation, opinion_poll: @opinion_poll
        @invitation[:email] = 'zedezd@zed'

        parameters = {
            title: @opinion_poll.title,
            description: @opinion_poll.description,
            place: @opinion_poll.place,
            invitations: [@invitation.email],
            time_slots: [{from: @time.from, to: @time.to}]
        }

        post :create, params: parameters
        @json = json_response
        @data = json_response[:data]
        @user_response = @json[:data]
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

        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user
        @time = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.create :invitation, opinion_poll: @opinion_poll

        get :index

        @json = json_response
        @data = json_response[:data]
      end

      it 'must be the same user' do
        expect(@data[:id]).to eql @user.id
      end

      it 'should be the same opinion poll' do
        expect(@data[:opinion_polls][0][:title]).to eql @opinion_poll.title
        expect(@data[:opinion_polls][0][:description]).to eql @opinion_poll.description
        expect(@data[:opinion_polls][0][:place]).to eql @opinion_poll.place
      end

      it { should respond_with 200 }
    end
  end

  describe 'GET #show' do
    context 'when is successfully to get specific opinion poll - ' do
      before do
        @user = FactoryGirl.create :user
        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user
        @time = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
        @invitation = FactoryGirl.create :invitation, opinion_poll: @opinion_poll

        parameters = {
            id: @opinion_poll.id,
        }

        get :show, params: parameters

        @json = json_response
        @data = json_response[:data]
      end


      it 'id should be the same' do
        expect(@data[:id]).to eql @opinion_poll.id
      end

      it { should respond_with 200 }
    end

    context 'when is not successfully to get specific opinion poll - not found' do
      before do
        parameters = {
            id: -1234,
        }

        get :show, params: parameters

        @json = json_response
        @data = json_response[:data]
      end

      it { should respond_with 404 }
    end
  end

  describe 'PUT/PATCH #update' do
    before(:each) do
      @user = FactoryGirl.create :user
      @opinion_poll = FactoryGirl.create :opinion_poll, user: @user

      token = generate_token @user
      api_authorization_header(token)
    end

    context 'when is successfully updated' do
      before(:each) do
        parameters = {
            id: @opinion_poll.id,
            title: 'test_update'
        }

        put :update, params: parameters
      end

      it 'renders the json representation for the updated opinion poll' do
        opinion_response = json_response[:data]
        expect(opinion_response[:title]).to eql 'test_update'
      end

      it { should respond_with 200 }
    end

    context 'when opinion poll is not found' do
      before(:each) do
        parameters = {
            id: -1234,
            title: 'Hello!'
        }

        put :update, params: parameters
      end

      it { should respond_with 404 }
    end
  end

  describe 'DELETE #destroy' do

    context 'when is successfully to get destroy an opinion poll' do
      before do
        @user = FactoryGirl.create :user
        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user

        token = generate_token @user
        api_authorization_header(token)

        parameters = {
            id: @opinion_poll.id
        }

        delete :destroy, params: parameters
      end

      it { should respond_with 204 }
    end


    context 'when opinion poll is not found ' do
      before do
        @user = FactoryGirl.create :user
        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user

        token = generate_token @user
        api_authorization_header(token)


        parameters = {id: 12}

        delete :destroy, params: parameters
      end

      it { should respond_with 404 }
    end
  end
end
