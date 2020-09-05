package ru.zharnitskiy.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;

import java.time.LocalTime;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public void changeVote(Vote vote, int id) throws RuntimeException {
        LocalTime votingEndTime = LocalTime.of(12, 0, 0);
        LocalTime time = LocalTime.now();
        if (time.isAfter(votingEndTime)) throw new RuntimeException("You can't change your vote");
        voteRepository.save(vote);
    }
}
