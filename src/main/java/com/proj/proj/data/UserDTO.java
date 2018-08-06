package com.proj.proj.data;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {
    private Map<String, User> users = new HashMap<>();

    private static UserDTO ourInstance = new UserDTO();

    public static UserDTO getInstance() {
        return ourInstance;
    }

    private UserDTO() {}

    public void newUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User getUser(String email) {
        return users.get(email);
    }

    public boolean removeUser(String email) {
        User user = users.get(email);
        return users.remove(email, user);
    }
}
