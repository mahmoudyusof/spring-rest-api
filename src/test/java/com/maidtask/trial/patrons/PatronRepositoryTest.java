package com.maidtask.trial.patrons;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PatronRepositoryTest {

    @Autowired
    PatronRepository patronsRepository;

    private static final Integer ID_TO_DELETE = 3;

    @Test
    void testFindAll() {
        List<Patron> patrons = patronsRepository.findAll();
        assertEquals(5, patrons.size());
    }

    @Test
    void testFindById() {
        Patron patron = patronsRepository.findById(1).orElse(null);
        assertNotNull(patron);
        assertEquals("John Doe", patron.fullName());
    }

    @Test
    void testDelete() {
        patronsRepository.deleteById(ID_TO_DELETE);
        Patron patron = patronsRepository.findById(ID_TO_DELETE).orElse(null);
        assertNull(patron);
    }

    @Test
    void testUpdate() {
        Patron patron = patronsRepository.findById(1).orElse(null);
        assertNotNull(patron);
        assertNotEquals(patron.fullName(), "John Doe 2");
        patron.setFullName("John Doe 2");
        patronsRepository.save(patron);
        Patron updatedPatron = patronsRepository.findById(1).orElse(null);
        assertNotNull(updatedPatron);
        assertEquals("John Doe 2", updatedPatron.fullName());
    }

}
