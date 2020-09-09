package ru.zharnitskiy.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(UserRestaurantController.REST_URL)
public class UserRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantService.get(id);
    }

    @GetMapping
    public List<Restaurant> getAllForDateWithDishes(@RequestParam(required = false) LocalDate date) {
        return restaurantService.getAllForDateWithDishes(date);
    }
}
