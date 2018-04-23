/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    if ($("#navegacionP").length > 0) {
        $.getJSON("../info_vaieController", {consultarMenu: "true"})
                .done(function (json) {
                    if (json.length > 0) {
                        for (var i = 0; i < json.length; i++) {
                            var url = "'" + json[i].url + "'";
                            var nombre = "'" + json[i].nombre + "'";
                            var id = "'" + json[i].id + "'";
                            var esmenu = "'esmenu'";
                            var espacio = "''";
                            if (json[i].id_menu == 0) {
                                var x = "'m" + json[i].id + "'";
                                if (json[i].tiene_submenu == 1) {
                                    if (json[i].id != 4) {
                                        $("#navegacionP").append('<li id="li' + json[i].id + '" class="treeview"> <a onclick="redirigir(' + url + ',' + nombre + ',' + id + ',' + esmenu + ')" id=' + x + ' style="cursor:pointer"><i class="fa fa-sticky-note"></i><span>' + json[i].nombre + '</span></a> </li>');
                                    } else {
                                        $("#navegacionP").append('<li id="li' + json[i].id + '" class="treeview"> <a  id=' + x + ' style="cursor:pointer"><i class="fa fa-sticky-note"></i><span>' + json[i].nombre + '</span></a> </li>');
                                    }
                                } else {
                                    $("#navegacionP").append('<li id="li' + json[i].id + '" class="treeview"> <a onclick="redirigir(' + url + ',' + nombre + ',' + id + ',' + esmenu + ')" style="cursor:pointer" id=' + x + '><i class="fa fa-sticky-note"></i><span>' + json[i].nombre + '</span></a> </li>');
                                }
                            } else {
                                if ($("#i" + json[i].id_menu).length == 0) {
                                    $("#m" + json[i].id_menu).append('<span id="i' + json[i].id_menu + '" class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span>');
                                    $("#li" + json[i].id_menu).append('<ul id="ul' + json[i].id_menu + '" class="treeview-menu"></ul>');
                                }

                                $("#ul" + json[i].id_menu).append('<li><a onclick="redirigir(' + url + ',' + nombre + ',' + id + ',' + espacio + ')" style="cursor:pointer"><i class="fa fa-circle-o"></i>' + json[i].nombre + '</a></li>');
                            }
                        }
                        var url = "'Menu'";
                        $("#navegacionP").append('<li id="li' + json.length + '" class="treeview"> <a onclick="redirigir(' + url + ',' + url + ',' + espacio + ',' + espacio + ')" style="cursor:pointer" id=m' + json.length + ' style="cursor:pointer"><i class="fa fa-plus-circle"></i><span>Agregar menu</span></a> </li>');
                    }

                });
    } else {
        $.getJSON("info_vaieController", {consultarMenu: "true"})
                .done(function (json) {
                    if (json.length > 0) {
                        for (var i = 3; i < json.length; i++) {
                            var nombre = "'" + +"'";
                            if (json[i].id_menu == 0) {
                                if (json[i].tiene_submenu == 1) {
                                    $("#ulAdd").append('<li id="li' + json[i].id + '" class="dropdown"><a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">' + json[i].nombre + '</a></li>');
                                    $("#li" + json[i].id).append('<ul id="ul' + json[i].id + '" class="dropdown-menu"></ul>');
                                } else {
                                    $("#ulAdd").append('<li class="nodropdown"><a href="#">' + json[i].nombre + '</a></li>');
                                }
                            } else {
                                $("#ul" + json[i].id_menu).append('<li ><a href="#">' + json[i].nombre + '</a></li>');
                            }
                        }
                    }

                });
        
    }
    if ($("#formIniciarSesion").length > 0) {
        $("#formIniciarSesion").bind("submit", function () {
            $.ajax({
                type: "POST",
                url: "user",
                data: $("#formIniciarSesion").serialize(),
                success: function (data) {
                    if (data === 'false') {
                        swal(
                                'Oops...',
                                'Credenciales erroneas',
                                'error'
                                )
                    } else {
                        window.location = "seccion/Administrador";
                    }
                },
                error: function (data) {

                }
            });
            return false;
        });
    }

});

