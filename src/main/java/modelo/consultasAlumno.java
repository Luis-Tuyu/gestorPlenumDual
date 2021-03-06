package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        String sql = "UPDATE alumno SET matricula=?, nombre=?, apellido=?, fechaNacimiento=?, celular=?, domicilio=?, id_escuela=? ,id_modalidad=?, id_cicloEscolar=? "
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
        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(null, "Error al actualizar alumno");
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

    public void getAlumnos() {

        try {
            Connection con = getConexion();
            String sql = "SELECT  A.nombre, A.apellido, E.nombre \"escuela\", M.nombre \"modalidad\", concat(CE.año ,\" \",CE.periodo) as \"periodo\" FROM alumno A, escuela E, modalidad M, cicloEscolar CE\n"
                    + "WHERE A.id_escuela = E.id AND A.id_modalidad = M.id AND A.id_cicloEscolar = CE.id ";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData resultMD = rs.getMetaData();
            int contColum = resultMD.getColumnCount();

            while (rs.next()) {
                for (int i = 0; i < contColum; i++) {
                    System.out.println(rs.getObject(i + 1));
                }
            }

        } catch (SQLException err) {
            System.err.print(err);
        }
    }

    public boolean deleteAlumno(alumno alum) {
        Connection con = getConexion();
        String sql = "DELETE FROM  alumno WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, alum.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuario eliminado con exito");
            return true;
        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(null, "Error al eliminar alumno");
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

    public void findAlumno(alumno alum) {
        try {
            Connection con = getConexion();
            String sql = "SELECT  A.nombre, A.apellido, E.nombre \"escuela\", M.nombre \"modalidad\", concat(CE.año ,\" \",CE.periodo) as \"periodo\" FROM alumno A, escuela E, modalidad M, cicloEscolar CE\n"
                    + "WHERE A.matricula=? AND A.id_escuela = E.id AND A.id_modalidad = M.id AND A.id_cicloEscolar = CE.id ";

            ps = con.prepareStatement(sql);
            ps.setString(1,alum.getMatricula());
            rs = ps.executeQuery();
            
            if(rs.next()){
                System.out.println(rs.getString("nombre"));
            }
            
            
        }catch (SQLException err) {
            System.err.print(err);
        }   
        
          
    }

}
