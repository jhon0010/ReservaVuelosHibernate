package co.com.reserva.vuelos.service;

import java.util.List;

import co.com.reserva.vuelos.entities.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(int id);
	public void removePerson(int id);
	
}