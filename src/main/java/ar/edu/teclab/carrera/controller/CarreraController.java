package ar.edu.teclab.carrera.controller;

import ar.edu.teclab.carrera.dto.Carrera;
import ar.edu.teclab.carrera.service.CarreraService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrera")
@CrossOrigin(origins = "*")
public class CarreraController {

    private static final Log LOG = LogFactory.getLog(CarreraController.class);

    @Autowired
    protected CarreraService carreraService;


    @GetMapping("/getCarreras")
    public ResponseEntity<JsonArray> getCarreras(){

        List<Carrera> carreras= carreraService.getAllCarreras();

        if (carreras.size() != 0) {
            Gson gson = new Gson();
            JsonArray jsonArray = new JsonParser().parse(gson.toJson(carreras)).getAsJsonArray();

            return ResponseEntity.ok().body(jsonArray);
        }

        LOG.error("No data founded");

        return null;
    }

    @GetMapping("/getCarrera")
    public ResponseEntity<JsonObject> getCarreras(@RequestParam(value = "id")String id){

        Optional<Carrera> carrera= carreraService.getCarreraById(id);

        JsonObject jsonObject;

        if (carrera.isPresent()){
            Gson gson = new Gson();
            jsonObject = new JsonParser().parse(gson.toJson(carrera.get())).getAsJsonObject();
            return ResponseEntity.ok().body(jsonObject);
        }

        jsonObject = new JsonParser().parse("Id not found").getAsJsonObject();

        return ResponseEntity.badRequest().body(jsonObject);
    }

    @DeleteMapping("/deleteCarrera")
    public ResponseEntity<String> deleteCarrera(@RequestParam(value = "id")String id){

        Boolean deleted= carreraService.deleteCarrera(id);

        if (deleted){
            return ResponseEntity.ok().body("Deletion done");
        }

        return ResponseEntity.badRequest().body("Id not found");
    }

    @PostMapping("/createCarrera")
    public ResponseEntity<JsonObject> createCarrera(@RequestParam(value = "name")String name,
             @RequestParam(value = "duracion")Integer duracion, @RequestParam(value = "materias")Integer materias,
             @RequestParam(value = "sede")String sede){

        JsonObject jsonObject;

        if(name.isEmpty() || sede.isEmpty() || duracion == null || materias == null){
            jsonObject = new JsonParser().parse("Missing values, please check").getAsJsonObject();
            return ResponseEntity.badRequest().body(jsonObject);
        }

        Carrera carrera= carreraService.createCarrera(name, duracion, materias, sede);

        Gson gson = new Gson();
        jsonObject = new JsonParser().parse(gson.toJson(carrera)).getAsJsonObject();
        return ResponseEntity.ok().body(jsonObject);
    }

    @PutMapping("/modifyCarrera")
    public ResponseEntity<String> modifyCarrera(@RequestParam(value = "id")String id,
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "duracion", required = false)Integer duracion,
            @RequestParam(value = "materias", required = false)Integer materias,
            @RequestParam(value = "sede", required = false)String sede){

        Boolean modified = carreraService.modifyCarrera(id, name, duracion, materias, sede);

        if (modified){
            return ResponseEntity.ok().body("Modification ok");
        }

        return ResponseEntity.badRequest().body("Id not found");
    }

}
