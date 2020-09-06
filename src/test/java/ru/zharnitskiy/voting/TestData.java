package ru.zharnitskiy.voting;

import ru.zharnitskiy.voting.model.Role;
import ru.zharnitskiy.voting.model.User;

public class TestData {
    public static final User USER = new User(100000, "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(100001, "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
}

