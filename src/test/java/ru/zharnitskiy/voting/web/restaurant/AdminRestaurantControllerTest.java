package ru.zharnitskiy.voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.zharnitskiy.voting.model.Restaurant;
import ru.zharnitskiy.voting.repository.RestaurantRepository;
import ru.zharnitskiy.voting.web.AbstractControllerTest;
import ru.zharnitskiy.voting.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.RESTAURANT_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.*;
import static ru.zharnitskiy.voting.web.json.JsonUtil.writeValue;

class AdminRestaurantControllerTest extends AbstractControllerTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void createRestaurant() throws Exception {
        ResultActions action = mockMvc.perform(post("/rest/admin/restaurants")
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(NEW_RESTAURANT)))
                .andDo(print())
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(action, Restaurant.class);
        NEW_RESTAURANT.setId(created.getId());
        RESTAURANT_MATCHER.assertMatch(created, NEW_RESTAURANT);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(created.getId()).orElse(null), NEW_RESTAURANT);
    }

    @Test
    void getRestaurant() throws Exception {
        mockMvc.perform(get("/rest/admin/restaurants/" + RESTAURANT_1.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> RESTAURANT_MATCHER.assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT_1));
    }

    @Test
    void updateRestaurant() throws Exception {
        mockMvc.perform(put("/rest/admin/restaurants/" + RESTAURANT_1.getId())
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_UPDATED)))
                .andDo(print())
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(RESTAURANT_1.getId()).orElse(null), RESTAURANT_UPDATED);
    }

    @Test
    void deleteRestaurant() throws Exception {
        mockMvc.perform(delete("/rest/admin/restaurants/" + RESTAURANT_1.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertNull(restaurantRepository.findById(RESTAURANT_1.getId()).orElse(null));
    }

    @Test
    void getAllForDateWithDishes() throws Exception {
        mockMvc.perform(get("/rest/admin/restaurants")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> RESTAURANT_MATCHER.assertMatch(readListFromJsonMvcResult(result, Restaurant.class), RESTAURANT_1, RESTAURANT_2));

        mockMvc.perform(get("/rest/admin/restaurants?date=2020-01-01")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> RESTAURANT_MATCHER.assertMatch(readListFromJsonMvcResult(result, Restaurant.class), restaurantRepository.findAllWithDishesByDate(DATE_1)));
    }
}