package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findAllByRestaurantIdAndDate(int restaurantId, LocalDate date);
}
