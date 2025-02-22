package co.edu.javeriana.as.personapp.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
public class PersonUseCase implements PersonInputPort {

	private PersonOutputPort personPersistence;
	
	public PersonUseCase(@Qualifier("personOutputAdapterMaria") PersonOutputPort personPersistence) {
		this.personPersistence = personPersistence;
	}
	
	@Override
	public void setPersistence(PersonOutputPort personPersintence) {
		this.personPersistence =personPersintence;
	}

	@Override
	public Person create(Person person) {
		log.debug("Into Person create on Application Domain");
		return personPersistence.save(person);
	}

	@Override
	public Person edit(Integer identification, Person person) throws NoExistException {
		Person oldPerson = personPersistence.findById(identification);
		if (oldPerson != null)
			return personPersistence.save(person);
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be edited");
	}

	@Override
	public Boolean drop(Integer identification) throws NoExistException {
		Person oldPerson = personPersistence.findById(identification);
		if (oldPerson != null)
			return personPersistence.delete(identification);
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be dropped");
	}

	@Override
	public List<Person> findAll() {
		log.info("Output: " + personPersistence.getClass());
		return personPersistence.find();
	}

	@Override
	public Person findOne(Integer identification) throws NoExistException {
		Person oldPerson = personPersistence.findById(identification);
		if (oldPerson != null)
			return oldPerson;
		throw new NoExistException("The person with id " + identification + " does not exist into db, cannot be found");
	}

	@Override
	public Integer count() {
		return findAll().size();
	}

	@Override
	public List<Phone> getPhones(Integer identification) throws NoExistException {
		Person oldPerson = personPersistence.findById(identification);
		if (oldPerson != null)
			return oldPerson.getPhoneNumbers();
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be getting phones");
	}

	@Override
	public List<Study> getStudies(Integer identification) throws NoExistException {
		Person oldPerson = personPersistence.findById(identification);
		if (oldPerson != null)
			return oldPerson.getStudies();
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be getting studies");
	}
}
