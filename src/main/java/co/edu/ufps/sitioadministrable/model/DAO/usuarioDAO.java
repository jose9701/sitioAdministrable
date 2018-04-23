/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DAO;

import co.edu.ufps.sitioadministrable.model.DTO.usuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eliza
 */
public class usuarioDAO {

    private Connection con;

    public usuarioDTO iniciarSesion(usuarioDTO usuarioDTO) throws Exception {
        usuarioDTO usuario = null;
        con = conexion.generarConexion();
        if (con != null) {
            PreparedStatement stmt = con.prepareStatement("SELECT codigo, nombre, apellido, correo FROM usuario "
                    + "WHERE codigo = ? AND contrasena = ?");
            stmt.setInt(1, usuarioDTO.getCodigo());
            stmt.setString(2, usuarioDTO.getContrasena());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuario = new usuarioDTO();
                usuario.setCodigo(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setCorreo(rs.getString(4));
            }
        }
        return usuario;
    }

}
