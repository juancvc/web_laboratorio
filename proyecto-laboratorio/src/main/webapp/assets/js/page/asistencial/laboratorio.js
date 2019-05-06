var accionRealizar = "";
var codigoRegistro = "";
function confirmar_eliminar(codigo,accion) {
	codigoRegistro = codigo;
	accionRealizar = accion;
	// alert(codigoReferencia);
	$('#md_confirmacion').modal('show');

}
function agregar_accion() {
	$('#md_reg_confirmacion').modal('show');
}

$(document).ready(function() {
	$("#btnConfirmarGeneric").click(function() {
		// console.log("accionRealizar " + accionRealizar);
		// console.log("codigoRegistro " + codigoRegistro);
		if( accionRealizar == "1" ){
			elimarExamen(codigoRegistro);
			$('#md_confirmacion').modal('hide');
		}else if(accionRealizar == "2" ){ 
			$('#md_confirmacion').modal('hide');
		}else if(accionRealizar == "3" ){ 
			$('#md_confirmacion').modal('hide');
		}
		
	});
});

function cargarTarifarioModal() {
	var contextPath = $('#contextPath').val();
	// var codigoLengua = $('#codigoLengua').val(); //
	// document.getElementById("codigoLengua").value;

	path = contextPath + "/ordenController/tarifarioModal";
	// alert("path " + path)
		$.ajax({
			type : "POST",
			url : path,

			success : function(data) {
				// console.log("SUCCESS: ", data);
				$("#modalTarifario").html(data);
				$("#modalTarifario").modal('show'); 
			},
			error : function(request, status, error) {
				console.log("ERROR: " + error);
			}
		}); 
}

function llenarExamenIndex(index){
	var contextPath = $('#contextPath').val();
	var htmlTabla = "";
	var item = 0; 
	var valida ="0";
	 console.log("index" + index);
	  $.ajax({
		    type: "GET",
		    // data: "entidad=" + entidad,
		   	
		    url: contextPath+"/ordenController/refrescarListaOrden?index="+index,
		    	success: function(data){ 
		    	if(data!=null){  
		    		for (var i = 0; i < listadoExamenes.length; i++) {
		    			var objOrdenDetalle = listadoExamenes[i]; 
		    			if(objOrdenDetalle.examen.codigo == data.examen.codigo){ 
		    				valida = "1";
		    			}
		    		} 
		    		if(valida == "1"){
		    			 msg_advertencia("El exámen ya fue ingresado.");
		    		}else{ 
			    		listadoExamenes.push(data);
			    		// console.log("listadoExamenes " + listadoExamenes);
			    		// console.log("listadoExamenes " + listadoExamenes.length);
			    		for (var i = 0; i < listadoExamenes.length; i++) {
			    			var objOrdenDetalle = listadoExamenes[i];
			    			item = item + 1;
			    		htmlTabla += 
			    			"<tr>"+
			    				"<td>"+item +"</td>"+
			    				"<td>"+objOrdenDetalle.examen.descripcion +"</td>"+ 
			    				"<td>"+objOrdenDetalle.examen.tipo.nombreCorto+"</td>"+ 
			    				"<td> <input type='text' class='form-control' "+
										"id="+[objOrdenDetalle.examen.codigo]+" required='required' "+
										"value = '1' " +
										"maxlength='3' "+
										"onkeypress= 'return soloNumeros(event);' "+
										"oninput=\"cambiarCantidad('"+[objOrdenDetalle.examen.codigo]+"');\" /></td>"+ 
				    			"<td>"+objOrdenDetalle.examen.sPrecio+"</td>"+ 
				    			"<td>"+objOrdenDetalle.sImporte+"</td>"+ 
				    			"<td>"+ 
						    	 	"<button type='button'"+
						    	 	" class='btn btn-outline-danger btn-sm' "+
						    	 	" data-toggle='tooltip'  data-placement='top'  title='Eliminar'"+
						    	 	"  onclick=\"confirmar_eliminar('"+[objOrdenDetalle.examen.codigo]+"','1');\""+
						      	 	" data-original-title='Eliminar'"+
						    	 	" id='agregarEspecialidad'>"+
						    	 	"<i class='icon-trash'></i></button> "+ 
						    		"</td>"+
			    		"</tr>";
			    		   
			    	
			    		} 
			    // console.log(htmlTabla);
			    		$("#idBtnCerrarModalCIEX").trigger("click");
				    	$('#idbodyCIEXref').empty();  
				  	    $('#idbodyCIEXref').html(htmlTabla);
				  	    var importe = 0.00;
				  		for (var i = 0; i < listadoExamenes.length; i++) {
				  			var objOrdenDetalle = listadoExamenes[i]; 
				  			importe = importe + objOrdenDetalle.importe; 
				  			//	$("#cboTipoDX"+objOrdenDetalle.valor4.trim()).val(objOrdenDetalle.valor7);
				  			 
				  		}
				  		 $('#txtCajaImporteTotal').val(importe.toFixed(2)); 
				  		$('#txtCajaImporteTotalHidden').val(importe.toFixed(2)); 
		    		}
		    	}
		    
			    },error: function(xhr,status,er) {
		         console.log("error: " + xhr + " status: " + status + " er:" + er);
					    if(xhr.status==500) {
					    	console.log(er);
					    	// Error_500(er);
					    }
					    if(xhr.status==901) {
				    	console.log(er);
				    	// spire_session_901(er);
		 			}
		
			    }
		  });
}

