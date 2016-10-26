FactoryGirl.define do
  factory :answer do
    name { FFaker::Name.name }
    app_id { FFaker::Internet.ip_v4_address }
    opinion_poll_id { rand(1...100) }
  end
end

