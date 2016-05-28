<?php
    $fecha = $_GET[ "fecha" ];
    $horario = $_GET[ "horario" ];
    $host = "db4free.net";
    $dbname = "clinica_0110";
    $usr = "steven_0110";
    $psw = "123456789";
    
    //$conn = new PDO("mysql:host=$host;dbname=", $usr, $psw);
    $conn = new mysqli($host, $usr, $psw, $dbname);
    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }
    $libre = 0;
    $sql = "SELECT * FROM citas WHERE consultorio = 1 AND fecha = $fecha AND hora = $horario;";
    $stm = $conn->stmt_init();
    for( $i = 1 ; $i <= 12 ; $i++ ){
        $sql = "SELECT * FROM citas WHERE consultorio = $i AND fecha = $fecha AND hora = $horario;";
        $stm->prepare($sql);
        //$sql = "SELECT * FROM citas WHERE consultorio = $i AND fecha = $fecha AND hora = $horario";
        //$sql = "SELECT * FROM citas WHERE consultorio = 1 AND fecha = '2016-05-17' AND hora = '7:00';";
        $stm->execute();
        $rs = $stm->get_result();
        $tuplas = $rs->num_rows;
        if( $tuplas == 0 ){
            //var_dump($stm);
            $libre = $i;
            break;
        }
    }
    $stm->close();
    $conn->close();
    echo $libre;
    //$stm = $conn->prepare("INSERT into citas VALUES(:id, :cons, :fecha, :hora)");

        
    
?>