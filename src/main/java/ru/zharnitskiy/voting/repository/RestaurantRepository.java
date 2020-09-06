package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zharnitskiy.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.date =?1 ORDER BY r.description ASC ")
    List<Restaurant> findAllWithDishesByDate(LocalDate date);
}
