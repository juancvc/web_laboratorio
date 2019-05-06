package sigelab.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "orden.insertar", procedureName = "[LABO].[SP_ORDEN_INSERTAR_ORDEN]", resultClasses = Orden_laboratorio.class, parameters = {
				
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "CODORDEN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODPERSO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CANT_ITEMS", type = int.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CADENA_CODTARIF", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CADENA_CANTIDAD", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "NROPERIO", type = String.class),  
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSCR", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPCR", type = String.class),
 }),
@NamedStoredProcedureQuery(name = "postulante.modificar", procedureName = "[SIGEHOV2BANC].BANC.CAMPANIA_UPDATE", resultClasses = Orden_laboratorio.class, parameters = {
				
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODCAMPA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMCAMPA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "LUGCAMPA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHAINI", type = String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHAFIN", type = String.class),
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = String.class),
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = String.class),
 }),
@NamedStoredProcedureQuery(name = "postulante.eliminar", procedureName = "[SIGEHOV2BANC].[BANC].[POSTULANTE_DELETE]", resultClasses = Orden_laboratorio.class, parameters = {
			
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROVERSI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class),
			
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPOST", type = String.class),
			
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = String.class),
 }),
@NamedStoredProcedureQuery(name = "postulante.buscarPorFiltros", procedureName = "[SIGEHOV2BANC].[BANC].[POSTULANTE_CAMPANIA_LISTA_X_FILTRO]", resultClasses = Orden_laboratorio.class, parameters = {
		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODCAMPA", type = String.class) 
 }),
@NamedStoredProcedureQuery(name = "postulante.buscarPorObjeto", procedureName = "[SIGEHOV2BANC].[BANC].[POSTULANTE_CAMPANIA_LISTA_X_OBJETO]", resultClasses = Orden_laboratorio.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPOST", type = String.class) 
		
}),
@NamedStoredProcedureQuery(name = "postulante.buscarUltimaDonacion", procedureName = "[SIGEHOV2BANC].[GENE].[ADMISION_FECHA_ULTIMA_DONACION]", resultClasses = Orden_laboratorio.class, parameters = {
		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODPERSO", type = String.class) 
 }),

@NamedStoredProcedureQuery(name = "postulante.estadoFisicoBuscarPorFiltros", procedureName = "[SIGEHOV2BANC].[BANC].[POSTULANTE_ESTADOFISICO_CAMPANIA_LISTA_X_FILTRO]", resultClasses = Orden_laboratorio.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHADES", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHAHAS", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "ESTADOEV", type = String.class)  
}),

@NamedStoredProcedureQuery(name = "postulante.estadoFisicoBuscarPorObjeto", procedureName = "[SIGEHOV2BANC]. [BANC].[POSTULANTE_ESTADOFISICO_CAMPANIA_LISTA_X_OBJETO]", resultClasses = Orden_laboratorio.class, parameters = {
	
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class) ,
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class) ,
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class) ,
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class) ,
	@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPOST", type = String.class) 
	
}),


@NamedStoredProcedureQuery(name = "postulante.preDonantesBuscarPorFiltros", procedureName = "[SIGEHOV2BANC].BANC.[PREDONANTES_ENTREVISTA_LISTA_X_FILTRO]", resultClasses = Orden_laboratorio.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", type = String.class)  
}),
	
@NamedStoredProcedureQuery(name = "postulante.litarEstadoFisicaPorPerso", procedureName = "[SIGEHOV2BANC].[GENE].[ADMISION_ULTIMA_EVALUACION_FISICA_V_WEB]", resultClasses = Orden_laboratorio.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODPERSO", type = String.class) })

 

})


@Entity
public class Orden_laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	 private Orden_laboratorioPK id;
   
	@Column(name = "SITUACRG")
	private String situacRg;
	
	@Column(name = "AUFECHCR")
	private Timestamp aufechcr;

	@Column(name = "CODPERSO")
	private String codPerso;
	
	@Column(name="AUCDUSCR")
	private String aucduscr;
	
	@Column(name = "AUFECHMO")
	private Timestamp aufechmo;

	public Orden_laboratorioPK getId() {
		return id;
	}

	public void setId(Orden_laboratorioPK id) {
		this.id = id;
	}

	public String getSituacRg() {
		return situacRg;
	}

	public void setSituacRg(String situacRg) {
		this.situacRg = situacRg;
	}

	public Timestamp getAufechcr() {
		return aufechcr;
	}

	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}

	public String getCodPerso() {
		return codPerso;
	}

	public void setCodPerso(String codPerso) {
		this.codPerso = codPerso;
	}

	public String getAucduscr() {
		return aucduscr;
	}

	public void setAucduscr(String aucduscr) {
		this.aucduscr = aucduscr;
	}

	public Timestamp getAufechmo() {
		return aufechmo;
	}

	public void setAufechmo(Timestamp aufechmo) {
		this.aufechmo = aufechmo;
	}
	 
	
	
 
}