package ru.zharnitskiy.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.repository.DishRepository;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.util.ValidationUtil;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @CacheEvict(value = "restaurants", allEntries = true)
    public Dish create(Dish dish, int restaurantId) {
        ValidationUtil.checkNew(dish);
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(new NotFoundException("No such entity with id " + restaurantId)));
        return dishRepository.save(dish);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Dish dish, int restaurantId, int dishId) {
        ValidationUtil.assureIdConsistent(dish, dishId);
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(new NotFoundException("No such entity with id " + restaurantId)));
        dishRepository.save(dish);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int restaurantId, int dishId) {
        dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
    }
}
