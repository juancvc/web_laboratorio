<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <style>
        #dataTable {border:1px solid #ccc;padding:10px;font-size:1em;}
        #dataTable tr:nth-child(even) {background:#F9F9F9;}
        #dataTable td {padding:5px;}
        #dataTable tr.noSearch {background:White;font-size:0.8em;}
        #dataTable tr.noSearch td {padding-top:10px;text-align:right;}
        .hide {display:none;}
        .red {color:Red;}
    </style>
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/datepicker.css">	
<div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
		<div class="modal-header">
			<label class="modal-title text-text-bold-600" id="myModalLabel35"><b>REGISTRO
					DE PERSONA</b></label>
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<f:form id="frmGuardarPersona" class="form-horizontal" role="form"
			method="post" action="" onsubmit="return false">
			<input id="contextPath" type="hidden"
				value="${pageContext.request.contextPath}">

			<f:input class="form-control" type="hidden"
				id="pacienteNombreCompleto" path="nombreCompleto" />


			<f:input type="hidden" class="form-control" id="personaCodigoModal"
				path="codigo" />
			<f:input type="hidden" class="form-control" id="txtCodRegUbigeo"
				path="ubigeoDireccion.codigoRegistro" />
			  
				
			<div class="modal-body" id="buscaPaciente">
				 
					
					 
				<div class="row">
				 <form> 
					 <div class="form-group col-md-8 mb-2">
						<label for="nombreCompleto" class="label_control">INGRESE DATO PARA BÚSQUEDA
						</label>
						<div class="controls">
							<input type="text" class="form-control" id="searchTerm" onkeyup="doSearch()"  />
						</div>
					</div>
				    </form>
					
					  
				</div>
				  
				<div class="row">
				<div class="form-group col-md-12 mb-2">
					<div class="card-body">
						<div class="table-responsive">
							<div id="div1">
								<table class="table table-bordered" id="dataTable"
									style="overflow-y: scroll">
									<thead class="tabla_th">
										<tr>
											<th width="50">ITEM</th>
											<th>APELLIDOS Y NOMBRES</th>
											<th>NRO DOCUMENTO</th> 
											<th width="45">ACCION</th>
										</tr>
									</thead>
									<tbody id="idBodyListaExamenes" class="label_control">

										<c:forEach var="persona" items="${lstPersona}"
											varStatus="loop">
											<tr>
												<td>${loop.count}</td> 
												<td>${persona.nombreCompleto}</td> 
												<td>${persona.nroDocumento}</td>
												<td><button type="button"
														class="btn btn-outline-success btn-sm"
														data-toggle="tooltip" data-placement="top" title=""
														data-original-title="Seleccionar"
														onclick="llenarPersonaIndex(${loop.index})"
														id="agregarEspecialidad">
														<i class="icon-check"></i>
													</button></td>
											</tr>
										</c:forEach>
										<tr class='noSearch hide'><td colspan="4"></td>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			  </div>	
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCerrarModalListaPersona"
					class="btn btn-secondary" data-dismiss="modal">
					<i class="fa fa-close"></i> CERRAR
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
			
	 <script language="javascript">
        function doSearch()
        {
            const tableReg = document.getElementById('dataTable');
            const searchText = document.getElementById('searchTerm').value.toLowerCase();
            let total = 0;
 
            // Recorremos todas las filas con contenido de la tabla
            for (let i = 1; i < tableReg.rows.length; i++) {
                // Si el td tiene la clase "noSearch" no se busca en su cntenido
                if (tableReg.rows[i].classList.contains("noSearch")) {
                    continue;
                }
 
                let found = false;
                const cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
                // Recorremos todas las celdas
                for (let j = 0; j < cellsOfRow.length && !found; j++) {
                    const compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                    // Buscamos el texto en el contenido de la celda
                    if (searchText.length == 0 || compareWith.indexOf(searchText) > -1) {
                        found = true;
                        total++;
                    }
                }
                if (found) {
                    tableReg.rows[i].style.display = '';
                } else {
                    // si no ha encontrado ninguna coincidencia, esconde la
                    // fila de la tabla
                    tableReg.rows[i].style.display = 'none';
                }
            }
 
            // mostramos las coincidencias
            const lastTR=tableReg.rows[tableReg.rows.length-1];
            const td=lastTR.querySelector("td");
            lastTR.classList.remove("hide", "red");
            if (searchText == "") {
                lastTR.classList.add("hide");
            } else if (total) {
                td.innerHTML="Se ha encontrado "+total+" coincidencia"+((total>1)?"s":"");
            } else {
                lastTR.classList.add("red");
                td.innerHTML="No se han encontrado coincidencias";
            }
        }
    </script> 
</div>



