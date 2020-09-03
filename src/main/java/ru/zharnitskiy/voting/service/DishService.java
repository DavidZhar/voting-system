//package ru.zharnitskiy.voting.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.zharnitskiy.voting.model.Restaurant;
//import ru.zharnitskiy.voting.repository.DishRepository;
//import ru.zharnitskiy.voting.to.DishTO;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class DishService {
//    @Autowired
//    private DishRepository dishRepository;
//
//    public List<DishTO> getAllByRestaurantAndDate(Restaurant restaurant, LocalDate date) {
//        return dishRepository.findAllByRestaurantAndDate(restaurant, date)
//                .stream().map(d -> new DishTO(d.getId(), d.getDescription(), d.getDate())).collect(Collectors.toList());
//    }
//}
