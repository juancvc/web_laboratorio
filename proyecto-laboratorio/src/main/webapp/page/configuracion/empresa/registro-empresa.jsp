<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es" data-textdirection="ltr" class="loading">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Stack admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
<meta name="author" content="">
<title>Registro de Empresa</title>
<!-- Bootstrap core CSS-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i%7COpen+Sans:300,300i,400,400i,600,600i,700,700i"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/css/plugins/forms/validation/form-validation.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/css/plugins/forms/switch.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/toastr.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/css/plugins/forms/extended/form-extended.css">
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/app-assets/css/sb-admin.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/app-assets/css/estilos-cayetano.css"
	rel="stylesheet">

<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/app-assets/images/ico/icoTeamSoftti32x32.png">
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/tables/datatable/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/forms/selects/select2.min.css">
<!-- Custom styles for this template-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/simple-line-icons/style.min.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/feather/style.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/font-awesome/css/font-awesome.min.css">

	<style>
	.msj{ 
		  font-size: 8px; 
		  color:#f20c2b; 
		} 
	</style>

</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav"> 
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<jsp:include page="/layout/menu-view.jsp" />
		</div>
	</nav>

	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Breadcrumbs
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Configuración /
						Maestra</a></li>
				<li class="breadcrumb-item active">Registro de maestra</li>
			</ol>-->
			<div class="tituloForm">DATOS EMPRESA</div>
			<!-- Icon Cards-->
			<!-- Area Chart Example-->
			<div class="card mb-3">
				<f:form id="frmRegistroEmpresa" class="form-horizontal" role="form"
					method="post" onsubmit="return false" action="">
					<input id="contextPath" type="hidden"
						value="${pageContext.request.contextPath}">
					<div class="card-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-4">
									<label for="exampleInputName" class="label_control">NRO
										RUC <span class="required">*</span>
									</label>
									<f:input type="text" class="form-control" id="nroRUC" value=""
										disabled="true" path="nroRUC" required="required"
										maxlength="11" />
								</div>
								<div class="col-md-6">
									<label for="exampleInputName" class="label_control">NOMBRE</label>
									<f:input type="text" class="form-control" id="descripcion"
										path="nombreEmpresa" maxlength="350" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<label for="exampleInputName" class="label_control">NOMBRE
										COMERCIAL <span class="required">*</span>
									</label>
									<f:input type="text" class="form-control" id="nombreComercial"
										value="" path="nombreComercial" required="required"
										maxlength="11" />
								</div>
								<div class="col-md-6">
									<label for="exampleInputName" class="label_control">DIRECCION</label>
									<f:input type="text" class="form-control" id="domicilioFiscal"
										path="domicilioFiscal" maxlength="350" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<label for="exampleInputName" class="label_control">TELEFONO
									</label>
									<f:input type="text" class="form-control" id="telefonoCelular"
										value="" path="telefonoCelular"
										maxlength="11" />
								</div>
								<div class="col-md-6">
									<label for="exampleInputName" class="label_control">CORREO</label>
									<f:input type="text" class="form-control" id="correo"
										path="correo" maxlength="350" />
								</div>
							</div>
							<div class="row">  
							
							<div class="col-md-8">
									<label for="exampleInputName" class="label_control">LOGO
									</label>
								<f:input type="file" path="logo" name="fileEmpresaLogo" 
											id="fileEmpresaLogo" 
											onchange="return validar_file('fileEmpresaLogo','imgEmpresa','${pageContext.request.contextPath}/empresaLogo/${nroRUC}','000001')" 
											
											value="" accept="image/png, .jpeg, .jpg, image/gif" style="display:none" />
											<div id="list1"   style="cursor: pointer; " class="form-group col-md-12"  >
											<c:choose>
											<c:when test="${empresaBean.nombreLogo != ''}">
											<label for=""><img id="imgEmpresa" onclick="abrir_input('fileEmpresaLogo')" 
											src="${pageContext.request.contextPath}/empresaLogo/${empresaBean.nombreLogo}" alt=""  
											class="img-fluid thumb-mini" data-toggle="modal" data-target="#xlarge" style="width: 200px; height: 100px;cursor: pointer;"></label>
											<div id="fake-btn-1" style="display:none" class="form-input text-xs-center truncate"></div>
												</c:when>
											
											<c:otherwise>
											<label for=""><img id="imgEmpresa"
											
											 src="${pageContext.request.contextPath}/assets/img/700x300.jpg"  
											onclick="abrir_input('fileEmpresaLogo')" alt="element 01" class="img-fluid thumb-mini" style="width: 200px; height: 100px;cursor: pointer;"></label> 
												</c:otherwise>
												</c:choose>
											</div>	
								</div> 
							</div>
						</div>
						
						<div class="row">
						<div class="form-group col-md-10"> 
						<span class='msj'>* Los cambios se reflejarán al reiniciar sesión</span>
						</div>
						</div> 
						<div class="row">
							<div class="form-group col-md-10 text-right"
								style="margin-top: 15px;"> 
								<button id="btn-save-reg" type="submit"
									class="btn btn-flat btn-primary" onclick="guardarEmpresa()">
									<i class="fa fa-floppy-o"></i> GUARDAR
								</button>


							</div>
						</div>
					</div>
				</f:form>
				<f:form id="frmRegistro" class="form-horizontal" role="form"
					method="POST"
					action="${pageContext.request.contextPath}/maestraController/listado">
					<button type="submit" style="display: none" id="btnListado">Click
						me</button>
				</f:form>
			</div>

			<!-- Example DataTables Card-->

		</div>
		<div class="modal fade text-xs-left" id="md_confirmacion"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel19"
			aria-hidden="true">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="label_title_modal modal-header">

						<h4 class="label_title" id="myModalLabel19">Confirma
							Acci&oacute;n</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="txt_confir" class="label_control">¿Está seguro de
							eliminar registro seleccionado?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn grey btn-outline-secondary"
							data-dismiss="modal">Cerrar</button>
						<button id="btnConfirmarGeneric" type="button"
							class="btn btn-outline-primary">Confirmar</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade text-xs-left" id="modalEstablecimiento"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
			data-dismiss="modal" aria-hidden="true" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content" id="modalEstablecimientoContent"></div>
			</div>
		</div>
		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<jsp:include page="/layout/footer-view.jsp" />
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>

		<!-- Bootstrap core JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/jquery/jquery.min.js"></script>


		<!-- Page level plugin JavaScript-->
		<!-- Custom scripts for all pages-->

		<!-- Custom scripts for this page-->
		<script
			src="${pageContext.request.contextPath}/app-assets/js/sb-admin-datatables.min.js"></script>
		<!-- Custom scripts for this page-->

		<!-- Core plugin JavaScript-->
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
			type="text/javascript" charset="utf-8"></script>
		<!-- Page level plugin JavaScript-->
		<!-- Bootstrap core JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/jquery/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- Core plugin JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/jquery-easing/jquery.easing.min.js"></script>
		<!-- Page level plugin JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/datatables/jquery.dataTables.js"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/datatables/dataTables.bootstrap4.js"></script>
		<!-- Custom scripts for all pages-->
		<script
			src="${pageContext.request.contextPath}/app-assets/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<script
			src="${pageContext.request.contextPath}/app-assets/js/sb-admin-datatables.min.js"></script>
		<!-- Custom scripts for all pages-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/toastr.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/ui/blockUI.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/js/scripts/extensions/block-ui.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/sweetalert.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/js/scripts/extensions/sweet-alerts.js"
			type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/assets/js/scripts.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/assets/js/page/general/empresa.js"
			type="text/javascript" charset="utf-8"></script>


		<script src="http://malsup.github.io/jquery.blockUI.js"></script>
	</div>
