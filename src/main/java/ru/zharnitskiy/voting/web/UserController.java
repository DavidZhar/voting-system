package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User user, @PathVariable int id) {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping()
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping()
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/bymail")
    public User getByMail(@RequestParam String email) {
        return userRepository.getByEmail(email);
    }
}
