package sigelab.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



@NamedStoredProcedureQueries(
		{ 
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.listarPorFiltros", 
					procedureName="[LABO].[UNIMEDI_LISTAR_X_FILTROS]",
					resultClasses= Examenes_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREVIATURA", type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="DESCRIPCION", type = String.class) 
					}),
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.registro", 
					procedureName="[LABO].[TARIFARIO_ITEM_INSERT]",
					resultClasses= Examenes_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.OUT, name ="CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TG1TIPOT", type = 	String.class),
					//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO",  	  type =	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "SUBTIPO",  type = 	String.class),						
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMPRODU", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "PRECIO",   type =    Float.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPOUNID", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFIN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFFI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUDUSUCR", type = 	String.class)
					}),
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.actualizar", 
					procedureName="[LABO].[TARIFARIO_ITEM_UPDATE]",
					resultClasses= Examenes_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TG1TIPOT", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO",  	  type =	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "SUBTIPO",  type = 	String.class),	
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMPRODU", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "PRECIO",   type =    Float.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPOUNID", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFIN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFFI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSCR", type = 	String.class) 
					}),
			
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.eliminar", 
					procedureName="[LABO].[TARIFARIO_ITEM_DELETE]",
					resultClasses= Examenes_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = 	String.class), 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = 	String.class) 
					}),
		}
		)
@Entity 
public class Examenes_laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODCOMPO")
	private String codigoUnidadMedida;

	@Column(name="AUCDUSCR")
	private String usuarioCreacion;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="ABREVIATURA")
	private String abrev;
	
	@Column(name="CODORGAN")
	private String codigoOrganizacion;
	
	@Column(name="CODINSTI")
	private Float codigoInstitucion;

	@Column(name="CODSEDEI")
	private String codigoSede;

	@Column(name="AUFECHCR")
	private Timestamp aufechcr;


	@Column(name="AUPCIPCR")
	private String aupcipcr;


	@Column(name="NROVERSI")
	private String NROVERSI;


	@Column(name="SITUACRG")
	private String situacrg;



	public Examenes_laboratorio() {
	}



	public String getCodigoUnidadMedida() {
		return codigoUnidadMedida;
	}



	public void setCodigoUnidadMedida(String codigoUnidadMedida) {
		this.codigoUnidadMedida = codigoUnidadMedida;
	}



	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}



	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getAbrev() {
		return abrev;
	}



	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}



	public String getCodigoOrganizacion() {
		return codigoOrganizacion;
	}



	public void setCodigoOrganizacion(String codigoOrganizacion) {
		this.codigoOrganizacion = codigoOrganizacion;
	}



	public Float getCodigoInstitucion() {
		return codigoInstitucion;
	}



	public void setCodigoInstitucion(Float codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}



	public String getCodigoSede() {
		return codigoSede;
	}



	public void setCodigoSede(String codigoSede) {
		this.codigoSede = codigoSede;
	}



	public Timestamp getAufechcr() {
		return aufechcr;
	}



	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}



	public String getAupcipcr() {
		return aupcipcr;
	}



	public void setAupcipcr(String aupcipcr) {
		this.aupcipcr = aupcipcr;
	}



	public String getNROVERSI() {
		return NROVERSI;
	}



	public void setNROVERSI(String nROVERSI) {
		NROVERSI = nROVERSI;
	}



	public String getSituacrg() {
		return situacrg;
	}



	public void setSituacrg(String situacrg) {
		this.situacrg = situacrg;
	}



	
}