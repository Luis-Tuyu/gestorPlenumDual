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
        String sql = "INSERT INTO alumno (matricula, nombre, apellido, fechaNacimiento, celular, domicilio,email, id_escuela, id_modalidad, id_cicloEscolar)"
                + "values (?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, alum.getMatricula());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellido());
            ps.setDate(4, Date.valueOf(alum.getFechaNacimiento()));
            ps.setString(5, alum.getTelefono());
            ps.setString(6, alum.getDomicilio());
            ps.setString(7, alum.getCorreo());
            ps.setInt(8, alum.getId_escuela());
            ps.setInt(9, alum.getId_modalidad());
            ps.setInt(10, alum.getId_ciclo());

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
        String sql = "UPDATE alumno SET matricula=?, nombre=?, apellido=?, fechaNacimiento=?, celular=?, domicilio=?, email=?, id_escuela=? ,id_modalidad=?, id_cicloEscolar=? "
                + "WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, alum.getMatricula());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellido());
            ps.setDate(4, Date.valueOf(alum.getFechaNacimiento()));
            ps.setString(5, alum.getTelefono());
            ps.setString(6, alum.getDomicilio());
            ps.setString(7, alum.getCorreo());
            ps.setInt(8, alum.getId_escuela());
            ps.setInt(9, alum.getId_modalidad());
            ps.setInt(10, alum.getId_ciclo());
            ps.setInt(11, alum.getId());

            ps.execute();
            //JOptionPane.showMessageDialog(null, "Usuario actualizado con exito");
            return true;
        } catch (SQLException err) {
            System.out.println(err);
            //JOptionPane.showMessageDialog(null, "Error al actualizar alumno");
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

    public alumno findAlumno(String matricula) {
        alumno objAlum = new alumno();
        try {
            Connection con = getConexion();
            String sql = "SELECT  A.id, A.nombre, A.apellido,A.fechaNacimiento,A.celular, A.Domicilio,  A.email,E.id \"escuelaId\", M.id \"modId\", CE.id \"cicloId\" FROM alumno A, escuela E, modalidad M, cicloEscolar CE\n"
                    + "WHERE A.matricula=? AND A.id_escuela = E.id AND A.id_modalidad = M.id AND A.id_cicloEscolar = CE.id ";

            ps = con.prepareStatement(sql);
            ps.setString(1,matricula);
            rs = ps.executeQuery();
            
            if(rs.next()){
                objAlum.setId(Integer.parseInt(rs.getString("id")));
                objAlum.setNombre(rs.getString("nombre"));
                objAlum.setApellido(rs.getString("apellido"));
                objAlum.setFechaNacimiento(rs.getString("fechaNacimiento"));
                objAlum.setTelefono(rs.getString("celular"));
                objAlum.setCorreo(rs.getString("email"));
                objAlum.setDomicilio(rs.getString("Domicilio"));
                objAlum.setId_escuela(Integer.parseInt(rs.getString("escuelaId")));
                objAlum.setId_modalidad(Integer.parseInt(rs.getString("modId")));
                objAlum.setId_ciclo(Integer.parseInt(rs.getString("cicloId")));
            }
            
            
        }catch (SQLException err) {
            System.err.print(err);
        }   
        
       return objAlum;   
    }

}
