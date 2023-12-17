package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public Person findById(Long id){
		
		logger.info("Finding one person...");
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}
	
	public List<Person> findAll(){
		
		logger.info("Finding all people...");
		
		return repository.findAll();
	}
	
	public Person create(Person person){
		
		logger.info("Creating one person...");
		
		return repository.save(person);
	}
	
	public Person update(Person person){
		
		logger.info("Updating one person ID:" + person.getId() + "Name: " + person.getFirstName() + " " + person.getLastName());
		
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID..."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id){
		
		logger.info("Deleting person ID:" + id);

		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID..."));

		repository.delete(entity);
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
