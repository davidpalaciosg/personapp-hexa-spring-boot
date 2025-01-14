package co.edu.javeriana.as.personapp.mongo.repository;

import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EstudioRepositoryMongo extends MongoRepository<EstudiosDocument, String> {
    public EstudiosDocument findByPrimaryProfesionAndPrimaryPersona(Integer professionID, Integer personID);
}
