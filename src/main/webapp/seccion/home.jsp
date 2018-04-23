<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<section class="content">
    <div class="row">
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-red-gradient">
                <div class="inner">
                    <h2>Administrar</h2>
                    <h2>Noticias</h2>
                </div>
                <a onclick="redirigir('Consultar','Noticias')" style="cursor:pointer" class="small-box-footer">Más información
                    <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-red-gradient">
                <div class="inner">
                    <h2>Administrar</h2>
                    <h2>Eventos</h2>
                </div>
                <a onclick="redirigir('Consultar','Eventos')" style="cursor:pointer" class="small-box-footer">Más información
                    <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-red-gradient">
                <div class="inner">
                    <h2>Administrar</h2>
                    <h2>Novedades</h2>
                </div>
                <a onclick="redirigir('Consultar','Novedades')" style="cursor:pointer" class="small-box-footer">Más información
                    <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
    </div>
</section>