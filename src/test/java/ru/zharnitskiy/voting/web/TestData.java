package ru.zharnitskiy.voting.web;

import ru.zharnitskiy.voting.model.Role;
import ru.zharnitskiy.voting.model.User;

import java.util.Set;

public class TestData {
    public static final User USER = new User(100000, "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(100001, "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
}

class MockUser{
    private String email;
    private String password;
    private Set<Role> roles;

    public MockUser() {
    }

    public MockUser(String email, String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}