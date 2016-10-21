FactoryGirl.define do
  factory :invitation do
    email { FFaker::Internet.email }
    opinion_poll
  end
end
