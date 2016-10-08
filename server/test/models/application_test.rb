require 'test_helper'

class ApplicationTest < ActiveSupport::TestCase
    test 'should save application model' do
        app = Application.new
        app.id = 2355642145453
        assert app.valid?
    end

    test 'should not save application model' do
        app = Application.new
        assert_not app.valid?
        app.id = 'ZEFGTR'
        assert_not app.valid?
    end
end
