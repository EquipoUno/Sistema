<?php
    $fecha = $_GET[ "fecha" ];
    $horario = $_GET[ "horario" ];
    $consultorio = $_GET[ "consultorio" ];
    $host = "db4free.net";
    $dbname = "clinica_0110";
    $usr = "steven_0110";
    $psw = "123456789";


    $conn = new mysqli($host, $usr, $psw, $dbname);
    $stm = $conn->stmt_init();
    while( true ){
        $id = rand( 1, 100000);
        $sql = "SELECT * FROM transacciones WHERE id=$id";
        $stm->prepare( $sql );
        $stm->execute();
        $rs = $stm->get_result();
        $cantidad = $rs->num_rows;
        if( $cantidad == 0 ){
            $sql_insert = "INSERT INTO transacciones VALUES($id, 'Consulta', 50, 'Pendiente')";
            $stm->prepare( $sql_insert );
            $stm->execute();
            $sql_insert2 = "INSERT INTO citas VALUES( $id, $consultorio, $fecha, $horario )";
            $stm->prepare( $sql_insert2);
            $stm->execute();
            break;
        }
    }
    echo $id;
?>