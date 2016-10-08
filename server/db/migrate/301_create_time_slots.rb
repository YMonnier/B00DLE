class CreateTimeSlots < ActiveRecord::Migration[5.0]
  def change
    create_table :time_slots do |t|
      t.date :from
      t.date :to
      t.belongs_to :opinion_poll, index: true, foreign_key: true
      t.timestamps
    end
  end
end
