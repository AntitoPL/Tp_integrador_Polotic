<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>TP Integrador Final - POLOTIC</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Trota<b>Mundos</b><img src="img/logo100.png" style="height:50px;"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor01">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Ventas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="servicios.jsp">Servicios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Paquetes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="clientes.jsp">Clientes</a>
                        </li>
                        <li class="nav-item  dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Empleados
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="empleados.jsp">Listar Empleados</a></li>
                                <li><a class="dropdown-item" href="alta_empleados.jsp">Crear Empleado</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- FORMULARIO DE ALTA DE EMPLEADOS -->
        <div class="row m-5">
            <div class ="col-2">
            </div>
            <div class ="col-8">
                <h1 class="text-center">Alta de Servicios Ofrecidos</h1>
                <form class="row g-3" action="SvServicio" method="POST">
                    <div class="col-md-12">
                        <label for="nombreServicio" class="form-label">Servicio</label>
                        <input type="text" required class="form-control" name="nombreServicio" id="nombreServicio" placeholder="Servicio">
                    </div>
                    <div class="col-md-12">
                        <label for="descripcionServicio" class="form-label">Descripción breve</label>
                        <input type="text" required class="form-control" name="descripcionServicio" id="descripcionServicio" placeholder="Descripción">
                    </div>
                    <div class="col-md-6">
                        <label for="destinoServicio" class="form-label">Destino</label>
                        <input type="text" required class="form-control" name="destinoServicio" id="destinoServicio" placeholder="Destino">
                    </div>
                    <div class="col-md-6">
                        <label for="fechaServicio" class="form-label">Fecha</label>
                        <input type="date" required class="form-control" name="fechaServicio" id="fechaServicio">
                    </div>
                    <div class="col-md-6">
                        <label for="tipoServicio" class="form-label">Tipo de Servicio</label>
                        <select name="tipoServicio" id="tipoServicio" class="form-select">
                            <option selected value="Hotel por noche">Hotel por noche</option>
                            <option value="Alquiler de auto">Alquiler de auto</option>
                            <option value="Pasaje de Colectivo">Pasaje de Colectivo</option>
                            <option value="Pasaje de avión">Pasaje de avión</option>
                             <option value="Pasaje de tren">Pasaje de tren</option>
                            <option value="Entrada a evento">Entrada a evento</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="costoServicio" class="form-label">Costo</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text">$</span>
                            <input type="number" required class="form-control" name="costoServicio" id="costoServicio" placeholder="1000.00">
                        </div>
                    </div>
                    <div class="col-md-12 justify-content-center">
                        <div class="row">
                            <button type="submit" class="btn btn-primary">Agregar Servicio</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class ="col-2">
            </div>
        </div>
        <footer class="footer mt-auto py-3 bg-dark fixed-bottom">
            <div class="container">
                <span class="text-muted">
                    Hecho con 
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
                    </svg>
                    - Antonella Paiz Loker
                </span>
            </div>
        </footer>
        <!-- Optional JavaScript; choose one of the two! -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <!--
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        -->
    </body>
</html>