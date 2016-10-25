FactoryGirl.define do
  factory :invitation do
    email { FFaker::Internet.email }
    opinion_poll_id { rand(1...100) }
    #opinion_poll
  end
end
