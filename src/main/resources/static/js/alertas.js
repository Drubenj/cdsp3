//--Eliminar Pedido 
function eliminar(id){
			Swal.fire({
			title: '¡Está seguro de eliminar el pedido?',
			text: "Una vez eliminado no podrá revertír la acción",
			icon: 'warning',
			backdrop: true,
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, eliminar!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url:"/cdsp/pedidos/eliminar/pedido/"+id,
					success: function(res){
					console.log(res);
					}
					
				});
				Swal.fire(
					'Eliminado!',
					'El pedido ha sido borrado.',
					'success'
				).then((result) => {
					if(result.isConfirmed){
						location.href="/cdsp/pedidos";
					}
				});
			}
		});
} 

//---EliminarDetalle venta

function eliminarDetalle(id){
			Swal.fire({
			title: '¿Está seguro de remover el menú del pedido?',
			text: "Una vez eliminado no podrá revertír la acción",
			icon: 'warning',
			backdrop: true,
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, eliminar!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url:"/cdsp/pedidos/eliminar/detalle/"+id,
					success: function(res){
					console.log(res);
					}
					
				});
				Swal.fire(
					'Removido!',
					'El detalle ha sido Removido',
					'success'
				).then((result) => {
					if(result.isConfirmed){
						location.href="/cdsp/pedidos/verDetalle";
					}
				});
			}
		});
} 



//---Cobrar venta (Confirmar)
function cobrarventa(id){ 
Swal.fire({
  title: 'Registro de venta',
  text: "Estas seguro de registrar esta venta?",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, registrar!'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/pedidos/venta/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Registro exitoso',
		'La venta ha sido registrada',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/pedidos/ventas";
			}
		});
  	}
})
}

//---Cobrar venta (Confirmar)
function actualizarEstado(id){ 
Swal.fire({
  title: 'Estado',
  text: "Estas seguro de Actualizar el estado?",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, Actualizar!'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/pedidos/estado/actualizar/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Actualizado',
		'El pedido ha sido actualizado exitosamente',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/pedidos/editar/pedido/"+id;
			}
		});
  	}
})
}


//mesero

function eliminarm(id){
			Swal.fire({
			title: '¡Está seguro de eliminar el pedido?',
			text: "Una vez eliminado no podrá revertír la acción",
			icon: 'warning',
			backdrop: true,
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, eliminar!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url:"/cdsp/pedidosm/eliminar/pedido/"+id,
					success: function(res){
					console.log(res);
					}
					
				});
				Swal.fire(
					'Eliminado!',
					'El pedido ha sido borrado.',
					'success'
				).then((result) => {
					if(result.isConfirmed){
						location.href="/cdsp/pedidosm";
					}
				});
			}
		});
} 

//---EliminarDetalle venta

function eliminarDetallem(id){
			Swal.fire({
			title: '¿Está seguro de remover el menú del pedido?',
			text: "Una vez eliminado no podrá revertír la acción",
			icon: 'warning',
			backdrop: true,
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, eliminar!'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url:"/cdsp/pedidosm/eliminar/detalle/"+id,
					success: function(res){
					console.log(res);
					}
					
				});
				Swal.fire(
					'Removido!',
					'El detalle ha sido Removido',
					'success'
				).then((result) => {
					if(result.isConfirmed){
						location.href="/cdsp/pedidosm/verDetalle";
					}
				});
			}
		});
} 



//---Cobrar venta (Confirmar)
function cobrarventam(id){ 
Swal.fire({
  title: 'Registro de venta',
  text: "Estas seguro de registrar esta venta?",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, registrar!'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/pedidosm/venta/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Registro exitoso',
		'La venta ha sido registrada',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/pedidosm/ventas";
			}
		});
  	}
})
}

//---Cobrar venta (Confirmar)
function actualizarEstadom(id){ 
Swal.fire({
  title: 'Estado',
  text: "Estas seguro de Actualizar el estado?",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, Actualizar!'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/pedidosm/estado/actualizar/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Actualizado',
		'El pedido ha sido actualizado exitosamente',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/pedidosm/editar/pedido/"+id;
			}
		});
  	}
})
}

//---
function actualizarEstadoaEnproceso(id){ 
Swal.fire({
  title: 'Elaborar pedido',
  text: "Al tomar el pedido, este actualizará su estado a 'En proceso' ",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, elaborar!'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/cocina/pedido/actualizar/estado/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Actualizado',
		'El pedido ha sido actualizado exitosamente',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/cocina/pedido/"+id;
			}
		});
  	}
})
}

function actualizarEstadoaListo(id){ 
Swal.fire({
  title: 'Pedido Listo',
  text: "El pedido actualizará su estado 'listo'. ¿Está seguro de marcar el pedido con ese estado?",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Sí, actualizar'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/cocina/pedido/actualizar/estado/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Actualizado',
		'El pedido ha sido actualizado exitosamente',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/cocina/pedido/"+id;
			}
		});
  	}
})
}


function actualizarEstadoDomicilio(id){ 
Swal.fire({
  title: 'Estado',
  text: "Estas seguro de Actualizar el estado del domicilio?",
  backdrop: true,
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, Actualizar!'
}).then((result) => {
  if (result.isConfirmed) {
	  	$.ajax({
			url:"/cdsp/domicilios/estado/actualizar/"+id,
			success: function(res){
			console.log(res);
		}
	});
	
	Swal.fire(
		'Actualizado',
		'El domicilio se ha sido actualizado exitosamente',
		'success'
		).then((result) => {
			if(result.isConfirmed){
				location.href="/cdsp/domicilios/consultar/"+id;
			}
		});
  	}
})
}



