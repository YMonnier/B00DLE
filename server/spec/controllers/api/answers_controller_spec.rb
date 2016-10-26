require 'rails_helper'
require 'pp'
RSpec.describe Api::AnswersController, :type => :controller do
  describe 'POST #create' do
    context 'when is successfully created' do
      before do
        @opinion_poll = FactoryGirl.create :opinion_poll

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

  describe 'PUT/PATCH #update' do
    before(:each) do
      @opinion_poll = FactoryGirl.create :opinion_poll

      @times = []
      4.times {
        t = FactoryGirl.create :time_slot, opinion_poll: @opinion_poll
        @times.append t.id
      }

      @answer = FactoryGirl.create :answer, opinion_poll: @opinion_poll

    end

    context 'when is successfully updated' do
      before(:each) do
        parameters = {
            id: @answer.id,
            app_id: @answer.app_id,
            times: [@times[0], @times[1]]
        }
        put :update, params: parameters
        @json = json_response
        @data = json_response[:data]
      end

      it 'time slot id should be the same' do
        expect(@data[:time_slots][0][:id]).to eql @times[0]
        expect(@data[:time_slots][1][:id]).to eql @times[1]
      end

      it { should respond_with 200 }
    end

    context 'when is not successfully updated with not found time slot error' do
      before(:each) do
        parameters = {
            id: @answer.id,
            app_id: @answer.app_id,
            times: [-1234]
        }
        put :update, params: parameters
        @json = json_response
        @data = json_response[:data]
      end

      it { should respond_with 404 }
    end

    context 'when is not successfully updated with not found answer error' do
      before(:each) do
        parameters = {
            id: -23,
            app_id: @answer.app_id,
            times: [@times[0]]
        }
        put :update, params: parameters
        @json = json_response
        @data = json_response[:data]
      end

      it { should respond_with 404 }
    end

    context 'when is not successfully updated with not found answer error' do
      before(:each) do
        parameters = {
            id: @answer.id,
            app_id: 'Not Found!',
            times: [@times[0]]
        }
        put :update, params: parameters
        @json = json_response
        @data = json_response[:data]
      end

      it { should respond_with 404 }
    end
  end

  describe 'DELETE #destroy' do
    context 'when is successfully to get destroy an answer' do
      before do
        @opinion_poll = FactoryGirl.create :opinion_poll
        @answer = FactoryGirl.create :answer, opinion_poll: @opinion_poll

        parameters = {
            id: @answer.id,
            app_id: @answer.app_id
        }


        delete :destroy, params: parameters
      end

      it { should respond_with 204 }
    end
  end

  context 'when is not successfully to get destroy an answer - not found app_id' do
    before do
      @opinion_poll = FactoryGirl.create :opinion_poll
      @answer = FactoryGirl.create :answer, opinion_poll: @opinion_poll

      parameters = {
          id: @answer.id,
          app_id: 'NOT FOUND'
      }


      delete :destroy, params: parameters
    end

    it { should respond_with 404 }
  end

  context 'when is not successfully to get destroy an answer - not found answer id' do
    before do
      @opinion_poll = FactoryGirl.create :opinion_poll
      @answer = FactoryGirl.create :answer, opinion_poll: @opinion_poll

      parameters = {
          id: -123578,
          app_id: @answer.app_id
      }


      delete :destroy, params: parameters
    end

    it { should respond_with 404 }
  end
end
