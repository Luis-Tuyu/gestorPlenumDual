
package utils;

import java.util.ArrayList;
import modelo.cicloEscolar;
import modelo.modalidad;

/**
 *
 * @author luis
 */
public class utilsCicloEsc {
    public int getIdByName(String periodo, int year, ArrayList<cicloEscolar> ciclos){
        int id=0;
        for(cicloEscolar item: ciclos){
            if(periodo.equals(item.getPeriodo()) && year == item.getAño()){
                id=item.getId();
                break;
            }
        }
        return id;
    }
}
