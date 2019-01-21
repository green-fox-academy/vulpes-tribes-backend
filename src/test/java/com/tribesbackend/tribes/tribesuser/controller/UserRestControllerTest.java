package com.tribesbackend.tribes.tribesuser.controller;

<<<<<<< HEAD
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tribesbackend.tribes.configurations.security.SecurityConstants.EXPIRATION_TIME;
=======
>>>>>>> 5b3ce4768ebc230a56566c3192b01711b2000a11
import static org.mockito.ArgumentMatchers.refEq;

import com.fasterxml.jackson.databind.ObjectMapper;
<<<<<<< HEAD
import com.tribesbackend.tribes.configurations.security.SecurityConstants;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorResponseModel;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.repositories.UserTRepository;
=======
import com.tribesbackend.tribes.tribesuser.service.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.service.errorservice.ErrorResponseModel;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
>>>>>>> 5b3ce4768ebc230a56566c3192b01711b2000a11
import com.tribesbackend.tribes.tribesuser.service.UserCrudService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestControllerTest {

    @Mock
    private UserCrudService userCrudService;
    @Mock
    private UserModelHelpersMethods userModelHelpersMethods;
    @Mock
    private ErrorMessagesMethods errorMessagesMethods;
    @Mock
    private UserTRepository userTRepository;
    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        Mockito.when(userModelHelpersMethods.usernameAlreadyTaken(newUser)).thenReturn(false);
        Mockito.doNothing().when(userCrudService).save(newUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("adamgyulavari")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("12345678ab")));
        Mockito.verify(userModelHelpersMethods).usernameAlreadyTaken(refEq(newUser));
    }

    @Test
    public void testRegisterTakenUsername() throws Exception {
        String json = "{\n" +
                "  \"username\": \"adamgyulavari\",\n" +
                "  \"password\": \"12345678ab\"\n" +
                "}";
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        Mockito.when(userModelHelpersMethods.usernameAlreadyTaken(refEq(newUser))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Username already taken, please choose another one.")));
        Mockito.verify(userModelHelpersMethods).usernameAlreadyTaken(refEq(newUser));
    }

    @Test
    public void testRegisterEmptyUsername() throws Exception {
        String json = "{\n" +
                "  \"username\": \"\",\n" +
                "  \"password\": \"12345678ab\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testRegisterShortPassword() throws Exception {
        String json = "{\n" +
                "  \"username\": \"adamgyulavari\",\n" +
                "  \"password\": \"1234ab\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testLoginNotSuchUser() throws Exception {
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        Optional<TribesUser> foundUser = Optional.ofNullable(null);
        Mockito.when(userTRepository.findTribesUserByUsername(newUser.getUsername())).thenReturn(foundUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Not such user: adamgyulavari")))
                .andDo(MockMvcResultHandlers.print());
        Mockito.verify(userTRepository).findTribesUserByUsername(newUser.getUsername());
    }

    @Test
    public void testLoginSuccessful() throws Exception {
        TribesUser newTribesUser = new TribesUser("adamgyulavari", "12345678ab");
        Optional<TribesUser> newUser = Optional.of(newTribesUser);
        Mockito.when(userTRepository.findTribesUserByUsername(newTribesUser.getUsername())).thenReturn(newUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(newTribesUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("ok")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token", Matchers.is("token")));
        Mockito.verify(userTRepository, Mockito.atLeast(2)).findTribesUserByUsername(newTribesUser.getUsername());
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        TribesUser wrongPasswordUser = new TribesUser("adamgyulavari", "12345678abc");
        Optional<TribesUser> wrongPassword = Optional.of(wrongPasswordUser);
        Mockito.when(userTRepository.findTribesUserByUsername(newUser.getUsername())).thenReturn(wrongPassword);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(newUser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Wrong password!")));
        Mockito.verify(userTRepository, Mockito.atLeast(3)).findTribesUserByUsername(newUser.getUsername());
    }

    @Test
    public void testLoginEmptyUsername() throws Exception {
        String json = "{\n" +
                "  \"username\": \"\",\n" +
                "  \"password\": \"123456ab\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Missing parameter(s): username")));
    }

    public static String asJsonString(final TribesUser user) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(user);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}