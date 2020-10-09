package be.abis.exercise.it;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAbisCourseService {
	
	@Autowired
	CourseService courseService;

	@Test
	public void findCourse7900() {
		Course c1 = courseService.findCourse(7900);
		assertEquals("Workshop SQL",c1.getShortTitle());
	}

}
