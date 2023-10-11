package com.fundingForAll.www.serviceTest;


import com.fundingForAll.www.User.User;
import com.fundingForAll.www.User.UserForm;
import com.fundingForAll.www.User.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void update() {
        UserForm userForm = new UserForm();
        userForm.setUserId("user100");
        userForm.setEmail("test1");

        User user = userService.save(userForm);

        userForm.setEmail("test2");
        userService.updateUser(user.getId(), userForm);

        User findUser = userService.getUser(user.getId());

        Assertions.assertThat(findUser.getEmail()).isEqualTo("test2");
    }
}
