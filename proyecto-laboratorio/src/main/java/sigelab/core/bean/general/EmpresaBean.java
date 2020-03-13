package sigelab.core.bean.general;

import org.springframework.web.multipart.MultipartFile;

import sigelab.base.bean.BaseBean;

public class EmpresaBean extends BaseBean{
	private String	nroRUC;
	private String  descripcion; 
	private String	nombreEmpresa;
	private String  nombreComercial; 
	private byte[]	logo;
	private MultipartFile	logoFile;
	private String  logoCadena; 
	private String	domicilioFiscal;
	private String	telefonoCelular;
	private String	correo;
	
	public EmpresaBean() {
		super();
	}
	
	public MultipartFile getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}
	public String getLogoCadena() {
		return logoCadena;
	}
	public void setLogoCadena(String logoCadena) {
		this.logoCadena = logoCadena;
	}
	public String getNroRUC() {
		return nroRUC;
	}
	public void setNroRUC(String nroRUC) {
		this.nroRUC = nroRUC;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public String getDomicilioFiscal() {
		return domicilioFiscal;
	}
	public void setDomicilioFiscal(String domicilioFiscal) {
		this.domicilioFiscal = domicilioFiscal;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