function redirigir(url, nombre, id, esmenu) {
    $("#Remp").empty();
    $("#Remp").load(url, function () {
        if (url == "Eventos") {
            var i = 0;
            $("#agregarP").on("click", function () {
                if ($("#fecha").val() == "" || $("#nombre").val() == "" || $("#descrip").val() == ""
                        || $("#responsable").val() == "" || $("#lugar").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe llenar todos los campos");
                } else {
                    var tds = '<tr id="' + i + '">';
                    tds += '<td><input style="border:0px; background: none" readonly name="fechaT" value="' + $("#fecha").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="nombreT" value="' + $("#nombre").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="descripT" value="' + $("#descrip").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="responsableT" value="' + $("#responsable").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="lugarT" value="' + $("#lugar").val() + '"></td>';
                    tds += '<td><a onclick="borrar(' + i + ')" style="cursor: pointer; color: #dd4b39"><span style="font-size:20px;" class="fa fa-trash-o"></span></a></td>';
                    tds += '</tr>';
                    $("#tablaProg").append(tds);
                    i++;
                    $("#fecha").val("");
                    $("#nombre").val("");
                    $("#descrip").val("");
                    $("#responsable").val("");
                    $("#lugar").val("");
                }
            });

            $("#frmRegistrarEvento").on("submit", function () {
                var form = $("#frmRegistrarEvento")[0];
                var formData = new FormData(form);
                if ($("#fechaIni").val() > $("#fechaFin").val()) {
                    alertify.logPosition("bottom right");
                    alertify.error("La fecha final no puede ser superior a la fecha de inicio del evento");
                    return false;
                }
                if (i == 0) {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe agregar la programaci\u00F3n del evento");
                    return false;
                }
                $.ajax({
                    type: "POST",
                    url: "../infoController",
                    data: formData,
                    enctype: 'multipart/form-data',
                    contentType: false,
                    processData: false,
                    cache: false,
                    success: function (data) {
                        if (data == "true") {
                            alertify.logPosition("bottom right");
                            alertify.success("Registro exitoso");
                            setTimeout(function () {
                                location.reload();
                            }, 700);
                        } else {
                            alertify.logPosition("bottom right");
                            alertify.error("Se ha producido un error");
                        }
                    },
                    error: function (data) {

                    }
                });
                return false;
            });

        } else if (url == "Noticias") {
            $("#frmRegistrarNoticia").on("submit", function () {
                if ($("#titulo").val() == "" || $("#descripcion").val() == "" ||
                        $("#archivo").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe llenar todos los campos");
                } else {
                    var form = $("#frmRegistrarNoticia")[0];
                    var formData = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "../infoController",
                        data: formData,
                        enctype: 'multipart/form-data',
                        contentType: false,
                        processData: false,
                        cache: false,
                        success: function (data) {
                            if (data == "true") {
                                alertify.logPosition("bottom right");
                                alertify.success("Registro exitoso");
                                setTimeout(function () {
                                    location.reload();
                                }, 700);
                            } else {
                                alertify.logPosition("bottom right");
                                alertify.error("Se ha producido un error");
                            }
                        },
                        error: function (data) {

                        }
                    });
                }
                return false;

            });
        } else if (url == "Novedades") {
            $("#frmRegistrarNovedad").on("submit", function () {
                if ($("#titulo").val() == "" || $("#descripcion").val() == "" ||
                        $("#archivo").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe llenar todos los campos");
                } else {
                    var form = $("#frmRegistrarNovedad")[0];
                    var formData = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "../infoController",
                        data: formData,
                        async: false,
                        success: function (data) {
                            if (data == "true") {
                                alertify.logPosition("bottom right");
                                alertify.success("Registro exitoso");
                                setTimeout(function () {
                                    location.reload();
                                }, 700);
                            } else {
                                alertify.logPosition("bottom right");
                                alertify.error("Se ha producido un error");
                            }
                        },
                        error: function (data) {

                        },
                        contentType: false,
                        processData: false,
                        cache: false
                    });
                }
                return false;

            });
        } else if (url == 'agregar') {
            $("#tituloH").prepend(nombre);
            $("#id").val(id);
            $("#titulo").val(nombre);
            $.getJSON("../info_vaieController", {consultarInfo: "true", id: id})
                    .done(function (json) {
                        $("#ruta").val(nombre);
                        $("#rutaVieja").val(json.archivo);
                        $("#descripcion").val(json.descripcion);
                    });
            $("#frmRegistrarDinamico").on("submit", function () {
                if ($("#descripcion").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe llenar el campo de descripción");
                } else {
                    var form = $("#frmRegistrarDinamico")[0];
                    var formData = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "../info_vaieController",
                        data: formData,
                        enctype: 'multipart/form-data',
                        success: function (data) {
                            if (data == "true") {
                                alertify.logPosition("bottom right");
                                alertify.success("Registro exitoso");
                                setTimeout(function () {
                                    location.reload();
                                }, 700);
                            } else {
                                alertify.logPosition("bottom right");
                                alertify.error("Se ha producido un error");
                            }
                        },
                        error: function (data) {

                        },
                        contentType: false,
                        processData: false,
                        cache: false

                    });
                }
                return false;
            });
        } else if (url == 'Consultar') {
            $("#titulo").prepend(nombre);
            $("#btnAgregar").on('click', function () {
                redirigir(nombre, '', '', '');
            });
            $.getJSON("../infoController", {listar: nombre})
                    .done(function (json) {
                        var tr, u;
                        if (nombre == 'Eventos') {
                            u = "'EditarEvento'";
                        } else {
                            u = "'Editar'";
                        }
                        var n = "'" + nombre + "'";
                        var espacio = "' '";
                        if (json.length > 0) {
                            for (var i = 0; i < json.length; i++) {
                                tr = $('<tr/>');
                                tr.append("<td>" + json[i].fPublicacion + "</td>");
                                tr.append("<td>" + json[i].titulo + "</td>");
                                tr.append('<td>\n\
<a style="cursor: pointer;>\n\
<span style="font-size:20px;" class="fa fa-pencil" title="Editar" onclick="redirigir(' + u + ',' + n + ',' + json[i].id + ',' + espacio + ')"></span></a></td>');
                                tr.append('<td><a style="cursor: pointer; \n\
color: #dd4b39"><span style="font-size:20px;" class="fa fa-trash-o" title="Eliminar" onclick="eliminar(' + json[i].id + ',' + n + ')"></span></a></td>');
                                $('#tablaInfo').append(tr);
                            }
                        } else {

                        }
                    });
        } else if (url == "Editar") {
            $("#btnVolver").on('click', function () {
                redirigir('Consultar', nombre, '', '');
            });
            $.getJSON("../infoController", {consultarInfo: "true", id: id})
                    .done(function (json) {
                        $("#titulo").val(json.titulo);
                        $("#descripcion").val(json.descripcion);
                        $("#ruta").val(nombre);
                        $("#rutaVieja").val(json.archivo);
                        $("#id").val(id);
                    });
            $("#nombre").prepend("Editar " + nombre);
            $("#frmEditarDinamico").on("submit", function () {
                var form = $("#frmEditarDinamico")[0];
                var formData = new FormData(form);
                $.ajax({
                    type: "POST",
                    url: "../infoController",
                    data: formData,
                    enctype: 'multipart/form-data',
                    success: function (data) {
                        if (data == "true") {
                            alertify.logPosition("bottom right");
                            alertify.success("La informaci\u00F3n se ha guardado correctamente");
                            setTimeout(function () {
                                location.reload();
                            }, 700);
                        } else {
                            alertify.logPosition("bottom right");
                            alertify.error("Se ha producido un error");
                        }
                    },
                    error: function (data) {

                    },
                    contentType: false,
                    processData: false,
                    cache: false

                });
                return false;
            });
        } else if (url == "Informacion") {
            if (esmenu != "esmenu") {
                $("#editarMenu").hide();
            }
            $("#titulo").val(nombre);
            $("#tituloH").prepend(nombre);
            $("#id").val(id);
            $("#editarMenu").attr('onclick', 'redirigir("Menu","' + nombre + '","' + id + '","' + esmenu + '")');
            $.getJSON("../info_vaieController", {consultarVaie: "true", id: id})
                    .done(function (json) {
                        if (json != null) {
                            $("#descripcion").val(json.descripcion);
                        }
                        $("#id").val(id);
                    });
            var i = 0;
            var f = 0;
            $("#agregarA").on("click", function () {
                var tds = '<tr id="' + i + '">';
                var text2 = $('#archivoT' + f).val().split('\\').pop();
                tds += '<td><input style="border:0px; background: none" readonly name="nombreT" value="' + text2 + '"></td>';
                tds += '<td><a onclick="borrarP(' + i + ')" title="Eliminar" style="cursor: pointer; color: #dd4b39"><span style="font-size:20px;" class="fa fa-trash-o"></span></a></td>';
                tds += '</tr>';
                $("#tablaArch").append(tds);
                $("#archivoT" + f).hide();
                f++;
                $("#cant").val(f);
                $("#archivosTabla").after('<input type="file" id="archivoT' + f + '" name="archivoT' + f + '" class="form-control">');
                i++;
            });

            $("#frmRegistrarInformacion").on("submit", function () {
                var form = $("#frmRegistrarInformacion")[0];
                var formData = new FormData(form);
                $.ajax({
                    type: "POST",
                    url: "../info_vaieController",
                    data: formData,
                    enctype: 'multipart/form-data',
                    success: function (data) {
                        if (data == "true") {
                            alertify.logPosition("bottom right");
                            alertify.success("La informaci\u00F3n se ha guardado correctamente");
                            setTimeout(function () {
                                location.reload();
                            }, 700);
                        } else {
                            alertify.logPosition("bottom right");
                            alertify.error("Se ha producido un error");
                        }
                    },
                    error: function (data) {

                    },
                    contentType: false,
                    processData: false,
                    cache: false

                });
                return false;
            });

        } else if (url == "EditarEvento") {
            var j, t = 't';
            $.getJSON("../infoController", {listarProgramacion: "true", id: id})
                    .done(function (json) {
                        j = json.lista_evento.length;
                        $("#titulo").val(json.id_info.titulo);
                        $("#descripcion").val(json.id_info.descripcion);
                        $("#lugarEv").val(json.lugar);
                        $("#fechaIni").val(json.fIni);
                        $("#fechaFin").val(json.fFin);
                        $("#rutaVieja").val(json.archivo);
                        $("responsable").prepend(json.responsable);
                        $("#id").val(json.id);
                        $("#id_info").val(json.id_info.id);
                        for (var i = 0; i < json.lista_evento.length; i++) {
                            var tds = '<tr id="' + i + '">';
                            tds += '<input type="hidden" name="id_pT" id="id_p" value="' + json.lista_evento[i].id + '">';
                            tds += '<td><input type="datetime-local" style="border:0px; background: none" name="fechaT" value="' + json.lista_evento[i].fHora + '"></td>';
                            tds += '<td><input style="border:0px; background: none" name="nombreT" value="' + json.lista_evento[i].nombre + '"></td>';
                            tds += '<td><input style="border:0px; background: none" name="descripT" value="' + json.lista_evento[i].descripcion + '"></td>';
                            tds += '<td><input style="border:0px; background: none" name="responsableT" value="' + json.lista_evento[i].responsable + '"></td>';
                            tds += '<td><input style="border:0px; background: none" name="lugarT" value="' + json.lista_evento[i].lugar + '"></td>';
                            if (i > 0) {
                                tds += '<td><a onclick="borrarP(' + i + ',' + json.lista_evento[i].id + ')" style="cursor: pointer; color: #dd4b39"><span style="font-size:20px;" class="fa fa-trash-o"></span></a></td>';
                            } else {
                                tds += '<td></td>';
                            }
                            tds += '</tr>';
                            $("#tablaProg").append(tds);
                        }
                    });
            $("#agregarP").on("click", function () {
                if ($("#fecha").val() == "" || $("#nombre").val() == "" || $("#descrip").val() == ""
                        || $("#responsable").val() == "" || $("#lugar").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe llenar todos los campos");
                } else {
                    var tds = '<tr id="' + j + '">';
                    tds += '<input type="hidden" name="id_pT" id="id_p" value="0">';
                    tds += '<td><input style="border:0px; background: none" readonly name="fechaT" value="' + $("#fecha").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="nombreT" value="' + $("#nombre").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="descripT" value="' + $("#descrip").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="responsableT" value="' + $("#responsable").val() + '"></td>';
                    tds += '<td><input style="border:0px; background: none" readonly name="lugarT" value="' + $("#lugar").val() + '"></td>';
                    tds += '<td><a onclick="borrar(' + j + ')" style="cursor: pointer; color: #dd4b39"><span style="font-size:20px;" class="fa fa-trash-o"></span></a></td>';
                    tds += '</tr>';
                    $("#tablaProg").append(tds);
                    j++;
                    $("#fecha").val("");
                    $("#nombre").val("");
                    $("#descrip").val("");
                    $("#responsable").val("");
                    $("#lugar").val("");
                }
            });

            $("#frmEditarEvento").on("submit", function () {
                var form = $("#frmEditarEvento")[0];
                var formData = new FormData(form);
                $.ajax({
                    type: "POST",
                    url: "../infoController",
                    data: formData,
                    enctype: 'multipart/form-data',
                    success: function (data) {
                        if (data == "true") {
                            alertify.logPosition("bottom right");
                            alertify.success("La informaci\u00F3n se ha guardado correctamente");
                            setTimeout(function () {
                                location.reload();
                            }, 700);
                        } else {
                            alertify.logPosition("bottom right");
                            alertify.error("Se ha producido un error");
                        }
                    },
                    error: function (data) {

                    },
                    contentType: false,
                    processData: false,
                    cache: false

                });
                return false;
            });
        } else if (url == "Menu") {
            if (esmenu == "esmenu" || esmenu == "editar") {
                $("#nombre").val(nombre);
                $("#id").val(id);
                $("#nombreS").attr("readonly", false);
                $("#id_info").append('<input type="hidden" name="editarMenu" id="editarMenu">');
            } else {
                $("#id_info").append('<input type="hidden" name="agregarMenu" id="agregarMenu" >');
            }
            $.getJSON("../info_vaieController", {consultarSubMenu: "true", id: id})
                    .done(function (json) {
                        if (json.length > 0) {
                            for (var i = 0; i < json.length; i++) {
                                var tds = '<tr id="' + i + '">';
                                tds += '<td><input style="border:0px; background: none" name="submenuT" value="' + json[i].nombre + '">\n\
<input type="hidden" name="id_menuT" value="' + json[i].id + '"></td>';
                                tds += '<td><a onclick="borrarP(' + i + ',' + json[i].id + ')" style="cursor: pointer; color: #dd4b39">\n\
<span style="font-size:20px;" class="fa fa-trash-o"></span></a></td>';
                                tds += '</tr>';
                                $("#tablaSubmenus").append(tds);
                            }
                        }
                    });
            $("#id_menu").val(id);
            $("#tituloH").append("Administrar Men\u00FA");
            var i = 0;
            $("#nombre").on("input", function () {
                if ($("#nombre").val() == "") {
                    $("#nombreS").attr("readonly", true);
                } else {
                    $("#nombreS").attr("readonly", false);
                }
            });
            $("#eliminarMenu").on("click", function () {
                eliminar(id, 'esmenu');
            });
            $("#agregarSub").on("click", function () {
                if ($("#nombreS").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe indicar el nombre del Submenu");
                } else {
                    var tds = '<tr id="' + i + '">';
                    tds += '<td><input style="border:0px; background: none" name="submenuT" value="' + $("#nombreS").val() + '">\n\
<input type="hidden" name="id_menuT" value="0"></td>';
                    tds += '<td><a onclick="borrar(' + i + ')" style="cursor: pointer; color: #dd4b39"><span style="font-size:20px;" class="fa fa-trash-o"></span></a></td>';
                    tds += '</tr>';
                    $("#tablaSubmenus").append(tds);
                    i++;
                    $("#nombreS").val("");
                }
            });
            $("#frmRegistrarMenu").on("submit", function () {
                if ($("#nombre").val() == "") {
                    alertify.logPosition("bottom right");
                    alertify.error("Debe indicar el nombre del menu");
                } else {
                    var form = $("#frmRegistrarMenu")[0];
                    var formData = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "../info_vaieController",
                        data: formData,
                        enctype: 'multipart/form-data',
                        contentType: false,
                        processData: false,
                        cache: false,
                        success: function (data) {
                            if (data == "true") {
                                alertify.logPosition("bottom right");
                                alertify.success("Registro exitoso");
                                setTimeout(function () {
                                    location.reload();
                                }, 700);
                            } else {
                                alertify.logPosition("bottom right");
                                alertify.error("Se ha producido un error");
                            }
                        },
                        error: function (data) {

                        }
                    });
                }
                return false;
            });
        }
    });

}

