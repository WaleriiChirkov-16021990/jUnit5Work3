package ru.chirkov.tdd;

import java.util.Objects;

public class User {

    private String name;
    private String password;
    private Boolean isAdmin;
    boolean isAuthenticate = false;

    public User(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    //3.6.
    public boolean authenticate(String name, String password) {
        if (name != null && password != null && !password.isEmpty() && !name.isEmpty()) {
            if (!isAuthenticate) {
                if (name.equals(this.name) && password.equals(this.password)) {
                    isAuthenticate = true;
                    return true;
                }
            } else {
                return true;
            }
        } else {
            throw new IllegalArgumentException ("method getting null or empty string");
        }
        return false;
    }

    public boolean isAuthenticate() {
        return isAuthenticate;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void logOut() {
        if (isAuthenticate()) {
            this.isAuthenticate = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(isAdmin, user.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword(), isAdmin);
    }
}