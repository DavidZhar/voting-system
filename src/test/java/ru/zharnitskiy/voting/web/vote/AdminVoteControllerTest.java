package ru.zharnitskiy.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.zharnitskiy.voting.model.Dish;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.DISH_MATCHER;
import static ru.zharnitskiy.voting.TestMatcher.VOTE_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.*;

class AdminVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository voteRepository;

    @Test
    void getVote() throws Exception {
        mockMvc.perform(get("/rest/admin/votes/" + VOTE_1.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> VOTE_MATCHER.assertMatch(readFromJsonMvcResult(result, Vote.class), VOTE_1));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/rest/admin/votes")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> VOTE_MATCHER.assertMatch(readListFromJsonMvcResult(result, Vote.class), VOTE_1, VOTE_2, VOTE_3, VOTE_4));
    }

    @Test
    void getAllByRestaurantAndDate() throws Exception {
        mockMvc.perform(get("/rest/admin/votes/restaurant/" + RESTAURANT_1.getId() + "?date=2020-01-01")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> VOTE_MATCHER.assertMatch(readListFromJsonMvcResult(result, Vote.class),
                        voteRepository.findAllByRestaurantIdAndDate(RESTAURANT_1.getId(), DATE_1)));
    }
}