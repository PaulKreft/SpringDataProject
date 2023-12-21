package de.neuefische.paulkreft.springdataproject;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<AsterixCharacter, String> {
}
