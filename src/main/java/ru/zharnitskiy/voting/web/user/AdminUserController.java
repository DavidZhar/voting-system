package ru.zharnitskiy.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/users")
public class AdminUserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping()
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/byemail")
    public User getByEmail(@RequestParam String email) {
        return userRepository.getByEmail(email);
    }
}
