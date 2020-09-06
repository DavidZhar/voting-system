package ru.zharnitskiy.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.service.UserService;
import ru.zharnitskiy.voting.util.SecurityUtil;
import ru.zharnitskiy.voting.util.ValidationUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/profile")
public class UserProfileController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public User get() {
        return userRepository.findById(SecurityUtil.authUserId()).orElseThrow(RuntimeException::new);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user) {
        ValidationUtil.assureIdConsistent(user, SecurityUtil.authUserId());
        userService.prepareToSave(user);
        userRepository.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        userRepository.deleteById(SecurityUtil.authUserId());
    }
}
