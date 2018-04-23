/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DTO;

/**
 *
 * @author Eliza
 */
public class archivoDTO {
    
    private int id, id_info;
    private String nombre;
    private tipoArchivoDTO id_tipoA;

    public archivoDTO() {
        id_tipoA = new tipoArchivoDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_info() {
        return id_info;
    }

    public void setId_info(int id_info) {
        this.id_info = id_info;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tipoArchivoDTO getId_tipoA() {
        return id_tipoA;
    }

    public void setId_tipoA(tipoArchivoDTO id_tipoA) {
        this.id_tipoA = id_tipoA;
    }
    
    
    
}
