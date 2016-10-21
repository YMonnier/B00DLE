class CreateOpinionPolls < ActiveRecord::Migration[5.0]
  def change
    create_table :opinion_polls do |t|
      t.string :title
      t.string :description
      t.string :place
      t.belongs_to :user, index: true, foreign_key: true
      t.boolean :close, default: false
      t.timestamps
    end
  end
end
