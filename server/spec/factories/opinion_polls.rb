FactoryGirl.define do
  factory :opinion_poll do
    title { FFaker::Lorem.word }
    description { FFaker::Lorem.phrase }
    place { FFaker::AddressFR.city }
    user_id { rand(1...50) }
    close { false }
  end
end
