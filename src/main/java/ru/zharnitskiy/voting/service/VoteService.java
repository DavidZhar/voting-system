package ru.zharnitskiy.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.util.exception.TimeExpireException;

import java.time.LocalTime;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public static final LocalTime VOTING_END_TIME = LocalTime.of(11, 0, 0);

    public void changeVote(Vote vote) throws RuntimeException {
        LocalTime time = LocalTime.now();
        if (time.isAfter(VOTING_END_TIME))
            throw new TimeExpireException("You can't change your vote after " + VOTING_END_TIME.toString());
        voteRepository.save(vote);
    }
}
