/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 10000;
        Scanner scanner = new Scanner(System.in);

        try (
            Socket echoSocket = new Socket(hostName, port);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        ) {
            int opcion;
            do {
                System.out.println("Menú del Cliente:");
                System.out.println("1. Listar Carpeta Local");//MIA
                System.out.println("2. Listar Carpeta Remota");
                System.out.println("3. Crerar Carpeta Local");//MIA
                System.out.println("4. Crerar Carpeta Remota");
                System.out.println("5. Eliminar archivos o carpetas en la Carpeta local");//MIA
                System.out.println("6. Eliminar Remota");
                System.out.println("7. Subir de Local a Remoto");//MIA
                System.out.println("8. Subir de Remoto a Local");
                System.out.println("9. Cambiar ruta de directorio Local");//MIA
                System.out.println("10. Cambiar ruta de directorio Remoto");
                System.out.println("11. Salir");
                System.out.println("Ingrese la opción deseada: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                

                switch (opcion) {
                   case 1:
                        System.out.println("1. Listar Carpeta Local");
                        File directorio = new File("C:\\Users\\paofl\\OneDrive\\Escritorio\\Local"); 
                        String[] archivos = directorio.list();
                        if (archivos == null || archivos.length == 0) {
                            System.out.println("No hay archivos en el directorio.");
                        } else {
                            System.out.println("Archivos en el directorio:");
                            for (String archivo : archivos) {
                                System.out.println(archivo);
                            }
                        }
                        break;
                    case 2:
                        System.out.println("2. Listar Carpeta Remota");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 3:
                        System.out.println("3. Crear Carpeta Local");
                        System.out.println("Ingrese el nombre de la carpeta que desea crear:");
                        String nombreCarpeta = scanner.nextLine();
                        File nuevaCarpeta = new File("C:\\Users\\paofl\\OneDrive\\Escritorio\\Local" + nombreCarpeta);
                        if (!nuevaCarpeta.exists()) {
                            if (nuevaCarpeta.mkdir()) {
                                System.out.println("Carpeta creada exitosamente.");
                            } else {
                                System.out.println("Error al crear la carpeta.");
                            }
                        } else {
                            System.out.println("La carpeta ya existe.");
                        }
                        break;
                    case 4:
                        System.out.println("4. Crerar Carpeta Remota");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 5:
                        System.out.println("5. Eliminar local");
                        System.out.println("Ingrese el nombre de la carpeta o archivo que desea eliminar:");
                        String nombreCarpetaArchivo = scanner.nextLine();
                        File carpetaArchivo = new File("C:\\Users\\paofl\\OneDrive\\Escritorio\\Local" + nombreCarpetaArchivo);
                        if (carpetaArchivo.exists()) {
                            if (carpetaArchivo.delete()) { //Se usa delete() en lugar de rmdir debido a que es para operativos similares a UNIX
                                System.out.println("Carpeta o archivo eliminado exitosamente.");
                            } else {
                                System.out.println("Error al eliminar la carpeta o archivo.");
                            }
                        } else {
                            System.out.println("La carpeta o archivo no existe.");
                        }
                        break;
                    case 6:
                        System.out.println("6. Eliminar Remota");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                        case 7:
                        System.out.println("7. Subir de Local a Remoto");
                        System.out.println("Ingrese el nombre del archivo o carpeta que desea enviar:");
                        String nombreArchivoCarpeta = scanner.nextLine();
                        File archivoCarpeta = new File("C:\\Users\\paofl\\OneDrive\\Escritorio\\Local" + nombreArchivoCarpeta);
                        if (archivoCarpeta.exists()) {
                            out.println("put " + nombreArchivoCarpeta); // Enviar el comando 'put' y el nombre del archivo o carpeta al servidor
                            if (archivoCarpeta.isDirectory()) {
                                File[] archivos = archivoCarpeta.listFiles();
                                for (File archivo : archivos) {
                                    enviarArchivo(archivo, echoSocket);
                                }
                            } else {
                                enviarArchivo(archivoCarpeta, echoSocket);
                            }
                            System.out.println("Archivo o carpeta enviados exitosamente.");
                        } else {
                            System.out.println("El archivo o carpeta no existen.");
                        }
                        break;

                    case 8:
                        System.out.println("8. Subir de Remoto a Local");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 9:
                        System.out.println("9. Cambiar ruta de directorio Local");
                        System.out.println("Ingrese la nueva ruta del directorio:");
                        String nuevaRuta = scanner.nextLine();
                        File nuevoDirectorio = new File(nuevaRuta);
                        if (nuevoDirectorio.exists() && nuevoDirectorio.isDirectory()) {
                            // Cambiar la ruta del directorio a la nueva ruta
                            archivoCarpeta = nuevoDirectorio; // Cambia la ruta del directorio
                            System.out.println("La ruta del directorio se ha cambiado exitosamente.");
                        } else {
                            System.out.println("La ruta ingresada no existe o no es un directorio.");
                        }
                        break;
                    case 10:
                        System.out.println("10. Cambiar ruta de directorio Remoto");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 11:
                        System.out.println("11. Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (opcion != 11);
        } catch (UnknownHostException e) {
            System.err.println("No se pudo conectar al host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener E/S para la conexión con: " + hostName);
            System.exit(1);
        }
    }
    
    private static void enviarArchivo(File archivo, Socket echoSocket) throws IOException {
    byte[] bytes = new byte[16 * 1024];
        try (InputStream in = new FileInputStream(archivo)) {
            OutputStream out = echoSocket.getOutputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.flush();
        }
}



}
