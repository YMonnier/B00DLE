class ApplicationController < ActionController::API
  include ActionController::Serialization
  include Knock::Authenticable

  ##
  #
  # Return json request
  # with status 400/Bad Request.
  # Parameters object: Error message.
  #
  ##
  def bad_request object
    return render json: {
        succeed: false,
        message: object
    }, status: 400
  end

  ##
  #
  # Return json request
  # with status 201/Created.
  # Parameters object: Object created.
  #
  ##
  def created_request object
    return render json: {
        succeed: true,
        data: object
    }, status: 201
  end

  ##
  #
  # Return json request
  # with status 200/OK.
  # Parameters object: Object wanted.
  #
  ##
  def ok_request object
    return render json: {
        succeed: true,
        data: object
    }, status: 200
  end
end
