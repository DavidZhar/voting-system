package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.util.ValidationUtil;

import javax.validation.Valid;

@RestController
public class RootController {
    @Autowired
    private UserRepository userRepository;

    //    @PreAuthorize
    @GetMapping("/")
    public String hello() {
        return null;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody User user) {
        ValidationUtil.checkNew(user);
        userRepository.save(user);
    }
}
