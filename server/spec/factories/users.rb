FactoryGirl.define do
  factory :user do
    email { FFaker::Internet.email }
    password { FFaker::Internet.password(8) }
    name { FFaker::Name.name }
  end
end
