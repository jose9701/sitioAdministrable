/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.model;

import co.edu.ufps.sitioadministrable.model.DAO.info_vaieDAO;
import co.edu.ufps.sitioadministrable.model.DAO.informacionDAO;
import co.edu.ufps.sitioadministrable.model.DAO.usuarioDAO;
import co.edu.ufps.sitioadministrable.model.DTO.archivoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.eventoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.info_vaieDTO;
import co.edu.ufps.sitioadministrable.model.DTO.informacionDTO;
import co.edu.ufps.sitioadministrable.model.DTO.menuDTO;
import co.edu.ufps.sitioadministrable.model.DTO.programacionDTO;
import co.edu.ufps.sitioadministrable.model.DTO.tipoInfoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.usuarioDTO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Eliza
 */
public class Facade {

    public usuarioDTO iniciarSesion(String contrasena, int codigo) throws Exception {
        usuarioDTO usuarioDTO = new usuarioDTO();
        usuarioDTO.setContrasena(contrasena);
        usuarioDTO.setCodigo(codigo);
        usuarioDAO usuarioDAO = new usuarioDAO();
        return usuarioDAO.iniciarSesion(usuarioDTO);
    }

    //MÉTODOS DE REGISTRAR
    public informacionDTO registrarInformacion(String titulo, String descripcion,
            usuarioDTO usuario, int id_tipo, String archivo, String ext) throws Exception {
        tipoInfoDTO t = new tipoInfoDTO();
        t.setId(id_tipo);
        informacionDTO i = new informacionDTO();
        i.setTitulo(titulo);
        i.setDescripcion(descripcion);
        i.setTipo(t);
        i.setUsuario(usuario);
        i.setArchivo(archivo);
        i.getTipo_a().setExt(ext);
        return i;
    }

    public int registrarNoticia(String titulo, String descripcion, usuarioDTO usuario,
            String archivo, String lugar, String ext) throws Exception {
        informacionDTO i = this.registrarInformacion(titulo, descripcion, usuario, 1, archivo, ext);
        informacionDAO dao = new informacionDAO();
        return dao.registrarInformacion(i);
    }

    public int registrarNovedad(String titulo, String descripcion, usuarioDTO usuario,
            String archivo, String lugar, String ext) throws Exception {
        informacionDTO i = this.registrarInformacion(titulo, descripcion, usuario, 2, archivo, ext);
        informacionDAO dao = new informacionDAO();
        return dao.registrarInformacion(i);
    }

    public int registrarEvento(Date fecha_ini, Date fecha_fin, String lugar,
            String titulo, String descripcion, usuarioDTO usuario, String archivo, String ext) throws Exception {
        eventoDTO e = new eventoDTO();
        e.setFecha_ini(fecha_ini);
        e.setFecha_fin(fecha_fin);
        e.setLugar(lugar);
        informacionDTO i = this.registrarInformacion(titulo, descripcion, usuario, 3, archivo, ext);
        e.setId_info(i);
        informacionDAO dao = new informacionDAO();
        return dao.registrarEvento(e);
    }

    public boolean registrarProgramacion(Date fecha_hora, String nombre, String descripcion,
            String responsable, String lugar, int id_info, int id_p) throws Exception {
        programacionDTO p = new programacionDTO();
        p.setFecha_hora(fecha_hora);
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setResponsable(responsable);
        p.setLugar(lugar);
        p.getEvento().setId(id_info);
        if (id_p != 0) {
            p.setId(id_p);
        }
        informacionDAO dao = new informacionDAO();
        boolean v;
        if (id_p == 0) {
            System.out.println(p.getEvento().getId());
            v = dao.registrarProgramacion(p);
        } else {
            v = dao.editarProgramacion(p);
        }
        return v;
    }

    public boolean registrarArchivo(int id, String nombre, int id_info, String ext) throws Exception {
        archivoDTO dto = new archivoDTO();
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setId_info(id_info);
        dto.getId_tipoA().setExt(ext);
        info_vaieDAO dao = new info_vaieDAO();
        boolean v = dao.registrarArchivo(dto);
        if (!v) {
            v = dao.actualizarArchivo(dto);
        }
        return v;
    }

    public int agregarMenu(String nombre, int id_menu, int tiene_submenu) throws Exception {
        menuDTO dto = new menuDTO();
        dto.setNombre(nombre);
        dto.setId_menu(id_menu);
        dto.setTiene_submenu(tiene_submenu);
        info_vaieDAO dao = new info_vaieDAO();
        return dao.agregarMenu(dto);
    }

    public int agregarSubMenu(String nombre, int id_menu) throws Exception {
        menuDTO dto = new menuDTO();
        dto.setNombre(nombre);
        dto.setId_menu(id_menu);
        dto.setUrl("Informacion");
        info_vaieDAO dao = new info_vaieDAO();
        return dao.agregarSubMenu(dto);
    }

    //MÉTODOS PARA LISTAR
    public ArrayList<informacionDTO> listarNoticias() throws Exception {
        informacionDAO dao = new informacionDAO();
        return dao.listarNoticias();
    }

    public ArrayList<informacionDTO> listarNovedades() throws Exception {
        informacionDAO dao = new informacionDAO();
        return dao.listarNovedades();
    }

    public ArrayList<informacionDTO> listarEventos() throws Exception {
        informacionDAO dao = new informacionDAO();
        return dao.listarEventos();
    }

