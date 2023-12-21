package de.neuefische.paulkreft.springdataproject;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends MongoRepository<AsterixCharacter, String> {

       List<Character>findAllByAge(int age);
}
