package de.neuefische.paulkreft.springdataproject;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asterix/characters")
@RequiredArgsConstructor
public class AsterixController {
    private final AsterixCharacterService asterixCharacterService;
    @GetMapping
    public List<AsterixCharacterResponse> getCharacters() {
        return asterixCharacterService.getAllCharacters();

    }

    @PostMapping
    public AsterixCharacter postCharacters(@RequestBody AsterixCharacterRequest asterixCharacterRequest){
        AsterixCharacter character = asterixCharacterService.createCharacter(asterixCharacterRequest);
        return character;
    }

//    @PutMapping
//    public void putCharacters(@RequestBody AsterixCharacter asterixCharacter){
//
//        Optional<AsterixCharacter> byId = asterixCharacterService.findById(asterixCharacter.id());
//        if (byId.isPresent()){
//            asterixCharacterService.deleteById(asterixCharacter.id());
//            asterixCharacterService.save(asterixCharacter);
//            System.out.println("character is edited");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCharacters(@PathVariable String id){
//        Optional<AsterixCharacter> byId = asterixCharacterService.findById(id);
//        if (byId.isPresent()){
//            asterixCharacterService.deleteById(id);
//            System.out.println("character is deleted");
//        }
//    }
//
//    @GetMapping
//    public List<AsterixCharacter> getCharacters(@RequestParam int age){
//
//        List<AsterixCharacter> allByAge = asterixCharacterService.findAllByAge(age);
//        return allByAge;
//        //List<AsterixCharacter> all = characterRepository.findAll();
//        // return all.stream().filter(asterixCharacter -> asterixCharacter.age() == age).toList();
//
//    }

}
