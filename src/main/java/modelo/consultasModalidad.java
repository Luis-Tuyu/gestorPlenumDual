/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * getLas modalidades
 */
public class consultasModalidad extends conexion {

    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<modalidad> getModalidad() {
        Connection con = null;
        ArrayList<modalidad> modalidades = new ArrayList<>();
        modalidad objMod = null;
        try {
            String sql = "SELECT * FROM modalidad";
            con = getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData resultMD = rs.getMetaData();
            int contColum = resultMD.getColumnCount();

            while (rs.next()) {
                objMod = new modalidad();
                for (int i = 0; i < contColum; i++) {
                    System.out.println(rs.getObject(i + 1));
                    switch (i) {
                        case 0:
                            objMod.setId((int) rs.getObject(i + 1));
                            break;
                        case 1:
                            objMod.setNombre((String) rs.getObject(i + 1));
                            break;
                    }
                }
                //add new arrayList
                modalidades.add(objMod);
            }

        } catch (SQLException err) {
            System.out.println(err);
        } finally {
            try {
                con.close();
            } catch (SQLException err) {
                System.out.println(err);

            }
        }
        
        return modalidades;
    }

}
