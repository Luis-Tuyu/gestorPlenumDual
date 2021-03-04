/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class consultasCiclosEscolares  extends conexion{
    
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<cicloEscolar> getCiclosEscolares(){
        Connection con = null;
        ArrayList<cicloEscolar> ciclosEscolares = new ArrayList<>();
        cicloEscolar objCicloEsc = null;
        try{
            con = getConexion();
            String sql ="SELECT * FROM cicloEscolar";
            ps = con.prepareStatement(sql);
            rs =ps.executeQuery();
            
            ResultSetMetaData resultMD  = rs.getMetaData();
            int contColum = resultMD.getColumnCount();
            while(rs.next()){
                objCicloEsc = new cicloEscolar();
                for(int i=0; i<contColum; i++){
                    switch (i) {
                        case 0:
                            objCicloEsc.setId((int) rs.getObject(i + 1));
                            break;
                        case 1:
                            objCicloEsc.setAño((int) rs.getObject(i + 1));
                            break;
                            
                        case 2:
                            objCicloEsc.setPeriodo((String) rs.getObject(i + 1));
                            break;
                    }
                }
                ciclosEscolares.add(objCicloEsc);
            }
            
        }catch(SQLException err){
            System.err.print(err);
        }finally {
            try {
                con.close();
            } catch (SQLException err) {
                System.out.println(err);

            }
        }
        return ciclosEscolares;
    }
    
}
