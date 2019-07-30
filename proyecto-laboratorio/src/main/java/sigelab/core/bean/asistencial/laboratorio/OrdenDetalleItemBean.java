package sigelab.core.bean.asistencial.laboratorio;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sigelab.base.bean.BaseBean;
import sigelab.core.bean.general.ExamenesLaboratorioBean;

import sigelab.core.bean.general.TablaBean;

public class OrdenDetalleItemBean extends BaseBean{
	
	private ExamenesLaboratorioBean examenesLaboratorioBean;
	private OrdenDetalleBean ordenDetalleBean;
	private String 		     resultado;
	private double    		 resultadoFormula;  
	private String 			 strFechaOrden;  
	private String 			 observacion;  
	private List<OrdenDetalleBean> lstOrdenDetalleBean;
	private String 			 fechaDesde;  
	private String 			 fechaHasta;  
	private TablaBean 		 motivoAnular;
	private String 		     detalleMotivoAnular;
	
	
	
	public ExamenesLaboratorioBean getExamenesLaboratorioBean() {
		return examenesLaboratorioBean;
	}
	public void setExamenesLaboratorioBean(ExamenesLaboratorioBean examenesLaboratorioBean) {
		this.examenesLaboratorioBean = examenesLaboratorioBean;
	}
	public OrdenDetalleBean getOrdenDetalleBean() {
		return ordenDetalleBean;
	}
	public void setOrdenDetalleBean(OrdenDetalleBean ordenDetalleBean) {
		this.ordenDetalleBean = ordenDetalleBean;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public double getResultadoFormula() {
		return resultadoFormula;
	}
	public void setResultadoFormula(double resultadoFormula) {
		this.resultadoFormula = resultadoFormula;
	}
	public String getStrFechaOrden() {
		return strFechaOrden;
	}
	public void setStrFechaOrden(String strFechaOrden) {
		this.strFechaOrden = strFechaOrden;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public List<OrdenDetalleBean> getLstOrdenDetalleBean() {
		return lstOrdenDetalleBean;
	}
	public void setLstOrdenDetalleBean(List<OrdenDetalleBean> lstOrdenDetalleBean) {
		this.lstOrdenDetalleBean = lstOrdenDetalleBean;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public TablaBean getMotivoAnular() {
		return motivoAnular;
	}
	public void setMotivoAnular(TablaBean motivoAnular) {
		this.motivoAnular = motivoAnular;
	}
	public String getDetalleMotivoAnular() {
		return detalleMotivoAnular;
	}
	public void setDetalleMotivoAnular(String detalleMotivoAnular) {
		this.detalleMotivoAnular = detalleMotivoAnular;
	}  
	
 

}
