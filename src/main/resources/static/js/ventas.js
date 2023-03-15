/*function abrirEstado(estadoNombre) {
var i;
var x = document.getElementsByClassName("estado");
for (i = 0; i < x.length; i++) {
x[i].style.display = "none";
}
document.getElementById(estadoNombre).style.display = "block";
}*/

$(document).ready(function() {
	$('#tablapedidos').DataTable({
		"language": {
			"lengthMenu": "Mostrar _MENU_ registros",
			"zeroRecords": "No se encontraton resultados",
			"info": "Mostrando registros del _START_ al _END_ en un total de _TOTAL_ registros",
			"infoEmpty": "Mostranto registros del 0 al 0 en un total de 0 registros",
			"infoFiltered": "Filtrando a un total de _MAX_ registros",
			"sSearch": "Buscar:",
			"oPaginate": {
				"sFirst": "Primero",
				"sLast": "Ultimo",
				"sNext": "Siguiente",
				"sPrevious": "Anterior",
			},
			"sProcessing": "Procesando...",
		}
	});
});


$(document).ready(function() {
	$('#tablaventas').DataTable({
		"language": {
			"lengthMenu": "Mostrar _MENU_ registros",
			"zeroRecords": "No se encontraton resultados",
			"info": "Mostrando registros del _START_ al _END_ en un total de _TOTAL_ registros",
			"infoEmpty": "Mostranto registros del 0 al 0 en un total de 0 registros",
			"infoFiltered": "Filtrando a un total de _MAX_ registros",
			"sSearch": "Buscar:",
			"oPaginate": {
				"sFirst": "Primero",
				"sLast": "Ultimo",
				"sNext": "Siguiente",
				"sPrevious": "Anterior",
			},
			"sProcessing": "Procesando...",
		}
	});
});

$(document).ready(function() {
	$('#tablaMenu').DataTable({
		"language": {
			"lengthMenu": "Mostrar _MENU_ registros",
			"zeroRecords": "No se encontraton resultados",
			"info": "Mostrando registros del _START_ al _END_ en un total de _TOTAL_ registros",
			"infoEmpty": "Mostranto registros del 0 al 0 en un total de 0 registros",
			"infoFiltered": "Filtrando a un total de _MAX_ registros",
			"sSearch": "Buscar:",
			"oPaginate": {
				"sFirst": "Primero",
				"sLast": "Ultimo",
				"sNext": "Siguiente",
				"sPrevious": "Anterior",
			},
			"sProcessing": "Procesando...",
		}
	});
});

