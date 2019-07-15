package sigelab.core.service.implementacion.general;



import sigelab.core.bean.general.ExamenesLaboratorioBean;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.general.ExamenesLaboratorioDAO;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.general.ExamenesLaboratorioService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ExamenesLaboratorioService")
@Transactional (readOnly = true)
public class ExamenesLaboratorioServiceImpl implements ExamenesLaboratorioService {
	
	
	@Autowired
	 private ExamenesLaboratorioDAO examenesLaboratorioDAO;
	
	@Override
	public boolean insertar(ExamenesLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenesLaboratorioDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(ExamenesLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenesLaboratorioDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(ExamenesLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenesLaboratorioDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public ExamenesLaboratorioBean getBuscarPorObjecto(ExamenesLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamenesLaboratorioBean> getBuscarPorFiltros(ExamenesLaboratorioBean t)
			throws ServiceException {
		List<ExamenesLaboratorioBean> lstExamenesLaboratorioBean =  null;
		try {
			lstExamenesLaboratorioBean = examenesLaboratorioDAO.getBuscarPorFiltros(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return lstExamenesLaboratorioBean;
	}

	@Override
	public boolean existe(ExamenesLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	 
	 
}
