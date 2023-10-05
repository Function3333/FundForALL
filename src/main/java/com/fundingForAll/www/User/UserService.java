package com.fundingForAll.www.User;

import com.fundingForAll.www.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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

    // Duplicate check
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

    // Authentication(userId_uuid 이런 형태로 세션에 저장하고 나중에 30초 뒤에 삭제하는 형태로 만들자)
    public String sendMail(String receiverEmail) throws IOException {
        String uuid = UUID.randomUUID().toString();

        EmailUtil emailUtil = new EmailUtil();
        emailUtil.sendEmail(receiverEmail, uuid);

        return uuid;
    }
}
