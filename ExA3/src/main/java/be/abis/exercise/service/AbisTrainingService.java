package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;

@Service
public class AbisTrainingService implements TrainingService {
	
	@Autowired
	private CourseService courseService;	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Course> showFollowedCourses(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Person> getAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPerson(int id) {
		return personRepository.findPerson(id);
	}

	@Override
	public Person findPerson(String emailAddress, String passWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPerson(Person p) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePerson(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(Person p, String newPswd) throws IOException {
		// TODO Auto-generated method stub

	}

}
