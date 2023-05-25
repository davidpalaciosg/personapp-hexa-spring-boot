package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/telefono")
public class TelefonoControllerV1 {

    @Autowired
    private TelefonoInputAdapterRest telefonoInputAdapterRest;

    //Get All Phones
    @ResponseBody
    @GetMapping(path="/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelefonoResponse> telefonos(@PathVariable String database){
        log.info("Into telefonos REST API");
        return telefonoInputAdapterRest.historial(database.toUpperCase());
    }

    //Post Phone
    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse crearTelefono(@RequestBody TelefonoResponse request) {
        log.info("esta en el metodo crearTelefono en el controller del api");
        return telefonoInputAdapterRest.crearTelefono(request);
    }

    //PUT PHONE BY ID
    @ResponseBody
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse updateTelefonoById(@RequestBody TelefonoResponse request) {
        log.info("Into updateTelefonoById REST API");
        return telefonoInputAdapterRest.editarTelefono(request);
    }

    //GET PHONE BY ID
    @ResponseBody
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse telefonoById(@RequestBody TelefonoResponse request){
        log.info("Into telefonoById REST API");
        return telefonoInputAdapterRest.buscarTelefono(request);
    }

    //DELETE PHONE BY ID
    @ResponseBody
    @DeleteMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse deleteTelefonoById(@RequestBody TelefonoResponse request) {
        log.info("Into deleteTelefonoById REST API");
        return telefonoInputAdapterRest.eliminarTelefono(request);
    }

}
