class CreateApplications < ActiveRecord::Migration[5.0]
  def change
    create_table :applications, id: false do |t|
      t.integer :id, null: false, primary_key: true
      t.timestamps
    end
  end
end