function buscarPersonaNroDoc(){

	var contextPath = $('#contextPath').val();
	var numeroDocumento = $('#nroDocumentoPaciente').val();
	var tipoDocumento = $('#tipoDocumentoPaciente').val();
	var htmlTabla = "";
	console.log("consultarPersonaPorDocumento tipoDocumentoPaciente "
			+ tipoDocumentoPaciente);
	if (tipoDocumento == null || tipoDocumento == "") {
		msg_advertencia("Seleccione tipo de documento");
		return;
	} else if (numeroDocumento == null || numeroDocumento.trim() == "") {
		msg_advertencia("Ingrese número de documento");
		return;
	} else if (tipoDocumento == '000001' && numeroDocumento.trim().length != 8) {
		msg_advertencia("Número de dni debe contener 8 dígitos.")
		return;
	} else if (tipoDocumento == '000002' && numeroDocumento.trim().length < 9) {
		msg_advertencia("Número de pasaporte incorrecto.")
		return;
	} else {
		// console.log("validarDni " + numeroDocumento );
		iniciarBloqueo();
		$
				.ajax({
					type : "GET",
					url : contextPath
							+ "/ordenController/consultarPersonaPorDocumento?tipoDocumento="
							+ tipoDocumento + "&numero=" + numeroDocumento,

					success : function(persona) {
						if (numeroDocumento.length < 7) {
							msg_advertencia("Número de documento incorrecto.");

						} else {

							if (persona != null) {
								console.log("persona.length " + persona.length);
								 
								if (persona.length != 0) {
									$('#personaApellidoPaterno').val(
											persona.apellidoPaterno);
									$('#personaApellidoMaterno').val(
											persona.apellidoMaterno);
									$('#personaPrimerNombre').val(
											persona.primerNombre);
									$('#personaSegundoNombre').val(
											persona.segundoNombre); 
									$('#personaCodigo').val(persona.codigo);    
									var todate = new Date(persona.fechaNac);
									var dia = todate.getDate();
									var mes = todate.getMonth() + 1;
									var anio = todate.getFullYear(); 
									var fechaActual = new Date(); 
									var anioActual = fechaActual.getFullYear();
									var edad = anioActual - anio;
									$('#edadPersona').val(edad);
 
									$('#sexoPaciente').val(persona.sexo.codReg);
									$('#personaDireccion').val(
											persona.direccion);
								 
									if (persona.ubigeoDireccion.codigoUbigeo != "") {
										$('#ubigeoDireccion').val(persona.ubigeoDireccion.codigoUbigeo);
										$('#codigoRegistroUbigeoDireccion').val(persona.ubigeoDireccion.codigoRegistro);
										$('#nombreUbigeoDireccion').val(persona.ubigeoDireccion.nombreUbigeo);
									}else{
										document
										.getElementById('btnBuscarUbigeoDireccion').disabled = false;
										document
										.getElementById('personaDireccion').disabled = false;
									}
							
								  
								}else {
									msg_advertencia("¡La persona no se encuentra registrada!")
								}  

							} else {
								msg_advertencia("¡No se encontraron registros.!")
							}
						}
					},
					error : function(xhr, status, er) {
						console.log("error: " + xhr + " status: " + status
								+ " er:" + er);
						if (xhr.status == 500) {
							console.log(er);
							// Error_500(er);
						}
						if (xhr.status == 901) {
							console.log(er);
							// spire_session_901(er);
						}

					}
				});
		finBloqueo();
	}
}

function grabarOrden(){   
		var personaCodigo = $('#personaCodigo').val();   
		var contextPath = $('#contextPath').val(); 
		var actionForm = $('#frmGuardarOrden').attr("action");
		
		if (personaCodigo =="") {
			msg_advertencia("Debe ingresar una persona para lo Orden."); 
			return;
			
		} 
		console.log("personaCodigo:: " + personaCodigo) ; 
		var myFormulario = $('#frmGuardarOrden');  
		
		if(!myFormulario[0].checkValidity()) {
			 msg_advertencia("Debe completar los campos requeridos(*) correctamente");

		}else{ 
			if(listadoExamenes.length == 0){
				msg_advertencia("Ingrese al menos una Orden de exámen.");
				return;
			
			
			}  
				iniciarBloqueo();
				$.ajax({
				contentType: "application/json",
				type: "POST",
				data: JSON.stringify(listadoExamenes),
				url : contextPath+"/ordenController/grabarOrden",  
		       
				success : function(data) {
					   // console.log("SUCCESS: ", data);
					    if (data == "") {
					    	msg_error("Error al registrar Orden");  
						}else{
						    msg_exito("Éxito al registrar Orden");  
						    // enviarListado();
							// $("#btnListado").trigger("click");
						}
 
				},
				
				error : function(xhr, status, er) { 
				        console.log("error: " + xhr + " status: " + status + " er:" + er);
							// msg_error();
	
						},
			  			complete: function()
	  			{
	  				finBloqueo();

				}
		});
	}
}

