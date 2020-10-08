package be.abis.exercise.service;

import org.springframework.stereotype.Service;

import be.abis.exercise.model.Person;

@Service
public class AbisPersonService implements PersonService {

	@Override
	public Person findPersonById(int id) {
		// TODO Auto-generated method stub
		return new Person("Sandy","Sch");
	}

}
