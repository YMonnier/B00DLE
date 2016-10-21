FactoryGirl.define do
  factory :time_slot do
    from { FFaker::Time.date }
    to { FFaker::Time.date }
    opinion_poll
  end
end
