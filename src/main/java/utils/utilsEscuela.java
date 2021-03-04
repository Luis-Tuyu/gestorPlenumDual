package utils;


import java.util.ArrayList;
import modelo.escuela;
public class utilsEscuela {
    
    public int getIdByName(String nombreEsc, ArrayList<escuela> escuelas){
        int id=0;
        for(escuela item: escuelas){
            if(nombreEsc.equals(item.getNombre())){
                id=item.getId();
                break;
            }
        }
        return id;
    }
    
}
