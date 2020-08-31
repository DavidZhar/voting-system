package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findAllByRestaurantAndDate(int restaurantId, LocalDate date);
}
