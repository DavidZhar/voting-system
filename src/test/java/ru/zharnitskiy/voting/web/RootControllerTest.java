package ru.zharnitskiy.voting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.zharnitskiy.voting.model.Role;
import ru.zharnitskiy.voting.model.User;
import ru.zharnitskiy.voting.repository.UserRepository;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.zharnitskiy.voting.web.json.JacksonObjectMapper.getMapper;

class RootControllerTest extends AbstractControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void create() throws Exception {
        MockUser newMockUser = new MockUser("new@mail.ru", "newpass", EnumSet.of(Role.USER));
        User newUser = new User("new@mail.ru", "newpass", EnumSet.of(Role.USER));
        ResultActions action = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(newMockUser)))
                .andDo(print())
                .andExpect(status().isCreated());

//        User created = getMapper().readValue(action.andReturn().getResponse().getContentAsString(), User.class);
//        int newId = created.getId();
//        newUser.setId(newId);
//        assertEquals(created, newUser);
        assertEquals(userRepository.getByEmail("new@mail.ru"), newUser);
    }
}