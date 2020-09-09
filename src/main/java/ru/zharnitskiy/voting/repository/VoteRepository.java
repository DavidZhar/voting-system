package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zharnitskiy.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v FROM Vote v JOIN FETCH v.user WHERE v.restaurant.id = ?1 AND v.date = ?2")
    List<Vote> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id = ?1 AND v.date = ?2")
    Vote getByUserIdAndDate(int userId, LocalDate date);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.date = ?1")
    List<Vote> findAllByDate(LocalDate date);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant JOIN FETCH v.user")
    List<Vote> findAllWithUsersAndRestaurants();

    @Override
    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant JOIN FETCH v.user WHERE v.id = ?1")
    Optional<Vote> findById(Integer integer);
}
