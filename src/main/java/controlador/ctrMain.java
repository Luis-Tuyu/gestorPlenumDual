package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.Menu;
import vista.PanelAlumnos;
import modelo.consultasAlumno;
import modelo.alumno;
import controlador.ctrlInicio;
import vista.panelInicio;

public class ctrMain implements ActionListener {

    private Menu frmMenu;

    public ctrMain(Menu frmMenu) {
        this.frmMenu = frmMenu;
        this.frmMenu.jmiAlumno.addActionListener(this);
        this.frmMenu.jmiCalificaciones.addActionListener(this);
        this.frmMenu.jmiListado.addActionListener(this);

        //iniar la vista principal
        panelInicio objPI = new panelInicio();
        ctrlInicio ctrPI = new ctrlInicio(objPI);

        frmMenu.mainPanel.add(ctrPI.iniciar());
        frmMenu.mainPanel.revalidate();
        frmMenu.mainPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmMenu.jmiAlumno) {
            //vamos a repintar el panel del frame
            PanelAlumnos pt = new PanelAlumnos();
            consultasAlumno queryAlumno = new consultasAlumno();
            alumno objAlum = new alumno();

            ctrAlumno objAlu = new ctrAlumno(queryAlumno, pt, objAlum);

            //repintar el panel
            frmMenu.mainPanel.removeAll();
            frmMenu.mainPanel.add(objAlu.iniciar());
            frmMenu.mainPanel.revalidate();
            frmMenu.mainPanel.repaint();
        } else if (e.getSource() == frmMenu.jmiListado) {
            panelInicio objPI = new panelInicio();
            ctrlInicio ctrPI = new ctrlInicio(objPI);
            
            frmMenu.mainPanel.removeAll();
            frmMenu.mainPanel.add(ctrPI.iniciar());
            frmMenu.mainPanel.revalidate();
            frmMenu.mainPanel.repaint();
        }
    }

    public void iniciar() {
        frmMenu.setTitle("Menú gestión Plenum");
        //frmMenu.setLocationRelativeTo(null);
    }

}
