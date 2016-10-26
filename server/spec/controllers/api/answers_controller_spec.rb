require 'rails_helper'
require 'pp'
RSpec.describe Api::AnswersController, :type => :controller do
  describe 'POST #create' do
    context 'when is successfully created' do
      before do
        @opinion_poll = FactoryGirl.create :opinion_poll #, user: @user

        @times = []
        4.times {
          t = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
          @times.append t.id
        }


        parameters = {
            app_id: '5E:FF:56:A2:AF:15',
            name: 'John',
            opinion_poll_id: @opinion_poll.id,
            times: @times
        }

        post :create, params: parameters
        @json = json_response
        @data = json_response[:data]
        @user_response = @json[:data]
      end


      it 'succeed should be succeed' do
        expect(@json[:succeed]).to eql true
      end

      it 'opinion poll id should be the same' do
        expect(@data[:opinion_poll][:id]).to eql @opinion_poll.id
      end

      it 'time slots id should be the same' do
        expect(@data[:time_slots].size).to eql 4
        @times.each_with_index do |id, index|
          expect(@data[:time_slots][index][:id]).to eql id
        end
      end

      it { should respond_with 201 }
    end
  end
end
