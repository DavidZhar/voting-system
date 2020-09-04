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
        LocalTime time11AM = LocalTime.of(11, 00, 00);
        LocalTime time = LocalTime.now();
        if (time.isAfter(time11AM)) throw new RuntimeException();
        voteRepository.save(vote);
    }
}
