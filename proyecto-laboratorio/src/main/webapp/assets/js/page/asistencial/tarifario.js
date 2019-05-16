function cargarRegistroTarifarioModal() {
	var contextPath = $('#contextPath').val();
	// var codigoLengua = $('#codigoLengua').val(); //
	// document.getElementById("codigoLengua").value;

	path = contextPath + "/laboratorioController/tarifarioRegModal";
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


function grabarTarifario(){   
	//	var personaCodigo = $('#personaCodigo').val();   
		var contextPath = $('#contextPath').val(); 
		var actionForm = $('#frmGuardarTarifario').attr("action");
		
	/*	if (personaCodigo =="") {
			msg_advertencia("Debe ingresar una persona para lo Orden."); 
			return;
			
		} */
		console.log("codigo  :: " + codigo) ; 
		var myFormulario = $('#frmGuardarTarifario');  
		
		if(!myFormulario[0].checkValidity()) {
			 msg_advertencia("Debe completar los campos requeridos(*) correctamente");

		}else{ 
			/*if(listadoExamenes.length == 0){
				msg_advertencia("Ingrese al menos una Orden de exámen.");
				return;
			
			
			}  */
				iniciarBloqueo();
				$.ajax({
				type: "POST",
				data: $('#frmGuardarTarifario').serialize(),
				url : contextPath+"/tarifarioController/grabarTarifario",  
		       
				success : function(data) {
					   // console.log("SUCCESS: ", data);
					    if (data == "") {
					    	msg_error("Error al registrar Tarifario");  
						}else{
						    msg_exito("Éxito al registrar Tarifario");  
						    // enviarListado();
							 $("#btnListado").trigger("click");
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