<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<section class="content">
    <div class="col-md-6">
    <div class="box box-danger">
            <div class="box-header with-border">
                <h3 class =" box-title">Registrar Noticia</h3>
            </div>
            <div class="box-body">
                <form id="frmRegistrarNoticia">
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12">
                            <label>Título: </label>
                            <input id="titulo" name="titulo" class="form-control" required>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12">
                            <label>Descripción: </label>
                            <textarea id="descripcion" name="descripcion" class="form-control" required></textarea>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-12">
                            <label>&nbsp</label><br>
                            <input type="file" id="archivo" name="archivo" class="form-control" required><br>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-md-2 col-md-offset-7">
                            <a style="cursor: pointer;" onclick="redirigir('Consultar','Noticias')" class="btn btn-danger">Volver</a>
                        </div>
                        <div class="col-md-2">
                            <input type="hidden" name="registrarNoticia">
                            <button class="btn btn-danger">Registrar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>