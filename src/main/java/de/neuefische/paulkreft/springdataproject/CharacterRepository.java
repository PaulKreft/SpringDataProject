package de.neuefische.paulkreft.springdataproject;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends MongoRepository<AsterixCharacter, String> {

       @Query()
       List<AsterixCharacter>findAllByAge(int age);
}
