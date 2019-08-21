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
<title>LABMED</title>

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
</head>

<style>
#datepicker {
	width: 180px;
	margin: 0 20px 20px 20px;
}

#datepicker>span:hover {
	cursor: pointer;
}

.card-body {
	-webkit-box-flex: 1;
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	padding: 0.9rem;
	border: 1px solid rgba(230, 212, 212, 0.33)
}

.titulo {
	font-family: Calibri ( Cuerpo);
	font-size: 13px;
	font-weight: bold;
	color: #00000082;
}

.spnResultado {
font-family: Cambria;
	color:   #000000 ;
	font-size: 14px;  
}

.titulo_ord {
	font-family: Calibri ( Cuerpo);
	font-size: 13px;
	font-weight: bold;
	color: #FFFFF;
}

.valor {
	color: #0274d8;
}

.detalle {
	font-family: Calibri;
	font-size: 13px;
	color: #5611afc9;
}

.btn-primary {
	color: var(- -button-color);
	background-color: var(- -button-background-color);
	border-radius: var(- -border-radius);
}

.btn-primary:hover {
	box-shadow: inset 0 0 0 20rem var(- -darken-1);
}

.btn-primary:active {
	box-shadow: inset 0 0 0 20rem var(- -darken-2), inset 0 3px 4px 0
		var(- -darken-3), 0 0 1px var(- -darken-2);
}

.btn-primary:disabled, .btn-primary.is-disabled {
	opacity: .5;
}

:root { -
	-button-color: white; -
	-button-background-color: green;
}
</style>
<f:form id="frmGuardarOrden" class="form-horizontal" role="form"
	method="post" action="" onsubmit="return false">
	<body class="fixed-nav sticky-footer bg-dark" id="page-top">
		<!-- Navigation-->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
			id="mainNav">
			<a class="navbar-brand label_control_hade" href="#l"> DASHBOARD</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<input id="contextPath" type="hidden"
				value="${pageContext.request.contextPath}">

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<jsp:include
					page="${pageContext.request.contextPath}/../layout/menu-view.jsp" />
			</div>
		</nav>
		<div class="content-wrapper">
			<div class="container-fluid">
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
					<li class="breadcrumb-item active">Tablero de Datos</li>
				</ol>
				<!-- Icon Cards-->

