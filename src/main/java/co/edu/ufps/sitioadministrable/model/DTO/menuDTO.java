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
public class menuDTO  implements Serializable{
    
    private int id, id_menu,tiene_submenu;
    private String nombre,url;

    public menuDTO() {
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

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTiene_submenu() {
        return tiene_submenu;
    }

    public void setTiene_submenu(int tiene_submenu) {
        this.tiene_submenu = tiene_submenu;
    }

    public int tiene_submenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
