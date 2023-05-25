package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.EstudioMapperRest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class EstudioInputAdapterRest {

    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    private EstudioMapperRest estudioMapperRest;

    StudyInputPort studyInputPort;

    private String setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            return DatabaseOption.MARIA.toString();
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMongo);
            return  DatabaseOption.MONGO.toString();
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public List<EstudioResponse> historial(String database){
        log.info("Into historial EstudioEntity in Input Adapter");

        try{
            String db = setStudyOutputPortInjection(database);
            if(db.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return studyInputPort.findAll().stream().map(estudioMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());
        }
            else {
                return studyInputPort.findAll().stream().map(estudioMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
    }catch (InvalidOptionException e){
            log.error("Error: " + e.getMessage());
            return null;
        }
    }

    public EstudioResponse crearEstudio(EstudioRequest request)
    {
        try{
            setStudyOutputPortInjection(request.getDatabase());
            Study study = studyInputPort.create(estudioMapperRest.fromAdapterToDomain(request));
            return estudioMapperRest.fromDomainToAdapterRestMaria(study);
        }catch (InvalidOptionException e){
            log.warn("Error: " + e.getMessage());
            return null;
        }
    }

    //TODO: Implementar los métodos restantes (actualizar, eliminar, consultar por id)

}
