<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<section class="content">
    <div class="col-md-8">
        <div class="box box-danger">
            <div class="box-header with-border">
                <h3 class =" box-title" id="titulo"></h3>
            </div>
            <div class="box-body">
                <button class="btn btn-danger col-md-offset-10" style="cursor:pointer" id="btnAgregar">Agregar</button>
                <div class="row table-responsive col-md-12" style="padding-top: 25px">
                    <table class="table table-hover table-bordered" id="tablaInfo">
                        <thead>
                            <tr>
                                <th>Fecha de publicación</th>
                                <th>Nombre</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
