/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import modelo.modalidad;

/**
 *
 * @author luis
 */
public class utilsModalidad {
    public int getIdByName(String nombreMod, ArrayList<modalidad> modalidades){
        int id=0;
        for(modalidad item: modalidades){
            if(nombreMod.equals(item.getNombre())){
                id=item.getId();
                break;
            }
        }
        return id;
    }
}
