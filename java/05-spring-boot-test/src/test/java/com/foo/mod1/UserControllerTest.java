package com.foo.mod1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController controller;

    @Test
    public void testFindByUsername_ExistingUser() {
        // Arrange
        String username = "alex";

        // Act
        Account result = controller.findByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

}
