document.getElementById("exito").onsubmit = function() {exito()};

function exito() {
  iziToast.success({
			id:'success',
			title:'Excelente',
			message : 'El usuario ha sido registrado con exito',
			position: 'bottomRight'

	});
	
}	


$(".success").click(function(){
	
		iziToast.success({
			id:'success',
			title:'Pedido',
			message : 'Registrado con exito',
			position: 'bottomRight'

	});
});

$("#warning").click(function(){

		iziToast.warning({
			id:'warning',
			title:'Advertencia',
			message : 'No podrás eliminar el pediddo',
			position: 'topRight'

	});
});
$("#error").click(function(){

		iziToast.error({
			id:'warning',
			title:'Error',
			message : 'No se pido eliminar el registro',
			position: 'topRight'

	});
});