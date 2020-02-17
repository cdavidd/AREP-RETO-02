package edu.escuelaing.arep;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Before
    public void servidor() {
        Thread servidor = new Thread(() -> {
            try {
                HttpServer.main(null);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                fail();
                // e.printStackTrace();
            }
        });
        servidor.start();
    }

    @Test
    public void peticion() {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            // System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            // System.out.println(echoSocket == null);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: localhost. " + e);
            // System.exit(1);
        }

        String res;

        try {
            out.println("GET /?id=1 HTTP/1.1 \n");
            res = in.readLine();
            assertTrue(res.contains("200"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            fail();
        }
        System.out.println("prueba 4");
    }
}
