package edu.escuelaing.arep.model;

public class Pelicula {
    private int id;
    private String name;
    private String descripcion;
    private String director;

    public Pelicula() {
    }

    public Pelicula(String name, String descripcion, String director) {
        this.name = name;
        this.descripcion = descripcion;
        this.director = director;
    }

    public Pelicula(int id, String name, String descripcion, String director) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}