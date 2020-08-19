package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;

import java.util.List;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/votes/{id}")
    public Vote get(@PathVariable int id) {
        return voteRepository.getOne(id);
    }

    @PutMapping("/votes/{id}")
    public void update(@RequestBody Vote vote, @PathVariable int id) {
        voteRepository.save(vote);
    }

    @DeleteMapping("/votes/{id}")
    public void delete(@PathVariable int id) {
        voteRepository.deleteById(id);
    }

    @PostMapping("/votes")
    public void create(@RequestBody Vote vote) {
        voteRepository.save(vote);
    }

    @GetMapping("/votes")
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }
}
