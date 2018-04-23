<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:if test = "${usuario == null}">
    <c:redirect url = "/Login"/>
</c:if>
<c:set var = "nom" value = "${usuario.nombre}"/>
<c:set var = "ape" value = "${usuario.apellido}"/>
<c:set var = "nom1" value = "${fn:split(nom, ' ')}" />
<c:set var = "ape1" value = "${fn:split(ape, ' ')}" />
<!DOCTYPE html>
<html>
    <head>
        <!-- Favicon -->
        <link href='https://ww2.ufps.edu.co/assets/img/ico/favicon.ico' rel='Shortcut icon'>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Administrador</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../assets/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="../assets/css/ionicons.min.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="../assets/css/jquery-jvectormap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="../assets/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="../assets/css/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition skin-red-light sidebar-mini">
        <div class="wrapper">

            <header class="main-header">

                <!-- Logo -->
                <a style="cursor: pointer;" onclick="redirigir('Home')" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>V</b>AIE</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Admin</b>VAIE</span>
                </a>

                <!-- Header Navbar: style can be found in headr.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="../assets/images/avatar3.png" class="user-image" alt="User Image">
                                    <span class="hidden-xs">${nom1[0]} ${ape1[0]}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="../assets/images/avatar3.png" class="img-circle" alt="User Image">

                                        <p>
                                            ${nom1[0]} ${ape1[0]}
                                        </p>
                                    </li>
                                    
                                    
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->
                            <li>
                                <a href="../user?out=true" ><i class="fa fa-power-off"></i></a>
                            </li>
                        </ul>
                    </div>

                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../assets/images/avatar3.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p id= "nombreUsu">${nom1[0]} ${ape1[0]}</p><br>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree" id="navegacionP">
                        <li class="header">NAVEGACIÓN PRINCIPAL</li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div id="Remp" class="content-wrapper">
                <jsp:include page="Home" />
            </div>


            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 1.0
                </div>
                <strong>Análisis y Diseño de sistemas - Semestre II 2017</strong>
            </footer>

            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>

        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="../assets/js/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../assets/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="../assets/js/adminlte.min.js"></script>
        <!-- SlimScroll -->
        <script src="../assets/js/jquery.slimscroll.min.js"></script>
        <script src="../assets/js/javascript.js"></script>
        <script src="https://cdn.rawgit.com/alertifyjs/alertify.js/v1.0.10/dist/js/alertify.js"></script>

    </body>
</html>
