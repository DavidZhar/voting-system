package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.service.UserService;

import javax.validation.Valid;

@RestController
public class RootController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String hello(){
        return null;
    }

    @PostMapping("/register")
    public void create(@Valid @RequestBody User user) {
        userRepository.save(user);
    }
}
