package ru.chirkov.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    // Тут можно хранить аутентифицированных пользователей
    List<User> data = new ArrayList<>();

    public void addUser(User user) {
        if (data == null) {
            throw new NullPointerException("data is null");
        }
        if (data.contains(user)){
            throw new IllegalStateException("User already");
        }
        if (!user.isAuthenticate){
            throw new IllegalStateException("user is not authenticated");
        }
        data.add(user);
    }
    public boolean findByName(String username) {
        for (User user : data) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void logOutUsers() {
        if (data.isEmpty()) {
            throw new NullPointerException("data is empty");
        }
        data.forEach(user -> {
            if (!user.isAdmin()) {
                user.logOut();
            }
        });
        data = data.stream().filter(User::isAdmin).collect(Collectors.toUnmodifiableList());
    }

}