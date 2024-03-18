import java.io.*;
import java.net.*;

public class Servidor {
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static DataInputStream dis;
    public static DataOutputStream dos;
    public static FileOutputStream fos;
    public static FileInputStream fis;
    public static BufferedOutputStream bos;


    public static void main (String [] args){
        Ins op;
        try {
            ServerSocket serv = new ServerSocket(10000);
            serv.setReuseAddress(true);
            String ruta = System.getProperty("user.dir");
            String rutaLista ="";
            System.out.println("Servidor iniciado esperando cliente...");
            for(;;){
                Socket cl = serv.accept();
                ois = new ObjectInputStream(cl.getInputStream());
                oos = new ObjectOutputStream(cl.getOutputStream());
                op = (Ins)(ois.readObject());
                switch (op.getOpcion()){
                    //Listar desde  carpeta remota
                    case 2:
                        if(op.getExtra() != null){
                            File dir = new File(op.getExtra());
                            if (dir.exists()){
                                if(dir.isDirectory()){
                                    rutaLista = ruta+"\\servidor\\" + op.getExtra() + "\\";;
                                    //Metodo listar
                                    listarServidor(rutaLista,0);
                                }
                                else{
                                    System.out.println("No se puede listar, es un archivo");
                                }
                            }else{
                                System.out.println("No se puede listar, el fichero no existe en el servidor");
                            }
                        }else{
                            listarServidor(ruta,0);
                        }
                        break;

                    //Crear carpeta remota
                    case 4:
                        String direx="", name="";
                        if(op.getExtra() != null){
                            if(op.getExtra().contains(" ")){
                                String[] nombdir = op.getExtra().split(" ", 2);
                                direx = nombdir[0];
                                name = nombdir[1];
                            }else{
                                name = op.getExtra();
                            }
                            File dir = new File(ruta+"\\servidor\\"+direx);
                            if (dir.exists()){
                                if(dir.isDirectory()){
                                    //File dir2 = new File(ruta+"\\servidor\\"+direx+"\\"+name);
                                    //name = dir.getAbsolutePath() + "\\" + name;
                                    //Metodo listar
                                    makeDir(dir.getAbsolutePath(), name);
                                }
                            }else{
                                System.out.println("No se puede crear, la direccion no existe en el servidor");
                            }
                        }else{
                            makeDir(ruta,name);
                        }
                        break;

                    //Eliminar remoto
                    case 6:
                        if(op.getExtra() != null){
                            delArch(ruta+"/"+op.getExtra());
                        }
                        break;

                    //Put, para subir de local a remota
                    case 7:
                        subir(ruta, cl);
                        break;


                }
            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static String listarServidor(String sDir, int tab) {
        File f = new File(sDir);
        String listadoFicheros = "";
        File[] lista = f.listFiles();
        if (lista == null || lista.length == 0) {
            return ("Empty\n");
        } else {
            for (int i = 0; i < lista.length; i++) {
                File elemento = lista[i];
                listadoFicheros += elemento.getName() + "\n";
                if (elemento.isDirectory()){
                    tab++;
                    listadoFicheros += listOther("./" + elemento.getName(), tab, "");

                }
            }
            return listadoFicheros;
        }
    }

    public static String listOther(String direccion, int nivel, String listado){
        File directorio = new File(direccion);
        File[] insider = directorio.listFiles();
        String tab = "  ";
        if (insider == null || insider.length == 0) {
            return listado;
        }
        else {
            for (int i=0; i< insider.length; i++) {
                File sub = insider[i];
                listado += tab.repeat(nivel) + "-> " + sub.getName() + "\n";
                if (sub.isDirectory()) {
                    listado = listOther(sub.getAbsolutePath(), nivel++, listado);
                }
            }
            return listado;
        }
    }

    public static String makeDir(String dir, String name ){
        File archcar = new File(dir + "/" + name);
        if (archcar.exists())
            return("Error, carpeta existente");
        else if (archcar.mkdirs())
            return("Carpeta creada");
        else
            return("Error...");
    }


    public static String delArch(String rutar) {
        File archivo = new File(rutar);
        if (archivo.exists()) {
            if (archivo.isDirectory()) {
                File c = new File (archivo.getAbsolutePath());
                File[] in = c.listFiles();

                if (in.length == 0){
                    c.delete();
                }else {
                    for (int i = 0; i < in.length; i++) {
                        File elemento = in[i];
                        elemento.delete();
                    }
                }
                archivo.delete();
                return "Borado correcto";
            } else {
                archivo.delete();
                return "Borrado correcto";
            }
        } else {
            return "El archivo/directorio \"" + rutar + "\" no existe";
        }
    }


    public static void subir(String dir, Socket cliente){
        try{
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

