package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findAllByRestaurantAndDate(Restaurant restaurant, LocalDate date);
}
