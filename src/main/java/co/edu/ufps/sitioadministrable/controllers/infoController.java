/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.controllers;

import co.edu.ufps.sitioadministrable.model.DTO.eventoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.informacionDTO;
import co.edu.ufps.sitioadministrable.model.DTO.programacionDTO;
import co.edu.ufps.sitioadministrable.model.DTO.usuarioDTO;
import co.edu.ufps.sitioadministrable.model.Facade;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Eliza
 */
public class infoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //REGISTRAR
    protected void registrarEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        titulo = new String(titulo.getBytes("iso-8859-1"), "UTF-8");
        String descripcion = request.getParameter("descripcion");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        usuarioDTO user = (usuarioDTO) request.getSession().getAttribute("usuario");
        String fecha_ini = request.getParameter("fechaIni");
        String fecha_fin = request.getParameter("fechaFin");
        String lugar = request.getParameter("lugarEv");
        lugar = new String(lugar.getBytes("iso-8859-1"), "UTF-8");
        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += "Eventos";
        Part file = request.getPart("archivo");
        String ext = file.getContentType();
        ext = ext.split("/")[1];
        OutputStream out = null;
        String nuevaRuta = "";
        InputStream filecontent = null;
        //fin archivo
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            //archivo
            if (!file.getSubmittedFileName().equals("")) {
                File dir = new File(ruta2);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                out = new FileOutputStream(new File(ruta2 + File.separator
                        + titulo + "." + ext));
                nuevaRuta = "/sitioAdministrable/Eventos/" + titulo + "." + ext;
                filecontent = file.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            //fin archivo
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date ini = format.parse(fecha_ini);
            Date fin = format.parse(fecha_fin);
            int x = facade.registrarEvento(ini, fin, lugar, titulo, descripcion, user, nuevaRuta, ext);
            out2.write("" + (x > 0));
            if (x > 0) {
                //inicio tabla
                String[] fechaT = request.getParameterValues("fechaT");
                String[] nombreT = request.getParameterValues("nombreT");
                String[] descripT = request.getParameterValues("descripT");
                String[] responsableT = request.getParameterValues("responsableT");
                String[] lugarT = request.getParameterValues("lugarT");
                //fin tabla
                for (int i = 0; i < fechaT.length; i++) {
                    Date hora = format.parse(fechaT[i]);
                    facade.registrarProgramacion(hora, nombreT[i], descripT[i], responsableT[i], lugarT[i], x, 0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void registrarNoticia(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        titulo = new String(titulo.getBytes("iso-8859-1"), "UTF-8");
        String descripcion = request.getParameter("descripcion");
        String lugar = request.getParameter("descripcion");
        lugar = new String(lugar.getBytes("iso-8859-1"), "UTF-8");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        usuarioDTO user = (usuarioDTO) request.getSession().getAttribute("usuario");
        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += "Noticias";
        Part file = request.getPart("archivo");
        String ext = file.getContentType(); // returns "exe"
        ext = ext.split("/")[1];
        OutputStream out = null;
        String nuevaRuta = "";
        InputStream filecontent = null;
        //fin archivo
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            //archivo
            if (!file.getSubmittedFileName().equals("")) {
                File dir = new File(ruta2);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                out = new FileOutputStream(new File(ruta2 + File.separator
                        + titulo + "." + ext));
                nuevaRuta = "/sitioAdministrable/Noticias/" + titulo + "." + ext;
                filecontent = file.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            //fin archivo
            out2.write("" + (facade.registrarNoticia(titulo, descripcion, user, nuevaRuta, lugar, ext) > 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void registrarNovedad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        titulo = new String(titulo.getBytes("iso-8859-1"), "UTF-8");
        String descripcion = request.getParameter("descripcion");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        String lugar = request.getParameter("lugar");
        lugar = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        usuarioDTO user = (usuarioDTO) request.getSession().getAttribute("usuario");
        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += "Novedades";
        Part file = request.getPart("archivo");
        String ext = file.getContentType(); // returns "exe"
        ext = ext.split("/")[1];
        OutputStream out = null;
        String nuevaRuta = "";
        InputStream filecontent = null;
        //fin archivo
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            //archivo
            if (!file.getSubmittedFileName().equals("")) {
                File dir = new File(ruta2);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                out = new FileOutputStream(new File(ruta2 + File.separator
                        + titulo + "." + ext));
                nuevaRuta = "/sitioAdministrable/Novedades/" + titulo + "." + ext;
                filecontent = file.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            //fin archivo
            out2.write("" + (facade.registrarNovedad(titulo, descripcion, user, nuevaRuta, lugar, ext) > 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //MÉTODOS PARA LISTAR
    public void listarNoticias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<informacionDTO> list;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            list = f.listarNoticias();
            Gson g = new Gson();
            String rel = g.toJson(list);
            out.write(rel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarNovedades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<informacionDTO> list;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            list = f.listarNovedades();
            Gson g = new Gson();
            String rel = g.toJson(list);
            out.write(rel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarEventos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<informacionDTO> list;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            list = f.listarEventos();
            Gson g = new Gson();
            String rel = g.toJson(list);
            out.write(rel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarProgramacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        eventoDTO dto;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            dto = f.listarProgramacion(Integer.parseInt(request.getParameter("id")));
            Gson g = new Gson();
            String rel = g.toJson(dto);
            out.write(rel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //CONSULTAR
    public void consultarInformacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        informacionDTO dto;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            dto = f.consultarInformacion(Integer.parseInt(request.getParameter("id")));
            Gson g = new Gson();
            String rel = g.toJson(dto);
            out.write(rel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //EDITAR
    protected void editarInformacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        titulo = new String(titulo.getBytes("iso-8859-1"), "UTF-8");
        String descripcion = request.getParameter("descripcion");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");

        String ruta = request.getParameter("ruta");
        String nuevaRuta = request.getParameter("rutaVieja");
        int id = Integer.parseInt(request.getParameter("id"));
        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += ruta;
        Part file = request.getPart("archivo");
        String ext = file.getContentType(); // returns "exe"
        ext = ext.split("/")[1];
        OutputStream out = null;
        InputStream filecontent = null;
        //fin archivo
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            //archivo
            if (!file.getSubmittedFileName().equals("")) {
                File dir = new File(ruta2);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                out = new FileOutputStream(new File(ruta2 + File.separator
                        + titulo + "." + ext));
                nuevaRuta = "/sitioAdministrable/" + ruta + "/" + titulo + "." + ext;
                filecontent = file.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            //fin archivo
            out2.write("" + (facade.editarInformacion(descripcion, nuevaRuta, titulo, id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void editarEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        titulo = new String(titulo.getBytes("iso-8859-1"), "UTF-8");
        String descripcion = request.getParameter("descripcion");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        usuarioDTO user = (usuarioDTO) request.getSession().getAttribute("usuario");
        String fecha_ini = request.getParameter("fechaIni");
        String fecha_fin = request.getParameter("fechaFin");
        String lugar = request.getParameter("lugarEv");
        lugar = new String(lugar.getBytes("iso-8859-1"), "UTF-8");
        String ruta = request.getParameter("ruta");
        String nuevaRuta = request.getParameter("rutaVieja");
        int id = Integer.parseInt(request.getParameter("id"));
        int id_info = Integer.parseInt(request.getParameter("id_info"));
        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += "Eventos";
        Part file = request.getPart("archivo");
        String ext = file.getContentType(); // returns "exe"
        ext = ext.split("/")[1];
        OutputStream out = null;
        InputStream filecontent = null;
        //fin archivo
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            //archivo
            if (!file.getSubmittedFileName().equals("")) {
                File dir = new File(ruta2);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                out = new FileOutputStream(new File(ruta2 + File.separator
                        + titulo + "." + ext));
                nuevaRuta = "/sitioAdministrable/Eventos/" + titulo + "." + ext;
                filecontent = file.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            //fin archivo
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date ini = format.parse(fecha_ini);
            Date fin = format.parse(fecha_fin);
            boolean x = facade.editarEvento(ini, fin, lugar, titulo, descripcion, id, nuevaRuta, id_info);
            out2.write("" + x);
            if (x) {
                //inicio tabla
                String[] fechaT = request.getParameterValues("fechaT");
                String[] nombreT = request.getParameterValues("nombreT");
                String[] descripT = request.getParameterValues("descripT");
                String[] responsableT = request.getParameterValues("responsableT");
                String[] lugarT = request.getParameterValues("lugarT");
                String[] id_pT = request.getParameterValues("id_pT");
                String[] id_borrarT = request.getParameterValues("id_borrarT");
                //fin tabla
                if (id_borrarT != null) {
                    for (int i = 0; i < id_borrarT.length; i++) {
                        int id_borrar = Integer.parseInt(id_borrarT[i]);
                        facade.eliminarProgramacion(id_borrar);
                    }
                }
                if (fechaT != null) {
                    for (int i = 0; i < fechaT.length; i++) {
                        Date hora = format.parse(fechaT[i]);
                        int id_p = Integer.parseInt(id_pT[i]);
                        facade.registrarProgramacion(hora, nombreT[i], descripT[i], responsableT[i], lugarT[i], id, id_p);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //ELIMINAR
    public void eliminarInformacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean v;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            v = f.eliminarInformacion(Integer.parseInt(request.getParameter("id")));
            out.write(v + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarEvento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean v;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            v = f.eliminarEvento(Integer.parseInt(request.getParameter("id")));
            out.write(v + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("listar") != null) {
            if (request.getParameter("listar").equals("Noticias")) {
                listarNoticias(request, response);
            } else if (request.getParameter("listar").equals("Novedades")) {
                listarNovedades(request, response);
            } else if (request.getParameter("listar").equals("Eventos")) {
                listarEventos(request, response);
            }
        } else if (request.getParameter("consultarInfo") != null) {
            consultarInformacion(request, response);
        } else if (request.getParameter("eliminarInfo") != null) {
            eliminarInformacion(request, response);
        } else if (request.getParameter("listarProgramacion") != null) {
            listarProgramacion(request, response);
        } else if (request.getParameter("eliminarEvento") != null) {
            eliminarEvento(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("registrarEvento") != null) {
            registrarEvento(request, response);
        } else if (request.getParameter("registrarNoticia") != null) {
            registrarNoticia(request, response);
        } else if (request.getParameter("registrarNovedad") != null) {
            registrarNovedad(request, response);
        } else if (request.getParameter("editarInformacion") != null) {
            editarInformacion(request, response);
        } else if (request.getParameter("editarEvento") != null) {
            editarEvento(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
