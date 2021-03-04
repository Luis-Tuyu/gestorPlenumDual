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
            String sql = "SELECT A.id, CONCAT(A.nombre,\" \",A.apellido) \"nombre\" , B.numero \"BLOQUE\",B.nombre \"NombreBloque\", C.puntuacion FROM calificacion C, bloque B, alumno A\n"
                    + "WHERE A.id = C.id_alumno AND C.id_bloque = B.id;";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData resultMD = rs.getMetaData();
            int contColum = resultMD.getColumnCount();

            ArrayList<Object[]> Registros = new ArrayList<>(); //registros

            while (rs.next()) {
                System.out.println("contador " + contColum);
                Object[] registro = new Object[contColum];
                for (int i = 0; i < contColum; i++) {
                    System.out.print(rs.getObject(i+1));
                    //almacenar en un objeto

                    registro[i] = rs.getObject(i + 1);
                }
                Registros.add(registro);
            }
            ArrayList<alumno> alumnoCali = new ArrayList<>();
            alumnoCali = formatearCalificaciones(Registros);
            showCalificacionByAlumno(alumnoCali);
        } catch (SQLException err) {
            System.err.print(err);
        }

    }

    public void showObjQuery(ArrayList<Object[]> registros) {

        for (Object[] row : registros) {
            for (Object item : row) {
                System.out.print(item);
            }
            System.out.println("");
        }

    }

    public ArrayList<alumno> formatearCalificaciones(ArrayList<Object[]> registros) {
        ArrayList<alumno> alumnoCali = new ArrayList<>();
        for (Object[] row : registros) {

            alumno alum = new alumno();
            calificacion cali = new calificacion();
            for (int i = 0; i < row.length; i++) {
                switch (i) {
                    case 1:
                        alum.setId(Integer.parseInt((String) row[i]));
                        break;
                    case 2:
                        alum.setNombre((String) row[i]);
                        break;
                    case 3:
                        cali.setBloque((int) row[i]);
                        break;
                    case 4:
                        cali.setNombre((String) row[i]);
                        break;
                    case 5:
                        cali.setPuntuacion((int) row[i]);
                        break;
                }
            }
            alum.addCalificaciones(cali);

            //comprobar si se repite el alumno y agregar calificacion
            if (alumnoCali.contains(alum)) {
                addCalificacion(alumnoCali, cali, alum.getId());
            } else {
                //se agregar al arreglo
                alumnoCali.add(alum);
            }

        }

        return alumnoCali;
    }

    public void addCalificacion(ArrayList<alumno> alumnos, calificacion cali, int id) {
        for (alumno item : alumnos) {
            if (item.getId() == id) {
                item.addCalificaciones(cali);
                break;
            }
        }
    }

    //falta mostrar las calificiones por alumno
    public void showCalificacionByAlumno(ArrayList<alumno> alumnos) {
        for (alumno itemAlumn : alumnos) {
            System.out.print(itemAlumn.getId() + " ");
            System.out.print(itemAlumn.getNombre() + " ");
            for (calificacion itemCali : itemAlumn.getCalificaciones()) {
                System.out.print(itemCali.getBloque() + " " + itemCali.getPuntuacion());
            }
            System.out.println("");
        }
    }
}
