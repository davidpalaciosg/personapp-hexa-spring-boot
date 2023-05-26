package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.terminal.model.EstudioModelCli;

@Mapper
public class EstudioMapperCli {

    public EstudioModelCli fromDomainToAdapterCli(co.edu.javeriana.as.personapp.domain.Study study)
    {
        EstudioModelCli estudioModelCli = new EstudioModelCli();
        estudioModelCli.setGraduationDate(study.getGraduationDate());
        estudioModelCli.setUniversityName(study.getUniversityName());
        return estudioModelCli;
    }
}
