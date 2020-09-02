package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.service.AuthService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public User get() {
        return userRepository.findById(AuthService.getAuthId()).orElse(null);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping
    public void delete() {
        userRepository.deleteById(AuthService.getAuthId());
    }
}
