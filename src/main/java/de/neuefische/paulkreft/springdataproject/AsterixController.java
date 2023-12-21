package de.neuefische.paulkreft.springdataproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AsterixController {

    private final CharacterRepository characterRepository;

    @GetMapping("/asterix/characters")
    public List<AsterixCharacter> getCharacters() {
        return characterRepository.findAll();
    }

    @PostMapping("/asterix/characters")
    public void postCharacters(@RequestBody AsterixCharacter asterixCharacter){
        characterRepository.save(asterixCharacter);
        System.out.println("character is added");
    }

    @PutMapping("/asterix/characters")
    public void putCharacters(@RequestBody AsterixCharacter asterixCharacter){

        Optional<AsterixCharacter> byId = characterRepository.findById(asterixCharacter.id());
        if (byId.isPresent()){
            characterRepository.deleteById(asterixCharacter.id());
            characterRepository.save(asterixCharacter);
            System.out.println("character is edited");
        }
    }

    @DeleteMapping("/asterix/characters/{id}")
    public void deleteCharacters(@PathVariable String id){
        Optional<AsterixCharacter> byId = characterRepository.findById(id);
        if (byId.isPresent()){
            characterRepository.deleteById(id);
            System.out.println("character is deleted");
        }
    }

    @GetMapping("/asterix/characters/")
    public List<AsterixCharacter> getCharacters(@RequestParam int age){
        List<AsterixCharacter> all = characterRepository.findAll();
        return all.stream().filter(asterixCharacter -> asterixCharacter.age() == age).toList();
    }

}
