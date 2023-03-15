const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const mesa = document.getElementById('mesa');
let fechai = document.getElementById("fechai");


const expresiones = {
	cantidad: /^\d{1,3}$/, // 1 a 3 numeros.
  cliente: /^\d{8,10}$/,
}
const campos = {
cantidad: false,
mesa: false,
fechai: false,
cliente: false

}

const validarFormulario = (e) => {

  switch (e.target.name){
    case"cantidad":
      if(expresiones.cantidad.test(e.target.value)){
        document.getElementById("grupo_cantidad").classList.remove('formulario_grupo-incorrecto')
        document.getElementById("grupo_cantidad").classList.add('formulario_grupo-correcto')
        document.querySelector("#grupo_cantidad i").classList.remove('fa-circle-xmark')
        document.querySelector("#grupo_cantidad i").classList.add('fa-check-circle')
        document.querySelector('#grupo_cantidad .formulario_input-error').classList.remove('formulario_input-error-activo')
        campos['cantidad'] = true;
      }else{
        document.getElementById("grupo_cantidad").classList.add('formulario_grupo-incorrecto')
        document.getElementById("grupo_cantidad").classList.remove('formulario_grupo-correcto')
        document.querySelector("#grupo_cantidad i").classList.remove('fa-check-circle')
        document.querySelector("#grupo_cantidad i").classList.add('fa-circle-xmark')
        document.querySelector('#grupo_cantidad .formulario_input-error').classList.add('formulario_input-error-activo')
        campos['cantidad'] = false;
      }
   
    break;
    case"cliente":
      if(expresiones.cliente.test(e.target.value)){
        document.getElementById("grupo_cliente").classList.remove('formulario_grupo-incorrecto')
        document.getElementById("grupo_cliente").classList.add('formulario_grupo-correcto')
        document.querySelector("#grupo_cliente i").classList.remove('fa-circle-xmark')
        document.querySelector("#grupo_cliente i").classList.add('fa-check-circle')
        document.querySelector('#grupo_cliente .formulario_input-error').classList.remove('formulario_input-error-activo')
        campos['cliente'] = true;
      }else{
        document.getElementById("grupo_cliente").classList.add('formulario_grupo-incorrecto')
        document.getElementById("grupo_cliente").classList.remove('formulario_grupo-correcto')
        document.querySelector("#grupo_cliente i").classList.remove('fa-check-circle')
        document.querySelector("#grupo_cliente i").classList.add('fa-circle-xmark')
        document.querySelector('#grupo_cliente .formulario_input-error').classList.add('formulario_input-error-activo')
        campos['cliente'] = false;
      }
   
    break;
    case"mesa":
    if(mesa!==null || mesa !==0 ){
      document.getElementById("grupo_mesa").classList.remove('formulario_grupo-incorrecto')
      document.getElementById("grupo_mesa").classList.add('formulario_grupo-correcto')
      document.querySelector("#grupo_mesa i").classList.remove('fa-circle-xmark')
      document.querySelector("#grupo_mesa i").classList.add('fa-check-circle')
      document.querySelector('#grupo_mesa .formulario_input-error').classList.remove('formulario_input-error-activo')  
      campos['mesa'] = true;
    }else{
      document.getElementById("grupo_mesa").classList.add('formulario_grupo-incorrecto')
      document.getElementById("grupo_mesa").classList.remove('formulario_grupo-correcto')
      document.querySelector("#grupo_mesa i").classList.remove('fa-check-circle')
      document.querySelector("#grupo_mesa i").classList.add('fa-circle-xmark')
      document.querySelector('#grupo_mesa .formulario_input-error').classList.add('formulario_input-error-activo')
      campos['mesa'] = false;
    }

   
    break;
    case"fechai":
    if(fechai==""){
      document.getElementById("grupo_fecha").classList.add('formulario_grupo-incorrecto')
      document.getElementById("grupo_fecha").classList.remove('formulario_grupo-correcto')
      document.querySelector("#grupo_fecha i").classList.remove('fa-check-circle')
      document.querySelector("#grupo_fecha i").classList.add('fa-circle-xmark')
      document.querySelector('#grupo_fecha .formulario_input-error').classList.add('formulario_input-error-activo')
      campos['fechai'] = false;

    }else{
     
    
      document.getElementById("grupo_fecha").classList.remove('formulario_grupo-incorrecto')
      document.getElementById("grupo_fecha").classList.add('formulario_grupo-correcto')
      document.querySelector("#grupo_fecha i").classList.remove('fa-circle-xmark')
      document.querySelector("#grupo_fecha i").classList.add('fa-check-circle')
      document.querySelector('#grupo_fecha .formulario_input-error').classList.remove('formulario_input-error-activo')
      campos['fechai'] = true;
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

formulario.addEventListener('submit', (e) => {
  e.preventDefault();
  const pedidova = document.getElementById('pedidova');
      if(campos.cantidad && campos.mesa && campos.fechai && pedidova.checked){
      formulario.reset();
        
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

mesa.addEventListener('change', validarFormulario)
mesa.addEventListener('blur', validarFormulario)



function abrirCategoria(categoriaNombre) {
    var i;
    var x = document.getElementsByClassName("categoria");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(categoriaNombre).style.display = "block";
}

function filtroMenu() {
  var input, filtro, table, tr, td, i;
  input = document.getElementById("filtro");
  filtro = input.value.toUpperCase();
  table = document.getElementById("tablaMenu");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filtro) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}







