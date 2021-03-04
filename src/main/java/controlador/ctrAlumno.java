/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.consultasAlumno;
import modelo.consultasEscuela;
import vista.frmAlumnos;
import modelo.alumno;
import modelo.cicloEscolar;
import modelo.consultasCiclosEscolares;
import modelo.escuela;
import modelo.consultasModalidad;
import utils.*;
/**
 * clase para controlar la vista del alumno
 */
import modelo.modalidad;

public class ctrAlumno implements ActionListener {

    //variables globales
    private alumno objAlum;
    private consultasAlumno queryAlumno;
    private frmAlumnos frmAlumno;
    private ArrayList<escuela> escuelas = new ArrayList<>();
    consultasEscuela queryEscuela = new consultasEscuela();
    private ArrayList<modalidad> modalidades = new ArrayList<>();
    consultasModalidad queryModalidad = new consultasModalidad();
    private ArrayList<cicloEscolar> ciclosEsc = new ArrayList<>();
    consultasCiclosEscolares queryCiclosEscolares = new consultasCiclosEscolares();

    public ctrAlumno(consultasAlumno queryAlumno, frmAlumnos frmAlumno, alumno objAlumno) {
        this.queryAlumno = queryAlumno;
        this.frmAlumno = frmAlumno;
        this.objAlum = objAlumno;

        //botones del frame
        this.frmAlumno.btnGuardar.addActionListener(this);
        this.frmAlumno.btnLimpiar.addActionListener(this);

        //Inicializar combo box, previamente consultas BD
        escuelas = queryEscuela.getEscuelas();
        for (escuela itemEscuela : escuelas) {
            frmAlumno.cbEscuelas.addItem(itemEscuela.getNombre());
        }
        modalidades = queryModalidad.getModalidad();
        for (modalidad itemModalidad : modalidades) {
            frmAlumno.cbModalidad.addItem(itemModalidad.getNombre());
        }
        ciclosEsc = queryCiclosEscolares.getCiclosEscolares();
        for (cicloEscolar itemCicloEsc : ciclosEsc) {
            frmAlumno.cbCicloEsc.addItem(itemCicloEsc.getAño() + " " + itemCicloEsc.getPeriodo());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //aqui vamos a enlazar el codigo de la vista con el modelo
        utilsEscuela utilsEsc = new utilsEscuela();
        utilsModalidad utilsMod = new utilsModalidad();
        utilsCicloEsc utilsCicloEsc = new utilsCicloEsc();
        if (e.getSource() == frmAlumno.btnGuardar) {
            //obtenemos los datos
            objAlum.setMatricula(frmAlumno.txtMatricula.getText());
            objAlum.setNombre(frmAlumno.txtNombre.getText());
            objAlum.setApellido(frmAlumno.txtApellido.getText());
            objAlum.setFechaNacimiento(frmAlumno.txtFecha.getText());
            objAlum.setTelefono(frmAlumno.txtCelular.getText());
            objAlum.setCorreo(frmAlumno.txtCorreoElectronico.getText());
            objAlum.setDomicilio(frmAlumno.txtDomicilio.getText());
            //LLAVES FORANEAS
            objAlum.setId_escuela(utilsEsc.getIdByName((String) frmAlumno.cbEscuelas.getSelectedItem(),escuelas));
            objAlum.setId_modalidad(utilsMod.getIdByName((String) frmAlumno.cbModalidad.getSelectedItem(), modalidades));
            objAlum.setId_ciclo(utilsCicloEsc.getIdByName(frmAlumno.cbCicloEsc.getSelectedItem().toString().substring(5), Integer.parseInt(frmAlumno.cbCicloEsc.getSelectedItem().toString().substring(0, 4)), ciclosEsc));
            queryAlumno.createAlumno(objAlum);
            
            /* //TEST
            System.out.println("idEscuela-->"+utilsEsc.getIdByName((String) frmAlumno.cbEscuelas.getSelectedItem(),escuelas));
            System.out.println("idModalidad-->"+utilsMod.getIdByName((String) frmAlumno.cbModalidad.getSelectedItem(), modalidades));
            System.out.println("Substring-->"+frmAlumno.cbCicloEsc.getSelectedItem().toString().substring(0, 4));
            System.out.println("Substring-->"+frmAlumno.cbCicloEsc.getSelectedItem().toString().substring(5));
            System.out.println("IdCicloEscolar-->"+utilsCicloEsc.getIdByName(frmAlumno.cbCicloEsc.getSelectedItem().toString().substring(5), Integer.parseInt(frmAlumno.cbCicloEsc.getSelectedItem().toString().substring(0, 4)), ciclosEsc));
            System.out.println("");*/
            reiniciar();
        }else if(e.getSource() == frmAlumno.btnLimpiar){
            reiniciar();
            
        }

    }

    public void iniciar() {
        frmAlumno.setTitle("Alumnos");
        frmAlumno.setLocationRelativeTo(null);
    }
    
    public void reiniciar(){
        frmAlumno.txtNombre.setText(null);
        frmAlumno.txtApellido.setText(null);
        frmAlumno.txtMatricula.setText(null);
        frmAlumno.txtCelular.setText(null);
        frmAlumno.txtCorreoElectronico.setText(null);
        frmAlumno.txtFecha.setText(null);
        frmAlumno.txtDomicilio.setText(null);
        frmAlumno.cbEscuelas.setSelectedIndex(0);
        frmAlumno.cbCicloEsc.setSelectedIndex(0);
        frmAlumno.cbModalidad.setSelectedIndex(0);
    }

}
