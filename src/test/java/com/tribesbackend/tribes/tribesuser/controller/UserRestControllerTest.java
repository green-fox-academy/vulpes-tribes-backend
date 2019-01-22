package com.tribesbackend.tribes.tribesuser.controller;

import static org.mockito.ArgumentMatchers.refEq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribesbackend.tribes.controllers.usercontrollers.UserRestController;
import com.tribesbackend.tribes.factories.KingdomFactory;
import com.tribesbackend.tribes.models.Kingdom;
import com.tribesbackend.tribes.models.TribesUser;
import com.tribesbackend.tribes.repositories.KingdomRepository;
import com.tribesbackend.tribes.services.userservice.UserModelHelpersMethods;
import com.tribesbackend.tribes.repositories.UserTRepository;
import com.tribesbackend.tribes.services.userservice.UserCrudService;
import com.tribesbackend.tribes.services.responseservice.ErrorMessagesMethods;
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
    @Mock
    private KingdomRepository kingdomRepository;
    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab", new Kingdom("mightykingdom"));
        Mockito.when(userModelHelpersMethods.usernameAlreadyTaken(newUser)).thenReturn(false);
        Mockito.doNothing().when(userCrudService).save(newUser);
        Mockito.doNothing().when(kingdomRepository);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(newUser)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("adamgyulavari")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avatar", Matchers.is("No avatar yet")));
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
                "   \"kingdomname\": \"mightykingdom\"\n"+
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
                "   \"kingdomname\": \"mightykingdom\"\n"+
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testLoginNotSuchUser() throws Exception {
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab", KingdomFactory.createValidSampleKingdom());
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
