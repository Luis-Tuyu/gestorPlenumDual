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

/**
 * getLas modalidades
 */
public class consultasModalidad extends conexion{

    PreparedStatement ps;
    ResultSet rs;
    
    public void getModalidad(){
        Connection con = null;
        try{
            String sql =  "SELECT * FROM modalidad";
            con = getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData resultMD =  rs.getMetaData();
            int contColum = resultMD.getColumnCount();
            
            while(rs.next()){
                for(int i=0; i<contColum; i++){
                    System.out.println(rs.getObject(i+1));
                }
            }
            con.close();
        }catch(SQLException err){
            System.out.println(err);
    }
        
    }

}
