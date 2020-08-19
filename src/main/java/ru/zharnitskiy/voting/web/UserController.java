package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.service.AuthService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public User get() {
        return userRepository.getOne(AuthService.getAuthId());
    }

    @PutMapping("/profile")
    public void update(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping("/profile")
    public void delete() {
        userRepository.deleteById(AuthService.getAuthId());
    }

    @PostMapping("/users")
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}