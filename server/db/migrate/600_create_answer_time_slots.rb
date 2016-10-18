class CreateAnswerTimeSlots < ActiveRecord::Migration[5.0]
  def change
    create_table :answer_time_slots, id: false do |t|
      t.belongs_to :answer
      t.belongs_to :time_slot
    end
  end
end
