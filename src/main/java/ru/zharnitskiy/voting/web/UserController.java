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

    @GetMapping("/users/{id}")
    public User get(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    public void update(@RequestBody User user, @PathVariable int id) {
        userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public void create(@RequestBody User user) {
        System.out.println(user.toString());
        userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
