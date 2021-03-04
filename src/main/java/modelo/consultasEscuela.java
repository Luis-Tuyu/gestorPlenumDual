package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/*relacionado con todas las oepraciones con escuela*/
public class consultasEscuela extends conexion {

    PreparedStatement ps;
    ResultSet rs;
    

    public ArrayList<escuela> getEscuelas() {
        Connection con = getConexion();
        ArrayList<escuela> escuelas = new ArrayList<>();
        escuela objEsc = null;
        try {
            String sql = "SELECT * FROM escuela";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData resultQuery = rs.getMetaData();
            int cantColumn = resultQuery.getColumnCount();
            //recorremos todos los arreglos
            while (rs.next()) {
                objEsc = new escuela();
                for (int i = 0; i < cantColumn; i++) {
                    System.out.println(rs.getObject(i+1));
                    System.out.println("");
                    switch(i){
                        case 0:
                            objEsc.setId((int)rs.getObject(i+1));
                            break;
                        case 1:
                            objEsc.setNombre((String) rs.getObject(i+1));
                            break;
                        case 2:
                            objEsc.setDomicilio((String) rs.getObject(i+1));
                            break;
                            
                    }
                }//end for
                escuelas.add(objEsc);
            }
            
        } catch (SQLException err) {
            System.out.println(err);
        }finally{
            try{
                con.close();
            }catch(SQLException err){
                 System.out.println(err);
            }
        }
        
        return escuelas;
    }

}
