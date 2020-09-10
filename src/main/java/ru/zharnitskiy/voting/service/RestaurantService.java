package ru.zharnitskiy.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.util.ValidationUtil;
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

    public List<Restaurant> getAll(LocalDate date) {
        return (date == null) ? getAllWithDishes() : getAllForDateWithDishes(date);
    }

    public List<Restaurant> getAllWithDishes() {
        return restaurantRepository.findAll();
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllForDateWithDishes(LocalDate date) {
        return restaurantRepository.findAllWithDishesByDate(date);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        ValidationUtil.checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant, int id) {
        ValidationUtil.assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }
}
