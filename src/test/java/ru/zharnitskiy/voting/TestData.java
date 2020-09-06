package ru.zharnitskiy.voting;

import ru.zharnitskiy.voting.model.Role;
import ru.zharnitskiy.voting.model.User;

import java.util.EnumSet;

public class TestData {
    public static final User USER = new User(100000, "user@mail.ru", "password", Role.USER);
    public static final User ADMIN = new User(100001, "admin@mail.ru", "admin", Role.ADMIN, Role.USER);
    public static final User USER_UPDATED = new User(100000, "updated@mail.ru", "passupd", EnumSet.of(Role.USER));
    public static final User NEW_USER = new User("new@mail.ru", "newpass", EnumSet.of(Role.USER));
    public static final MockUser MOCK_NEW_USER = new MockUser("new@mail.ru", "newpass", EnumSet.of(Role.USER));
    public static final MockUser MOCK_USER_UPDATED = new MockUser(100000, "updated@mail.ru", "passupd", EnumSet.of(Role.USER));
    public static final MockUser MOCK_EXISTING_USER = new MockUser("user@mail.ru", "newpass", EnumSet.of(Role.USER));


}

