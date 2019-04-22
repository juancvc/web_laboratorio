<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Registro de Orden</title>
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
	href="${pageContext.request.contextPath}/app-assets/images/ico/logoLabico.ico">
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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src='js_propio/cookies.js'></script>
<script src='js_propio/funciones.js'></script>
<script src='js_propio/eventos.js'></script>


<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="assets/css/vendor.css">
<link rel="stylesheet" type="text/css" href="assets/css/flat-admin.css">

<!-- Theme -->
<link rel="stylesheet" type="text/css"
	href="assets/css/theme/blue-sky.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme/blue.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme/red.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/theme/yellow.css">
</head>

<style>
#datepicker {
	width: 180px;
	margin: 0 20px 20px 20px;
}

#datepicker>span:hover {
	cursor: pointer;
}
</style>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand label_control_hade" href="#"> LABMED </a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>


		<div class="collapse navbar-collapse" id="navbarResponsive">
			<jsp:include
				page="${pageContext.request.contextPath}/../layout/menu-view.jsp" />
		</div>
	</nav>

	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="tituloForm">REGISTRO DE PROCEDIMIENTOS Y TARIFAS</div>
			<div class="card mb-3">
				<f:form id="frmGuardarCampania" class="form-horizontal" role="form"
					method="post" action="" onsubmit="return false">
					<input id="contextPath" type="hidden"
						value="${pageContext.request.contextPath}">
					<f:input type="hidden" id="codigo" path="codigo" />
					<f:input type="hidden" id="numeroVersion" path="numeroVersion" />
					<f:input type="hidden" id="numeroPeriodo" path="numeroPeriodo" />
					<f:input type="hidden" id="codigoOrganizacion"
						path="codigoOrganizacion" />
					<f:input type="hidden" id="codigoSede" path="codigoSede" />
					<f:input type="hidden" id="codigoInstitucion"
						path="codigoInstitucion" />

					<div class="card-body">
						<div class="form-group">
						<div class="label_title">DATOS DE PACIENTE :</div>
						<div class="row">
								<div class="form-group col-md-3 mb-1">
									<label for="situacion" class="label_control">TIPO
										<span class="required">*</span>
									</label>
									<div class="controls">
										<f:select id="tipoDocumentoPaciente"
											path="tipo.codReg" required="required"
											class="form-control" onchange="limpiarPorTipo()">
											<f:options items="${lstDocumento}" itemValue="codReg"
												itemLabel="nombreCorto" />
										</f:select>
									</div>
								</div>
								<div class="form-group col-md-3 mb-1">
									SUB-TIPO <span class="required">*</span>
									
									<div class="position-relative has-icon-left">
										<input id="contextPath" type="hidden"
											value="${pageContext.request.contextPath}">
										<div class="controls">
											<f:input type="text" class="form-control" required="required"
												maxlength="12" id="nroDocumentoPaciente"
												path="subtipo"
												onkeypress="return runScript(event)" />
										</div>
									</div>
								</div>
								
								 
							</div>
							<div class="row">
								<div class="form-group col-md-3 mb-1">
									<label for="nombreCompleto" class="label_control">DESCRIPCION <span class="required">*</span>
									</label>
									<div class="controls">
										<f:input type="text" class="form-control" required="required"
											onkeyup="javascript:this.value=this.value.toUpperCase();"
											id="personaApellidoPaterno" disabled="true"
											path="descripcion" />

									</div>
								</div>
					
								
								
							</div>
							<div class="row">
							
							
								<div class="form-group col-md-3 mb-1">
									<label for="nombreCompleto" class="label_control">EDAD
									</label>
									<div class="controls">
										<f:input type="text" class="form-control" required="required"
											disabled="true" id="edadPersona" path="precio" />

									</div>
								</div>
								<div class="form-group col-md-3 mb-2">
									<label for="nombreCompleto" class="label_control">PRECIO
									</label>
									<div class="controls">
										<f:input type="text" class="form-control" required="required"
											disabled="true" id="edadPersona" path="precio" />

									</div>
								</div>
								</div>
						  
					 
						</div>
						<div class="row">
							<div class="form-group col-md-12 text-right"
								style="margin-top: 15px;">
								<a
									href="${pageContext.request.contextPath}/bancoController/nuevaCampania"
									class="btn btn-secondary" title=""> <i class="fa icon-plus"></i>
									NUEVO
								</a> 

								<button type="submit" onclick="grabarCampania()"
									class="btn btn-primary">
									<i class="fa fa-floppy-o"></i> REGISTRAR
								</button>
							</div>
						</div>
					</div>

				</f:form>
				<f:form id="frmRegistro" class="form-horizontal" role="form"
					method="POST"
					action="${pageContext.request.contextPath}/bancoController/listadoCampania">
					<button type="submit" style="display: none" id="btnListado">Click
						me</button>
				</f:form>
			</div>
		
	<div class="modal fade text-xs-left" id="modalTarifario"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
			data-dismiss="modal" aria-hidden="true" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content" id="modalEstablecimientoContent"></div>
			</div>
		</div>
		
			<!-- Example DataTables Card-->

		</div>
		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<jsp:include
			page="${pageContext.request.contextPath}/../layout/footer-view.jsp" />
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
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
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

		<script
			src="${pageContext.request.contextPath}/assets/js/page/asistencial/laboratorio.js"
			type="text/javascript" charset="utf-8"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/toastr.min.js"
			type="text/javascript"></script>

		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/sweetalert.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/js/scripts/extensions/sweet-alerts.js"
			type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/assets/js/scripts.js"
			type="text/javascript"></script>

		<script type="text/javascript" src="assets/js/vendor.js"></script>
		<script type="text/javascript" src="assets/js/app.js"></script>
		<script src='js_propio/cookies.js'></script>

		<!-- Include Date Range Picker -->

		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
		<script src="js/moment.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/locales/bootstrap-datepicker.es.min.js"></script>




		<script>
			$(document)
					.ready(
							function() {
								var date_input = $('input[id="date"]'); //our date input has the name "date"
								var container = $('.bootstrap-iso form').length > 0 ? $(
										'.bootstrap-iso form').parent()
										: "body";
								date_input.datepicker({
									format : 'dd/mm/yyyy',
									container : container,
									todayHighlight : true,
									autoclose : true,
									language : 'es'

								})
							})

			$(document)
					.ready(
							function() {
								var date_inputHasta = $('input[id="dateHasta"]'); //our date input has the name "date"
								var containerHasta = $('.bootstrap-iso form').length > 0 ? $(
										'.bootstrap-iso form').parent()
										: "body";
								date_inputHasta.datepicker({
									format : 'dd/mm/yyyy',
									container : containerHasta,
									todayHighlight : true,
									autoclose : true,
									language : 'es'

								})
							})
		</script>
	</div>
</body>

</html>










