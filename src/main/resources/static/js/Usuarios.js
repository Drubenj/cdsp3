var nombre = document.getElementById('usuario');
var password = document.getElementById('contra')
var error = document.getElementById('divxd')
error.style.color = 'red';


function EnviarFormulario(){

    console.log('Enviando Formulario...');

    var mesajeserror = [];

    if(nombre.value === null || nombre.value === '' ){
        
        mesajeserror.push('ingresa tu nombre')
    }

    if(contra.value === null || nombre.value === '' ){
        
        mesajeserror.push('ingresa tu contra')
    }

    error.innerHTML = mesajeserror.join(',');
 

    return false


   // var Usuario = document.getElementById("Usuar").value;
   // var Contraseña = document.getElementById("Contra").value;
    //var rol = document.getElementById("rol").value

    //if (Usuario == "")
   // alert ("Por favor ...")


    //if (Contraseña == "")
   // alert ("Por favor...")

   // if (rol == "")
   // alert ("Porfavor")


   // if(Usuario == "Steven" && Contraseña == "123" && rol == administrador){

      //  alert ("Ingreso exitoso");
        //window.location = "1InterfazClienteDomicilios.html";
       // return true;

   // }else {

     //   alert ("Ingreso no valido");
       // return false;
  //  }
    
    



}