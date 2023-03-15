const formulario_v = document.getElementById('formulario_v');
const inputs = document.querySelectorAll('#formulario_v input');



// Validacion Filtro de fecha
const campos = {

    fecha_de: false,
    fecha_a: false,
    
    }

const validarFormulario = (e) => {

    switch (e.target.name){
      case"fecha_de":
        if(fecha_de==""){

            document.getElementById("grupo_fecha_de").classList.add('formulario_grupo-incorrecto')
            document.getElementById("grupo_fecha_de").classList.remove('formulario_grupo-correcto')
            document.querySelector("#grupo_fecha_de i").classList.remove('fa-check-circle')
            document.querySelector("#grupo_fecha_de i").classList.add('fa-circle-xmark')
            document.querySelector('#grupo_fecha_de .formulario_input-error').classList.add('formulario_input-error-activo')
            campos['fecha_de'] = false;
         
        }else{
        
            document.getElementById("grupo_fecha_de").classList.remove('formulario_grupo-incorrecto')
            document.getElementById("grupo_fecha_de").classList.add('formulario_grupo-correcto')
            document.querySelector("#grupo_fecha_de i").classList.remove('fa-circle-xmark')
            document.querySelector("#grupo_fecha_de i").classList.add('fa-check-circle')
            document.querySelector('#grupo_fecha_de .formulario_input-error').classList.remove('formulario_input-error-activo')
            document.getElementById('formulario_mensaje').classList.remove('formulario_mensaje-activo')
            campos['fecha_de'] = true;
        }
     
      break;
      case"fecha_a":
    if(fecha_a=="" && fecha_a<=fecha_de){
      document.getElementById("grupo_fecha_a").classList.add('formulario_grupo-incorrecto')
      document.getElementById("grupo_fecha_a").classList.remove('formulario_grupo-correcto')
      document.querySelector("#grupo_fecha_a i").classList.remove('fa-check-circle')
      document.querySelector("#grupo_fecha_a i").classList.add('fa-circle-xmark')
      document.querySelector('#grupo_fecha_a .formulario_input-error').classList.add('formulario_input-error-activo')
      campos['fecha_a'] = false;

    }else{
     
    
      document.getElementById("grupo_fecha_a").classList.remove('formulario_grupo-incorrecto')
      document.getElementById("grupo_fecha_a").classList.add('formulario_grupo-correcto')
      document.querySelector("#grupo_fecha_a i").classList.remove('fa-circle-xmark')
      document.querySelector("#grupo_fecha_a i").classList.add('fa-check-circle')
      document.querySelector('#grupo_fecha_a .formulario_input-error').classList.remove('formulario_input-error-activo')
      document.getElementById('formulario_mensaje').classList.remove('formulario_mensaje-activo')
      campos['fecha_a'] = true;
    }
   
    break;
  }
}

const validarCampo = () => {}

inputs.forEach((input) => {
  input.addEventListener('keyup',validarFormulario)
  input.addEventListener('change',validarFormulario)
  input.addEventListener('blur',validarFormulario)


});

formulario_v.addEventListener('submit', (e) => {
    e.preventDefault();
        if(campos.fecha_de && campos.fecha_a){
        formulario_v.reset();
          
        document.getElementById('formulario_mensaje-exito').classList.add('formulario_mensaje-exito-activo');
        setTimeout(() => {
          document.getElementById('formulario_mensaje-exito').classList.remove('formulario_mensaje-exito-activo');
        }, 4000);
        document.querySelectorAll('.formulario_grupo-correcto').forEach((icono) => {
        icono.classList.remove('formulario_grupo-correcto');
        document.getElementById('formulario_mensaje').classList.remove('formulario_mensaje-activo')
        })
      }else{
        document.getElementById('formulario_mensaje').classList.add('formulario_mensaje-activo')
      }
  });


