<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
* {
	box-sizing: border-box;
}

body {
font-family: Cambria;
font-size: 13px;  
}

/*the container must be positioned relative:*/
.autocomplete {
	/*position: relative;*/
	display: inline-block;
}

input {
	border: 1px solid transparent; 
	font-size: 13px;
}

input[type=text] {
	width: 100%;
}

.autocomplete-items {
	position: absolute;
	border: 1px solid #d4d4d4;
	border-bottom: none;
	border-top: none;
	z-index: 99;
	/*position the autocomplete items to be the same width as the container:*/
	top: 100%;
	left: 0;
	right: 0;
}

.autocomplete-items div {
	padding: 10px;
	cursor: pointer;
	background-color: #fff;
	border-bottom: 1px solid #d4d4d4;
}

/*when hovering an item:*/
.autocomplete-items div:hover {
	background-color: #e9e9e9;
}

/*when navigating through the items using the arrow keys:*/
.autocomplete-active {
	background-color: DodgerBlue !important;
	color: #ffffff;
}
</style>
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/datepicker.css">	

	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/estilo.css">	
<div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
		<div class="modal-header">
			<label class="modal-title text-text-bold-600" id="myModalLabel35"><b>REGISTRO
					DE FORMULA</b></label>
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<f:form id="frmGuardarFormula" name="fo" class="form-horizontal" role="form " 
			method="post" action="" onsubmit="return false">
			<input id="contextPath" type="hidden"
				value="${pageContext.request.contextPath}">

			  
				
			<div class="modal-body" id="buscaFormula" onload="init();">
			
			<div class="row">

					<div class="form-group col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">ANALISIS
							<span class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" required="required"
								
								id="codigoAnalisisFormula" path="codigo" />

						</div>
					</div>
					
				
				</div>	
				

			
<div class="label_title">
								INGRESE LOS RESULTADOS <span class="required">*</span>:
							</div>
							<div id="panelCEX" class="panel_style col-md-12">
						
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead class="tabla_th">
													<tr>
														<th width="30">ITEM</th>
														<th>DESCRIPCION</th>
														<th>TIPO</th>
														<th>UNIDAD MEDIDA</th>
														<th>RESULTADO</th>
														<th>IMPRESION</th>
													</tr>
												</thead>
												<tbody id="idbodyCIEXref" class="label_control">
													<c:forEach var="orden" items="${lstOrdenDetalleItemBean}"
														varStatus="loop">
					<f:input type="hidden" id="codigo" value="${orden.codigo}" path="lstOrdenDetalleItemBean[${loop.index}].codigo" />
					
					<f:input type="hidden" id="numeroVersion" value="${orden.numeroVersion}" path="lstOrdenDetalleBean[${loop.index}].numeroVersion" />
					<f:input type="hidden" id="numeroPeriodo" value="${orden.numeroPeriodo}" path="lstOrdenDetalleBean[${loop.index}].numeroPeriodo" />
					<f:input type="hidden" id="codigoOrganizacion" value="${orden.codigoOrganizacion}" path="lstOrdenDetalleBean[${loop.index}].codigoOrganizacion" />
					<f:input type="hidden" id="codigoSede" value="${orden.codigoSede}" path="lstOrdenDetalleBean[${loop.index}].codigoSede" />
					<f:input type="hidden" id="codigoInstitucion" value="${orden.codigoInstitucion}" path="lstOrdenDetalleBean[${loop.index}].codigoInstitucion" />
														<tr>
															<td>${loop.count}</td>
															<td>${orden.examenesLaboratorioBean.descripcion}</td>
															<td>${orden.examenesLaboratorioBean.tipoExamenAsoc}</td>
															<td>${orden.examenesLaboratorioBean.examenUnidadMedidaLaboratorioBean.abrev}</td>
															<td>
															<c:choose>
															<c:when test="${orden.resultado==null || orden.resultado==''}">
															
																	<f:input type="text" min="1" maxlength="50"
																		class="form-control" required="required"
																		id="tarifarioUnidades" path="lstOrdenDetalleBean[${loop.index}].resultado" />
																
																
																</c:when> 
																<c:otherwise>
																${orden.resultado}
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
			<div class="modal-footer">
				<button type="button" id="btnCerrarModalPaciente"
					class="btn btn-secondary" data-dismiss="modal">
					<i class="fa fa-close"></i> CERRAR
				</button>

				<button type="button" onclick="limpiar()" class="btn btn-default">
					<i class="fa fa-eraser"></i> LIMPIAR
				</button>

				<button type="submit" onclick="grabarResultados()"
					class="btn btn-primary">
					<i class="fa fa-floppy-o"></i> GRABAR
				</button>
				
				<button type="submit"
					class="btn btn-primary">
					<i class="fa fa-print"></i> IMPRIMIR
				</button>
			</div>



		</f:form>
	</div>
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/datepicker.js"
			type="text/javascript" charset="utf-8"></script>
			
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/datepicker.es.min.js"
			type="text/javascript" charset="utf-8"></script>	
			
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
			type="text/javascript" charset="utf-8"></script>
			
	
			
	<script> 
	$(document).ready(function(){
		console.log("log");
	
	
	});		

	function retornar(num){
		console.log("retornar");
	    var anterior=document.fo.resultado.value;

	    document.getElementById("resultado").value=anterior+num;

	}
	
	</script>
	
	
	
   
  
	 
</div>


