package com.tribesbackend.tribes.tribesuser.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tribesbackend.tribes.security.SecurityConstants.EXPIRATION_TIME;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.refEq;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribesbackend.tribes.security.SecurityConstants;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorMessagesMethods;
import com.tribesbackend.tribes.tribesuser.errorservice.ErrorResponseModel;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.model.UserModelHelpersMethods;
import com.tribesbackend.tribes.tribesuser.okstatusservice.JWTToken;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import com.tribesbackend.tribes.tribesuser.service.UserCrudService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

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
//        String json = "{\n" +
//                "  \"username\": \"adamgyulavari\",\n" +
//                "  \"password\": \"12345678ab\"\n" +
//                "}";
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        Mockito.when(userModelHelpersMethods.usernameAlreadyTaken(newUser)).thenReturn(false);
        Mockito.doNothing().when(userCrudService).save(newUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("adamgyulavari")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("12345678ab")));
//        Mockito.verify(userCrudService).save(refEq(newUser));
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
        ErrorResponseModel toReturn = new ErrorResponseModel();
        toReturn.setStatus("error");
        toReturn.setErrorMessage("Username already taken, please choose another one.");
        Mockito.when(errorMessagesMethods.usernameAlreadyTaken()).thenReturn(toReturn);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        Mockito.verify(errorMessagesMethods).usernameAlreadyTaken();
        Mockito.verify(userModelHelpersMethods).usernameAlreadyTaken(refEq(newUser));
    }

    @Test
    public void testRegisterEmptyUsername() throws Exception {
        String json = "{\n" +
                "  \"username\": \"\",\n" +
                "  \"password\": \"12345678ab\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testLoginNotSuchUser() throws Exception{
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        Mockito.when(userTRepository.findTribesUserByUsername(newUser.getUsername())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(newUser))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errortype", Matchers.is("error")));
        Mockito.verify(userTRepository).findTribesUserByUsername(newUser.getUsername());
    }

    @Test
    public void testLoginSuccessful() throws Exception {
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        String token = JWT.create()
                .withSubject(newUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));

        Mockito.when(userTRepository.findTribesUserByUsername(newUser.getUsername())).thenReturn(newUser);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(newUser)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("ok")));
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.token", Matchers.is(token)));
        Mockito.verify(userTRepository, Mockito.atLeast(2)).findTribesUserByUsername(newUser.getUsername());
    }
    

    @Test
    public void testLoginWrongPassword() throws Exception{
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        TribesUser wrongPassword = new TribesUser("adamgyulavari", "12345678abc");
        Mockito.when(userTRepository.findTribesUserByUsername(newUser.getUsername())).thenReturn(wrongPassword);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newUser))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errortype", Matchers.is("error")));
        Mockito.verify(userTRepository, Mockito.atLeast(3)).findTribesUserByUsername(newUser.getUsername());
    }

    @Test
    public void testLoginEmptyUsername() throws Exception{
        String json = "{\n" +
                "  \"username\": \"\",\n" +
                "  \"password\": \"123456ab\"\n" +
                "}";
        TribesUser newUser = new TribesUser("", "123456ab");
        Mockito.when(errorMessagesMethods.jsonFieldIsEmpty(refEq(newUser))).thenReturn(new ErrorResponseModel("error", "Missing parameter(s): username"));
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage", Matchers.is("Missing parameter(s): username")));
        Mockito.verify(errorMessagesMethods).jsonFieldIsEmpty(refEq(newUser));
    }

    @Test
    public void testLoginEmptyPassword() throws Exception{
        String json = "{\n" +
                "  \"username\": \"adamgyulavari\",\n" +
                "  \"password\": \"\"\n" +
                "}";
        TribesUser newUser = new TribesUser("adamgyulavari", "");
        Mockito.when(errorMessagesMethods.jsonFieldIsEmpty(refEq(newUser))).thenReturn(new ErrorResponseModel("error", "Missing parameter(s): password"));
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("error")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage", Matchers.is("Missing parameter(s): password")));
        Mockito.verify(errorMessagesMethods).jsonFieldIsEmpty(refEq(newUser));
    }

    @Test
    public void testLoginNullUsername() throws Exception{
        String json = "{\n" +
                "  \"username\": null,\n" +
                "  \"password\": \"123456ab\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSuccessfulLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/logout")
                .header("token", "blablabla"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Logged out successfully!")));
    }

    @Test
    public void testLogoutEmptyHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/logout")
                .header("token", ""))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Unauthorized request!")));
    }

    @Test
    public void testLogoutNullHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/logout"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Unauthorized request!")));
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
