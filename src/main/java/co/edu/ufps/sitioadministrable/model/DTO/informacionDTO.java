/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model.DTO;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Eliza
 */
public class informacionDTO implements Serializable {
    
    private int id;
    private String titulo, descripcion, archivo,fPublicacion;
    private Date f_publicacion;
    private tipoInfoDTO tipo;
    private tipoArchivoDTO tipo_a;
    private usuarioDTO usuario;
    private ArrayList<eventoDTO> lista_evento;

    public informacionDTO() {
        tipo_a = new tipoArchivoDTO();
        tipo = new tipoInfoDTO();
        usuario = new usuarioDTO();
        lista_evento = new ArrayList<>();
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getF_publicacion() {
        return f_publicacion;
    }

    public void setF_publicacion(Date f_publicacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        fPublicacion = formatter.format(f_publicacion);
        this.f_publicacion = f_publicacion;
    }

    public tipoInfoDTO getTipo() {
        return tipo;
    }

    public void setTipo(tipoInfoDTO tipo) {
        this.tipo = tipo;
    }

    public usuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ArrayList<eventoDTO> getLista_evento() {
        return lista_evento;
    }

    public void setLista_evento(ArrayList<eventoDTO> lista_evento) {
        this.lista_evento = lista_evento;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public tipoArchivoDTO getTipo_a() {
        return tipo_a;
    }

    public void setTipo_a(tipoArchivoDTO tipo_a) {
        this.tipo_a = tipo_a;
    }
   
    
    
}
