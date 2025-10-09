package com.foo.mod1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest3 {

    @Mock
    private UserService service;

    @InjectMocks
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
