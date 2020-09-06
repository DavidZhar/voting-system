package ru.zharnitskiy.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.util.exception.NotFoundException;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(new NotFoundException("Restaurant is not found"));
    }
}
