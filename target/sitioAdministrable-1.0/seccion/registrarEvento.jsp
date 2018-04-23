<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<section class="content">
    <div class="box">
        <div class="box-header with-border">
            <h3 class="box-title">Registrar Evento</h3>
        </div>
        <div class="box-body">
            <form id="frmRegistrarEvento">
                <div class="row" style="padding-top: 15px">
                    <div class="col-md-6">
                        <label>Título: </label>
                        <input id="titulo" name="titulo" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label>Descripción: </label>
                        <textarea id="descripcion" name="descripcion" class="form-control" required></textarea>
                    </div>
                </div>
                <div class="row" style="padding-top: 15px">
                    <div class="col-md-6">
                        <label>Fecha de inicio: </label>
                        <input type="date" id="fechaIni" name="fechaIni" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label>Fecha de finalización: </label>
                        <input type="date" id="fechaFin" name="fechaFin" class="form-control" required>
                    </div>
                </div>
                <div class="row" style="padding-top: 15px">
                    <div class="col-md-6">
                        <label>Lugar: </label>
                        <input id="lugarEv" name="lugarEv" class="form-control" required>
                    </div>
                    <div class="col-md-6">
                        <label>&nbsp</label><br>
                        <input type="file" id="archivo" name="archivo" class="form-control" required><br>
                    </div>
                </div>
                <br>
                <div class="box box-danger">
                    <div class="box-header with-border">
                        <h3 class="box-title">Registrar Programación</h3>
                    </div>
                    <div class="box-body">
                        <div class="row" style="padding-top: 15px">
                            <div class="col-md-6">
                                <label>Fecha y hora: </label>
                                <input type="datetime-local" id="fecha" name="fecha" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label>Nombre: </label>
                                <input id="nombre" name="nombre" class="form-control">
                            </div>
                        </div>
                        <div class="row" style="padding-top: 15px">
                            <div class="col-md-6">
                                <label>Descripción: </label>
                                <textarea id="descrip" name="descrip" class="form-control"></textarea>
                            </div>
                            <div class="col-md-6">
                                <label>Responsable: </label>
                                <input id="responsable" name="responsable" class="form-control">
                            </div>
                        </div>
                        <div class="row" style="padding-top: 15px">
                            <div class="col-md-6">
                                <label>Lugar: </label>
                                <input id="lugar" name="lugar" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label>&nbsp</label><br>
                                <a style="cursor: pointer" class="btn btn-danger" id="agregarP">Agregar programación</a>
                            </div>
                        </div>
                        <div class="row table-responsive col-md-12" style="padding-top: 25px">
                            <table class="table table-hover table-bordered" id="tablaProg">
                                <thead>
                                    <tr>
                                        <th>Fecha y hora</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>Responsable</th>
                                        <th>Lugar</th>
                                        <th></th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="row" style="padding-top: 15px">
                            <div class="col-md-1 col-md-offset-10">
                                <a style="cursor: pointer;" onclick="redirigir('Consultar', 'Evento')" class="btn btn-danger">Volver</a>
                            </div>
                            <div class="col-md-1">
                                <input type="hidden" name="registrarEvento">
                                <button class="btn btn-danger">Registrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>        
        </div>
    </div>
</section>