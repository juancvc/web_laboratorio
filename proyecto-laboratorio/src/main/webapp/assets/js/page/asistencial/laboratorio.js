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
										"id='pesoPostulante' required='required' "+
										"value = '1' " +
										"maxlength='3' "+
										"onkeypress= 'return soloNumeros(event);' "+
										"onchange = 'calculaIMC(); ' /></td>"+ 
				    			"<td>"+objOrdenDetalle.examen.sPrecio+"</td>"+ 
				    			"<td>"+objOrdenDetalle.sImporte+"</td>"+ 
				    			"<td>"+ 
						    	 	"<button type='button'"+
						    	 	" class='btn btn-outline-danger btn-sm' "+
						    	 	" data-toggle='tooltip'  data-placement='top'  title='Eliminar'"+
						    	 	"  onclick=\"confirmar_eliminar('"+[objOrdenDetalle.codReg]+"','2');\""+
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
				  	    
				  		for (var i = 0; i < listadoExamenes.length; i++) {
				  			var objOrdenDetalle = listadoExamenes[i]; 
				  			//	$("#cboTipoDX"+objOrdenDetalle.valor4.trim()).val(objOrdenDetalle.valor7);
				  			 
				  		}
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