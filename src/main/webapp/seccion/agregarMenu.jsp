<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<%@page pageEncoding="UTF-8"%>
<section class="content">
    <div class="col-md-8">
        <div class="box box-danger">
            <div class="box-header with-border">
                <h3 class =" box-title" id="tituloH" name="tituloH"></h3>
            </div>
            <div class="box-body">
                <form id="frmRegistrarMenu">
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-6">
                            <label>Nombre del menú</label>
                            <input id="nombre" name="nombre" class="form-control">
                        </div>
                        <div class="col-md-2">
                            <a style="cursor: pointer" class="btn btn-app" id="eliminarMenu" name="eliminarMenu">
                                <i class="fa fa-trash-o"></i>Eliminar menú
                            </a>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-6">
                            <label>Nombre del submenú </label>
                            <input id="nombreS" name="nombreS" readonly class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label>&nbsp</label><br>
                            <a style="cursor: pointer" class="btn btn-danger" id="agregarSub">Agregar submenú</a>
                        </div>
                    </div>
                    <div class="row table-responsive col-md-12" style="padding-top: 25px">
                        <table class="table table-hover table-bordered" id="tablaSubmenus">
                            <thead>
                                <tr>
                                    <th>Submenú</th>
                                    <th></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12" id="id_info" name="id_info">
                            <input type="hidden" name="tiene_submenu" id="tiene_submenu">
                            <input type="hidden" name="id" id="id" value="0">
                            <button class="btn btn-danger">Guardar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>