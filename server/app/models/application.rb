class Application < ApplicationRecord
    has_many :answers
    validates :id,
                presence: true,
                numericality: true,
                allow_blank: false
end
