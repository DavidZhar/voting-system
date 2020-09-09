package ru.zharnitskiy.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.repository.DishRepository;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.util.ValidationUtil;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;
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
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "restaurants", allEntries = true)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        ValidationUtil.checkNew(dish);
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(new NotFoundException("No such entity with id " + restaurantId)));
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/restaurants/{restaurantId}/dishes/{id}").buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int dishId) {
        return dishRepository.findById(dishId).orElseThrow(new NotFoundException("No such entity " + restaurantId));
    }

    @PutMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int dishId) {
        ValidationUtil.assureIdConsistent(dish, dishId);
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(new NotFoundException("No such entity with id " + restaurantId)));
        dishRepository.save(dish);
    }

    @DeleteMapping("/restaurants/{restaurantId}/dishes/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(@PathVariable int restaurantId, @PathVariable int dishId) {
        dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}/dishes")
    public List<Dish> getAllByRestaurantAndDate(@PathVariable int restaurantId, @RequestParam LocalDate date) {
        return dishRepository.findAllByRestaurantIdAndDate(restaurantId, date);
    }
}