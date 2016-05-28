function cargaCalendario(){
    $(document).ready(function() {
        $('#datepicker').datepicker({
            minDate: 0,				
            dateFormat: 'yy-mm-dd',
            beforeShowDay: noSunday
        });

        function noSunday(date){
            var day = date.getDay();
            return [(day > 0 && day < 6), ''];
        }; 

    });
}
function cargaHorarios(){
    //Elimina remueve todos los elementos del sel actual
    var sel = document.getElementById("horario");
    var hora = 7;
    while( sel.firstChild ){
        sel.removeChild( sel.firstChild );
    }
    for( var i = 1 ; i < 12 ; i++ ){
        for( var j = 1 ; j < 3 ; j++ ){
            var option = document.createElement("option");
            var minuto;
            minuto = (30 * (j+i) ) % 60
            if( minuto == 0)
                minuto += "0";
            var texto = document.createTextNode( (hora  + ":" + minuto) );
            option.appendChild( texto );
            option.setAttribute( "value",  option.innerHTML ); 
            sel.appendChild( option );
        }
        hora++;
    }
}
function revisarCupo(){
    mostrarCarga();
    var fecha = document.getElementById("datepicker").value;
    var sel= document.getElementById("horario");
    var hora = sel.options[ sel.selectedIndex ].innerHTML;
    var xml = new XMLHttpRequest();
    xml.onreadystatechange = function() {
        if (xml.readyState == 4 && xml.status == 200) {
            ocultarCarga();
            var cupo = Number( xml.responseText );
            if( cupo == 0 ){
                //CODIGO PARA CUANO NO HAY CONSULTORIO LIBRE
                mostrarNoCupo();
            }
            else{
                mostrarCupo( cupo );           
            } 
        }
    };
    var url = "solicitar.php?fecha='" + fecha + "'" + "&horario='" + hora + "'";
    console.log(url);
    xml.open( "GET", url, true );
    xml.send();
}
function registrarCita(){
    mostrarCarga2();
    var fecha = document.getElementById("datepicker").value;
    var sel= document.getElementById("horario");
    var hora = sel.options[ sel.selectedIndex ].innerHTML;
    var consultorio = document.getElementById("consultorio").innerHTML;
    
    var xml = new XMLHttpRequest();
    xml.onreadystatechange = function() {
        if (xml.readyState == 4 && xml.status == 200) {
            console.log(xml.responseText);
            var resp = Number( xml.responseText );
            if( !isNaN( resp ) ){
                //CODIGO PARA CUANO NO HAY CONSULTORIO LIBRE
                alert("Descarga el recibo que se te proporcionará.\nSin él no podrás pagar tu consulta.");
                genPDF( resp )
                quitarPopups();
                ocultarCarga2();
            }
            else{         
                console.log( resp );
            } 
        }
    };
    var url = "registrar.php?fecha='" + fecha + "'" + "&horario='" + hora + "'" + "&consultorio='" + consultorio + "'";
    console.log( url );
    xml.open( "GET", url, true );
    xml.send();
}

function mostrarCarga(){
    var img = document.getElementById("gif");
    img.setAttribute("src", "cargando.gif");
}
function ocultarCarga(){
    var img = document.getElementById("gif");
    img.setAttribute("src", "");    
}
function mostrarCarga2(){
    var img = document.getElementById("gif2");
    img.setAttribute("src", "cargando2.gif");
}
function ocultarCarga2(){
    var img = document.getElementById("gif2");
    img.setAttribute("src", "");    
}
function mostrarCupo( numero ){
    $('div#pop-upCUPO').show();
    $('#fade').show();
    var nodo = document.createTextNode( "" + numero );
    var cons = document.getElementById("consultorio");
    console.log( "cons: " + numero );
    cons.innerHTML="";
    cons.appendChild( nodo );
    
}
function mostrarNoCupo( numero ){
    $('div#pop-upNOCUPO').show();
    $('#fade').show();
}
function quitarPopups(){
    $('div#pop-upCUPO').hide();
    $('div#pop-upNOCUPO').hide();
    $('#fade').hide();
}
function genPDF( id ){
    var pdf = new jsPDF();
    
    var fecha = document.getElementById("datepicker").value;
    var sel= document.getElementById("horario");
    var hora = sel.options[ sel.selectedIndex ].innerHTML;
    var consultorio = document.getElementById("consultorio").innerHTML;
    
    pdf.setFontSize(25);
    pdf.text(40, 10, "RECIBO DE PAGO");
    pdf.setFontSize(20);
    pdf.text( 10, 25, "ID de consulta médica: " + id );
    pdf.text( 10, 35, "Fecha: " + fecha );
    pdf.text( 10, 45, "Hora: " + hora + " hrs.");
    pdf.text( 10, 55, "Consultorio: " + consultorio );
    pdf.text( 10, 65, "Monto a pagar: $50 MXN."  );
    pdf.setFontSize(7);
    pdf.text( 40, 102, "SELLO DE PAGO: " );
    pdf.rect( 10, 75, 70, 30 );
    pdf.setFontSize(10);
    pdf.text( 90, 80, "Favor de imprimir y pasar a la caja del consultorio médico con" );
    pdf.text( 90, 84, "éste documento." );
    pdf.text( 95, 94, "NOTA:." );
    pdf.text( 90, 99, "La consulta debe ser pagada al menos 10 minutos antes de comenzar," );
    pdf.text( 90, 103, "de lo contrario será cedida a alguien más" );
    
    pdf.save("recibo.pdf");
}

