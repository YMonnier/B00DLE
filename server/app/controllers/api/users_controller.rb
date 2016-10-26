class Api::UsersController < ApplicationController

  def create
    return bad_request 'password not the same' if params[:password] != params[:password_confirmation]

    @user = User.new(user_params)
    #@user.name = user_params[:name]
    #@user.email = user_params[:email]
    #@user.password = user_params[:password]

    if @user.save
      return created_request @user
    else
      return bad_request @user.errors
    end
  end

  private
  def user_params
    params.permit(:name, :email, :password)
  end
end
