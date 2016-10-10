Rails.application.routes.draw do

  namespace :api, defaults: {format: :json} do
    mount Knock::Engine => '/user'
    resources :users, :only => [:create]
    resources :opinion_polls, :only => [:create]
    #resources :users
  end
end