function elimarExamen(codigo){
	var htmlTabla = "";
	var item = 0; 
	
	console.log("codigo " + codigo);
	for (var i = 0; i < listadoExamenes.length; i++) {
		var objOrdenDetalle = listadoExamenes[i];
		if(objOrdenDetalle.examen.codigo == codigo){ 
			  console.log("objOrdenDetalle.examen.codReg " + objOrdenDetalle.examen.codigo);
			listadoExamenes.splice(i, 1);
		}
	}  
	for (var i = 0; i < listadoExamenes.length; i++) {
		var objOrdenDetalle = listadoExamenes[i];
		item = item + 1;
		htmlTabla += 
				"<tr>"+
				"<td>"+item +"</td>"+
				"<td>"+objOrdenDetalle.examen.descripcion +"</td>"+ 
				"<td>"+objOrdenDetalle.examen.tipo.nombreCorto+"</td>"+ 
				"<td> <input type='text' class='form-control' "+
						"id="+[objOrdenDetalle.examen.codigo]+" required='required' "+
						"value = '1' " +
						"maxlength='3' "+
						"onkeypress= 'return soloNumeros(event);' "+
						"oninput=\"cambiarCantidad('"+[objOrdenDetalle.examen.codigo]+"');\" /></td>"+ 
				"<td>"+objOrdenDetalle.examen.sPrecio+"</td>"+ 
				"<td>"+objOrdenDetalle.sImporte+"</td>"+ 
				"<td>"+ 
		    	 	"<button type='button'"+
		    	 	" class='btn btn-outline-danger btn-sm' "+
		    	 	" data-toggle='tooltip'  data-placement='top'  title='Eliminar'"+
		    	 	"  onclick=\"confirmar_eliminar('"+[objOrdenDetalle.examen.codigo]+"','1');\""+
		      	 	" data-original-title='Eliminar'"+
		    	 	" id='agregarEspecialidad'>"+
		    	 	"<i class='icon-trash'></i></button> "+ 
		    		"</td>"+
		"</tr>";
	
	}
	$('#idbodyCIEXref').empty();  
	$('#idbodyCIEXref').html(htmlTabla);
		var importe = 0.00;
		for (var i = 0; i < listadoExamenes.length; i++) {
			var objOrdenDetalle = listadoExamenes[i]; 
			importe = importe + objOrdenDetalle.importe; 
			//	$("#cboTipoDX"+objOrdenDetalle.valor4.trim()).val(objOrdenDetalle.valor7);
			 
		}
		 $('#txtCajaImporteTotal').val(importe.toFixed(2)); 
		$('#txtCajaImporteTotalHidden').val(importe.toFixed(2)); 
}

function cambiarCantidad(objeto){
	var nombreObjet = "#"+objeto+""
	console.log("nombreObjet "+ nombreObjet);
	var cantidad = $(nombreObjet).val(); 
	
	console.log("cantidad "+ cantidad);
}

function cambio(){
$("#dataTable tbody tr").each(function (index) 
	    {
	    	var asignado,input,idAcceso,idCompo;
	    	
	    	var objComponente = {
		  		codigo : 0	
		  	};
		    	
			var objAcceso = {
				item 		: 0,
				codigo		: 0,
				flgAsignado : '0',
				flgRead 	: false,
				flgWrite 	: false,
				flgDelete 	: false,
				flgExport 	: false,
				perfil		: objPerfil,
				componente	: objComponente
		  	};
	    	
	        $(this).children("td").each(function (index2) 
	        {
	        	if(index2 == 4 ){ // Columna Asignado
	        		input    = $(this).children("input");
	        		idAcceso = $(input).val();
	  	   			idCompo  = $(input).attr("id");
	  	   			
	  	   			objComponente.codigo 	= idCompo;
	  	   			
	      	   		if($(input).is(':checked')) {
	      	   			asignado = '1';
	           	    }else {
	           	    	asignado = '0';
	           	    }
	      	   		
	          	  	objAcceso.item 			= index+1;
	          	  	objAcceso.codigo 		= idAcceso;
	          	  	objAcceso.flgAsignado	= asignado;
	          	  	objAcceso.componente	= objComponente;
	        	}
	        })
	        arrayMenus.push(objAcceso);
	    })
}
	    