package de.neuefische.paulkreft.springdataproject;

import de.neuefische.paulkreft.springdataproject.utils.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsterixCharacterService {
    private final AsterixCharacterRepository asterixCharacterRepository;
    private final IdService idService;

    public List<AsterixCharacterResponse> getAllCharacters() {
        List<AsterixCharacter> asterixCharacterList = asterixCharacterRepository.findAll();

        List<AsterixCharacterResponse> asterixCharacterResponseList = asterixCharacterList.stream()
                .map(character -> new AsterixCharacterResponse(character.name(), character.age(), character.profession()))
                .toList();

        return asterixCharacterResponseList;
    }

    public AsterixCharacter createCharacter(AsterixCharacterRequest character) {
        AsterixCharacter asterixCharacter = new AsterixCharacter(idService.randomId(), character.name(), character.age(), character.profession());

        return asterixCharacterRepository.save(asterixCharacter);
    }

    public AsterixCharacterResponse getCharacterById(String id) {
        Optional<AsterixCharacter> asterixCharacter = asterixCharacterRepository.findById(id);

        if (asterixCharacter.isPresent()) {
            return new AsterixCharacterResponse(asterixCharacter.get().name(), asterixCharacter.get().age(), asterixCharacter.get().profession());
        }

        throw new NoSuchElementException("No Character with id: " + id + " found");
    }

    public AsterixCharacter deleteCharacterById(String id) {
        Optional<AsterixCharacter> asterixCharacter = asterixCharacterRepository.findById(id);

        if (asterixCharacter.isPresent()) {
            asterixCharacterRepository.deleteById(id);
            return asterixCharacter.get();
        }

        throw new NoSuchElementException("No Character with id: " + id + " found");
    }

    public AsterixCharacter updateCharacterById(String id, AsterixCharacterRequest character) {
        AsterixCharacter asterixCharacter = new AsterixCharacter(id, character.name(), character.age(), character.profession());
        return asterixCharacterRepository.save(asterixCharacter);
    }
}
