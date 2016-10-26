class CreateAnswers < ActiveRecord::Migration[5.0]
  def change
    create_table :answers do |t|
      t.string :name
      t.string :app_id
      t.belongs_to :opinion_poll, index: true, foreign_key: true
      t.timestamps
    end
  end
end