<div class="card-header">
	<div class="row">
		<div class="col-sm-4 ">
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" id="materialInline1"
							<c:if test="${uOrdenBeanVentadiaria.swDiario!=null || uOrdenBeanVentadiaria.swDiario==true}">
			checked 
			</c:if>
							name="inlineMaterialRadiosExample"
							onclick="refrescarListadoVentasDiario();"> <label
							class="form-check-label" for="materialInline1">Diario</label>
					</div>
					<!-- Material inline 2 -->
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" id="materialInline2"
							<c:if test="${uOrdenBeanVentadiaria.swSemanal!=null || uOrdenBeanVentadiaria.swSemanal==true}">
			checked 
			</c:if>
							name="inlineMaterialRadiosExample"
							onclick="refrescarListadoVentasSemanal();"> <label
							class="form-check-label" for="materialInline2">Semanal</label>
					</div>

					<!-- Material inline 3 -->
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" id="materialInline3"
			<c:if test="${uOrdenBeanVentadiaria.swMensual!=null || uOrdenBeanVentadiaria.swMensual==true}">
			checked 
			</c:if>
							name="inlineMaterialRadiosExample"
							onclick="refrescarListadoVentasMensual();"> <label
							class="form-check-label" for="materialInline3">Mensual</label>
					</div>

					<!-- Material inline 4 -->
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" id="materialInline4"
							<c:if test="${uOrdenBeanVentadiaria.swAnual!=null || uOrdenBeanVentadiaria.swAnual==true}">
			checked 
			</c:if>
							name="inlineMaterialRadiosExample"
							onclick="refrescarListadoVentasAnual();"> <label
							class="form-check-label" for="materialInline4">Anual</label>
					</div>
					</div>
				
					<c:if test="${uOrdenBeanVentadiaria.swDiario!=null || uOrdenBeanVentadiaria.swDiario==true}">
					<div class="col-sm-4 ">
						<f:input class="form-control" id="date" name="date"
							placeholder="DD/MM/YYYY" type="text" path="sFecha" />
					</div>	
					<div class="col-sm-2 ">
					<button type="button"  
						class="btn btn-outline-success btn-sm"
						data-toggle="tooltip" data-placement="top" title=""
						data-original-title="Agregar"
						onclick="refrescar()"
						id="refrescar">
						<i class="icon-refresh"> Refrescar</i> 
					</button>  
				</div>	
				<div class="col-sm-2 "> 
					</div>			
					</c:if>
					
					
					
				<c:if test="${uOrdenBeanVentadiaria.swSemanal!=null || uOrdenBeanVentadiaria.swSemanal==true}">
				<div class="col-sm-1 form-group  text-right"> 
				<label for="situacion" class="label_control  text-right">AÑO
									</label> 
							</div>
							
				<div class="col-sm-1 ">  
						<f:input class="form-control"  type="year" id="year" placeholder="Año" path="periodo"  /> 
							</div>
										
				<div class="col-sm-1  form-group  text-right">  
					<label for="situacion" class="label_control">SEMANA
									</label> 
							</div>
				<div class="col-sm-1 ">   
						<f:input class="form-control"  type="number" id="week"  maxlength="2" placeholder="Semana" min="1" max="53" path="nroSemana"  /> 
							</div>
										
					<div class="col-sm-1 " style="margin-top: 5px;">    
						<button type="button"  
								class="btn btn-outline-success btn-sm"
								data-toggle="tooltip" data-placement="top" title=""
								data-original-title="Refrescar"
								onclick="calculaSemana()"
								id="refrescar">
								<i class="icon-refresh"> Refrescar</i> 
							</button>  
					</div>
					<div class="col-sm-3 form-group text-left"  >    
							<span  class="spnResultado"   id="resultado"></span>   
					</div>						
				</c:if>	 		
			</div>	
		</div>

				<br>

				<div class="row">
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-black o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon"></div>
								<div class="mr-5 titulo">${nombreVenta}</div>
								<h1 id="cantVentaTotal" class="valor" align="center">S/.
									${uOrdenBeanVentadiaria.cantidadVentas}</h1>
							</div>
							<!-- 
            <a class="card-footer text-black clearfix small z-1" href="">
              <span class="float-left detalle" onclick="cargarVentaModal();"  >VER DETALLE</span>
               
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span> 
            </a>  
             -->
							<button type="button" onclick="cargarVentaModal()"
								class="btn btn-primary">
								<i class="fa fa-angle-right"></i> VER DETALLE
							</button>

							<a id="idDescargarExcel" class="btn btn-primary mb1 bg-olive"
								target="_Blank"
								href="<c:url value='/inicioController/descargarExcelTipo'/>">
								<i class="fa fa-file-excel-o"></i> Excel
							</a>

						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-warning o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<!-- <i class="fa fa-fw fa-list"></i> -->
								</div>
								<div class="mr-5 titulo_ord">ORDENES PENDIENTES</div>
								<h1 align="center">${uOrderBean.cantidadOrdenes}</h1>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">Ver Detalle</span> <span
								class="float-right"> <i class="fa fa-angle-right"></i>
							</span>
							</a> <a id="idDescargarExcel2" class="btn btn-primary mb1 bg-green"
								target="_Blank"
								href="<c:url value='/inicioController/descargarExcelTipo'/>">
								<i class="fa fa-file-excel-o"></i> Excel
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-success o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon"></div>
								<div class="mr-5 titulo_ord">ORDENES REALIZADOS</div>
								<h1 align="center">${uOrdenBean2.cantidadOrdenes}</h1>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">Ver Detalle</span> <span
								class="float-right"> <i class="fa fa-angle-right"></i>
							</span>
							</a> <a id="idDescargarExcel3" class="btn btn-primary mb1 bg-olive"
								target="_Blank"
								href="<c:url value='/inicioController/descargarExcel'/>"> <i
								class="fa fa-file-excel-o"></i> Excel
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-danger o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon"></div>
								<div class="mr-5 titulo_ord">ORDENES ANULADOS</div>
								<h1 align="center">${uOrdenBean3.cantidadOrdenes}</h1>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#">
								<span class="float-left">Ver Detalle</span> <span
								class="float-right"> <i class="fa fa-angle-right"></i>
							</span>
							</a> <a id="idDescargarExcel4" class="btn btn-primary mb1 bg-olive"
								target="_Blank"
								href="<c:url value='/inicioController/descargarExcelDiario'/>">
								<i class="fa fa-file-excel-o"></i> Excel
							</a>
						</div>
					</div>
				</div>
				<!-- Area Chart Example-->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fa fa-area-chart"></i> Gráfico de área(Resumen día a día
						de ingresos en el mes)
					</div>
					<div class="card-body">
						<canvas id="Areaportada" width="100%" height="30" ></canvas>
					</div>
					<div class="card-footer small text-muted">${diaSemana}</div>
				</div>
				<div class="row">
					<div class="col-lg-8">
						<!-- Example Bar Chart Card-->
						<div class="card mb-3">
							<div class="card-header">
								<i class="fa fa-bar-chart"></i>Gráfico de barras(Resumen mes a
								mes de ingresos en el año)
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-sm-8 my-auto">
										<canvas id="barraPortada" width="100" height="50"></canvas>
									</div>
									<div class="col-sm-4 text-center my-auto">
										<div class="h4 mb-0 text-primary">$34,693</div>
										<div class="small text-muted">YTD Revenue</div>
										<hr>
										<div class="h4 mb-0 text-warning">$18,474</div>
										<div class="small text-muted">YTD Expenses</div>
										<hr>
										<div class="h4 mb-0 text-success">$16,219</div>
										<div class="small text-muted">YTD Margin</div>
									</div>
								</div>
							</div>
							<div class="card-footer small text-muted">${diaSemana}</div>
						</div>
						<!-- /Card Columns-->
					</div>
					<div class="col-lg-4">
						<!-- Example Pie Chart Card-->
						<div class="card mb-3">
							<div class="card-header">
								<i class="fa fa-pie-chart"></i>Gráfico circular(los 4 exámenes
								con mayor demanda)
							</div>
							<div class="card-body">
								<canvas id="myPieChart" width="100%" height="100"></canvas>
							</div>
							<div class="card-footer small text-muted">${diaSemana}</div>
						</div>
						<!-- Example Notifications Card-->

					</div>
				</div>
				<!-- Example DataTables Card-->
				<div class="card mb-3" style="display: none">
					<div class="card-header">
						<i class="fa fa-table"></i> Data Table Example
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>Name</th>
										<th>Position</th>
										<th>Office</th>
										<th>Age</th>
										<th>Start date</th>
										<th>Salary</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Name</th>
										<th>Position</th>
										<th>Office</th>
										<th>Age</th>
										<th>Start date</th>
										<th>Salary</th>
									</tr>
								</tfoot>
								<tbody> 
									<tr>
										<td>Ashton Cox</td>
										<td>Junior Technical Author</td>
										<td>San Francisco</td>
										<td>66</td>
										<td>2009/01/12</td>
										<td>$86,000</td>
									</tr>
									<tr>
										<td>Cedric Kelly</td>
										<td>Senior Javascript Developer</td>
										<td>Edinburgh</td>
										<td>22</td>
										<td>2012/03/29</td>
										<td>$433,060</td>
									</tr>
									<tr>
										<td>Airi Satou</td>
										<td>Accountant</td>
										<td>Tokyo</td>
										<td>33</td>
										<td>2008/11/28</td>
										<td>$162,700</td>
									</tr>
									<tr>
										<td>Brielle Williamson</td>
										<td>Integration Specialist</td>
										<td>New York</td>
										<td>61</td>
										<td>2012/12/02</td>
										<td>$372,000</td>
									</tr>
									<tr>
										<td>Herrod Chandler</td>
										<td>Sales Assistant</td>
										<td>San Francisco</td>
										<td>59</td>
										<td>2012/08/06</td>
										<td>$137,500</td>
									</tr> 
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted">${diaSemana}</div>
				</div>
			</div>
			<!-- /.container-fluid-->
			<!-- /.content-wrapper-->
			<footer class="sticky-footer">
				<div class="container">
					<div class="text-center">
						<small>Copyright © Laboratorios LABMED 2019</small>
					</div>
				</div>
			</footer>
			<!-- Scroll to Top Button-->
			<a class="scroll-to-top rounded" href="#page-top"> <i
				class="fa fa-angle-up"></i>
			</a>
			<!-- Logout Modal-->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Ready to
								Leave?</h5>
							<button class="close" type="button" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">Ã</span>
							</button>
						</div>
						<div class="modal-body">Select "Logout" below if you are
							ready to end your current session.</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">Cancel</button>
							<a class="btn btn-primary" href="login.html">Logout</a>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade text-xs-left" id="modalVentaDiaria"
				tabindex="-2" role="dialog" aria-labelledby="myModalLabel35"
				data-dismiss="modal" aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content" id="modalVentaDiariaContent"></div>
				</div>
			</div>

			<a class="nav-link" style="display: none"
				href="${pageContext.request.contextPath}/inicioController/portadaListarDiario"><i
				class="fa fa-home fa-fw"> </i><span id="spnDiario"
				class="nav-link-text label_control_barra"></span></a> <a
				class="nav-link" style="display: none"
				href="${pageContext.request.contextPath}/inicioController/portadaListarSemanal"><i
				class="fa fa-home fa-fw"> </i><span id="spnSemanal"
				style="display: none" class="nav-link-text label_control_barra"></span></a>
			<a class="nav-link" style="display: none"
				href="${pageContext.request.contextPath}/inicioController/portadaListarMensual"><i
				class="fa fa-home fa-fw"> </i><span id="spnMensual"
				style="display: none" class="nav-link-text label_control_barra"></span></a>
			<a class="nav-link" style="display: none"
				href="${pageContext.request.contextPath}/inicioController/portadaListarAnual"><i
				class="fa fa-home fa-fw"> </i><span id="spnAnual"
				style="display: none" class="nav-link-text label_control_barra"></span></a>

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
				src="${pageContext.request.contextPath}/app-assets/vendor/chart.js/Chart.min.js"></script>
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
				src="${pageContext.request.contextPath}/app-assets/js/sb-admin-charts.min.js"></script>


			<script
				src="${pageContext.request.contextPath}/assets/js/page/general/portada.js"
				type="text/javascript" charset="utf-8"></script>
				
				<script
				src="${pageContext.request.contextPath}/app-assets/js/area.js"
				type="text/javascript" charset="utf-8"></script>

			<script type="text/javascript"
				src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

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
			</script>

		</div>
	</body>
