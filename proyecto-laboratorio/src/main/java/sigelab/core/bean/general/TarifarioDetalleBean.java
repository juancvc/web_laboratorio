//package pe.gob.procalidad.natigu.core.bean.bean.generico;
package sigelab.core.bean.general;

import sigelab.base.bean.BaseBean;

public class TarifarioDetalleBean extends BaseBean{

 
	private TarifarioBean tarifarioBean; 
	private String unidades; 
	private String valoresRefIni;
	private String valoresRefFin; 
	private String observacion; 
	
	public TarifarioDetalleBean() {
		super();
	}
 
	public TarifarioBean getTarifarioBean() {
		return tarifarioBean;
	}

	public void setTarifarioBean(TarifarioBean tarifarioBean) {
		this.tarifarioBean = tarifarioBean;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String obvservacion) {
		this.observacion = obvservacion;
	}
	  
	
	
	 
}
