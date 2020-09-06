package ru.zharnitskiy.voting.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.web.AbstractControllerTest;
import ru.zharnitskiy.voting.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.USER_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.readFromJsonMvcResult;
import static ru.zharnitskiy.voting.TestUtil.userHttpBasic;

class UserProfileControllerTest extends AbstractControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void getProfile() throws Exception {
        mockMvc.perform(get("/rest/profile")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> USER_MATCHER.assertMatch(readFromJsonMvcResult(result, User.class), USER));
    }

    @Test
    void getWithUnauthorized() throws Exception {
        mockMvc.perform(get("/rest/profile"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateProfile() throws Exception {
        mockMvc.perform(put("/rest/profile")
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MOCK_USER_UPDATED)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.getByEmail("updated@mail.ru"), USER_UPDATED);
    }

    @Test
    void deleteProfile() throws Exception {
        mockMvc.perform(delete("/rest/profile")
                .with(userHttpBasic(USER)))
                .andDo(print());
        mockMvc.perform(get("/rest/profile")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}