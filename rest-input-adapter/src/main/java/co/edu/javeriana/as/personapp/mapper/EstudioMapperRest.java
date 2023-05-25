package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;

import java.util.ArrayList;

@Mapper
public class EstudioMapperRest {

    public EstudioResponse fromDomainToAdapterRestMaria(Study study) {
        return fromDomainToAdapterRest(study,"MariaDB");
    }

    public EstudioResponse fromDomainToAdapterRestMongo(Study study) {
        return fromDomainToAdapterRest(study,"MongoDB");
    }

    public EstudioResponse fromDomainToAdapterRest(Study study, String database) {
       return new EstudioResponse(
               study.getGraduationDate(),
               study.getUniversityName(),
               database,
               "OK");
    }

    public Study fromAdapterToDomain(EstudioRequest request) {
        Study newStudy = new Study();
        newStudy.setGraduationDate(request.getGraduationDate());
        newStudy.setUniversityName(request.getUniversityName());

        //TODO FIX PERSON AND PROFESSION
        int random = (int) (Math.random() * 1000) + 1;
        Person person = new Person(random,"PRUEBA","PRUEBA", Gender.OTHER,22,new ArrayList<>(),new ArrayList<>());
        newStudy.setPerson(person);
        Profession profession = new Profession(random,"PRUEBA","PRUEBA",new ArrayList<>());
        newStudy.setProfession(profession);

        return newStudy;
    }
}
