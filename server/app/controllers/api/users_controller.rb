class Api::UsersController < ApplicationController

  def create
    return render json: {
        succeed: false,
        message: 'password not the same'
    }, status: 400 if user_params[:password] != user_params[:password_confirmation]

    user = User.new
    user.name = user_params[:name]
    user.email = user_params[:email]
    user.password = user_params[:password]

    if user.save
      return render json: {
          succeed: true,
          data: user
      }, status: 201
    else
      return render json: {
          succeed: false,
          message: user.errors
      }, status: 400
    end
  end

  private
  def user_params
    params.permit(:name, :email, :password, :password_confirmation)
  end
end
