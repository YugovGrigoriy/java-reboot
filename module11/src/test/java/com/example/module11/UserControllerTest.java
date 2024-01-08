package com.example.module11;

import com.example.module11.controller.UserController;
import com.example.module11.entity.User;
import com.example.module11.service.UserDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserDB userDataBase;


    @BeforeEach
    public void setup() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Григорий");
        mockUser.setAge(23);

        when(userDataBase.findByIdUser(anyLong())).thenReturn(mockUser);
        Authentication authentication = new TestingAuthenticationToken("user", "password", "ADMIN");

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void createUserTest() throws Exception {
        mockMvc.perform(get("/main-user-page/createUser")
                        .param("_csrf", "csrfTokenValue")
                        .param("name", "Григорий")
                        .param("age", "23"))
                .andExpect(status().isOk());
    }

}
