# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20161009091458) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "administrators", force: :cascade do |t|
    t.string   "provider",               default: "email", null: false
    t.string   "uid",                    default: "",      null: false
    t.string   "encrypted_password",     default: "",      null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,       null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip"
    t.string   "last_sign_in_ip"
    t.string   "confirmation_token"
    t.datetime "confirmed_at"
    t.datetime "confirmation_sent_at"
    t.string   "unconfirmed_email"
    t.string   "name"
    t.string   "email"
    t.json     "tokens"
    t.datetime "created_at",                               null: false
    t.datetime "updated_at",                               null: false
    t.index ["email"], name: "index_administrators_on_email", using: :btree
    t.index ["reset_password_token"], name: "index_administrators_on_reset_password_token", unique: true, using: :btree
    t.index ["uid", "provider"], name: "index_administrators_on_uid_and_provider", unique: true, using: :btree
  end

  create_table "answers", force: :cascade do |t|
    t.string   "name"
    t.integer  "application_id"
    t.integer  "opinion_poll_id"
    t.integer  "time_slot_id"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
    t.index ["application_id"], name: "index_answers_on_application_id", using: :btree
    t.index ["opinion_poll_id"], name: "index_answers_on_opinion_poll_id", using: :btree
    t.index ["time_slot_id"], name: "index_answers_on_time_slot_id", using: :btree
  end

  create_table "applications", id: :integer, force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "invitations", id: false, force: :cascade do |t|
    t.integer  "opinion_poll_id"
    t.string   "email"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
    t.index ["opinion_poll_id"], name: "index_invitations_on_opinion_poll_id", using: :btree
  end

  create_table "opinion_polls", force: :cascade do |t|
    t.string   "title"
    t.string   "description"
    t.string   "place"
    t.integer  "administrator_id"
    t.datetime "created_at",       null: false
    t.datetime "updated_at",       null: false
    t.index ["administrator_id"], name: "index_opinion_polls_on_administrator_id", using: :btree
  end

  create_table "time_slots", force: :cascade do |t|
    t.date     "from"
    t.date     "to"
    t.integer  "opinion_poll_id"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
    t.index ["opinion_poll_id"], name: "index_time_slots_on_opinion_poll_id", using: :btree
  end

  create_table "users", force: :cascade do |t|
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.inet     "current_sign_in_ip"
    t.inet     "last_sign_in_ip"
    t.datetime "created_at",                          null: false
    t.datetime "updated_at",                          null: false
    t.index ["email"], name: "index_users_on_email", unique: true, using: :btree
    t.index ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree
  end

  add_foreign_key "answers", "applications"
  add_foreign_key "answers", "opinion_polls"
  add_foreign_key "answers", "time_slots"
  add_foreign_key "invitations", "opinion_polls"
  add_foreign_key "opinion_polls", "administrators"
  add_foreign_key "time_slots", "opinion_polls"
end
