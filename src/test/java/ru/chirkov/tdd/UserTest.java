package ru.chirkov.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User userAdmin;
    User userNotAdmin;

    @BeforeEach
    void setUp() {
        userNotAdmin = new User("Win", "true", false);
        userAdmin = new User("Bob", "marley", true);
    }

    @AfterEach
    void tearDown() {
        System.out.println("User test is over");
    }

    @Test
    void authenticate() {
        assertFalse(userAdmin.isAuthenticate);
        assertFalse(userAdmin.authenticate("Bob", "Marley"));
        assertFalse(userAdmin.isAuthenticate);   // проверяем что неправильные данные не приводят к успешной аутентификации
        assertFalse(userAdmin.authenticate("Bob", "true"));
        assertFalse(userAdmin.isAuthenticate);
        assertThrows(IllegalArgumentException.class, () -> assertFalse(userAdmin.authenticate("Bob", "")), "method getting empty string");
        assertFalse(userAdmin.isAuthenticate);
        assertThrows(IllegalArgumentException.class, () -> assertFalse(userAdmin.authenticate("", "marley")), "method getting empty string");
        assertFalse(userAdmin.isAuthenticate);
        assertThrows(IllegalArgumentException.class, () -> assertFalse(userAdmin.authenticate("Bob", null)), "method getting null");
        assertFalse(userAdmin.isAuthenticate);
        assertThrows(IllegalArgumentException.class, () -> assertFalse(userAdmin.authenticate(null, "marley")), "method getting null");
        assertFalse(userAdmin.isAuthenticate);
        assertTrue(userAdmin.authenticate("Bob", "marley"));
        assertTrue(userAdmin.isAuthenticate);
        assertTrue(userAdmin.authenticate("Bob", "marley")); // для авторизованного пользователя повторная авторизации вернёт true
        assertTrue(userAdmin.isAuthenticate);
        // check user is authenticated and perform authentication once again after successful checking user is authenticated.Authentication is not lost.

    }


    @Test
    void isAuthenticate() {
        assertFalse(userAdmin.isAuthenticate());
        userAdmin.authenticate("Bob", "marley");
        assertTrue(userAdmin.isAuthenticate());
    }

    @Test
    void isAdmin() {
        assertTrue(userAdmin.isAdmin());
        assertFalse(userNotAdmin.isAdmin());
        assertTrue(!userNotAdmin.isAdmin());
    }

    @Test
    void getName() {
        assertNotEquals("Bobs", userAdmin.getName());
        assertEquals("Bob", userAdmin.getName());
    }

    @Test
    void getPassword() {
        assertNotEquals("Bob", userAdmin.getPassword());
        assertEquals("marley", userAdmin.getPassword());
    }

    @Test
    void hashCodeTest() {
        assertEquals(Objects.hash("Bob","marley",true), userAdmin.hashCode());
        assertEquals(Objects.hash("Win","true",false), userNotAdmin.hashCode());
    }

    @Test
    void equalsTest() {
        User userEquals = new User("Bob","marley",true);
        User userNotEquals = new User("Bo","marley",true);
        assertEquals(userAdmin, userEquals);
        assertNotEquals(userAdmin, userNotEquals);
    }

    @Test
    void logOutTest() {
        userAdmin.authenticate(userAdmin.getName(), userAdmin.getPassword());
        assertTrue(userAdmin.isAuthenticate());
        userAdmin.logOut();
        assertFalse(userAdmin.isAuthenticate());

    }


}