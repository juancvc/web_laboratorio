
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Listado de Usuario</title>
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



</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav"> 
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<jsp:include
				page="/layout/menu-view.jsp" />
		</div>
	</nav>

	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Breadcrumbs
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Seguridad / Usuario</a></li>
				<li class="breadcrumb-item active">Listado de usuario</li>
			</ol>-->
			<div class="tituloForm">LISTADO DE USUARIO</div>
			<!-- Icon Cards--> 
			<!-- Area Chart Example-->
			<div class="card mb-3">
				<div class="divCabeceraPanel"><img id ="imgFotoPaciente" src="${pageContext.request.contextPath}/assets/img/busqueda.png" > FILTROS</div>
				<f:form id="frmListadoPerfil" class="form-horizontal" role="form"
					enctype="multipart/form-data" method="post"
					action="${pageContext.request.contextPath}/usuarioController/buscar">
					<input id="contextPath" type="hidden"
						value="${pageContext.request.contextPath}">

					<div class="card-body">
						<div class="form-group">

							<div class="row">

								<div class="col-md-4">
									<label for="exampleInputName" class="label_control">NOMBRE DE USUARIO</label>
									<f:input type="text" class="form-control" id="txtNombreUsuario"
										path="nombreUsuario" maxlength="25" />
								</div>
								<div class="col-md-4 ">
									<label for="exampleInputName" class="label_control">PERFIL
									</label>
									<div class="controls">
										<f:select id="comboPerfil" path="perfil.codigo"
											class="form-control">
											<f:option value="" label="Todos" selected="true"
												disabled="disabled" />
											<f:options items="${lstPerfiles}" itemValue="codigo"
												itemLabel="nombrePerfil" />
										</f:select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12 text-right"
									style="margin-top: 15px;">
									<button id="btnRefrescarListaUser" class="btn btn-info"
										type="submit">
										<i class="ft-search"></i> BUSCAR
									</button>
									<button
										onclick="limpiarForm();$('#dataTable').dataTable().fnClearTable();"
										type="button" class="btn btn-flat btn-default">
										<i class="fa fa-eraser"></i> LIMPIAR
									</button>
									<a
										href="${pageContext.request.contextPath}/usuarioController/nuevo"
										class="btn btn-secondary" title=""> <i class="icon-plus"></i>
										NUEVO
									</a>
								</div>
							</div>
						</div>
					</div>
				</f:form>

			</div>
		<div class="card mb-3">
				<div class="divCabeceraPanel"><img id ="imgFotoPaciente" src="${pageContext.request.contextPath}/assets/img/listado.png" > LISTADO</div>
				<div class="card-body">
					<div class="form-group"> 
							<div class="row">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable">
										<thead class="tabla_th">
											<tr>
												<th width="30">ITEM</th>
												<th>NOMBRE USUARIO</th>
												<th>PERFIL</th>
												<th width="50">ACCIONES</th>
											</tr>
										</thead>
										<tbody id="idBodyListaCIEX">
											<c:forEach var="usuario" items="${lstUsuarioBean}"
												varStatus="loop">
												<tr>
													<td>${loop.count}</td>
													<td>${usuario.nombreUsuario}</td>
													<td>${usuario.perfil.nombrePerfil}</td>

													<td><c:choose>
									<c:when test="${usuario.codigo=='000001'}"> 
									</c:when>
									<c:otherwise>
									<a title="Modificar" data-placement="top"
														data-toggle="tooltip"
														class="btn btn-outline-success btn-sm"
														onclick="javascript:modificarElementoGenerico('/usuarioController/modificar','${usuario.codigo}')"
														href="#"><i class="icon-pencil"></i></a> <!--   <a title="Eliminar" data-placement="top" data-toggle="tooltip"
                                                         class="btn btn-outline-danger btn-sm" href="eliminar?codigo=${lenguaBean.codigo}"><i class="icon-trash"></i></a> -->
								
									<button type="button"
															class="btn btn-outline-danger btn-sm eliminarPerfil"
															data-toggle="tooltip" data-placement="top" title=""
															onclick="confirmar_accion('${usuario.codigo}')"
															data-original-title="Eliminar"
															id="eliminarPerfil${usuario.codigo}">
															<i class="icon-trash"></i>
														</button>
									</c:otherwise>
								</c:choose>
								
														</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
					</div>
					</div>
					</div>
			<!-- Example DataTables Card-->

		</div>

		<div class="modal fade text-xs-left" id="md_confirmacion"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel19"
			aria-hidden="true">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="label_title_modal modal-header">

						<h4 class="label_title" id="myModalLabel19">CONFIRMA
							ACCION</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="txt_confir" class="label_control">¿ESTÁ SEGURO DE ELIMINAR REGISTRO SELECCIONADO?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn grey btn-outline-secondary"
							data-dismiss="modal"><i class="fa fa-close"></i> CERRAR</button>
						<button id="btnConfirmarGeneric" type="button"
							class="btn btn-outline-primary">CONFIRMAR</button>
					</div>
				</div>
			</div>
		</div>

		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<jsp:include
			page="/layout/footer-view.jsp" />
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>

		<script src="${pageContext.request.contextPath}/assets/js/scripts.js"
			type="text/javascript"></script>

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
		<script
			src="${pageContext.request.contextPath}/assets/js/page/seguridad/usuario.js"
			type="text/javascript" charset="utf-8"></script>
		<!-- Custom scripts for all pages-->
	</div>
</body>
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

<script>
	function limpiarForm() {
		$("#comboPerfil").val("");
		$("#txtNombreUsuario").val("");
	}
</script>
</html>
