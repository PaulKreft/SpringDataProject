package de.neuefische.paulkreft.springdataproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AsterixCharacterRepository asterixCharacterRepository;

    @DirtiesContext
    @Test
    public void getCharactersTest_shouldReturnListWithOneObject_whenOneObjectWasSavedInRepository() throws Exception {
        AsterixCharacter asterixCharacter = new AsterixCharacter("1", "TestName", 10, "TestProfession");
        asterixCharacterRepository.save(asterixCharacter);

        MvcResult result = mockMvc.perform(get("/asterix/characters")).
                andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                {
                                "name":"TestName",
                                "age":10,
                                "profession":"TestProfession"
                                }
                                ]
                                """
                ))
                .andReturn();

        System.out.println(result.getResponse());
    }

    @Test
    @DirtiesContext
    public void postCharactersTest_shouldAddOneObject() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/asterix/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                
                                    {
                                    "name":"TestName",
                                    "age":10,
                                    "profession":"TestProfession"
                                    }
                                     
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                        
                                        {
                                        "name":"TestName",
                                        "age":10,
                                        "profession":"TestProfession"
                                        }
                                        
                        """))
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.age").isNotEmpty())
                .andExpect(jsonPath("$.profession").isNotEmpty())
                .andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @DirtiesContext
    @Test
    public void getCharacterByIdTest_shouldReturnListWithObjectWithSpecifiedId_whenOneSpecificObjectWasRequested() throws Exception {
        // GIVEN
        AsterixCharacter asterixCharacter = new AsterixCharacter("1", "TestName", 10, "TestProfession");
        asterixCharacterRepository.save(asterixCharacter);

        // WHEN
        mockMvc.perform(get("/asterix/characters/1"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                    {
                                    "name":"TestName",
                                    "age":10,
                                    "profession":"TestProfession"
                                    }
                                        """
                ));

    }

    @Test
    @DirtiesContext
    public void deleteCharacterTest_shouldDeleteObject_whenObjectExists() throws Exception {
        // GIVEN
        String objectId = "1";
        AsterixCharacter asterixCharacter = new AsterixCharacter(objectId, "TestName", 10, "TestProfession");
        asterixCharacterRepository.save(asterixCharacter);

        // WHEN
        mockMvc.perform(delete("/asterix/characters/{id}", objectId))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id": "1",
                        "name":"TestName",
                        "age":10,
                        "profession":"TestProfession"
                        }
                        """));

        // THEN
        assertThrows(NoSuchElementException.class, () -> mockMvc.perform(get("/asterix/characters/" + objectId)));

    }

    @DirtiesContext
    @Test
    public void updateCharacterByIdTest_shouldReturnUpdatedObject_whenUpdatedObjectWasRequested() throws Exception {
        // GIVEN
        AsterixCharacter initialCharacter = new AsterixCharacter("1", "TestName", 10, "TestProfession");
        AsterixCharacterRequest updatedCharacter = new AsterixCharacterRequest( "TestNameUpdated", 10, "TestProfession");


        asterixCharacterRepository.save(initialCharacter);

        // WHEN
        mockMvc.perform(put("/asterix/characters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                            {
                                            "name":"TestNameUpdated",
                                            "age":10,
                                            "profession":"TestProfession"
                                            }
                                           """

                        ))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                    {
                                    "id": "1",
                                    "name":"TestNameUpdated",
                                    "age":10,
                                    "profession":"TestProfession"
                                    }
                                   """
                ));

    }
}