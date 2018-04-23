<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<%@page pageEncoding="UTF-8"%>
<section class="content">
    <div class="col-md-6">
    <div class="box box-danger">
            <div class="box-header with-border">
                <h3 class =" box-title" id="tituloH"></h3>
            </div>
            <div class="box-body">
                <form id="frmRegistrarDinamico">
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12">
                            <label>Título: </label>
                            <input id="titulo" name="titulo" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12">
                            <label>Descripción: </label>
                            <textarea id="descripcion" name="descripcion" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12">
                            <label>&nbsp</label><br>
                            <input type="file" id="archivo" name="archivo" value=" " class="form-control"><br>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12" id="final">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="ruta" name="ruta">
                            <input type="hidden" id="rutaVieja" name="rutaVieja">
                            <input type="hidden" name="agregarDinamico">
                            <button class="btn btn-danger">Guardar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>