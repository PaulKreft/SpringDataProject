package de.neuefische.paulkreft.springdataproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsterixController {
    private final CharacterRepository characterRepository;

    @GetMapping("/asterix/characters")
    public CharacterRepository getCharacters() {
        return characterRepository;

    }
}
