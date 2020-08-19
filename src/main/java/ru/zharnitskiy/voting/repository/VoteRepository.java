package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
