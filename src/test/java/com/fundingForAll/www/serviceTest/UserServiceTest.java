package com.fundingForAll.www.serviceTest;

import com.fundingForAll.www.User.User;
import com.fundingForAll.www.User.UserDto;
import com.fundingForAll.www.User.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    public UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId("userA");
        userDto.setPassword("password");
        userDto.setEmail("userA@email.com");
        userDto.setPhone("01012341234");
        userDto.setAccount("123456");

        return userDto;
    }

    @Test
    void getUser(){
        UserDto userDto = createUserDto();

        String userId = userService.createUser(userDto);
        User findUser = userService.getUser(userId);

        //success
        Assertions.assertThat(findUser.getId()).isEqualTo(userId);

        //fail
        try {
            User wrongUser = userService.getUser("fail");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(NoSuchElementException.class);
        }
    }

    @Test
    void updateUser() {
        UserDto userDto = createUserDto();

        userService.createUser(userDto);

        userDto.setEmail("changeEmail");
        String userId = userService.updateUser(userDto);

        User user = userService.getUser(userId);

        Assertions.assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
    }


}
