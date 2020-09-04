package ru.zharnitskiy.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/votes")
public class AdminVoteController {
    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return voteRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping()
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Vote> getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam LocalDate date) {
        return voteRepository.findAllByRestaurantIdAndDate(restaurantId, date);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        voteRepository.deleteById(id);
    }
}
