/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Eliza
 */
public class usuarioDTO  implements Serializable{
    private int codigo;
    private String nombre, apellido, correo, contrasena;
    private ArrayList<informacionDTO> lista_info;

    public usuarioDTO() {
        lista_info = new ArrayList<>();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<informacionDTO> getLista_info() {
        return lista_info;
    }

    public void setLista_info(ArrayList<informacionDTO> lista_info) {
        this.lista_info = lista_info;
    }
    
    
}
