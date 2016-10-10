class ApplicationController < ActionController::API
  #include ActionController::Serialization
  include Knock::Authenticable
end
