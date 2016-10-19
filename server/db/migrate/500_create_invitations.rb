class CreateInvitations < ActiveRecord::Migration[5.0]
  def change
    create_table :invitations do |t|
      t.belongs_to :opinion_poll, index: true, foreign_key: true
      t.string :email
      t.timestamps
    end
  end
end
