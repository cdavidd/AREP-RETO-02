# AREP-RETO-02

[![CircleCI](https://circleci.com/gh/cdavidd/AREP-RETO-02.svg?style=svg)](https://circleci.com/gh/cdavidd/AREP-RETO-02)

## Heroku

- https://limitless-hamlet-51946.herokuapp.com/

## Empezando

### Prerrequisitos

**Java**
Se necesitara del java JDK 1.8, para verificar que se tiene abriremos nuestra terminal o consola de comando y ejecutaremos el siguiente comando:

```
java -version
java version "1.8.0_221"
Java(TM) SE Runtime Environment (build 1.8.0_221-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.221-b11, mixed mode)
```

**Maven**

Se necesitara de Maven para ejecutar el programa, para eso se verificara si se tiene con `mvn -v` en caso de no tenerlo se podrá hacer [aqui](https://maven.apache.org/install.html)

**Heroku**

Para correrlo localmente mediante heroku asegurece de terner el CLI de este con el comando `heroku --version`

```
heroku/7.37.0 win32-x64 node-v12.4.0
```

### Instalación

**Ejecución**

## Ejecutando las pruebas

Comenzaremos primero inicializando el servidor para poder hacerle las peticiones

```java
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
```

Ahora haremos la conexión y enviaremos la petición a resolver

```java
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
```

## Generar javaDocs

Para generar el javaDocs utilizaremos `mvn javadoc:javadoc`

## Autores

- Cristian López

## Licencia

- GNU General Public License v3.0
