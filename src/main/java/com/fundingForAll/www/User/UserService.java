package com.fundingForAll.www.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // CRUD
    public String createUser(UserDto userDto) {
        User user = new User().createUser(userDto);
        String userId = userRepository.save(user);

        return userId;
    }

    public User getUser(String userId) {
        try {
            User findUser = userRepository.findById(userId).get();

            return findUser;
        } catch (NoSuchElementException e) {
            throw  e;
        }
    }

    public String updateUser(UserDto userDto) {
        try {
            User findUser = userRepository.findById(userDto.getId()).get();
            findUser.updateUser(userDto);

            String userId = userRepository.save(findUser);
            return userId;
        } catch (NoSuchElementException e) {
            throw  e;
        }
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Authentication
    public boolean isIdPresent(String id) {
        Optional<User> findById = userRepository.findById(id);

        boolean flag = (findById.isPresent()) ? true : false;
        return  flag;
    }

    public boolean isEmailPresent(String email) {
        Optional<User> findByEmail = userRepository.findByEmail(email);

        boolean flag = (findByEmail.isPresent()) ? true : false;
        return flag;
    }

    public boolean isAccountPresent(String account) {
        Optional<User> findByAccount = userRepository.findByAccount(account);

        boolean flag = (findByAccount.isPresent()) ? true : false;
        return flag;
    }
}
