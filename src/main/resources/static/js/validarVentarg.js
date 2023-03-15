const formulario_rv = document.getElementById('formulario_rv');
const inputs = document.querySelectorAll('#formulario_rv input');

const campos= {

    fechaf: false,
    
    }
    const validarFormulariorv = (e) => {

        switch (e.target.name){
          case"fechaf":
            if(fechaf==""){
    
                document.getElementById("grupo_fechaf").classList.add('formulario_grupo-incorrecto')
                document.getElementById("grupo_fechaf").classList.remove('formulario_grupo-correcto')
                document.querySelector("#grupo_fechaf i").classList.remove('fa-check-circle')
                document.querySelector("#grupo_fechaf i").classList.add('fa-circle-xmark')
                document.querySelector('#grupo_fechaf .formulario_input-error').classList.add('formulario_input-error-activo')
                campos ['fechaf'] = false;
             
            }else{
            
                document.getElementById("grupo_fechaf").classList.remove('formulario_grupo-incorrecto')
                document.getElementById("grupo_fechaf").classList.add('formulario_grupo-correcto')
                document.querySelector("#grupo_fechaf i").classList.remove('fa-circle-xmark')
                document.querySelector("#grupo_fechaf i").classList.add('fa-check-circle')
                document.querySelector('#grupo_fechaf .formulario_input-error').classList.remove('formulario_input-error-activo')
                document.getElementById('formulario_mensaje').classList.remove('formulario_mensaje-activo')
                campos ['fechaf'] = true;
            }
          break;
      }
    }
    
    const validarCampo = () => {}
    
    inputs.forEach((input) => {
      input.addEventListener('keyup',validarFormulariorv)
      input.addEventListener('change',validarFormulariorv)
      input.addEventListener('blur',validarFormulariorv)
    
    
    });
    
    formulario_rv.addEventListener('submit', (e) => {
        e.preventDefault();
        const venta_va= document.getElementById('venta_va');
            if(campos.fechaf && venta_va.checked){
            formulario_rv.reset();
              
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