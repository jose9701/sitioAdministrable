/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Eliza
 */
public class eventoDTO implements Serializable{
    
    private int id;
    private Date fecha_ini, fecha_fin,f;
    private String lugar,fIni,fFin;
    private informacionDTO id_info;
    private ArrayList<programacionDTO> lista_evento;

    public eventoDTO() {
        id_info = new informacionDTO();
        lista_evento = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        fIni = formatter.format(fecha_ini);
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        fFin = formatter.format(fecha_ini);
        this.fecha_fin = fecha_fin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public informacionDTO getId_info() {
        return id_info;
    }

    public void setId_info(informacionDTO id_info) {
        this.id_info = id_info;
    }

    public ArrayList<programacionDTO> getLista_evento() {
        return lista_evento;
    }

    public void setLista_evento(ArrayList<programacionDTO> lista_evento) {
        this.lista_evento = lista_evento;
    }
    
    
    
}
