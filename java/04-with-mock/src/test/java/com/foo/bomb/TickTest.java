package com.foo.bomb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TickTest {

    @Test
    public void testTickCallsFireWhenTimeIsUp() {
        // Mock the Bomb constructor
        try (MockedConstruction<Bomb> mocked = mockConstruction(Bomb.class, (mock, context) -> {
            // No setup needed for fire()
        })) {
            Tick tick = new Tick(1); // sum = 1, so first tick will fire
            boolean result = tick.tick();
            assertFalse(result);

            // Verify that fire was called on the mock
            Bomb mockBomb = mocked.constructed().get(0);
            verify(mockBomb, times(1)).fire();
        }
    }

    @Test
    public void testTickDoesNotCallFireWhenTimeRemains() {
        try (MockedConstruction<Bomb> mocked = mockConstruction(Bomb.class)) {
            Tick tick = new Tick(2); // sum = 2

            // First tick should return true and not call fire
            boolean result1 = tick.tick();
            assertTrue(result1);

            // Second tick should return false and call fire
            boolean result2 = tick.tick();
            assertFalse(result2);

            // Verify that fire was called once
            Bomb mockBomb = mocked.constructed().get(0);
            verify(mockBomb, times(1)).fire();
        }
    }
}
