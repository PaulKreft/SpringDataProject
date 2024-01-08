package de.neuefische.paulkreft.springdataproject;

import de.neuefische.paulkreft.springdataproject.utils.IdService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AsterixCharacterServiceTest {

    @Test
    void createCharacterTest_whenAddAsterixCharacterRequest_thenAsterixCharacter() {
        // Given
        String id = "12345";

        IdService idService = Mockito.mock(IdService.class);
        Mockito.when(idService.randomId()).thenReturn(id);

        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);
        Mockito.when(asterixCharacterRepository.save(new AsterixCharacter(id, "Ichkannix", 28, "Programmer")))
                .thenReturn(new AsterixCharacter(id, "Ichkannix", 28, "Programmer"));


        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository, idService);

        // When
        AsterixCharacter returnedCharacter = asterixCharacterService.createCharacter(new AsterixCharacterRequest("Ichkannix", 28, "Programmer"));

        // Then
        assertEquals(new AsterixCharacter(id, "Ichkannix", 28, "Programmer"), returnedCharacter);

        Mockito.verify(idService, Mockito.times(1)).randomId();
        Mockito.verify(asterixCharacterRepository, Mockito.times(1)).save(Mockito.any());

        Mockito.verifyNoMoreInteractions(idService, asterixCharacterRepository);
    }

    @Test
    void updateCharacterByIdTest_whenUpdateAge_thenAgeUpdated() {
        // Given
        String id = "1";
        String name = "Obelix";
        String profession = "strong";
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);

        Mockito.when(asterixCharacterRepository.save(Mockito.any())).thenReturn(
                new AsterixCharacter(id, "Obelix", 36, "strong")
        );

        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository, new IdService());

        // When
        AsterixCharacter newObelix = asterixCharacterService.updateCharacterById(id, new AsterixCharacterRequest("Obelix", 36, "strong"));

        // Then
        assertEquals(new AsterixCharacter(id, "Obelix", 36, "strong"), newObelix);

        Mockito.verify(asterixCharacterRepository, Mockito.times(1)).save(Mockito.any());

        Mockito.verifyNoMoreInteractions(asterixCharacterRepository);
    }

    @Test
    void deleteCharacterByIdTest_whenDelete1_thenDeletedCharacter() {
        // Given
        String id = "1";
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);

        Mockito.when(asterixCharacterRepository.findById(id)).thenReturn(
                Optional.of(new AsterixCharacter(id, "Obelix", 36, "strong"))
        );

        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository, new IdService());

        // When
        AsterixCharacter deletedCharacter = asterixCharacterService.deleteCharacterById(id);

        // Then
        assertEquals(new AsterixCharacter(id, "Obelix", 36, "strong"), deletedCharacter);

        Mockito.verify(asterixCharacterRepository, Mockito.times(1)).findById(id);
        Mockito.verify(asterixCharacterRepository, Mockito.times(1)).deleteById(id);

        Mockito.verifyNoMoreInteractions(asterixCharacterRepository);
    }

    @Test
    void findCharacterById() {
        String id = "1";
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);

        Mockito.when(asterixCharacterRepository.findById(id)).thenReturn(
                Optional.of(new AsterixCharacter(id, "John", 23, "door"))
        );

        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository, new IdService());

        AsterixCharacterResponse asterixCharacterResponse = asterixCharacterService.getCharacterById(id);

        assertEquals(new AsterixCharacterResponse("John", 23, "door"), asterixCharacterResponse);

        Mockito.verify(asterixCharacterRepository, Mockito.times(1)).findById(id);

        Mockito.verifyNoMoreInteractions(asterixCharacterRepository);
    }

    @Test
    public void findAllCharacters() {
        // Given
        AsterixCharacterRepository asterixCharacterRepository = Mockito.mock(AsterixCharacterRepository.class);

        Mockito.when(asterixCharacterRepository.findAll()).thenReturn(List.of(
                new AsterixCharacter("1", "Asterix", 4, "-"),
                new AsterixCharacter("2", "Obelix", 30, "-")
        ));

        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(asterixCharacterRepository, new IdService());

        // When
        List<AsterixCharacterResponse> asterixCharacterList = asterixCharacterService.getAllCharacters();

        // Then
        assertEquals(List.of(
                new AsterixCharacterResponse("Asterix", 4, "-"),
                new AsterixCharacterResponse("Obelix", 30, "-")
        ), asterixCharacterList);

        Mockito.verify(asterixCharacterRepository, Mockito.times(1)).findAll();

        Mockito.verifyNoMoreInteractions(asterixCharacterRepository);
    }
}



