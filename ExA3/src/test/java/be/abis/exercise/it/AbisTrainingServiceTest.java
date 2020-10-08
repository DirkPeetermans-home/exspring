package be.abis.exercise.it;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AbisTrainingServiceTest {

	@Autowired
	CourseService courseService;
	@Autowired
	TrainingService trainingService;

	@Test
	public void findPersonTest() {
		Person p1 = trainingService.findPerson(2);
		assertEquals("Mary",p1.getFirstName());
	}

	@Test
	public void findCourse() {
		Course c1 = courseService.findCourse(3);
		assertEquals("Java Prog",c1.getShortTitle());
	}
}
