function validarInicioSession(){
    var Usuario = document.getElementById('usuario').value
    const contrasenia = document.getElementById('contrasenia').value
    var rol = document.getElementById('rol').value
    var expreRegCorreo = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
    var correoValido = expreRegCorreo.test(Usuario)
    var expreRegContrasenia = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}/;
    var contraValida = expreRegContrasenia.test(contrasenia)
    var terminos = document.getElementById('terminos').checked;
if (rol == "" || rol ==0)
    alert("Selecciones un rol")
if (contraValida == false )
    alert ("Contraseña invalida")

if (correoValido == false)
    alert('Correo invalido')
if(terminos == false )
alert("No has aceptado terminos")


    if(rol == 1 && contraValida == true && correoValido == true && terminos == true){
    alert('Inicio de sessión válido -Bienvenido Administrador-')
    window.location = 'bienvenida.html';
    return true;
        }else if (rol == 2 && contraValida == true && correoValido == true && terminos == true){
        alert('Inicio de sessión válido -Bienvenido Jefe de cocina-')
        window.location = 'PRODUCCIONPRIJEFE.html';
        return true;
        }else if (rol == 3 && contraValida == true && correoValido == true && terminos == true){
            alert('Inicio de sessión válido -Bienvenido Mesero-')
            window.location = 'MESERO.html ';
            return true;
            }else if (rol == 4 && contraValida == true && correoValido == true && terminos == true){
            alert('Inicio de sessión válido -Bienvenido Domiciliario-')
            window.location = 'DOMICILIARIO.html';
            return true;
                 }else if (rol == 5 && contraValida == true && correoValido == true && terminos == true){
                alert('Inicio de sessión válido -Bienvenido Cliente-')
                window.location = 'bienvenida2.html';
                return true;
                }
                else {
                    alert('Incio de sessión fallido')
                    return false;
                }
}