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
public class tipoInfoDTO  implements Serializable{
    
    private int id;/*1:Noticia;2:novedad;3:evento*/
    private String descripcion;
    

    public tipoInfoDTO() {
    
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
    
}
