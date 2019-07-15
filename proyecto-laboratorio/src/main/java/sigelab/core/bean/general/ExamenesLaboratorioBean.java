package sigelab.core.bean.general;


import sigelab.base.bean.BaseBean;

public class ExamenesLaboratorioBean extends BaseBean{

 

	private String descripcion;
	private String abrev;
	
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

	

}
