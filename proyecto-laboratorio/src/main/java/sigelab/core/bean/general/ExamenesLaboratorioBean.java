package sigelab.core.bean.general;


import sigelab.base.bean.BaseBean;

public class ExamenesLaboratorioBean extends BaseBean{

 

	private String descripcion;
	private String abrev;
	private String observacion;
	private TarifarioBean tarifarioBean;
	private TablaBean tipoExamenLab;
	private TablaBean unidadMedida;
	
	public ExamenesLaboratorioBean() {
		super();
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

	public TarifarioBean getTarifarioBean() {
		return tarifarioBean;
	}

	public void setTarifarioBean(TarifarioBean tarifarioBean) {
		this.tarifarioBean = tarifarioBean;
	}

	public TablaBean getTipoExamenLab() {
		return tipoExamenLab;
	}

	public void setTipoExamenLab(TablaBean tipoExamenLab) {
		this.tipoExamenLab = tipoExamenLab;
	}

	public TablaBean getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(TablaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	

	

}
