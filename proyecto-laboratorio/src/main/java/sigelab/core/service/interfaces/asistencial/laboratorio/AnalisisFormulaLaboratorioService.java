package sigelab.core.service.interfaces.asistencial.laboratorio;

import sigelab.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import sigelab.core.service.interfaces.BaseService;
import sigelab.core.service.exception.ServiceException;
public interface AnalisisFormulaLaboratorioService extends BaseService<AnalisisFormulaLaboratorioBean>{ 
	public AnalisisFormulaLaboratorioBean formularResultado(AnalisisFormulaLaboratorioBean analisisFormulaLaboratorioBean)throws ServiceException;
}
