document.getElementById("pedidoForm").onsubmit = function() {exitoPedido()};

function exitoPedido() {
  iziToast.success({
			id:'success',
			title:'Excelente',
			message : 'El Pedido ha sido registrado con exito',
			position: 'bottomRight'

	});
}

