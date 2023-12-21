package de.neuefische.paulkreft.springdataproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AsterixController {

    private final CharacterRepository characterRepository;

    @GetMapping("/asterix/characters")
    public List<AsterixCharacter> getCharacters() {
        return characterRepository.findAll();
    }


}
