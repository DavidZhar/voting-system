package ru.zharnitskiy.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.DishRepository;
import ru.zharnitskiy.voting.repository.RestaurantRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/admin")
public class AdminDishController {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/restaurants/{restaurantId}/dishes")
    public void create(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new));
        dishRepository.save(dish);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int dishId) {
        return dishRepository.findById(dishId).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int dishId) {
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new));
        dishRepository.save(dish);
    }

    @DeleteMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public void delete(@PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.deleteById(dishId);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes")
    public List<Dish> getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam  LocalDate date) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        return dishRepository.findAllByRestaurantAndDate(restaurant, date);
    }
}