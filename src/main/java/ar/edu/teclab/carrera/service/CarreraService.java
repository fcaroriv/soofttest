package ar.edu.teclab.carrera.service;

import ar.edu.teclab.carrera.dto.Carrera;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    private static final Log LOG = LogFactory.getLog(CarreraService.class);

    List<Carrera> carreras = new ArrayList<>();

    public Carrera createCarrera(String name, Integer duracion, Integer materias, String sede){
        Carrera carrera = new Carrera(name, duracion, materias, sede);
        carreras.add(carrera);
        return  carrera;
    }

    public List<Carrera> getAllCarreras(){
        return carreras;
    }

    public Optional<Carrera> getCarreraById(String id){
        return carreras.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst();
    }

    public Boolean deleteCarrera(String id){
        Optional<Carrera> carreraOptional = carreras.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst();

        if (carreraOptional.isPresent()){
            carreras.remove(carreraOptional.get());
            return true;
        }else {return false;}
    }

    public Boolean modifyCarrera(String id, String name, Integer duracion, Integer materias, String sede){
        Optional<Carrera> carreraOptional = carreras.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst();

        if (carreraOptional.isPresent()){
            Carrera carrera = carreraOptional.get();
            carreras.remove(carreraOptional.get());
            carrera.setName(name != null ? name : carrera.getName());
            carrera.setDuracion(duracion != null ? duracion : carrera.getDuracion());
            carrera.setMaterias(materias != null ? duracion : carrera.getMaterias());
            carrera.setSede(sede != null ? sede : carrera.getSede());

            carreras.add(carrera);

            return true;
        }else {return false;}
    }

}
