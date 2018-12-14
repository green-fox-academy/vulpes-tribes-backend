package com.tribes_backend.tribes.tribesUser.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.refEq;

import com.tribes_backend.tribes.tribesUser.errorService.ErrorMessagesMethods;
import com.tribes_backend.tribes.tribesUser.errorService.ErrorResponseModel;
import com.tribes_backend.tribes.tribesUser.model.TribesUser;
import com.tribes_backend.tribes.tribesUser.model.UserModelHelpersMethods;
import com.tribes_backend.tribes.tribesUser.controller.UserRestController;
import com.tribes_backend.tribes.tribesUser.service.UserCrudService;
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

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestControllerTest {


    @Mock
    private UserCrudService userCrudService;
    @Mock
    private UserModelHelpersMethods userModelHelpersMethods;
    @Mock
    private ErrorMessagesMethods errorMessagesMethods;
    @Mock
    private TribesUser mockedUser;
    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        String json = "{\n" +
                "  \"username\": \"adamgyulavari\",\n" +
                "  \"password\": \"12345678ab\"\n" +
                "}";
        TribesUser newUser = new TribesUser("adamgyulavari", "12345678ab");
        Mockito.when(userCrudService.save(newUser)).thenReturn(true);
        Mockito.when(userModelHelpersMethods.usernameAlreadyTaken(newUser)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("adamgyulavari")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("12345678ab")));
                Mockito.verify(userCrudService).save(refEq(newUser));
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
}
