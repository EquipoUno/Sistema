<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="fondos.css">
    <link rel="stylesheet" href="blocks.css">
    <link rel="stylesheet" href="fonts.css">
    <link rel="stylesheet" href="botones.css">
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <script src="utilidades.js"></script>
    <script src="jspdf.min.js"></script>
        
<title>Cita médica</title>
</head>
<body class="bg-white" onload="cargaCalendario();cargaHorarios();window.resizeTo(500,500)">
    <h1 style="text-align:center;">Solicitar cita médica:</h1>
    <!-- CAPA QUE OSCURECE LA PANTALLA-->
    <div id="fade" class="overlay"></div>
    
        <div class="row">
            <div class="col-6 right bg-light-gray redondo"> Selecciona una fecha:</div> <input class="col-6 redondo" id="datepicker" name="fecha"/>
        </div>
        <div class="row">
            <div class="col-6 right bg-light-gray redondo">Selecciona una hora:</div> <select class="col-6 redondo" name="horario" id="horario"></select>
        </div>
        <div class="row">
            <button class="boton-bloque btn-green redondo" onclick="revisarCupo()" >Revisar cupo</button>
    </div>
    <img id="gif" src="" alt=""/>
    
    <div class="redondo" id="pop-upCUPO">
        <p class="center"> Hay cupo en el consultorio: <span class="center" id="consultorio"></span></p>
        <button class="boton-bloque btn-green redondo" onclick="registrarCita()">Confirmar</button><br/>

        <button class="boton-bloque btn-red redondo" onclick="quitarPopups()">Escoger otro horario</button><br/>
        <img id="gif2" src="" />
    </div>    
    
    
    <div class="redondo" id="pop-upNOCUPO">
        <p class="center">No hay cupo en ningun consultorio para ese horario</p>
        <p class="center">Por favor seleccione una hora o fecha distintos</p>
        <button class="boton-bloque btn-red redondo" onclick="quitarPopups()">Escoger otro horario</button>
        
    </div>
    
</body>
</html>