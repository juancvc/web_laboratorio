<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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


			<f:input type="hidden" class="form-control" id="personaCodigo"
				path="codigo" />
			<f:input type="hidden" class="form-control" id="personaCodigoSigeho"
				path="codigoPersonaSigeho" />
			<div class="modal-body" id="buscaPaciente">
				<div class="row">
					<div class="form-group col-md-4 mb-2">
						<label for="situacion" class="label_control">TIPO
							DOCUMENTO<span class="required">*</span>
						</label>
						<div class="controls">
							<f:select id="tipoDocumentoPaciente" path="tipoDocumento.codReg"
								required="required" class="form-control">
								<f:options items="${lstDocumento}" itemValue="codReg"
									itemLabel="nombreCorto" />
							</f:select>
						</div>
					</div>
					<div class="form-group col-md-4 mb-2">
						<label for="exampleInputName" class="label_control">N°
							DOCUMENTO <span class="required">*</span>
						</label>
						<div class="position-relative has-icon-left">
							<f:input type="hidden" path="codigo" id="codigoReferenciaBean"
								val="" />
							<input id="contextPath" type="hidden"
								value="${pageContext.request.contextPath}">
							<div class="controls">
								<f:input type="text" class="form-control" required="required"
									maxlength="12" id="nroDocumentoPaciente" path="nroDocumento"
									onkeypress="return runScript(event)" />
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<br>
						<button id="idBtnCargarPaciente" type="button"
							style="margin-top: 5px;" onclick="buscarPacienteSISWebService()"
							class="form-control btn btn-info">
							<i class="ft-search"></i> BUSCAR
						</button>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">APELLIDO
							PATERNO <span class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" required="required"
								onkeyup="javascript:this.value=this.value.toUpperCase();"
								id="personaApellidoPaterno" path="apellidoPaterno" />

						</div>
					</div>
					<div class="form-group col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">APELLIDO
							MATERNO </label>
						<div class="controls">
							<f:input type="text" class="form-control"
								onkeyup="javascript:this.value=this.value.toUpperCase();"
								id="personaApellidoMaterno" path="apellidoMaterno" />

						</div>
					</div>
					<div class="col-md-4 mb-2">
						<label for="situacion" class="label_control">SEXO <span
							class="required">*</span></label>
						<div class="controls">
							<f:select id="sexoPaciente" path="sexo.codReg"
								class="form-control" required="required">
								<f:option value="" label="Seleccionar" selected="true"
									disabled="disabled" />
								<f:options items="${lstSexo}" itemValue="codReg"
									itemLabel="nombreCorto" />
							</f:select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">PRIMER
							NOMBRE <span class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" required="required"
								onkeyup="javascript:this.value=this.value.toUpperCase();"
								id="personaPrimerNombre" path="primerNombre" />

						</div>
					</div>
					<div class="form-group col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">SEGUNDO
							NOMBRE </label>
						<div class="controls">
							<f:input type="text" class="form-control"
								onkeyup="javascript:this.value=this.value.toUpperCase();"
								id="personaSegundoNombre" path="segundoNombre" />

						</div>
					</div>
					<div class="col-md-4 mb-2">
						<label for="exampleInputName" class="label_control">FECHA
							NACIMIENTO <span class="required">*</span>
						</label>
						<f:input class="form-control" id="date" name="date"
							required="required" placeholder="DD/MM/YYYY" type="text"
							path="fechaNac" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4 mb-1">
						<label for="lbltipoSeguroPaciente" class="label_control">NACIONALIDAD<span
							class="required"> *</span></label>
						<div class="controls">
							<f:select id="personaNacionalidad"
								path="nacionalidad.codReg"
								class="form-control" required="required">
								<f:option value="" label="Seleccionar" selected="true"
									disabled="disabled" />
								<f:options items="${lstNacionalidad}" itemValue="codReg"
									itemLabel="nombreCorto" />
							</f:select>
						</div>
					</div>

					<div class="col-md-4">
						<label for="lbltipoSeguroPaciente" class="label_control">ESTADO
							CIVIL<span class="required">*</span>
						</label>
						<div class="controls">
							<f:select id="estadoCivilPersona"
								path="estadoCivil.codReg"
								class="form-control" required="required">
								<f:option value="000006" label="Seleccionar" selected="true"
									disabled="disabled" />
								<f:options items="${lstEstadoCivil}" itemValue="codReg"
									itemLabel="nombreCorto" />
							</f:select>
						</div>
					</div>
					<div class="form-group col-md-4 mb-1">
						<label for="nombreCompleto" class="label_control">TELEFONO
							<span class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" required="required"
								onKeyPress="return soloNumeros(event)" maxlength="9"
								id="telefono" path="telefonoNumero" />

						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<label for="nombreCompleto" class="label_control">DIRECCION
							<span class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" required="required"
								id="personaDireccion" path="direccion" />

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

				<button type="submit" onclick="enviarPaciente()"
					class="btn btn-primary">
					<i class="fa fa-floppy-o"></i> GRABAR
				</button>
			</div>



		</f:form>
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/locales/bootstrap-datepicker.es.min.js"></script>

	<script>
		$(document).ready(
				function() {
					var date_input = $('input[id="date"]'); //our date input has the name "date"
					var container = $('.bootstrap-iso form').length > 0 ? $(
							'.bootstrap-iso form').parent() : "body";
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
	<script
		src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
		type="text/javascript" charset="utf-8"></script>

	<script type="text/javascript">
	/*	$(document).ready(function() {
			habilitar();
		});
*/
		function habilitar() {

			document.getElementById('personaApellidoPaterno').disabled = true;
			document.getElementById('personaApellidoMaterno').disabled = true;
			document.getElementById('sexoPaciente').disabled = true;
			document.getElementById('personaPrimerNombre').disabled = true;
			document.getElementById('personaSegundoNombre').disabled = true;
			document.getElementById('personaFechaNac').disabled = true;

			$("#nroDocumentoPaciente").focus();
		}

		function limpiar() {
			$('#tipoDocumentoPaciente').val("000002");
			$('#nroDocumentoPaciente').val("");
			$('#personaApellidoPaterno').val("");
			$('#personaApellidoMaterno').val("");
			$('#sexoPaciente').val("");
			$('#personaPrimerNombre').val("");
			$('#personaSegundoNombre').val("");
			$('#personaFechaNac').val("");
			$("#nroDocumentoPaciente").focus();
		}

		function runScript(e) {
			//See notes about 'which' and 'key'
			if (e.keyCode == 13) {
				iniciarBloqueo();
				buscarPacienteSISWebService();
				finBloqueo();
				return false;
			}
		}

		//	$(document).ready(function() {
		//	$("#nroDocumentoPaciente").keyup(function(event) { 
		//		if (event.keyCode === 13) { 
		//			buscarPacienteSISWebService();
		//		}
		//		});
		//	});
	</script>
</div>



