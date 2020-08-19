package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
