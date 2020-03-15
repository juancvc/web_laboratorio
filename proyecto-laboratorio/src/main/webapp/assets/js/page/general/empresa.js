  /** function readFile(input) {

        if (input.files && input.files[0]) {

            var reader = new FileReader();

 

            reader.onload = function (e) {

                var filePreview = document.getElementById('file-preview');

              //  filePreview.id = 'file-preview';

                //e.target.result contents the base64 data from the image uploaded

                
                filePreview.src = e.target.result;

              // console.log( $('#file-upload').val());

                $('#txtLogo').val(e.target.result.substring(23,e.target.result.length));
                var previewZone = document.getElementById('file-preview-zone');

                previewZone.appendChild(filePreview);
                
              //  console.log("" + e.target.result.substring(23,e.target.result.length));  

            }

 

            reader.readAsDataURL(input.files[0]);

        }

    }
    
        var fileUpload = document.getElementById('file-upload');

    fileUpload.onchange = function (e) {

        readFile(e.srcElement);

    }
 */

function guardarEmpresa(){
	var contextPath = $('#contextPath').val();
	var dataForms = new FormData();
	var nombre = $("#nombre").val();
	var nombreComercial = $("#nombreComercial").val();
	var domicilioFiscal = $("#domicilioFiscal").val();
 	var telefonoCelular = $("#telefonoCelular").val();
	var correo = $("#correo").val(); 
		var inputFileImage = document.getElementById("fileEmpresaLogo");
    	var lfImgRela = inputFileImage.files[0];
//    	dataForms.append('lfImgRela',lfImgRela);
//	} else{
//		console.log("en 1");
//		var lfImgRela = new File([$("#contentImg img").attr("src")],("img--Vacio---"+$("#contentImg img").attr("title")));
//		dataForms.append('lfImgRela',lfImgRela);
//	}
	 
	  
		dataForms.append("nombre",nombre);
		dataForms.append("nombreComercial",nombreComercial);
		dataForms.append("telefono",telefonoCelular);
		dataForms.append("direccion",domicilioFiscal);
		dataForms.append("correo",correo); 
		
		if (lfImgRela!= '' && lfImgRela!=null){
			dataForms.append("imgLogo",lfImgRela);
			}
		
		
		
		//console.log("paso");
		var url = contextPath+"/empresaController/grabarEmpresa"; 
			//console.log("paso 2");
			$.ajax({
	  			type 	: "POST",
	  			data 	: dataForms,
	  			enctype : 'multipart/form-data',
	  			contentType:false,
		   		processData:false,
		   		cache:false,
	  			url 	: url,
	  			dataType : 'json',
	  			success : function(data){
	  				if (data != "0") { 
	  	  				msg_exito(); 
					}
	  				else{
					}
	  			},
	  			error : function(){
	  				//console.log("ERROR: ");
	  			}
	  		});
		
}


    function guardarEmpresa2() {
    	// debugger;
    	var htmlTabla = "";
    	var contextPath = $('#contextPath').val();
    	var actionForm = $('#frmRegistroEmpresa').attr("action");
    	var url = contextPath + "/empresaController/grabar";
    	var myFormulario = $('#frmRegistroEmpresa');
    	var telefono = $('#telefono').val();
    	var codigoRegistroUbigeoDireccion = $('#codigoRegistroUbigeoDireccion').val();
    	
    	// console.log("fechaIni " + fechaIni);
    	// console.log("fechaFin " + fechaFin);

    	if (!myFormulario[0].checkValidity()) {
    		msg_advertencia("Debe completar los campos requeridos(*) correctamente"); 
    		
    	}else {
    		iniciarBloqueo();
    		$.ajax({
    			type : "POST",
    			url : url,
    			data : $('#frmRegistroEmpresa').serialize(),
    			success : function(data) {
    				console.log("SUCCESS: ", data);
    				if (data == "") {
    					msg_error("Error al guardar datos");
    				} else {
    					msg_exito("Éxito al guardar datos");  
    		 
    				}

    			},

    			error : function(xhr, status, er) {
    				console.log("error: " + xhr + " status: " + status + " er:"
    						+ er);
    				// msg_error();

    			},
    			complete : function() {
    				finBloqueo();
    			}
    		});
    	}
    }

 

