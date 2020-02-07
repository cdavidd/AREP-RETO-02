package edu.escuelaing.arep.database;

import java.util.List;

import edu.escuelaing.arep.exception.PeliculaDBException;
import edu.escuelaing.arep.model.Pelicula;

public interface PeliculasPers {
    List<Pelicula> getPeliculas() throws PeliculaDBException;

    void insertPeliculas(Pelicula peli) throws PeliculaDBException;

}