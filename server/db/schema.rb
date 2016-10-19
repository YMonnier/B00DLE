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

ActiveRecord::Schema.define(version: 500) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

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

  create_table "invitations", force: :cascade do |t|
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
    t.integer  "user_id"
    t.boolean  "close",       default: false
    t.datetime "created_at",                  null: false
    t.datetime "updated_at",                  null: false
    t.index ["user_id"], name: "index_opinion_polls_on_user_id", using: :btree
  end

  create_table "time_slots", force: :cascade do |t|
    t.datetime "from"
    t.datetime "to"
    t.integer  "opinion_poll_id"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
    t.index ["opinion_poll_id"], name: "index_time_slots_on_opinion_poll_id", using: :btree
  end

  create_table "users", force: :cascade do |t|
    t.string "name"
    t.string "email"
    t.string "password_digest"
  end

  add_foreign_key "answers", "applications"
  add_foreign_key "answers", "opinion_polls"
  add_foreign_key "answers", "time_slots"
  add_foreign_key "invitations", "opinion_polls"
  add_foreign_key "opinion_polls", "users"
  add_foreign_key "time_slots", "opinion_polls"
end
