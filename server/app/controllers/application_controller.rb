class ApplicationController < ActionController::API
  #include ActionController::Serialization
  include Knock::Authenticable

  def bad_request object
    return render json: {
        succeed: false,
        message: object
    }, status: 400
  end

  def created_request object
    return render json: {
        succeed: true,
        data: object
    }, status: 201
  end
end
