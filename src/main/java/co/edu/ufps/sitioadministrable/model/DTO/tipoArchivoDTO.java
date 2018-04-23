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
public class tipoArchivoDTO  implements Serializable{
    
    private int id;/*1:Imágen;2:pdf*/
    private String ext;
    
    public tipoArchivoDTO() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

}