function borrar(id) {
    $("#" + id).remove();
}

function borrarP(id, id_p) {
    var tds = '<input type="hidden" name="id_borrarT" id="id_borrarT" value="' + id_p + '">';
    $("#id_info").append(tds);
    borrar(id);
}

function eliminar(id, n) {
    if (n == "esmenu") {
        alertify.confirm("\u00BFEst\u00E1 segur@ de eliminar?", function () {
            $.ajax({
                type: "GET",
                url: "../info_vaieController",
                data: {eliminarMenu: "true", id: id},
                success: function (data) {
                    alertify.logPosition("bottom right");
                    alertify.success("Se ha eliminado correctamente");
                    setTimeout(function () {
                        location.reload();
                    }, 700);
                }
            });
        }, function () {

        });
    } else if (n != "Eventos") {
        alertify.confirm("\u00BFEst\u00E1 segur@ de eliminar?", function () {
            $.ajax({
                type: "GET",
                url: "../infoController",
                data: {eliminarInfo: "true", id: id},
                success: function (data) {
                    alertify.logPosition("bottom right");
                    alertify.success("Se ha eliminado correctamente");
                    setTimeout(function () {
                        location.reload();
                    }, 700);
                }
            });
        }, function () {

        });
    } else {
        alertify.confirm("\u00BFEst\u00E1 segur@ de eliminar?", function () {
            $.ajax({
                type: "GET",
                url: "../infoController",
                data: {eliminarEvento: "true", id: id},
                success: function (data) {
                    alertify.logPosition("bottom right");
                    alertify.success("Se ha eliminado correctamente");
                    setTimeout(function () {
                        location.reload();
                    }, 700);
                }
            });
        }, function () {

        });
    }
}

