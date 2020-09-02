package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.DishRepository;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.service.DishService;
import ru.zharnitskiy.voting.to.DishTO;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DishController {
    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    private DishService dishService;

    @PutMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void update(@RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.save(dish);
    }

    @PostMapping("/restaurants/{restaurantId}/dishes")
    public void create(@RequestBody Dish dish, @PathVariable int restaurantId) {
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new));
        System.out.println(dish.toString());
        dishRepository.save(dish);
    }

    @DeleteMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void delete(@PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.deleteById(dishId);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int dishId) {
        return dishRepository.findById(dishId).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes")
    public List<DishTO> getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        return dishService.getAllByRestaurantAndDate(restaurant, date);
    }
}