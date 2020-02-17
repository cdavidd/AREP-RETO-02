package edu.escuelaing.arep.exception;

public class PeliculaDBException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Exception 
     * @param msg 
     */
    public PeliculaDBException(String msg) {
        super(msg);
    }

}