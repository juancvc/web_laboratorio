package sigelab.core.service.implementacion.asistencial.laboratorio;



import sigelab.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.asistencial.laboratorio.AnalisisFormulaLaboratorioDAO;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.asistencial.laboratorio.AnalisisFormulaLaboratorioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AnalisisFormulaLaboratorioService")
@Transactional (readOnly = true)
public class AnalisisFormulaLaboratorioServiceImpl implements AnalisisFormulaLaboratorioService {
	
	
	@Autowired
	 private AnalisisFormulaLaboratorioDAO analisisFormulaLaboratorioDAO;
	
	@Override
	public boolean insertar(AnalisisFormulaLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  analisisFormulaLaboratorioDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(AnalisisFormulaLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  analisisFormulaLaboratorioDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(AnalisisFormulaLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  analisisFormulaLaboratorioDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public AnalisisFormulaLaboratorioBean getBuscarPorObjecto(AnalisisFormulaLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnalisisFormulaLaboratorioBean> getBuscarPorFiltros(AnalisisFormulaLaboratorioBean t)
			throws ServiceException {
		List<AnalisisFormulaLaboratorioBean> lstAnalisisFormulaLaboratorioBean =  null;
		try {
			lstAnalisisFormulaLaboratorioBean = analisisFormulaLaboratorioDAO.getBuscarPorFiltros(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return lstAnalisisFormulaLaboratorioBean;
	}

	@Override
	public boolean existe(AnalisisFormulaLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	 
	 
}
