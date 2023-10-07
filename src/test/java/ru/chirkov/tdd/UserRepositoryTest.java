package ru.chirkov.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    UserRepository userRepository;
    User userNotAdmin;
    User userAdmin;
    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userNotAdmin = new User("Win", "true", false);
        userAdmin = new User("Bob", "marley", true);
    }

    @AfterEach
    void tearDown() {
        System.out.println("UserRepository test is completed");
    }

    @Test
    void addUser() {
        this.userRepository.data = null;
        assertThrows(NullPointerException.class,() -> userRepository.addUser(userNotAdmin));
        this.userRepository = new UserRepository();
        assertTrue(userRepository.data.isEmpty());
        assertThrows(IllegalStateException.class,() -> userRepository.addUser(userNotAdmin));
        assertTrue(userRepository.data.isEmpty());
        userNotAdmin.authenticate("Win", "true");
        userRepository.addUser(userNotAdmin);
        assertFalse(userRepository.data.isEmpty());
        assertEquals(1, userRepository.data.size());
        assertEquals("Win", userRepository.data.get(0).getName());
        assertEquals("true", userRepository.data.get(0).getPassword());
        assertTrue(userRepository.data.get(0).isAuthenticate());
        assertThrows(IllegalStateException.class,() -> userRepository.addUser(userNotAdmin));

    }

    @Test
    void findByName() {
        userNotAdmin.authenticate("Win", "true");
        userAdmin.authenticate("Bob", "marley");
        userRepository.addUser(userAdmin);
        userRepository.addUser(userNotAdmin);
        assertFalse(userRepository.findByName("bob"));
        assertTrue(userRepository.findByName("Bob"));
        assertTrue(userRepository.findByName("Win"));
    }

    @Test
    void logOutUsers() {
        assertThrows(NullPointerException.class,() -> userRepository.logOutUsers());
        userNotAdmin.authenticate("Win", "true");
        assertFalse(userNotAdmin.isAdmin());
        userAdmin.authenticate("Bob", "marley");
        assertTrue(userAdmin.isAdmin());
        userRepository.addUser(userAdmin);
        userRepository.addUser(userNotAdmin);
        assertEquals(2,userRepository.data.size());
        userRepository.logOutUsers();
        assertEquals(1,userRepository.data.size());
        assertTrue(userRepository.data.get(0).isAdmin());
        this.userRepository = null;
        assertThrows(NullPointerException.class,() -> userRepository.logOutUsers());
    }
}