import java.io.*;

public class Ins implements Serializable {
    int opcion;
    String extra;

    Ins(String lineaComp){
        if (lineaComp.contains(" ")){
            String[] separador = lineaComp.split(" ", 2);
            opcion = Integer.parseInt(separador[0]);
            extra = separador[1];
        }
        else{
            opcion = Integer.parseInt(lineaComp);
            extra = null;
        }
    }
    
    public int getOpcion(){
        return opcion;
    }
    
    public String getExtra(){
        return extra;
    }
    
}
