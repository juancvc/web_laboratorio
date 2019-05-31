//package pe.gob.procalidad.natigu.core.bean.bean.generico;
package sigelab.core.bean.general;

import sigelab.base.bean.BaseBean;

public class TarifarioBean extends BaseBean{

 
	private TablaBean tipo;
	private String subtipo;
	private String descripcion;
	private float precio; 
	private String sPrecio; 
	private String unidades; 
	private String valoresRefIni;
	private String valoresRefFin; 
	public TarifarioBean() {
		super();
	}

	public TablaBean getTipo() {
		if(tipo == null) {
			tipo = new TablaBean();
		}
		return tipo;
	}

	public void setTipo(TablaBean tipo) {
		this.tipo = tipo;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getDescripcion() {
		if (descripcion == null) {
			descripcion = "";
		}
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getsPrecio() {
		return sPrecio;
	}

	public void setsPrecio(String sPrecio) {
		this.sPrecio = sPrecio;
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
	  
	
	
	 
}
