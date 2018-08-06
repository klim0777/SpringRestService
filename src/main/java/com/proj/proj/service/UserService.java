package com.proj.proj.service;

import com.proj.proj.data.User;
import com.proj.proj.data.UserDTO;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserService {
    private UserDTO userDTO = UserDTO.getInstance();

    private String encodePass(String password) {
        byte[] pass = password.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] digest = md.digest(pass);
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public User addNewUser(String email, String name, String secondName, Date birthday, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setSecondName(secondName);
        user.setBirthday(birthday);
        user.setPassword(encodePass(password));

        userDTO.newUser(user);
        return user;
    }

    public User findUser(String email) {
        return userDTO.getUser(email);
    }

    public boolean removeUser(String email) {
        return userDTO.removeUser(email);
    }
}
