package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class consultasCalificacion extends conexion {

    PreparedStatement ps = null;
    ResultSet rs;

    public void getCalificaciones() {
        try {
            Connection con = getConexion();
            String sql = "SELECT A.matricula  FROM calificacion C, bloque B, alumno A \n"
                    + "WHERE B.id = C.id_bloque AND A.id = C.id_alumno WHERE alumno.id = calificacion.id_alumno";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData resultMD = rs.getMetaData();
            int contColum = resultMD.getColumnCount();

            //  ArrayList<Object[]> Registros = new ArrayList<>(); //registros
            while (rs.next()) {
                System.out.println("contador " + contColum);
                Object[] registro = new Object[contColum];
                for (int i = 0; i < contColum; i++) {
                    System.out.print(rs.getObject(i + 1) + " ");
                }
                System.out.println("");

            }

            //showObjQuery(Registros);
            con.close();
        } catch (SQLException err) {
            System.err.print(err);
        }

    }

    public void getCalificacionesv2() {
        Connection con = getConexion();
        try {
            String sql = "SELECT distinct A.matricula, B.Numero, C.puntuacion FROM alumno A, calificacion C, bloque B\n"
                    + "WHERE C.id_alumno = A.id AND C.id_bloque = B.id;";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            //getdatos
            ResultSetMetaData resultMD = rs.getMetaData();
            int contColum = resultMD.getColumnCount();
            while (rs.next()) {
                for (int i = 0; i < contColum; i++) {
                    System.out.print(rs.getObject(i + 1) + " ");
                }
                System.out.println("");
            }

        } catch (SQLException err) {
            System.err.print(err);
        } finally {
            try {
                con.close();
            } catch (SQLException err) {
                System.out.println(err);
            }
        }
    }

    public void showRow(Object[] registro) {
        for (Object item : registro) {
            System.out.print(item + " ");
        }
        System.out.println("");
    }

    public void showObjQuery(ArrayList<Object[]> registros) {

        for (Object[] row : registros) {
            for (Object item : row) {
                System.out.print(item + " ");
            }
            System.out.println("");
        }

    }

    public void addCalificacion(Object[] cali, ArrayList<Object[]> calificaciones) {
        //filas
        for (Object[] item : calificaciones) {
            //las matriculas son iguales
            System.out.println("comparacion");
            System.out.println(item[0] + "-" + cali[0]);
            if (item[0] == cali[0]) {
                //4
                System.out.println("Entro al if");
                item[4] = cali[2];
                item[5] = cali[3];
            } else {
                System.out.println("add new calificacion");
                calificaciones.add(cali);
            }
        }
    }

}
