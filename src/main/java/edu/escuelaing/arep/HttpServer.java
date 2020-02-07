package edu.escuelaing.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import edu.escuelaing.arep.database.PeliculasPers;
import edu.escuelaing.arep.database.impl.PeliculasDBImpl;
import edu.escuelaing.arep.exception.PeliculaDBException;
import edu.escuelaing.arep.model.Pelicula;

public class HttpServer {

    static PeliculasPers peliculasDB;

    public static void main(String[] args) throws IOException {
        int port = getPort();
        ServerSocket serverSocket = null;
        peliculasDB = new PeliculasDBImpl();
        while (true) {
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + port);
                System.exit(1);
            }

            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, archivo;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibi: " + inputLine);
                if (!in.ready()) {
                    break;
                }
                if (inputLine.contains("GET")) {
                    System.out
                            .println(inputLine.indexOf("/") + " " + inputLine.indexOf(" ", inputLine.indexOf(" ") + 1));
                    archivo = inputLine.substring(inputLine.indexOf("/") + 1,
                            inputLine.indexOf(" ", inputLine.indexOf(" ") + 1));
                    System.out.println("archivo: " + archivo + " " + archivo.contains("?"));
                    String header = "HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n";
                    if (archivo.length() == 0 || archivo.equals("index.html")) {
                        out.println(header);
                        out.println(index(0, false));

                    } else if (archivo.contains("?")) {
                        int pos = archivo.indexOf("=") + 1;
                        int id = Integer.parseInt(archivo.substring(pos, archivo.length()));
                        out.println(header);
                        out.println(index(id, true));
                    }
                    break;
                }
            }

            // outputLine = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n";

            // out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }

    }

    private static String index(int id, boolean b) {
        String res = "";
        Pelicula busqueda = null;
        try {
            List<Pelicula> peliculas = peliculasDB.getPeliculas();
            busqueda = peliculas.get(0);
            res += "<html><body><h1>Peliculas</h1><div><table><tr><th style=\"border:1px solid black\">id</th><th style=\"border:1px solid black\">Pelicula</th></tr>";
            for (Pelicula p : peliculas) {
                if (p.getId() == id && b) {
                    busqueda = p;
                }
                res += "<tr><td style=\"border:1px solid black\">" + p.getId()
                        + "</td><td style=\"border:1px solid black\">" + p.getName() + "</td></tr>";

            }

            res += "</table></div></body></html> <br/><br/>";
            res += "<form method=\"GET\" action=\"/\">";
            res += "Id pelicula:";
            res += "<input type=\"number\" required=\"true\" name=\"id\">";
            res += "<input type=\"submit\" value=\"Mostrar Descripcion\">";
            res += "</form>";
            if (b) {
                res += "<table>";
                res += "<tr>";
                res += "<th style=\"border:1px solid black\">Pelicula</th>";
                res += "<th style=\"border:1px solid black\">Descripcion</th>";
                res += "<th style=\"border:1px solid black\">Director</th>";
                res += "</tr>";
                res += "<tr>";
                res += "<td style=\"border:1px solid black\">" + busqueda.getName() + "</td>";
                res += "<td style=\"border:1px solid black\">" + busqueda.getDescripcion() + "</td>";
                res += "<td style=\"border:1px solid black\">" + busqueda.getDirector() + "</td>";
                res += "</tr>";
                res += "</table>";
            }

        } catch (PeliculaDBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; // returns default port if heroku-port isn't set(i.e. on localhost)
    }
}