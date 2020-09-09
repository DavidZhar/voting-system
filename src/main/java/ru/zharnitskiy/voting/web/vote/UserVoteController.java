package ru.zharnitskiy.voting.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.service.VoteService;
import ru.zharnitskiy.voting.util.ValidationUtil;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import static ru.zharnitskiy.voting.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(UserVoteController.REST_URL)
public class UserVoteController {
    static final String REST_URL = "/rest/votes";

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
    public ResponseEntity<Vote> create(@Valid @RequestBody Vote vote) {
        ValidationUtil.checkNew(vote);
        vote.setUser(userRepository.getOne(authUserId()));
        Vote created = voteRepository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(vote, id);
        vote.setUser(userRepository.getOne(authUserId()));
        voteService.changeVote(vote);
    }
}
