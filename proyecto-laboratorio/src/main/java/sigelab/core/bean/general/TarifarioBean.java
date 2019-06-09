//package pe.gob.procalidad.natigu.core.bean.bean.generico;
package sigelab.core.bean.general;

import java.util.List;

import sigelab.base.bean.BaseBean;

public class TarifarioBean extends BaseBean{

 
	private TablaBean tipo;
	private String subtipo;
	private String descripcion;
	private float precio; 
	private String sPrecio;  
	private List<TarifarioDetalleBean> lstTarifarioDetalleBean;
	private TarifarioDetalleBean tarifarioDetalleBean;
	
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

	public List<TarifarioDetalleBean> getLstTarifarioDetalleBean() {
		return lstTarifarioDetalleBean;
	}

	public void setLstTarifarioDetalleBean(List<TarifarioDetalleBean> lstTarifarioDetalleBean) {
		this.lstTarifarioDetalleBean = lstTarifarioDetalleBean;
	}

	public TarifarioDetalleBean getTarifarioDetalleBean() {
		return tarifarioDetalleBean;
	}

	public void setTarifarioDetalleBean(TarifarioDetalleBean tarifarioDetalleBean) {
		this.tarifarioDetalleBean = tarifarioDetalleBean;
	}
	
 
}
