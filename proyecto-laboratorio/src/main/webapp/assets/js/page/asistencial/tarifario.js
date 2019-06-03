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



function llenarDetalleTarifario(){
	//debugger; 
	
	var cboTipoResultado = $('#cboTipoResultado').val();
	var contextPath = $('#contextPath').val();
	$.ajax({
		url : contextPath + "/tarifarioController/agregarDetalle",
		type : 'GET',
		data: $('#frmGuardarTarifario').serialize(),
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#tblListaDetalle').html(data);
		},
		error : function() {
			//console.log("ERROR: ");
		}
	});
	/***
var nuevaFila ="";
var fila = $("#tabla tbody tr").length ;
var nfila = Number(fila) + 1;
            // añadimos las columnas

            nuevaFila+="<tr>"+ 
            
            	"<td>"+nfila+"</td>" +
            		"<td><input type='text' class='form-control' "+ 
    										"value = '' required='required'></td>" +
            		"<td><input type='text' class='form-control' "+ 
    										"value = '' required='required'/></td>" +
            		"<td><input type='text' class='form-control' "+ 
    										"value = '' required='required'/></td>" +
    				"<td>"+ 
    		    	 	"<button type='button'"+
    		    	 	" class='btn btn-outline-danger btn-sm' "+
    		    	 	" data-toggle='tooltip'  data-placement='top'  title='Eliminar'"+
    		    	 	"  onclick=\"confirmar_eliminar('"+nfila+"','1');\""+
    		      	 	" data-original-title='Eliminar'"+
    		    	 	" id='agregarEspecialidad'>"+
    		    	 	"<i class='icon-trash'></i></button> "+ 
    				"</td>"
    		    	 	"</tr>";

         

    $("#tabla tbody").append(nuevaFila);
    */
}


function modificarElementoGenerico(url, index){
	var contextPath = $('#contextPath').val();
	var urlComplete = contextPath + url;
	doPost(urlComplete, { index : index } );
}
function doPost(path, params, method) {
    method = method || "post";

    var form = document.createElement("form");

    form._submit_function_ = form.submit;

    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", key);
        hiddenField.setAttribute("value", params[key]);

        form.appendChild(hiddenField);
    }

    document.body.appendChild(form);
    form._submit_function_();
}