Rails.application.routes.draw do
  namespace :api, defaults: {format: :json} do
    mount Knock::Engine => '/login'
    resources :users, :only => [:create]
    resources :opinion_polls
    #resources :users
  end
end
