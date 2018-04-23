/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Eliza
 */
public class programacionDTO  implements Serializable{
    
    private int id;
    private Date fecha_hora;
    private String nombre, descripcion, responsable, lugar,fHora;
    private eventoDTO evento;

    public programacionDTO() {
        evento = new eventoDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        fHora = formatter.format(fecha_hora);
        this.fecha_hora = fecha_hora;
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public eventoDTO getEvento() {
        return evento;
    }

    public void setEvento(eventoDTO evento) {
        this.evento = evento;
    }
    
    
    
}
