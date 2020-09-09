package ru.zharnitskiy.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(new NotFoundException("Restaurant is not found"));
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllForDateWithDishes(LocalDate date) {
        return (date == null) ? restaurantRepository.findAll() : restaurantRepository.findAllWithDishesByDate(date);
    }
}
