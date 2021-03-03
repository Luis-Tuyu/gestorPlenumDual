package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class consultasAlumno extends conexion {

    PreparedStatement ps = null;
    ResultSet rs;

    public boolean createAlumno(alumno alum) {
        Connection con = getConexion();
        String sql = "INSERT INTO alumno (matricula, nombre, apellido, fechaNacimiento, celular, domicilio, id_escuela, id_modalidad, id_cicloEscolar)"
                + "values (?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, alum.getMatricula());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellido());
            ps.setDate(4, Date.valueOf(alum.getFechaNacimiento()));
            ps.setString(5, alum.getTelefono());
            ps.setString(6, alum.getDomicilio());
            ps.setInt(7, alum.getId_escuela());
            ps.setInt(8, alum.getId_modalidad());
            ps.setInt(9, alum.getId_ciclo());

            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuario creado con exito");
            return true;

        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(null, "Error al crear alumno");
            return false;
        } finally {
            //cerramos la conexion de BD
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public boolean updateAlumno(alumno alum) {
        Connection con = getConexion();
        String sql= "UPDATE alumno SET matricula=?, nombre=?, apellido=?, fechaNacimiento=?, celular=?, domicilio=?, id_escuela=? ,id_modalidad=?, id_cicloEscolar=? "
                + "WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, alum.getMatricula());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellido());
            ps.setDate(4, Date.valueOf(alum.getFechaNacimiento()));
            ps.setString(5, alum.getTelefono());
            ps.setString(6, alum.getDomicilio());
            ps.setInt(7, alum.getId_escuela());
            ps.setInt(8, alum.getId_modalidad());
            ps.setInt(9, alum.getId_ciclo());
            ps.setInt(10, alum.getId());
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuario actualizado con exito");
            return true;
        }catch(SQLException err){
            System.out.println(err);
            JOptionPane.showMessageDialog(null, "Error al actualizar alumno");
            return false;
        }finally {
            //cerramos la conexion de BD
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

}