</f:form>
<script>

var arrayMenus = []; 
	$(document).ready(function() {
		console.log("log");
		
		<c:forEach var="orden" items="${lstOrdenBeanArea}"
			varStatus="loop">
		var objOrden = {
				strFechaOrden : "",
				cantidadVentas		: ""
		  	};
		objOrden.strFechaOrden ='${orden.strFechaOrden}'; 
		objOrden.cantidadVentas ='${orden.cantidadVentas}'; 
			  arrayMenus.push(objOrden);  
		</c:forEach>
		
		
		area(arrayMenus);
		
	var arrayBarra = [];
	
	<c:forEach var="orden" items="${lstOrdenBeanBarra}"
		varStatus="loop">
	var objOrdenBarra = {
			nombreMes : "",
			cantidadVentas		: ""
	  	};
	objOrdenBarra.nombreMes ='${orden.nombreMes}'; 
	objOrdenBarra.cantidadVentas ='${orden.cantidadVentas}'; 
	arrayBarra.push(objOrdenBarra);  
	</c:forEach>
	
	
	barra(arrayBarra);
		//init();
	});
	function init() {
		//	document.getElementById("cantVenta").innerHTML ="S/."+${uOrdenBeanVentadiaria.cantidadVentas};
	}
</script>
</html>
