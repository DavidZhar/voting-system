package ru.zharnitskiy.voting.util;


//import ru.javawebinar.topjava.to.UserTo;
//import ru.javawebinar.topjava.util.UserUtil;
import ru.zharnitskiy.voting.model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

//    private UserTo userTo;
    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
//        this.userTo = UserUtil.asTo(user);
        this.user = user;
    }

//    public int getId() {
//        return userTo.id();
//    }
    public int getId() {
        return user.getId();
    }

//    public void update(UserTo newTo) {
//        userTo = newTo;
//    }
    public void update(User newTo) {
        user = newTo;
    }

//    public UserTo getUserTo() {
//        return userTo;
//    }
    public User getUser() {
        return user;
    }

    @Override
//    public String toString() {
//        return userTo.toString();
//    }
    public String toString() {
        return user.toString();
    }
}