</body>

<script type="text/javascript">
/*
$(document).ready(function() {
console.log("logo empresa " + "${empresaBean.logo}");
	var image = new Image();
	var src = contextPath
			+ "/assets/img/700x300.jpg";
	if ("${empresaBean.logo}" != null) {
		src = "data:image/png;base64,"
				+ "${empresaBean.logoCadena}";
	}
	console.log("src" + src);
	image.src = src;
	image.alt = "Image"
	// $('#imgFotoPaciente').append(image);
	$("#file-preview")
			.attr("src", image.src);
	
	
});
*/
	function validar() {

	}
	function abrir_input(idinput) {
		document.getElementById(idinput).click();
	}
	

    function validar_file(fileid,imgid,imgsrc,idcodigo){
/*    debugger;*/
    var fileInput = document.getElementById(fileid);
    var filePath  = fileInput.value;
    var allowedExtensions = /(.jpg|.jpeg|.png|.gif|.svg)$/i;
    var extensionvacia    = /()$/i;
    if(allowedExtensions.exec(filePath)){
       if (fileInput.files && fileInput.files[0]) {
           var reader = new FileReader();
           reader.onload = function(e) { 
        	   $('#'+imgid).attr('src',''+e.target.result+''); 
           };
           reader.readAsDataURL(fileInput.files[0]);
       } 
    }else{ 
       if(extensionvacia.exec(filePath) && (filePath=='')) {
           limpiar_img(imgid,imgsrc,idcodigo);  
           return false;  
       } else {
           msg_info("extensión no válida");
           fileInput.value = '';
           limpiar_img(imgid,imgsrc,idcodigo);
           return false; 
       }
    } 
}
    
    var path = null;
	$(document).ready(function() {
		path = document.getElementById("contextPath").value;

	});
    
</script>
</html>
