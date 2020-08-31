package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return voteRepository.getOne(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Vote vote, @PathVariable int id) {
        voteRepository.save(vote);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        voteRepository.deleteById(id);
    }

    @PostMapping()
    public void create(@RequestBody Vote vote) {
        voteRepository.save(vote);
    }

    @GetMapping()
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Vote> getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam LocalDate date) {
        return voteRepository.findAllByRestaurantAndDate(restaurantId, date);
    }
}
