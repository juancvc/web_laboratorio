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
		    			if(objOrdenDetalle.codReg == data.codReg){ 
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
				    			"<td>"+objOrdenDetalle.importe+"</td>"+ 
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
				  				$("#cboTipoDX"+objOrdenDetalle.valor4.trim()).val(objOrdenDetalle.valor7);
				  			 
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