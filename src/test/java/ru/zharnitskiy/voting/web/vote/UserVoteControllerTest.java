package ru.zharnitskiy.voting.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.zharnitskiy.voting.model.Vote;
import ru.zharnitskiy.voting.repository.VoteRepository;
import ru.zharnitskiy.voting.service.VoteService;
import ru.zharnitskiy.voting.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.VOTE_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.*;


class UserVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository voteRepository;

    @Test
    void getUserVoteForToday() throws Exception {
        create();

        mockMvc.perform(get("/rest/votes")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> VOTE_MATCHER.assertMatch(readFromJsonMvcResult(result, Vote.class), NEW_VOTE));
    }

    @Test
    void create() throws Exception {
        ResultActions action = mockMvc.perform(post("/rest/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"restaurant\":{\"id\":100002}}")
                .with(userHttpBasic(USER)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Vote created = readFromJson(action, Vote.class);
        NEW_VOTE.setId(created.getId());
        VOTE_MATCHER.assertMatch(created, NEW_VOTE);
    }

    @Test
    void update() throws Exception {
        create();

        if (LocalTime.now().isAfter(VoteService.VOTING_END_TIME)) {
            mockMvc.perform(put("/rest/votes/" + NEW_VOTE.getId())
                    .with(userHttpBasic(USER))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"restaurant\":{\"id\":100003}}"))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        } else {
            mockMvc.perform(put("/rest/votes/" + NEW_VOTE.getId())
                    .with(userHttpBasic(USER))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"restaurant\":{\"id\":100003}}"))
                    .andDo(print())
                    .andExpect(status().isNoContent());

            NEW_VOTE.setRestaurant(RESTAURANT_2);
            VOTE_MATCHER.assertMatch(voteRepository.findById(NEW_VOTE.getId()).orElse(null), NEW_VOTE);
        }
    }
}