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
                System.out.println("5. Eliminar local");//MIA
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
                        break;
                    case 2:
                        System.out.println("2. Listar Carpeta Remota");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 3:
                        System.out.println("3. Crerar Carpeta Local");
                        break;
                    case 4:
                        System.out.println("4. Crerar Carpeta Remota");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 5:
                        System.out.println("5. Eliminar local");
                        break;
                    case 6:
                        System.out.println("6. Eliminar Remota");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 7:
                        System.out.println("7. Subir de Local a Remoto");
                        break;
                    case 8:
                        System.out.println("8. Subir de Remoto a Local");
                        out.println(opcion);
                        System.out.println("Número de opción " + opcion + " enviado al servidor.");
                        break;
                    case 9:
                        System.out.println("9. Cambiar ruta de directorio Local");
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

}
