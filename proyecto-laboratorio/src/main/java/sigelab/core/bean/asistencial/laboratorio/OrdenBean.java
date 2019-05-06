package sigelab.core.bean.asistencial.laboratorio;
 
import java.util.Date;
import java.util.List;

import sigelab.base.bean.BaseBean;
import sigelab.core.bean.general.PacienteBean;

public class OrdenBean extends BaseBean{
	
	private PacienteBean pacienteBean;
	private Date fechaOrden;
	private String observacion;  
	private Float importeTotal;
	private String descripcionImporte;
	
	private List<OrdenDetalleBean> lstOrdenDetalleBean;
	
	public PacienteBean getPacienteBean() {
		return pacienteBean;
	}
	public void setPacienteBean(PacienteBean pacienteBean) {
		this.pacienteBean = pacienteBean;
	}
	public Date getFechaOrden() {
		return fechaOrden;
	}
	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getDescripcionImporte() {
		return descripcionImporte;
	}
	public void setDescripcionImporte(String descripcionImporte) {
		this.descripcionImporte = descripcionImporte;
	}  
	
	public void calcularImporteTotal(){
		Float tmpImporte = (float) 0.00;
		for (OrdenDetalleBean ordenDetalleBean : lstOrdenDetalleBean) {
			tmpImporte += ordenDetalleBean.getImporte();
		}
		System.out.println("tmpImporte " + tmpImporte);
		this.setImporteTotal(tmpImporte);
	}
 
}
