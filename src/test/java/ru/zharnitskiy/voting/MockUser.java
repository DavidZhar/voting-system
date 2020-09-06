package ru.zharnitskiy.voting;

import ru.zharnitskiy.voting.model.Role;

import java.util.Set;

public class MockUser {
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
