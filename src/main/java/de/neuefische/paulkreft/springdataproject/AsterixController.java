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
    public AsterixCharacter postCharacters(@RequestBody AsterixCharacterRequest request){
        return asterixCharacterService.createCharacter(request);
    }

    @GetMapping("/{id}")
    public AsterixCharacterResponse getCharacterById(@PathVariable String id) {
        return asterixCharacterService.getCharacterById(id);
    }

    @DeleteMapping("/{id}")
    public AsterixCharacter deleteCharacterById(@PathVariable String id) {
        return asterixCharacterService.deleteCharacterById(id);
    }

     @PutMapping("/{id}")
    public AsterixCharacter updateCharacterById(@PathVariable String id, @RequestBody AsterixCharacterRequest request) {
      return asterixCharacterService.updateCharacterById(id, request);
     }
}
