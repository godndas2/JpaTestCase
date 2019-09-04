package com.boot.jpa;

import com.boot.jpa.model.User;
import com.boot.jpa.model.UserInfo;
import com.boot.jpa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpatestcaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
@Rollback
@Transactional
public class JpatestcaseApplicationTests {

    @Autowired  private WebApplicationContext webApplicationContext;
    @Autowired private UserRepository userRepository;
    @Autowired private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void test_user_not_exist() throws Exception {
        // parameter
        String email = "halfdev@gmail.com";

        // request
        mockMvc.perform(
                get("/user/login")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message").value("user is not exist !!"));
    }

    @Test
    public void test_user_exist() throws Exception {
        // parameter
        String email = "user@test.com";

        // insert user
        User user = new User();
        user.setType(User.USER_TYPE.USER);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setUser(user);

        user.setUserInfo(userInfo);

        userRepository.save(user);

        // request get
        mockMvc.perform(
                get("/user/login")
                        .param("email", email)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("user exist"));
    }
}
