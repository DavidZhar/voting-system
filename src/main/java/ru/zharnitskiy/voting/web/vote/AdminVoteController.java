package ru.zharnitskiy.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(AdminVoteController.REST_URL)
public class AdminVoteController {
    static final String REST_URL = "/rest/admin/votes";

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return voteRepository.findById(id).orElseThrow(new NotFoundException("No such entity with id " + id));
    }

    @GetMapping()
    public List<Vote> getAll(@RequestParam @Nullable LocalDate date) {
        return (date == null) ? voteRepository.findAllWithUsersAndRestaurants() : voteRepository.findAllByDate(date);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Vote> getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam LocalDate date) {
        return voteRepository.findAllByRestaurantIdAndDate(restaurantId, date);
    }
}
