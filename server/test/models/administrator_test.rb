require 'test_helper'

class AdministratorTest < ActiveSupport::TestCase
    test 'should not save admin without name, email and password' do
        admin = Administrator.new
        puts admin.valid?
        assert_not admin.valid?
    end

    test 'should save admin' do
        name = 'John'
        email = 'john@mail.com'
        password = 'myPassword'
        password_confirmation = 'myPassword'

        admin = Administrator.new
        admin.name = name
        admin.email = email
        admin.password = password
        admin.password_confirmation = password_confirmation

        assert admin.valid?
    end

    test 'should not save admin without a valid email' do
        name = 'John'
        email = 'zed@edzedzedzed'
        password = 'myPassword'
        password_confirmation = 'myPassword'

        admin = Administrator.new
        admin.name = name
        admin.email = email
        admin.password = password
        admin.password_confirmation = password_confirmation

        assert_not admin.valid?
    end

    test 'should not save admin without the same passwords(checking)' do
        name = 'John'
        email = 'zed@edzedzedzed.com'
        password = '13FERFFZ4'
        password_confirmation = 'myPassword'

        admin = Administrator.new
        admin.name = name
        admin.email = email
        admin.password = password
        admin.password_confirmation = password_confirmation

        assert_not admin.valid?
    end

    test 'should not save admin without an empty name' do
        name = ''
        email = 'john@mail.com'
        password = 'myPassword'
        password_confirmation = 'myPassword'

        admin = Administrator.new
        admin.name = name
        admin.email = email
        admin.password = password
        admin.password_confirmation = password_confirmation

        assert_not admin.valid?
    end

end
