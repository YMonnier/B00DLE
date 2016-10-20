FactoryGirl.define do
  factory :time_slot do
    id { rand(1...100) }
    from { FFaker::Time.date }
    to { FFaker::Time.date }
    opinion_poll_id { rand(1...100) }
  end
end
