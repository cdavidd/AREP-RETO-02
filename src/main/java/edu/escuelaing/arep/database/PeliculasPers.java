package edu.escuelaing.arep.database;

import java.util.List;

import edu.escuelaing.arep.exception.PeliculaDBException;
import edu.escuelaing.arep.model.Pelicula;

public interface PeliculasPers {
    /**
     * Obtiene las peliculas registradas
     * @return
     * @throws PeliculaDBException 
     */
    List<Pelicula> getPeliculas() throws PeliculaDBException;
    
    /**
     * Inserta la pelicula
     * @param peli
     * @throws PeliculaDBException 
     */
    void insertPeliculas(Pelicula peli) throws PeliculaDBException;

}