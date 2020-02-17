package edu.escuelaing.arep.database.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arep.database.PeliculasPers;
import edu.escuelaing.arep.exception.PeliculaDBException;
import edu.escuelaing.arep.model.Pelicula;

public class PeliculasDBImpl implements PeliculasPers {

    private static String urlDB = "jdbc:postgresql://ec2-184-72-235-80.compute-1.amazonaws.com:5432/dbtl4cgci93us8";
    private static String usuarioDB = "kdhtqiwvrrmoth";
    private static String passwordDB = "eb9eea3f28cda481450f86b6646fb25d67f71a628a003a0e223b989a95cabf70";
    private static Connection connection = null;

    /**
     * Realiza la conexion a la base de datos y crea las tablas de ser necesario
     */
    public PeliculasDBImpl() {
        coneccion();
        createTablePelicula();
    }

    /**
     * Realiza la coneccion a la base de datos
     */
    public void coneccion() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("La conexion se realizo sin problemas!");
            connection = DriverManager.getConnection(urlDB, usuarioDB, passwordDB);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + e);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Error al conectar a PostgreSQL: " + e);
            // e.printStackTrace();
        }
    }

    /**
     * Crea la tabla si es necesario
     */
    public void createTablePelicula() {
        Statement stmt = null;
        DatabaseMetaData dbm = null;
        try {
            dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "pelicula", null);
            boolean existe = tables.next();
            // System.out.println("Existeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + existe);
            if (!existe) {
                // System.out.println("Creaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + tables.next());
                stmt = connection.createStatement();
                String sql = "CREATE TABLE PELICULA " + "(id INTEGER not NULL, " + " name VARCHAR(40), "
                        + " descripcion VARCHAR(255), " + " director VARCHAR(40), " + " PRIMARY KEY ( id ))";

                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Error al crear la tabla en PostgreSQL: " + e);
            // e.printStackTrace();
        }
    }

    /**
     * Inserta la pelicula en la base de datos
     * 
     * @param peli
     * @throws PeliculaDBException
     */
    public void insertPeliculas(Pelicula peli) throws PeliculaDBException {
        try {
            Statement stmt = connection.createStatement();
            String insertPelicula = "INSERT INTO PELICULA (id, name, descripcion, director) VALUES (" + peli.getId()
                    + "," + peli.getName() + "," + peli.getDescripcion() + "," + peli.getDirector() + ")";
            stmt.executeUpdate(insertPelicula);
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Obtiene las peliculas de la base de datos
     * 
     * @return
     * @throws PeliculaDBException
     */
    public List<Pelicula> getPeliculas() throws PeliculaDBException {
        // TODO Auto-generated method stub
        Statement stmt = null;
        List<Pelicula> peliculas = new ArrayList<Pelicula>();

        try {
            stmt = connection.createStatement();
            String sqlSelect = "SELECT * FROM PELICULA";
            ResultSet rs = stmt.executeQuery(sqlSelect);
            Pelicula temp;
            while (rs.next()) {
                temp = new Pelicula(rs.getInt("id"), rs.getString("name"), rs.getString("descripcion"),
                        rs.getString("director"));
                peliculas.add(temp);
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return peliculas;
    }

}