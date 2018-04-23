/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.sitioadministrable.controllers;

import co.edu.ufps.sitioadministrable.model.DTO.archivoDTO;
import co.edu.ufps.sitioadministrable.model.DTO.info_vaieDTO;
import co.edu.ufps.sitioadministrable.model.DTO.menuDTO;
import co.edu.ufps.sitioadministrable.model.Facade;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Eliza
 */
public class info_vaieController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //ACUTUALIZAR
    protected void actualizarDinamico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("titulo");
        nombre = new String(nombre.getBytes("iso-8859-1"), "UTF-8");
        String descripcion = request.getParameter("descripcion");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += "Info";
        String nuevaRuta = request.getParameter("rutaVieja");
        nuevaRuta = new String(nuevaRuta.getBytes("iso-8859-1"), "UTF-8");
        Part file = request.getPart("archivo");
        String ext = file.getContentType();
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
                        + nombre + "." + ext));
                nuevaRuta = "/sitioAdministrable/Info/" + nombre + "." + ext;
                filecontent = file.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
            }
            //fin archivo
            out2.write("" + facade.actualizarDinamico(descripcion, nuevaRuta, nombre, id, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //REGISTRAR
    protected void guardarArchivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String descripcion = request.getParameter("descripcion");
        descripcion = new String(descripcion.getBytes("iso-8859-1"), "UTF-8");
        String urlR = request.getParameter("urlR");
        urlR = new String(urlR.getBytes("iso-8859-1"), "UTF-8");

        String nombreT[] = request.getParameterValues("nombreT");
        int cant = Integer.parseInt(request.getParameter("cant"));
        int id = Integer.parseInt(request.getParameter("id"));

        archivoDTO[] vector = new archivoDTO[cant + 1];

        //archivo
        String ruta2 = getServletContext().getRealPath("/");
        ruta2 += "ArchivosInfo";

        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            for (int i = 0; i <= cant; i++) {
                if (cant > 0) {
                    archivoDTO dto = new archivoDTO();
                    //inicio archivo
                    Part file = request.getPart("archivoT"+i);
                    System.out.println("File: "+file.getSubmittedFileName());
                    OutputStream out = null;
                    String nuevaRuta = "";
                    String ext = file.getContentType();
                    ext = ext.split("/")[1];
                    InputStream filecontent = null;
                    //fin archivo
                    //archivo
                    if (!file.getSubmittedFileName().equals("")) {
                        File dir = new File(ruta2);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        out = new FileOutputStream(new File(ruta2 + File.separator
                                + nombreT[i] + "." + ext));
                        nuevaRuta = "/sitioAdministrable/ArchivoInfo/" + nombreT[i].split("\\.")[0] + "." + ext;
                        filecontent = file.getInputStream();

                        int read = 0;
                        final byte[] bytes = new byte[1024];

                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                    }
                    //fin archivo
                    dto.setNombre(nuevaRuta);
                    dto.getId_tipoA().setExt(ext);
                    vector[i] = dto;
                }
            }
            out2.write("" + facade.registrarInfoVaie(descripcion, urlR, vector, id,cant));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void agregarMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        nombre = new String(nombre.getBytes("iso-8859-1"), "UTF-8");
        int tiene_submenu = 0;
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            String[] submenuT = request.getParameterValues("submenuT");
            if (submenuT != null) {
                tiene_submenu = 1;
            }
            int x = facade.agregarMenu(nombre, 0, tiene_submenu);
            if (submenuT != null) {
                for (int i = 0; i < submenuT.length; i++) {
                    String n = submenuT[i];
                    n = new String(n.getBytes("iso-8859-1"), "UTF-8");
                    facade.agregarSubMenu(n, x);
                }
            }
            out2.write("" + (x > 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void editarMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("nombre");
        titulo = new String(titulo.getBytes("iso-8859-1"), "UTF-8");
        int id_menu = Integer.parseInt(request.getParameter("id"));
        int tiene_submenu = 0;
        Facade facade = new Facade();
        PrintWriter out2 = response.getWriter();
        try {
            String[] submenuT = request.getParameterValues("submenuT");
            String[] id_borrarT = request.getParameterValues("id_borrarT");
            String[] id_menuT = request.getParameterValues("id_menuT");
            if (submenuT != null) {
                tiene_submenu = 1;
            }
            boolean x = facade.actualizarMenu(titulo, id_menu, tiene_submenu);
            if (x) {
                if (id_borrarT != null) {
                    for (int i = 0; i < id_borrarT.length; i++) {
                        System.out.println("borrar" + id_borrarT[i]);
                        int id_borrar = Integer.parseInt(id_borrarT[i]);
                        facade.eliminarMenu(id_borrar);
                    }
                }
                if (submenuT != null) {
                    for (int i = 0; i < submenuT.length; i++) {
                        String n = submenuT[i];
                        n = new String(n.getBytes("iso-8859-1"), "UTF-8");
                        int y = Integer.parseInt(id_menuT[i]);
                        facade.actualizarSubmenu(n, id_menu, y);

                    }
                }
            }

            out2.write("" + x);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //CONSULTAR
    public void consultarMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Facade facade = new Facade();
            ArrayList<menuDTO> list = facade.consultarMenu();
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarSubmenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Facade facade = new Facade();
            if (request.getParameter("id") != null) {
                ArrayList<menuDTO> list = facade.consultarSubmenu(Integer.parseInt(request.getParameter("id")));
                Gson gson = new Gson();
                PrintWriter out = response.getWriter();
                out.write(gson.toJson(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarInfoVaie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        info_vaieDTO dto;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("id") != null) {
                dto = f.consultarInfoVaie(Integer.parseInt(request.getParameter("id")));
                Gson g = new Gson();
                String rel = g.toJson(dto);
                System.out.println("1: " + request.getParameter("id"));
                System.out.println(rel);
                out.write(rel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ELIMINAR
    public void eliminarMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean v;
        Facade f = new Facade();
        PrintWriter out = response.getWriter();
        try {
            System.out.println(Integer.parseInt(request.getParameter("id")));
            v = f.eliminarMenu(Integer.parseInt(request.getParameter("id")));
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
        if (request.getParameter("consultarMenu") != null) {
            consultarMenu(request, response);
        } else if (request.getParameter("consultarInfo") != null) {
            consultarInfoVaie(request, response);
        } else if (request.getParameter("consultarVaie") != null) {
            consultarInfoVaie(request, response);
        } else if (request.getParameter("eliminarMenu") != null) {
            eliminarMenu(request, response);
        } else if (request.getParameter("consultarSubMenu") != null) {
            consultarSubmenu(request, response);
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
        if (request.getParameter("agregarDinamico") != null) {
            actualizarDinamico(request, response);
        } else if (request.getParameter("guardarInfoMenu") != null) {
            guardarArchivo(request, response);
        } else if (request.getParameter("agregarMenu") != null) {
            agregarMenu(request, response);
        } else if (request.getParameter("editarMenu") != null) {
            editarMenu(request, response);
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
