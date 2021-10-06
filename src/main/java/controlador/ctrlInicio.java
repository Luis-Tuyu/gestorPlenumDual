package controlador;

/**
 * Controlador diseñado para mostrar la lista de los alumnos de una tabla
 */
import javax.swing.table.DefaultTableModel;
import vista.panelInicio;
import modelo.consultasAlumno;
public class ctrlInicio {

    private panelInicio panelIni;
    private consultasAlumno queryAlum = new consultasAlumno();
    public ctrlInicio(panelInicio panelIni) {
        this.panelIni = panelIni;
        
        //aqui vamos a inicializar el query de los datos
        queryAlum.getAlumnos(this.panelIni.jtAlumnos);
        
    }
    
    public panelInicio iniciar(){
        panelIni.setSize(900,500);
        return panelIni;
    }
}
