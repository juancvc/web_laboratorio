package sigelab.core.repository.interfaces.asistencial.laboratorio;

import sigelab.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.BaseDAO;
 
 
public interface AnalisisFormulaLaboratorioDAO extends BaseDAO<AnalisisFormulaLaboratorioBean>{
	
	public AnalisisFormulaLaboratorioBean formularResultado(AnalisisFormulaLaboratorioBean analisisFormulaLaboratorioBean)throws DAOException;
 
}
