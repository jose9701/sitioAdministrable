<%-- 
    Document   : login
    Created on : 1/10/2017, 04:37:31 PM
    Author     : Eliza
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:if test = "${usuario != null}">
    <c:redirect url = "/seccion/admin.jsp"/>
</c:if>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Inicio de sesión</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">



        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link href='https://ww2.ufps.edu.co/assets/img/ico/favicon.ico' rel='Shortcut icon'>

        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/sweetalert2.min.css">


        <!-- Modernizr JS -->
        <script src="assets/js/modernizr-2.6.2.min.js"></script>
        <!-- FOR IE9 below -->
        <!--[if lt IE 9]>
        <script src="js/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <!-- Start Sign In Form -->
                    <form id="formIniciarSesion" class="fh5co-form animate-box" data-animate-effect="fadeIn">
                        <h2>Iniciar sesión</h2>
                        <div class="form-group">
                            <label for="codigo" class="sr-only">Código</label>
                            <input required type="number" class="form-control" id="codigo" placeholder="Código" autocomplete="off" name="codigo">
                        </div>
                        <div class="form-group">
                            <label for="contrasena" class="sr-only">Contraseña</label>
                            <input required type="password" class="form-control" id="contrasena" placeholder="Contraseña" autocomplete="off" name="contrasena">
                        </div>
                        <div class="form-group">
                            <p><a href="#">¿Olvidaste tu contraseña?</a></p>
                        </div>
                        <div class="form-group">
                            <input hidden value="ingresar" name="ingresar">
                            <button class="btn btn-primary">Ingresar</button>
                        </div>
                    </form>
                    <!-- END Sign In Form -->

                </div>
            </div>

        </div>

        <!-- jQuery -->
        <script src="assets/js/jquery.min.js"></script>
        <!-- javascript -->
        <script src="assets/js/javascript.js"></script>
        <!-- Bootstrap -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- Placeholder -->
        <script src="assets/js/jquery.placeholder.min.js"></script>
        <!-- Waypoints -->
        <script src="assets/js/jquery.waypoints.min.js"></script>
        <!-- Main JS -->
        <script src="assets/js/main.js"></script>

        <script src="assets/js/sweetalert2.min.js"></script>


    </body>
</html>

