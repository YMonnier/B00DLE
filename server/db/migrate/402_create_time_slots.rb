class CreateTimeSlots < ActiveRecord::Migration[5.0]
  def change
    create_table :time_slots do |t|
      t.datetime :from
      t.datetime :to
      t.belongs_to :opinion_poll, index: true, foreign_key: true
      t.timestamps
    end
  end
end
