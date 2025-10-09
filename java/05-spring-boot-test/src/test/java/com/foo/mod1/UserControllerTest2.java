package com.foo.mod1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest2 {

    @MockBean
    private UserService service;

    @Autowired
    private UserController controller;

    @Test
    public void testFindByUsername_ExistingUser() {
        // Arrange
        String username = "alex";
        Account expectedAccount = new Account(username);
        when(service.find(username)).thenReturn(expectedAccount);

        // Act
        Account result = controller.findByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(service, times(1)).find(username);
    }

    @Test
    public void testFindByUsername_NonExistingUser() {
        // Arrange
        String username = "nonexistent";
        when(service.find(username)).thenReturn(null);

        // Act
        Account result = controller.findByUsername(username);

        // Assert
        assertNull(result);
        verify(service, times(1)).find(username);
    }
}
