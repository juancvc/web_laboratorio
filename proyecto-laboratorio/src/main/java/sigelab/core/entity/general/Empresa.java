package sigelab.core.entity.general;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "empresa.select",
			procedureName = "GENE.USP_EMPRESA_SELECT", 
		resultClasses = Empresa.class, parameters = {
	}),
	@NamedStoredProcedureQuery(name = "empresa.actualizar",
	procedureName = "GENE.[USP_EMPRESA_UPDATE]", 
	resultClasses = Empresa.class, parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NRORUC", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "LOGO", type = byte[].class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "DOMICILIO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "DESCRIPCI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "TELFCELU", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CORREO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = String.class),
			
})
	  
})


@Entity
@Table(name="EMPRESA")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODEMPRE")
	private String codempre;

	@Column(name="AUCDUSCR")
	private String aucduscr;

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

	@Column(name="DESCRIPCI")
	private String descripci;

	@Column(name="DOMICILIO")
	private String domicilio;

	@Column(name="ESTADORG")
	private String estadorg;

	@Column(name="LOGO")
	private byte[] logo;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="NOMCOM")
	private String nomcom;

	@Column(name="NRORUC")
	private String nroruc;

	@Column(name="SITUACRG")
	private String situacrg;
	
	@Column(name="TELFCELU")
	private String TELFCELU;
	
	@Column(name="CORREO")
	private String CORREO;

	public Empresa() {
	}

	public String getCodempre() {
		return this.codempre;
	}

	public void setCodempre(String codempre) {
		this.codempre = codempre;
	}

	public String getAucduscr() {
		return this.aucduscr;
	}

	public void setAucduscr(String aucduscr) {
		this.aucduscr = aucduscr;
	}

	public String getAucdusmo() {
		return this.aucdusmo;
	}

	public void setAucdusmo(String aucdusmo) {
		this.aucdusmo = aucdusmo;
	}

	public Timestamp getAufechcr() {
		return this.aufechcr;
	}

	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}

	public Timestamp getAufechmo() {
		return this.aufechmo;
	}

	public void setAufechmo(Timestamp aufechmo) {
		this.aufechmo = aufechmo;
	}

	public String getAuobsemo() {
		return this.auobsemo;
	}

	public void setAuobsemo(String auobsemo) {
		this.auobsemo = auobsemo;
	}

	public String getAupcipcr() {
		return this.aupcipcr;
	}

	public void setAupcipcr(String aupcipcr) {
		this.aupcipcr = aupcipcr;
	}

	public String getAupcipmo() {
		return this.aupcipmo;
	}

	public void setAupcipmo(String aupcipmo) {
		this.aupcipmo = aupcipmo;
	}

	public String getDescripci() {
		return this.descripci;
	}

	public void setDescripci(String descripci) {
		this.descripci = descripci;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEstadorg() {
		return this.estadorg;
	}

	public void setEstadorg(String estadorg) {
		this.estadorg = estadorg;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomcom() {
		return this.nomcom;
	}

	public void setNomcom(String nomcom) {
		this.nomcom = nomcom;
	}

	public String getNroruc() {
		return this.nroruc;
	}

	public void setNroruc(String nroruc) {
		this.nroruc = nroruc;
	}

	public String getSituacrg() {
		return this.situacrg;
	}

	public void setSituacrg(String situacrg) {
		this.situacrg = situacrg;
	}

	public String getTELFCELU() {
		return TELFCELU;
	}

	public void setTELFCELU(String tELFCELU) {
		TELFCELU = tELFCELU;
	}

	public String getCORREO() {
		return CORREO;
	}

	public void setCORREO(String cORREO) {
		CORREO = cORREO;
	}

}