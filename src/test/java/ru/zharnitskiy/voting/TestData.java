package ru.zharnitskiy.voting;

import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.model.Role;
import ru.zharnitskiy.voting.model.User;

import java.time.LocalDate;
import java.util.EnumSet;

public class TestData {
    public static final User USER = new User(100000, "user@mail.ru", "password", Role.USER);
    public static final User ADMIN = new User(100001, "admin@mail.ru", "admin", Role.ADMIN);
    public static final User NEW_USER = new User("new@mail.ru", "newpass", EnumSet.of(Role.USER));
    public static final User USER_UPDATED = new User(100000, "updated@mail.ru", "passupd", EnumSet.of(Role.USER));
    public static final MockUser MOCK_NEW_USER = new MockUser("new@mail.ru", "newpass", EnumSet.of(Role.USER));
    public static final MockUser MOCK_USER_UPDATED = new MockUser(100000, "updated@mail.ru", "passupd", EnumSet.of(Role.USER));
    public static final MockUser MOCK_EXISTING_USER = new MockUser("user@mail.ru", "newpass", EnumSet.of(Role.USER));

    public static final Restaurant RESTAURANT_1 = new Restaurant(100002, "Restaurant1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(100003, "Restaurant2");
    public static final Restaurant NEW_RESTAURANT = new Restaurant("New Restaurant");
    public static final Restaurant RESTAURANT_UPDATED = new Restaurant(100002, "Restaurant1 Updated");

    public static final Dish NEW_DISH = new Dish("New Dish", 1000, RESTAURANT_1, LocalDate.now());
    public static final Dish DISH_1 = new Dish(100004, "Restaurant1_Dish1_Date1", 111, RESTAURANT_1, LocalDate.of(2020, 1, 1));
    public static final Dish DISH_UPDATED = new Dish(100004, "Restaurant1_Dish1_Date1 Updated", 1111, RESTAURANT_1, LocalDate.of(2020, 1, 1));


}

