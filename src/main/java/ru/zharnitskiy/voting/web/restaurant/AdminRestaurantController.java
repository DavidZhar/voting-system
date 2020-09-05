package ru.zharnitskiy.voting.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.service.RestaurantService;
import ru.zharnitskiy.voting.util.ValidationUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/restaurants")
public class AdminRestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "restaurants", allEntries = true)
    public void create(@Valid @RequestBody Restaurant restaurant) {
        ValidationUtil.checkNew(restaurant);
        restaurantRepository.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return restaurantService.get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(@PathVariable int id) {
        restaurantRepository.deleteById(id);
    }

    @GetMapping
    @Cacheable("restaurants")
    public List<Restaurant> getAllForDateWithDishes(@RequestParam(required = false) LocalDate date) {
        return (date == null) ? restaurantRepository.findAll() : restaurantRepository.findAllWithDishesByDate(date);
    }
}
