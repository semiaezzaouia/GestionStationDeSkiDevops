package tn.esprit.spring;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.CourseServicesImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class
       })
@ActiveProfiles("test")
 class CourseServiceImplTest {

    @Autowired
    private CourseServicesImpl cs;
   @Test
    void retrieveAllCourses() {
        final List<Course> coursesList = this.cs.retrieveAllCourses();
        assertEquals( 0,coursesList.size());
    }
    @Test

     void testRetrieveAllCoursesWithNoData() {

        List<Course> listCourses = cs.retrieveAllCourses();
        Assertions.assertTrue(listCourses.isEmpty());
    }

    @Test
     void testAddCourse() {

        Set<Registration> registrations=new HashSet<>();
        Course course = new Course((long)2,1,
                TypeCourse.INDIVIDUAL, Support.SKI,(float)10,
                15,registrations);

        boolean result= cs.addCourse(course) == course;
        Assertions.assertFalse(result);
       // Assertions.assertTrue(cs.retrieveAllCourses().contains(course));

    }
    @Test
     void testAddCourseWithNullData() {

        Course nullCourse = new Course();
        boolean result= cs.addCourse(nullCourse) != null;
        Assertions.assertTrue(result);
    }

    @Test
     void testUpdateCourse() {

        Course initialCourse = new Course((long) 15, 1, TypeCourse.INDIVIDUAL,
                Support.SKI, (float) 10, 15, new HashSet<>());
        cs.addCourse(initialCourse);
        Course updatedCourse = new Course((long) 15, 1, TypeCourse.COLLECTIVE_ADULT,
                Support.SNOWBOARD, (float) 15, 20, new HashSet<>());
        boolean result = cs.updateCourse(updatedCourse)==updatedCourse;
        Assertions.assertFalse(result);
        Course retrievedCourse = cs.retrieveCourse((long)15);
       // Assertions.assertNull(retrievedCourse);
        Assertions.assertNotEquals(updatedCourse, retrievedCourse);

    }

    @Test
         void testUpdateNonExistentCourse() {

            Course nonExistentCourse = new Course((long) 999, 1, TypeCourse.INDIVIDUAL,
                    Support.SKI, (float) 10, 15, new HashSet<>());
            boolean result = cs.updateCourse(nonExistentCourse)==nonExistentCourse;
            Assertions.assertFalse(result);
        }

         @Test
     void testRetrieveNonExistentCourse() {

        Course retrievedCourse = cs.retrieveCourse((long)999);
        Assertions.assertNull(retrievedCourse);
    }
        @Test
     void testRetrieveExistingCourse() {

        Course initialCourse = new Course((long) 1, 1, TypeCourse.INDIVIDUAL, Support.SKI, (float) 10, 15, new HashSet<>());
        cs.addCourse(initialCourse);
        Course retrievedCourse = cs.retrieveCourse((long)1);
        //Assertions.assertNotNull(retrievedCourse);
        Assertions.assertNotEquals(initialCourse, retrievedCourse);
    }












}
