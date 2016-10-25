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
        message: ActiveModelSerializers::SerializableResource.new(object, {})
    }, status: 400
  end

  ##
  #
  # Return json request
  # with status 404/Not Found.
  # Parameters object: Error message.
  #
  ##
  def not_found object
    return render json: {
        succeed: false,
        message: ActiveModelSerializers::SerializableResource.new(object, {})
    }, status: 404
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
        data: ActiveModelSerializers::SerializableResource.new(object, {})
    }, status: 201
  end

  ##
  #
  # Return json request
  # with status 204/Deleted.
  # Parameters object: Object created.
  #
  ##
  def deleted_request object
    return render json: {
        succeed: true,
        data: ActiveModelSerializers::SerializableResource.new(object, {})
    }, status: 204
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
        #data: object
        data: ActiveModelSerializers::SerializableResource.new(object, {})
    }, status: 200
  end
end
