package ru.zharnitskiy.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        restaurantRepository.save(restaurant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        restaurantRepository.deleteById(id);
    }

    @PostMapping()
    public void create(@RequestBody Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @GetMapping()
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
}
