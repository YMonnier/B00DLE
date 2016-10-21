FactoryGirl.define do
  factory :opinion_poll do
    title { FFaker::Lorem.word }
    description { FFaker::Lorem.phrase }
    place { FFaker::AddressFR.city }
    close { false }
    user
  end
end
