package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.repository.DishRepository;
import ru.zharnitskiy.voting.repository.RestaurantRepository;

import java.time.LocalDate;

@RestController
public class DishController {
    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PutMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void update(@RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.save(dish);
    }

    @PostMapping("/restaurants/{restaurantId}/dishes")
    public void create(@RequestBody Dish dish, @PathVariable int restaurantId) {
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new));
        dishRepository.save(dish);
    }

    @DeleteMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void delete(@PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.deleteById(dishId);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void get(@PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.findById(dishId);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes")
    public void getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam LocalDate date) {
        dishRepository.findAllByRestaurantAndDate(restaurantId, date);
    }
}
