package edu.escuelaing.arep.model;

public class Pelicula {
    private int id;
    private String name;
    private String descripcion;
    private String director;
    
    /**
     * Contructor vacio
     */
    public Pelicula() {
    }
    
    /**
     * Contructor
     * @param name
     * @param descripcion
     * @param director 
     */
    public Pelicula(String name, String descripcion, String director) {
        this.name = name;
        this.descripcion = descripcion;
        this.director = director;
    }
    /**
     * Constructor
     * @param id
     * @param name
     * @param descripcion
     * @param director 
     */
    public Pelicula(int id, String name, String descripcion, String director) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.director = director;
    }
    
    /**
     * Retorna el nombre
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Cambia el nombre
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retorna la descipcion
     * @return 
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Cambia la descripcion
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Retorna el director
     * @return  String
     */
    public String getDirector() {
        return director;
    }
    
    /**
     * Cambia el director
     * @param director String
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
    /**
     * Obtiene el id
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Cambia el id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

}