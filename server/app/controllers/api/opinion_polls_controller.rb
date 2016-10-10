class Api::OpinionPollsController < ApplicationController
  before_action :authenticate_user, :only => [:create, :delete]

  def create
    render json: {test: 'CREATE OPINION POLL', user: current_user}
  end
end
