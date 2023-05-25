package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.ProfesionInputAdapterRest;
import co.edu.javeriana.as.personapp.model.response.ProfesionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/profesion")
public class ProfesionControllerV1 {

    @Autowired
    private ProfesionInputAdapterRest profesionInputAdapterRest;

    //GET PROFESIONES
    @ResponseBody
    @GetMapping(path = "/{database}", produces = "application/json")
    public List<ProfesionResponse> profesiones(@PathVariable String database) {
        log.info("Into profesiones REST API");
            return profesionInputAdapterRest.historial(database.toUpperCase());
    }

    //POST NEW PROFESION
    @ResponseBody
    @PostMapping(path = "", produces = "application/json", consumes = "application/json")
    public ProfesionResponse crearProfesion(@RequestBody ProfesionResponse request) {
        log.info("esta en el metodo crearProfesion en el controller del api");
        return profesionInputAdapterRest.crearProfesion(request);
    }

    //PUT PROFESION BY ID
    @ResponseBody
    @PutMapping(path = "", produces = "application/json", consumes = "application/json")
    public ProfesionResponse updateProfesionById(@RequestBody ProfesionResponse request) {
        log.info("Into updateProfesionById REST API");
        return profesionInputAdapterRest.editarProfesion(request);
    }

    //GET PROFESION BY ID
    @ResponseBody
    @GetMapping(path = "", produces = "application/json")
    public ProfesionResponse profesionById(@RequestBody ProfesionResponse request){
        log.info("Into profesionById REST API");
        return profesionInputAdapterRest.buscarProfesion(request);
    }

    //DELETE PROFESION BY ID
    @ResponseBody
    @DeleteMapping(path = "", produces = "application/json")
    public ProfesionResponse deleteProfesionById(@RequestBody ProfesionResponse request) {
        log.info("Into deleteProfesionById REST API");
        return profesionInputAdapterRest.eliminarProfesion(request);
    }
}