    public eventoDTO listarProgramacion(int id) throws Exception {
        informacionDAO dao = new informacionDAO();
        return dao.listarProgramacion(id);
    }

    //MÉTODOS PARA ACTUALIZAR
    public boolean actualizarMenu(String nombre, int id_menu, int tiene_submenu) throws Exception {
        menuDTO dto = new menuDTO();
        dto.setNombre(nombre);
        dto.setId(id_menu);
        dto.setTiene_submenu(tiene_submenu);
        info_vaieDAO dao = new info_vaieDAO();
        return dao.actualizarMenu(dto);
    }

    public boolean actualizarSubmenu(String nombre, int id_menu, int id) throws Exception {
        menuDTO dto = new menuDTO();
        dto.setNombre(nombre);
        dto.setId_menu(id_menu);
        dto.setId(id);
        dto.setUrl("Informacion");
        info_vaieDAO dao = new info_vaieDAO();
        boolean v = dao.actualizarSubmenu(dto);
        System.out.println(v);
        if (!v) {
            v = dao.agregarSubMenu(dto) > 0;
        }
        return v;
    }

//    public boolean actualizarSubmenu(String nombre, int id)
    public boolean actualizarDinamico(String descripcion, String archivo, String nombre, int id, String url)
            throws Exception {
        info_vaieDTO dto = new info_vaieDTO();
        dto.setDescripcion(descripcion);
        dto.setNombre(nombre);
        dto.getMenu().setId(id);
        dto.setArchivo(archivo);
        dto.setUrl(url);
        info_vaieDAO dao = new info_vaieDAO();
        boolean v = dao.registrarDinamico(dto) > 0;
        if (!v) {
            v = dao.actualizarDinamico(dto);
        }
        return v;
    }

    public boolean editarInformacion(String descripcion, String archivo, String titulo, int id)
            throws Exception {
        informacionDTO dto = new informacionDTO();
        dto.setDescripcion(descripcion);
        dto.setArchivo(archivo);
        dto.setId(id);
        dto.setTitulo(titulo);
        informacionDAO dao = new informacionDAO();
        return dao.editarInformacion(dto);
    }

    public boolean editarEvento(Date fecha_ini, Date fecha_fin, String lugar, String titulo, String descripcion,
            int id, String archivo, int id_info) throws Exception {
        eventoDTO dto = new eventoDTO();
        dto.setFecha_ini(fecha_ini);
        dto.setFecha_fin(fecha_fin);
        dto.setLugar(lugar);
        dto.setId(id);
        editarInformacion(descripcion, archivo, titulo, id_info);
        informacionDAO dao = new informacionDAO();
        return dao.editarEvento(dto);
    }

    public boolean registrarInfoVaie(String descripcion, String url, archivoDTO[] archivos, int id_menu, int cant) throws Exception {
        info_vaieDTO info = new info_vaieDTO();
        info_vaieDAO dao = new info_vaieDAO();
        info.setDescripcion(descripcion);
        info.setUrl(url);
        info.setArchivo("");
        info.getMenu().setId(id_menu);
        int e = dao.registrarDinamico(info);
        if (cant > 0) {
            for (int i = 0; i < archivos.length; i++) {
                System.out.println("a: " + e);
                archivos[i].setId_info(e);
                archivos[i].getId_tipoA().setId(dao.buscarTipoArchivo(archivos[i].getId_tipoA().getExt()));
                dao.registrarArchivo(archivos[i]);
            }
        }
        return e > 0;
    }

    //CONSULTAR
    public ArrayList<menuDTO> consultarMenu() throws Exception {
        info_vaieDAO dao = new info_vaieDAO();
        return dao.consultarMenu();
    }

    public ArrayList<menuDTO> consultarSubmenu(int id) throws Exception {
        menuDTO dto = new menuDTO();
        dto.setId_menu(id);
        info_vaieDAO dao = new info_vaieDAO();
        return dao.consultarSubmenu(dto);
    }

    public informacionDTO consultarInformacion(int id) throws Exception {
        informacionDAO dao = new informacionDAO();
        return dao.consultarInformacion(id);
    }

    public info_vaieDTO consultarInfoVaie(int id) throws Exception {
        info_vaieDAO dao = new info_vaieDAO();
        return dao.consultarInfoVaie(id);
    }

    //ELIMINAR
    public boolean eliminarMenu(int id) throws Exception {
        menuDTO dto = new menuDTO();
        dto.setId(id);
        info_vaieDAO dao = new info_vaieDAO();
        System.out.println(dao.eliminarMenu(dto));
        return dao.eliminarMenu(dto);
    }

    public boolean eliminarInformacion(int id) throws Exception {
        informacionDTO dto = new informacionDTO();
        dto.setId(id);
        informacionDAO dao = new informacionDAO();
        return dao.eliminarInformacion(dto);
    }

    public boolean eliminarProgramacion(int id) throws Exception {
        programacionDTO dto = new programacionDTO();
        dto.setId(id);
        informacionDAO dao = new informacionDAO();
        return dao.eliminarProgramacion(dto);
    }

    public boolean eliminarEvento(int id) throws Exception {
        eventoDTO dto = new eventoDTO();
        dto.getId_info().setId(id);
        dto.setId(id);
        informacionDAO dao = new informacionDAO();
        dto.setId(dao.consultarEvento(id).getId());
        return dao.eliminarEvento(dto);
    }

}
