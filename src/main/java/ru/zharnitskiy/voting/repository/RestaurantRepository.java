package ru.zharnitskiy.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zharnitskiy.voting.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
