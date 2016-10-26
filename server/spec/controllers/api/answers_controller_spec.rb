require 'rails_helper'
require 'pp'
RSpec.describe Api::AnswersController, :type => :controller do
  describe 'POST #create' do
    context 'when is successfully created' do
      before do
        @user = FactoryGirl.create :user

        token = generate_token @user
        api_authorization_header(token)

        @opinion_poll = FactoryGirl.create :opinion_poll, user: @user

        @time = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll

        pp '-- > @opinion_poll'
        pp @opinion_poll
        pp '-- > @time'
        pp @time

        parameters = {
            app_id: '5E:FF:56:A2:AF:15',
            name: 'John',
            opinion_poll_id: @opinion_poll.id,
            times: [@time.id]
        }
        pp parameters

        post :create, params: parameters
        @json = json_response
        pp @json
        @data = json_response[:data]
        @user_response = @json[:data]
      end


      #it 'succeed should be succeed' do
      #  expect(@json[:succeed]).to eql true
      #end

      it { should respond_with 201 }
    end
  end
end
