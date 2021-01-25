package ar.edu.teclab.carrera.dto;

import java.util.UUID;

public class Carrera {

    private String id;
    private String name;
    private Integer duracion;
    private Integer materias;
    private String sede;

    public Carrera() {
    }

    public Carrera(String name, Integer duracion, Integer materias, String sede) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.duracion = duracion;
        this.materias = materias;
        this.sede = sede;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getMaterias() {
        return materias;
    }

    public void setMaterias(Integer materias) {
        this.materias = materias;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

}
