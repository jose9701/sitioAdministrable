/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DAO;

import co.edu.ufps.sitioadministrable.model.DTO.eventoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.informacionDTO;
import co.edu.ufps.sitioadministrable.model.DTO.programacionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Eliza
 */
public class informacionDAO {

    //instancia de la conexión
    private Connection con;

    //MÉTODOS DE REGISTRO
    public int registrarInformacion(informacionDTO dto) throws Exception {
        int exito = 0;
        con = conexion.generarConexion();
        dto = this.buscarTipoArchivo(dto);
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("insert into informacion "
                    + "(titulo, descripcion, tipo, cod_usuario, archivo, tipoArchivo)"
                    + " values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dto.getTitulo());
            stmt.setString(2, dto.getDescripcion());
            stmt.setInt(3, dto.getTipo().getId());
            stmt.setInt(4, dto.getUsuario().getCodigo());
            stmt.setString(5, dto.getArchivo());
            stmt.setInt(6, dto.getTipo_a().getId());
            try {
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                exito = rs.getInt(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                con.close();
                stmt.close();
            }
        }
        return exito;
    }

    public int registrarEvento(eventoDTO dto) throws Exception {
        dto.getId_info().setId(this.registrarInformacion(dto.getId_info()));
        int exito = 0;
        con = conexion.generarConexion();
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("insert into evento "
                    + "(fecha_ini, fecha_fin, lugar, id_info)"
                    + " values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            Date ini = new Date(dto.getFecha_ini().getTime());
            Date fin = new Date(dto.getFecha_fin().getTime());
            stmt.setDate(1, ini);
            stmt.setDate(2, fin);
            stmt.setString(3, dto.getLugar());
            stmt.setInt(4, dto.getId_info().getId());
            try {
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                exito = rs.getInt(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                con.close();
                stmt.close();
            }
        }
        return exito;
    }

    public boolean registrarProgramacion(programacionDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO programacion"
                    + "(nombre, descripcion, responsable, lugar, id_evento, fecha_hora)"
                    + "values (?,?,?,?,?,?)");
            stmt.setString(1, dto.getNombre());
            stmt.setString(2, dto.getDescripcion());
            stmt.setString(3, dto.getResponsable());
            stmt.setString(4, dto.getLugar());
            stmt.setInt(5, dto.getEvento().getId());
            Date fech = new Date(dto.getFecha_hora().getTime());
            stmt.setDate(6, fech);
            try {
                exito = stmt.executeUpdate() > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                con.close();
                stmt.close();
            }
        }
        return exito;
    }
    
    
    //MÉTODOS PARA LISTAR
    public ArrayList<informacionDTO> listarNoticias() throws Exception {
        ArrayList<informacionDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT i.id, i.titulo, "
                + "i.f_publicacion,i.descripcion,i.archivo FROM informacion i, tipo_info t WHERE i.tipo = t.id "
                + "AND t.nombre = 'Noticia' ORDER BY f_publicacion DESC");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            informacionDTO dto = new informacionDTO();
            dto.setId(rs.getInt(1));
            dto.setTitulo(rs.getString(2));
            dto.setF_publicacion(rs.getDate(3));
            dto.setDescripcion(rs.getString(4));
            dto.setArchivo(rs.getString(5));
            list.add(dto);
        }
        return list;
    }

    public ArrayList<informacionDTO> listarNovedades() throws Exception {
        ArrayList<informacionDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT i.id, i.titulo, "
                + "i.f_publicacion FROM informacion i, tipo_info t WHERE i.tipo = t.id "
                + "AND t.nombre = 'Novedad' ORDER BY f_publicacion DESC");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            informacionDTO dto = new informacionDTO();
            dto.setId(rs.getInt(1));
            dto.setTitulo(rs.getString(2));
            dto.setF_publicacion(rs.getDate(3));
            list.add(dto);
        }
        return list;
    }

    public ArrayList<informacionDTO> listarEventos() throws Exception {
        ArrayList<informacionDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT i.id, i.titulo, "
                + "i.f_publicacion FROM informacion i, tipo_info t WHERE i.tipo = t.id "
                + "AND t.nombre = 'Evento' ORDER BY f_publicacion DESC");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            informacionDTO dto = new informacionDTO();
            dto.setId(rs.getInt(1));
            dto.setTitulo(rs.getString(2));
            dto.setF_publicacion(rs.getDate(3));
            list.add(dto);
        }
        return list;
    }

    public eventoDTO listarProgramacion(int id) throws Exception {
        ArrayList<programacionDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT p.fecha_hora, "
                + "p.nombre, p.descripcion, p.responsable, p.lugar, p.id "
                + "FROM programacion p, informacion i, evento e WHERE "
                + "p.id_evento = e.id AND i.id = e.id_info AND i.id = ? ORDER BY fecha_hora DESC");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            programacionDTO dto = new programacionDTO();
            dto.setFecha_hora(rs.getDate(1));
            dto.setNombre(rs.getString(2));
            dto.setDescripcion(rs.getString(3));
            dto.setResponsable(rs.getString(4));
            dto.setLugar(rs.getString(5));
            dto.setId(rs.getInt(6));
            list.add(dto);
        }
        eventoDTO dto = consultarEvento(id);
        dto.setLista_evento(list);
        return dto;
    }

    //CONSULTAR
    public informacionDTO buscarTipoArchivo(informacionDTO dto) throws Exception {
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT id FROM tipo_archivo WHERE ext= ?");
        stmt.setString(1, dto.getTipo_a().getExt().toLowerCase());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            dto.getTipo_a().setId(rs.getInt(1));
        }
        return dto;
    }

    public informacionDTO consultarInformacion(int id) throws Exception {
        informacionDTO dto = null;
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT titulo, descripcion, "
                + "archivo, f_publicacion FROM informacion WHERE id=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            dto = new informacionDTO();
            dto.setTitulo(rs.getString(1));
            dto.setDescripcion(rs.getString(2));
            dto.setArchivo(rs.getString(3));
            dto.setF_publicacion(rs.getDate(4));
            dto.setId(id);
        }
        return dto;
    }
    

    public eventoDTO consultarEvento(int id) throws Exception {
        eventoDTO dto = null;
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT fecha_ini, fecha_fin, "
                + "lugar, id FROM evento WHERE id_info = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            dto = new eventoDTO();
            dto.setFecha_ini(rs.getDate(1));
            dto.setFecha_fin(rs.getDate(2));
            dto.setLugar(rs.getString(3));
            dto.setId(rs.getInt(4));
            dto.setId_info(consultarInformacion(id));
        }
        return dto;
    }
    
    //EDITAR
    public boolean editarInformacion(informacionDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE informacion set descripcion = ?, archivo = ?, titulo = ? WHERE id = ?";
            stmt = con.prepareStatement(update);
            stmt.setString(1, dto.getDescripcion());
            stmt.setString(2, dto.getArchivo());
            stmt.setString(3, dto.getTitulo());
            stmt.setInt(4, dto.getId());

            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }

    public boolean editarEvento(eventoDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE evento set fecha_ini = ?, fecha_fin = ?, lugar = ? WHERE id = ?";
            stmt = con.prepareStatement(update);
            Date ini = new Date(dto.getFecha_ini().getTime());
            Date fin = new Date(dto.getFecha_fin().getTime());
            stmt.setDate(1, ini);
            stmt.setDate(2, fin);
            stmt.setString(3, dto.getLugar());
            stmt.setInt(4, dto.getId());
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }

    public boolean editarProgramacion(programacionDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE programacion set fecha_hora = ?, nombre = ?, "
                    + "descripcion = ?, responsable = ?, lugar = ? WHERE id = ?";
            stmt = con.prepareStatement(update);
            Date fecha = new Date(dto.getFecha_hora().getTime());
            stmt.setDate(1, fecha);
            stmt.setString(2, dto.getNombre());
            stmt.setString(3, dto.getDescripcion());
            stmt.setString(4, dto.getResponsable());
            stmt.setString(5, dto.getLugar());
            stmt.setInt(6, dto.getId());
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }

    //ELIMINAR
    public boolean eliminarInformacion(informacionDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM informacion WHERE id = ?";
            stmt = con.prepareStatement(delete);
            stmt.setInt(1, dto.getId());
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }

    public boolean eliminarEvento(eventoDTO dto) throws Exception {
        boolean exito = false;
        this.eliminarProgramacionEvento(dto.getId());
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM evento WHERE id = ?";
            stmt = con.prepareStatement(delete);
            stmt.setInt(1, dto.getId());
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.eliminarInformacion(dto.getId_info());        
        return exito;
    }

    private boolean eliminarProgramacionEvento(int id) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM programacion WHERE id_evento = ?";
            stmt = con.prepareStatement(delete);
            stmt.setInt(1, id);
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return exito;

    }

    public boolean eliminarProgramacion(programacionDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM programacion WHERE id = ?";
            stmt = con.prepareStatement(delete);
            stmt.setInt(1, dto.getId());
            int total = stmt.executeUpdate();
            if (total > 0) {
                exito = true;
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return exito;
    }

}
