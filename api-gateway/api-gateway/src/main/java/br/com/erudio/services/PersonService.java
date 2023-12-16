package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id){
		
		logger.info("Finding one person...");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Carolina");
		person.setLastName("Xavier");
		person.setAddress("Q20 Paranoá");
		person.setGender("Female");
		return person;
	}
	
	public List<Person> findAll(){
		
		logger.info("Finding all people...");
		List<Person> persons = new ArrayList<>();
		for(int i = 0; i < 8; i++){
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	
	public Person create(Person person){
		
		logger.info("Creating one person...");
		
		return person;
	}
	
	public Person update(Person person){
		
		logger.info("Updating one person ID:" + person.getId() + "Name: " + person.getFirstName() + " " + person.getLastName());
		
		return person;
	}
	
	public void delete(String id){
		
		logger.info("Deleting person ID:" + id);
		
	}
	
	private Person mockPerson(int i){
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person " + i);
		person.setLastName("LastName " + i);
		person.setAddress("Address number " + i);
		if(i % 2 == 0){
			person.setGender("Male");
		} else {
			person.setGender("Female");
		}
		return person;
	}
}
