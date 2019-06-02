package sigelab.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



@NamedStoredProcedureQueries(
		{
				
			@NamedStoredProcedureQuery(name = "tarifarioUnidad.insertar",
					procedureName = "[RECO].REFAPOYO_INSERT", 
					resultClasses = Tarifario_unidadValor.class, parameters = {
					@StoredProcedureParameter(mode = ParameterMode.OUT, name ="CODAPOYO", type = String.class),
					
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODREFCB",  type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERCB",  type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROVERCB",  type = String.class),
					
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class), 
					
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "TG14TPAP",  type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTARCB",  type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSCR", type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPCR", type = String.class)
					}), 
			@NamedStoredProcedureQuery(
					name="tarifarioUnidad.listarPorFiltros", 
					procedureName="[LABO].[TARIFARIO_LISTAR_X_FILTROS]",
					resultClasses= Tarifario_unidadValor.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="TG1TIPOT", type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="DESCRIPC", type = String.class) 
					}),
			@NamedStoredProcedureQuery(
					name="tarifarioUnidad.registro", 
					procedureName="[LABO].[TARIFARIO_ITEM_INSERT]",
					resultClasses= Tarifario_unidadValor.class,
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
					name="tarifarioUnidad.actualizar", 
					procedureName="[LABO].[TARIFARIO_ITEM_UPDATE]",
					resultClasses= Tarifario_unidadValor.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.OUT, name ="CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TG1TIPOT", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO",  	  type =	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "SUBTIPO",  type = 	String.class),	
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMPRODU", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "PRECIO",   type =    Float.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPOUNID", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFIN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFFI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUDUSUCR", type = 	String.class) 
					}),
			
		}
		)
@Entity 
public class Tarifario_unidadValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODUNVAL")
	private String CODUNVAL;
	
	@Column(name="PERIODO")
	private String PERIODO;
	
	@Column(name="CODTARIF")
	private String CODTARIF;

	@Column(name="UNIDADES")
	private String unidades;
	
	@Column(name="VALORINI")
	private String valoresRefIni;
	
	@Column(name="VALORFIN")
	private String valoresRefFin; 

	@Column(name="AUCDUSMO")
	private String aucdusmo;

	@Column(name="AUFECHCR")
	private Timestamp aufechcr;

	@Column(name="AUFECHMO")
	private Timestamp aufechmo;

	@Column(name="AUOBSEMO")
	private String auobsemo;

	@Column(name="AUPCIPCR")
	private String aupcipcr;

	@Column(name="AUPCIPMO")
	private String aupcipmo;
  
	@Column(name="ESTADORG")
	private String estadorg; 

	@Column(name="SITUACRG")
	private String situacrg;

	@Column(name="SWMIGRAD")
	private short swmigrad; 
	

	public Tarifario_unidadValor() {
	}


	public String getCODUNVAL() {
		return CODUNVAL;
	}


	public void setCODUNVAL(String cODUNVAL) {
		CODUNVAL = cODUNVAL;
	}


	public String getPERIODO() {
		return PERIODO;
	}


	public void setPERIODO(String pERIODO) {
		PERIODO = pERIODO;
	}


	public String getCODTARIF() {
		return CODTARIF;
	}


	public void setCODTARIF(String cODTARIF) {
		CODTARIF = cODTARIF;
	}


	public String getUnidades() {
		return unidades;
	}


	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}


	public String getValoresRefIni() {
		return valoresRefIni;
	}


	public void setValoresRefIni(String valoresRefIni) {
		this.valoresRefIni = valoresRefIni;
	}


	public String getValoresRefFin() {
		return valoresRefFin;
	}


	public void setValoresRefFin(String valoresRefFin) {
		this.valoresRefFin = valoresRefFin;
	}


	public String getAucdusmo() {
		return aucdusmo;
	}


	public void setAucdusmo(String aucdusmo) {
		this.aucdusmo = aucdusmo;
	}


	public Timestamp getAufechcr() {
		return aufechcr;
	}


	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}


	public Timestamp getAufechmo() {
		return aufechmo;
	}


	public void setAufechmo(Timestamp aufechmo) {
		this.aufechmo = aufechmo;
	}


	public String getAuobsemo() {
		return auobsemo;
	}


	public void setAuobsemo(String auobsemo) {
		this.auobsemo = auobsemo;
	}


	public String getAupcipcr() {
		return aupcipcr;
	}


	public void setAupcipcr(String aupcipcr) {
		this.aupcipcr = aupcipcr;
	}


	public String getAupcipmo() {
		return aupcipmo;
	}


	public void setAupcipmo(String aupcipmo) {
		this.aupcipmo = aupcipmo;
	}


	public String getEstadorg() {
		return estadorg;
	}


	public void setEstadorg(String estadorg) {
		this.estadorg = estadorg;
	}


	public String getSituacrg() {
		return situacrg;
	}


	public void setSituacrg(String situacrg) {
		this.situacrg = situacrg;
	}


	public short getSwmigrad() {
		return swmigrad;
	}


	public void setSwmigrad(short swmigrad) {
		this.swmigrad = swmigrad;
	}

 
}