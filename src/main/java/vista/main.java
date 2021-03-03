/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import modelo.consultasEscuela;
import modelo.consultasModalidad;
import modelo.consultasCiclosEscolares;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       consultasEscuela objEscuela = new consultasEscuela();
       consultasModalidad objModalidad = new consultasModalidad();
       consultasCiclosEscolares objCiclosEsc = new consultasCiclosEscolares();
       
       objEscuela.getEscuelas();
       objModalidad.getModalidad();
       objCiclosEsc.getCiclosEscolares();
       
    }
    
}
