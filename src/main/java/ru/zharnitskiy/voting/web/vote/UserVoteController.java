package ru.zharnitskiy.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.service.VoteService;
import ru.zharnitskiy.voting.util.SecurityUtil;
import ru.zharnitskiy.voting.util.ValidationUtil;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.time.LocalDate;

import static ru.zharnitskiy.voting.util.SecurityUtil.authUserId;

@RestController
@RequestMapping("/rest/votes")
public class UserVoteController {
    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Vote getUserVoteForToday() {
        return voteRepository.getByUserIdAndDate(authUserId(), LocalDate.now());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Vote vote) {
        ValidationUtil.checkNew(vote);
        vote.setUser(userRepository.findById(SecurityUtil.authUserId()).orElseThrow(new NotFoundException("User not found")));
        voteRepository.save(vote);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(vote, id);
        vote.setUser(userRepository.findById(SecurityUtil.authUserId()).orElseThrow(new NotFoundException("User not found")));
        voteService.changeVote(vote, id);
    }
}
