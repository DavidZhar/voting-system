package ru.zharnitskiy.voting.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.repository.DishRepository;
import ru.zharnitskiy.voting.web.AbstractControllerTest;
import ru.zharnitskiy.voting.web.json.JsonUtil;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.DISH_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.*;
import static ru.zharnitskiy.voting.web.json.JsonUtil.writeValue;

class AdminDishControllerTest extends AbstractControllerTest {
    @Autowired
    private DishRepository dishRepository;

    @Test
    void createDish() throws Exception {
        ResultActions action = mockMvc.perform(post("/rest/admin/restaurants/" + RESTAURANT_1.getId() + "/dishes")
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(NEW_DISH)))
                .andDo(print())
                .andExpect(status().isCreated());

        Dish created = readFromJson(action, Dish.class);
        NEW_DISH.setId(created.getId());
        DISH_MATCHER.assertMatch(created, NEW_DISH);
        DISH_MATCHER.assertMatch(dishRepository.findById(created.getId()).orElse(null), NEW_DISH);
    }

    @Test
    void getDish() throws Exception {
        mockMvc.perform(get("/rest/admin/restaurants/" + RESTAURANT_1.getId() + "/dishes/" + DISH_1.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> DISH_MATCHER.assertMatch(readFromJsonMvcResult(result, Dish.class), DISH_1));
    }

    @Test
    void updateDish() throws Exception {
        mockMvc.perform(put("/rest/admin/restaurants/" + RESTAURANT_1.getId() + "/dishes/" + DISH_1.getId())
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(DISH_UPDATED)))
                .andDo(print())
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishRepository.findById(DISH_1.getId()).orElse(null), DISH_UPDATED);

    }

    @Test
    void deleteDish() throws Exception {
        mockMvc.perform(delete("/rest/admin/restaurants//" + RESTAURANT_1.getId() + "//dishes/" + DISH_1.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertNull(dishRepository.findById(DISH_1.getId()).orElse(null));
    }

    @Test
    void getAllByRestaurantAndDate() throws Exception {
        mockMvc.perform(get("/rest/admin/restaurants/" + RESTAURANT_1.getId() + "/dishes?date=2020-01-01")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> DISH_MATCHER.assertMatch(readListFromJsonMvcResult(result, Dish.class),
                        dishRepository.findAllByRestaurantIdAndDate(RESTAURANT_1.getId(), DATE_1)));
    }
}