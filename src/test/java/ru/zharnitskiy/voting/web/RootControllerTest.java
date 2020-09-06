package ru.zharnitskiy.voting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.zharnitskiy.voting.TestData;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.TestData.*;
import static ru.zharnitskiy.voting.TestMatcher.USER_MATCHER;
import static ru.zharnitskiy.voting.TestUtil.readFromJson;
import static ru.zharnitskiy.voting.web.json.JacksonObjectMapper.getMapper;
import static ru.zharnitskiy.voting.web.json.JsonUtil.writeValue;

class RootControllerTest extends AbstractControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void create() throws Exception {
        ResultActions action = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(MOCK_NEW_USER)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = readFromJson(action, User.class);
        int newId = created.getId();
        NEW_USER.setId(newId);
        USER_MATCHER.assertMatch(created, NEW_USER);
        USER_MATCHER.assertMatch(userRepository.findById(newId).orElse(null), NEW_USER);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createWithExistingEmail() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(MOCK_EXISTING_USER)))
                .andDo(print())
                .andExpect(status().isConflict());

        assertEquals(userRepository.getByEmail("user@mail.ru").getPassword(), "{noop}" + TestData.USER.getPassword());
    }
}