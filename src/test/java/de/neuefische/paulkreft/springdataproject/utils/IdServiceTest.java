package de.neuefische.paulkreft.springdataproject.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IdServiceTest {
    @Test
    void randomIdTest_whenCall_thenUUID() {
        // Given
        UUID expectedUuid = UUID.fromString("57d3c4ac-09a1-4adb-bcec-818da6531fbb");

        try (MockedStatic<UUID> uuidMock = Mockito.mockStatic(UUID.class)) {
            uuidMock.when(UUID::randomUUID).thenReturn(expectedUuid);

            // When
            String actual = new IdService().randomId();

            // Then
            assertEquals(expectedUuid.toString(), actual);
        }
    }
}