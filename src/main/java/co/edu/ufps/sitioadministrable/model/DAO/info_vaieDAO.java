/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DAO;

import co.edu.ufps.sitioadministrable.model.DTO.archivoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.info_vaieDTO;
import co.edu.ufps.sitioadministrable.model.DTO.informacionDTO;
import co.edu.ufps.sitioadministrable.model.DTO.menuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Eliza
 */
public class info_vaieDAO {
    
    private Connection con;
    
    //REGISTRAR
    
    public int registrarDinamico(info_vaieDTO dto) throws Exception {
        int exito = 0;
        con = conexion.generarConexion();
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("insert into info_vaie "
                    + "(descripcion, archivo, id_menu, url)"
                    + " values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dto.getDescripcion());
            stmt.setString(2, dto.getArchivo());
            stmt.setInt(3, dto.getMenu().getId());
            stmt.setString(4, dto.getUrl());
            try {
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                exito = rs.getInt(1);
                System.out.println("asd: "+exito);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                con.close();
                stmt.close();
            }
        }
        return exito;
    }
    
    public boolean registrarArchivo(archivoDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        dto = this.buscarTipoA(dto);
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO archivo"
                    + "(nombre,id_info, id_tipoA) values (?,?,?)");
            stmt.setString(1, dto.getNombre());
            stmt.setInt(2, dto.getId_info());
            stmt.setInt(3, dto.getId_tipoA().getId());
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
    
    public int agregarMenu(menuDTO dto) throws Exception {
        int exito = 0;
        con = conexion.generarConexion();
        if(dto.getTiene_submenu()==1){
            dto.setUrl("Menu");
        }else{
            if(dto.getId_menu()==0){
                dto.setUrl("Menu");
            }
            dto.setUrl("Informacion");
        }
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO MENU"
                    + "(nombre, url,id_menu, tiene_submenu) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dto.getNombre());
            stmt.setString(2,dto.getUrl());
            stmt.setInt(3, dto.getId_menu());
            stmt.setInt(4, dto.getTiene_submenu());
            try {
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                exito=rs.getInt(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                con.close();
                stmt.close();
            }
        }
        return exito;
    }
    
     public int agregarSubMenu(menuDTO dto) throws Exception {
        int exito = 0;
        con = conexion.generarConexion();
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO MENU"
                    + "(nombre, url,id_menu) values (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dto.getNombre());
            stmt.setString(2,dto.getUrl());
            stmt.setInt(3, dto.getId_menu());
            try {
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                exito=rs.getInt(1);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                con.close();
                stmt.close();
            }
        }
        return exito;
    }
     
    
    //ACTUALIZAR
    
    public boolean actualizarMenu(menuDTO dto) throws Exception{
        boolean exito = false;
        con = conexion.generarConexion();
        if(dto.getTiene_submenu()==1){
            dto.setUrl("Menu");
        }else{
            dto.setUrl("Informacion");
        }
        PreparedStatement stmt;
        try {
            String update = "UPDATE menu SET nombre = ? , url = ? , id_menu = ? ,"
                    + " tiene_submenu = ? WHERE id = ?";
            stmt = con.prepareStatement(update);
            stmt.setString(1, dto.getNombre());
            stmt.setString(2, dto.getUrl());
            stmt.setInt(3, dto.getId_menu());
            stmt.setInt(4, dto.getTiene_submenu());
            stmt.setInt(5, dto.getId());
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
    
    public boolean actualizarSubmenu(menuDTO dto) throws Exception{
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE menu SET nombre = ?, id_menu=? WHERE id = ?";
            stmt = con.prepareStatement(update);
            stmt.setString(1, dto.getNombre());
            stmt.setInt(2, dto.getId_menu());
            stmt.setInt(3, dto.getId());
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
    
    
    public boolean actualizarArchivo(archivoDTO dto) throws Exception{
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE archivo set nombre = ? WHERE id_info = ? AND id=?";
            stmt = con.prepareStatement(update);
            stmt.setString(1, dto.getNombre());
            stmt.setInt(2, dto.getId_info());
            stmt.setInt(3, dto.getId());
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
    
    //ACTUALIZAR
    
    public boolean actualizarDinamico(info_vaieDTO dto) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String update = "UPDATE info_vaie set descripcion = ?, archivo = ?, url= ? WHERE id_menu = ?";
            stmt = con.prepareStatement(update);
            stmt.setString(1, dto.getDescripcion());
            stmt.setString(2, dto.getArchivo());
            stmt.setString(3, dto.getUrl());
            stmt.setInt(4, dto.getMenu().getId() );
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
    
    //CONSULTAR
        
    public archivoDTO buscarTipoA(archivoDTO dto) throws Exception{
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT id FROM tipo_archivo WHERE ext = ?");
        stmt.setString(1, dto.getId_tipoA().getExt());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            dto.getId_tipoA().setId(1);
        }
        return dto;
    }
    
    public ArrayList<menuDTO> consultarMenu() throws Exception {
        ArrayList<menuDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT id, nombre, url, id_menu, tiene_submenu from menu");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            menuDTO rel = new menuDTO();
            rel.setId(rs.getInt(1));
            rel.setNombre(rs.getString(2));
            rel.setUrl(rs.getString(3));
            rel.setId_menu(rs.getInt(4));
            rel.setTiene_submenu(rs.getInt(5));
            list.add(rel);
        }
        return list;
    }
    
    public ArrayList<menuDTO> consultarSubmenu(menuDTO dto) throws Exception{
        ArrayList<menuDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT id, nombre, url "
                + "FROM menu WHERE id_menu = ?");
        stmt.setInt(1, dto.getId_menu());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            menuDTO rel = new menuDTO();
            rel.setId(rs.getInt(1));
            rel.setNombre(rs.getString(2));
            rel.setUrl(rs.getString(3));
            list.add(rel);
        }
        return list;
    }
    
    public int buscarTipoArchivo(String i) throws Exception {
        int x = 0;
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT id FROM tipo_archivo WHERE ext=?");
        stmt.setString(1, i);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            x = (rs.getInt(1));
        }
        return x;
    }
    
    public info_vaieDTO consultarInfoVaie(int id) throws Exception {
        info_vaieDTO dto = null;
        con = conexion.generarConexion();
        PreparedStatement stmt = con.prepareStatement("SELECT descripcion, "
                + "id,archivo,url FROM info_vaie WHERE id_menu= ?");
        stmt.setInt(1, id);
        ResultSet rs= stmt.executeQuery();
        while(rs.next()){
            dto = new info_vaieDTO();
            dto.setDescripcion(rs.getString(1));
            dto.setId(rs.getInt(2));
            dto.setArchivo(rs.getString(3));
            dto.setUrl(rs.getString(4));
        }
        return dto;
    }
    
    public ArrayList<archivoDTO> consultarArchivosInfo(int id) throws Exception{
        ArrayList<archivoDTO> list = new ArrayList<>();
        con = conexion.generarConexion();
        return null;
    }
    
    //ELIMINAR
    
    public boolean eliminarMenu(menuDTO dto) throws Exception {
        boolean exito = false;
        this.eliminarSubmenu(dto.getId());
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM menu WHERE id = ?";
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
    
    public boolean eliminarSubmenu(int id) throws Exception {
        boolean exito = false;
        con = conexion.generarConexion();
        PreparedStatement stmt;
        try {
            String delete = "DELETE FROM menu WHERE id_menu = ?";
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
    
}
