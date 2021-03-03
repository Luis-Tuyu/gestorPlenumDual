
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
Create conexion con la BD
 */
public class conexion {
   private final String base = "plenumsoft";
   private final String user = "root";
   private final String password = "tec17$22E";
   private final String url = "jdbc:mysql://localhost:3306/"+base;
   private Connection con = null;
   
   public Connection getConexion(){
       try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con  = DriverManager.getConnection(this.url, this.user, this.password);
       }catch(SQLException err){
           System.out.println(err);
       } catch (ClassNotFoundException ex) { 
           Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return con;
   }
}
