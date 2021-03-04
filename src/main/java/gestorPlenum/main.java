/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorPlenum;

import modelo.consultasEscuela;
import modelo.consultasModalidad;
import modelo.consultasCiclosEscolares;
import modelo.alumno;
import modelo.consultasAlumno;
import modelo.consultasCalificacion;
import controlador.ctrMain;
import vista.Menu;
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*consultasEscuela objEscuela = new consultasEscuela();
        
        consultasModalidad objModalidad = new consultasModalidad();
        consultasCiclosEscolares objCiclosEsc = new consultasCiclosEscolares();
        consultasCalificacion objCalificaciones =  new consultasCalificacion();
        */
        
        /*objEscuela.getEscuelas();
       objModalidad.getModalidad();
       objCiclosEsc.getCiclosEscolares();*/
 /*probar las operaciones con alumno*/
        alumno objAlum = new alumno();
        objAlum.setNombre("Luis");
        objAlum.setApellido("Chale");
        objAlum.setMatricula("e170814824");
        objAlum.setTelefono("992412903");
        objAlum.setCorreo("jose@gmail.com");
        objAlum.setDomicilio("chablekal, Mexico");
        objAlum.setFechaNacimiento("1999-04-26");
        objAlum.setId_ciclo(1);
        objAlum.setId_escuela(2);//Es del tec
        objAlum.setId_modalidad(2); //es servicio social

        consultasAlumno queryAlum = new consultasAlumno();
        //CREATE 
        //queryAlum.createAlumno(objAlum);

        //UPDATE
        /*objAlum.setId(4);
        queryAlum.updateAlumno(objAlum);*/
        
        //SELECT ALL ALUMNOS
        //queryAlum.getAlumnos();
        
        //DELETE
        //objAlum.setId(4);
        //queryAlum.deleteAlumno(objAlum);
        
        //FIND BY MATRICULA
        //queryAlum.findAlumno(objAlum);
        
        
        //getCalificaciones
        //Suspendido no funciono
        //objCalificaciones.getCalificaciones();
       
        //INICIALIZAR LA VISTA DE ALUMNOS
        //vista alumno
        /**frmAlumnos vistaAlum = new frmAlumnos();
        ctrAlumno ctrlAlum = new ctrAlumno(queryAlum, vistaAlum, objAlum);
        ctrlAlum.iniciar();
        vistaAlum.setVisible(true);**/
        
        //Enlazando varias vistas en Menu
        Menu objMenu = new Menu();
        ctrMain main = new ctrMain(objMenu);
        main.iniciar();
        objMenu.setVisible(true);
    }

}
