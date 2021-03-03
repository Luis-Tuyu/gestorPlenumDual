package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*relacionado con todas las oepraciones con escuela*/
public class consultasEscuela extends conexion {

    PreparedStatement ps;
    ResultSet rs;

    public void getEscuelas() {
        Connection con = getConexion();
        try {
            String sql = "SELECT * FROM escuela";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData resultQuery = rs.getMetaData();
            int cantColumn = resultQuery.getColumnCount();
            //recorremos todos los arreglos
            while (rs.next()) {
                for (int i = 0; i < cantColumn; i++) {
                    System.out.println(rs.getObject(i+1));
                    System.out.println("");
                }
            }
            con.close();
        } catch (SQLException err) {
            System.out.println(err);
        }
    }

}
