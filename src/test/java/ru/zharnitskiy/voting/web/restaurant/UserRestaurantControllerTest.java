package ru.zharnitskiy.voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.RESTAURANT_1;
import static ru.zharnitskiy.voting.TestData.USER;
import static ru.zharnitskiy.voting.TestMatcher.RESTAURANT_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.*;

class UserRestaurantControllerTest extends AbstractControllerTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void getRestaurant() throws Exception {
        mockMvc.perform(get("/rest/restaurants/100002")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> RESTAURANT_MATCHER.assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT_1));
    }

    @Test
    void getAllForDateWithDishes() throws Exception {
        mockMvc.perform(get("/rest/restaurants?date=2020-01-01")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> RESTAURANT_MATCHER.assertMatch(readListFromJsonMvcResult(result, Restaurant.class), restaurantRepository.findAllWithDishesByDate(LocalDate.of(2020, 1, 1))));
    }
}