package ru.zharnitskiy.voting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.service.UserService;
import ru.zharnitskiy.voting.util.ValidationUtil;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/users")
public class AdminUserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(new NotFoundException("No such entity with id " + id));
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "users", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(user, id);
        userService.prepareToSave(user);
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "users", allEntries = true)
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping()
    @Cacheable("users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/byemail")
    public User getByEmail(@RequestParam String email) {
        return userRepository.getByEmail(email);
    }
}
