package de.neuefische.paulkreft.springdataproject;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsterixCharacterRepository extends MongoRepository<AsterixCharacter, String> {

//       @Query()
//       List<AsterixCharacter>findAllByAge(int age);
}
