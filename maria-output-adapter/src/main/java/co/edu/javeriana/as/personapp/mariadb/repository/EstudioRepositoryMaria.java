package co.edu.javeriana.as.personapp.mariadb.repository;

import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudioRepositoryMaria extends JpaRepository<EstudiosEntity, Integer> {
    public EstudiosEntity findByPersonIDAndProfessionID(Integer personID, Integer professionID);
}
