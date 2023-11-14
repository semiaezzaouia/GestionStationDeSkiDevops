package tn.esprit.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    IInstructorRepository instructorRepository;

    @InjectMocks
    InstructorServicesImpl instructorService;

    @Test
    void testRetrieveInstructor() {
        // Arrange
        Instructor instructor = Instructor.builder()
                .numInstructor(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfHire(LocalDate.of(2022, 1, 1))
                .build();
        Mockito.when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act
        Instructor retrievedInstructor = instructorService.retrieveInstructor(1L);

        // Assert
        Assertions.assertNotNull(retrievedInstructor);
        Assertions.assertEquals(instructor, retrievedInstructor);
    }

    @Test
    void testRetrieveAllInstructors() {
        // Arrange
        List<Instructor> instructors = Arrays.asList(
                Instructor.builder().numInstructor(1L).firstName("John").lastName("Doe").build(),
                Instructor.builder().numInstructor(2L).firstName("Jane").lastName("Doe").build()
        );
        Mockito.when(instructorRepository.findAll()).thenReturn(instructors);

        // Act
        List<Instructor> retrievedInstructors = instructorService.retrieveAllInstructors();

        // Assert
        Assertions.assertNotNull(retrievedInstructors);
        Assertions.assertEquals(instructors.size(), retrievedInstructors.size());
    }

    @Test
    void testAddInstructor() {
        // Arrange
        Instructor instructorToAdd = Instructor.builder()
                .firstName("New")
                .lastName("Instructor")
                .dateOfHire(LocalDate.of(2023, 1, 1))
                .build();
        Mockito.when(instructorRepository.save(instructorToAdd)).thenReturn(instructorToAdd);

        // Act
        Instructor addedInstructor = instructorService.addInstructor(instructorToAdd);

        // Assert
        Assertions.assertNotNull(addedInstructor);
        Assertions.assertEquals(instructorToAdd, addedInstructor);
    }

    @Test
    void testUpdateInstructor() {
        // Arrange
        Instructor instructorToUpdate = Instructor.builder()
                .numInstructor(1L)
                .firstName("Updated")
                .lastName("Instructor")
                .dateOfHire(LocalDate.of(2023, 1, 1))
                .build();
        Mockito.when(instructorRepository.save(instructorToUpdate)).thenReturn(instructorToUpdate);

        // Act
        Instructor updatedInstructor = instructorService.updateInstructor(instructorToUpdate);

        // Assert
        Assertions.assertNotNull(updatedInstructor);
        Assertions.assertEquals(instructorToUpdate, updatedInstructor);
    }


}
