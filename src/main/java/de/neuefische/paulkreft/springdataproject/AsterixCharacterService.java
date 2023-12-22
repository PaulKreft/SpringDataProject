package de.neuefische.paulkreft.springdataproject;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsterixCharacterService {
    private final AsterixCharacterRepository asterixCharacterRepository;

    public List<AsterixCharacterResponse> findAllCharacters() {
        List<AsterixCharacter> asterixCharacterList = asterixCharacterRepository.findAll();

        List<AsterixCharacterResponse> asterixCharacterResponseList = asterixCharacterList.stream()
                .map(character -> new AsterixCharacterResponse(character.name(), character.age(), character.profession()))
                .toList();

        return asterixCharacterResponseList;
    }

    public AsterixCharacter createCharacter(AsterixCharacterRequest character) {
        AsterixCharacter asterixCharacter = new AsterixCharacter(UUID.randomUUID().toString(), character.name(), character.age(), character.profession());

        return asterixCharacterRepository.save(asterixCharacter);
    }
}
