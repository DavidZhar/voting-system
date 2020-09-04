package ru.zharnitskiy.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.service.VoteService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.zharnitskiy.voting.util.SecurityUtil.authUserId;

@RestController
@RequestMapping("/rest/votes")
public class UserVoteController {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteService voteService;

    @GetMapping
    public Vote getUserVoteForToday() {
        return voteRepository.getByUserIdAndDate(authUserId(), LocalDate.now());
    }

    @PostMapping()
    public void create(@Valid @RequestBody Vote vote) {
        voteRepository.save(vote);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Vote vote, @PathVariable int id) {
        voteService.changeVote(vote, id);
    }
}
