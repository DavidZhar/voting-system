package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zharnitskiy.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    Vote getByUserIdAndDate(int userId, LocalDate now);

    List<Vote> findAllByDate(LocalDate date);
}
