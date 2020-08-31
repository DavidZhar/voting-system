package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findAllByRestaurantAndDate(int restaurantId, LocalDate date);
}
