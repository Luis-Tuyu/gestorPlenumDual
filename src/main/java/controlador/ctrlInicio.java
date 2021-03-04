package controlador;

/**
 * Controlador diseñado para mostrar la lista de los alumnos de una tabla
 */
import vista.panelInicio;

public class ctrlInicio {

    private panelInicio panelIni;

    public ctrlInicio(panelInicio panelIni) {
        this.panelIni = panelIni;
        
        //aqui vamos a inicializar el query de los datos

    }
    
    public panelInicio iniciar(){
        panelIni.setSize(900,500);
        return panelIni;
    }
}
