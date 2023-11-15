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
     void testAddCourseWithNullData() {

        Course nullCourse = new Course();
        boolean result= cs.addCourse(nullCourse) != null;
        Assertions.assertTrue(result);
    }

    @Test
         void testUpdateNonExistentCourse() {

            Course nonExistentCourse = new Course((long) 999, 1, TypeCourse.INDIVIDUAL,
                    Support.SKI, (float) 10, 15, new HashSet<>());
            boolean result = cs.updateCourse(nonExistentCourse)==nonExistentCourse;
            Assertions.assertFalse(result);
        }

}
