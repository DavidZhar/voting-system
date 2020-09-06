package ru.zharnitskiy.voting.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;
import ru.zharnitskiy.voting.web.AbstractControllerTest;
import ru.zharnitskiy.voting.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.USER_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.*;

class AdminUserControllerTest extends AbstractControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/rest/admin/users/100000")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> USER_MATCHER.assertMatch(readFromJsonMvcResult(result, User.class), USER));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/rest/admin/users/100000")
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MOCK_USER_UPDATED)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.getByEmail("updated@mail.ru"), USER_UPDATED);
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/rest/admin/users/100000")
                .with(userHttpBasic(ADMIN)))
                .andDo(print());
        assertNull(userRepository.getByEmail("user@mail.ru"));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/rest/admin/users")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> USER_MATCHER.assertMatch(readListFromJsonMvcResult(result, User.class), USER, ADMIN));
    }

    @Test
    void getByEmail() throws Exception {
        mockMvc.perform(get("/rest/admin/users/byemail?email=user@mail.ru")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> USER_MATCHER.assertMatch(readFromJsonMvcResult(result, User.class), USER));
    }
}