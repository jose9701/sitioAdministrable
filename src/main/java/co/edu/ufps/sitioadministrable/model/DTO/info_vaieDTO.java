/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DTO;

import java.io.Serializable;

/**
 *
 * @author Eliza
 */
public class info_vaieDTO implements Serializable{
    
    private int id;
    private String nombre, descripcion, archivo, url;
    private menuDTO menu;

    public info_vaieDTO() {
        menu = new menuDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public menuDTO getMenu() {
        return menu;
    }

    public void setMenu(menuDTO menu) {
        this.menu = menu;
    }
    
    
    
    
}
