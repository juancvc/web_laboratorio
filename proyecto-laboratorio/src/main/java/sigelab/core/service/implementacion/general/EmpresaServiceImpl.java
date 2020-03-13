package sigelab.core.service.implementacion.general;

import sigelab.core.bean.general.EmpresaBean;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.general.EmpresaDAO;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.general.PersonaService;
import sigelab.core.service.interfaces.general.EmpresaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("empresaService")
@Transactional (readOnly = true)
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDAO EmpresaDAO;
	
	
	
	public EmpresaDAO getEmpresaDAO() {
		return EmpresaDAO;
	}

	public void setEmpresaDAO(EmpresaDAO EmpresaDAO) {
		this.EmpresaDAO = EmpresaDAO;
	}

	@Override
	public boolean insertar(EmpresaBean EmpresaBean) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  EmpresaDAO.insertar(EmpresaBean);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(EmpresaBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  EmpresaDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(EmpresaBean t) throws ServiceException {
		 
		return false;
	}

	@Override
	public EmpresaBean getBuscarPorObjecto(EmpresaBean t)
			throws ServiceException {
 
		EmpresaBean oEmpresaBean = null;
		try {
			oEmpresaBean = EmpresaDAO.getBuscarPorObjecto(t);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return oEmpresaBean;
	}

	@Override
	public List<EmpresaBean> getBuscarPorFiltros(EmpresaBean t)
			throws ServiceException {
	 
		List<EmpresaBean> lstReferenciaBean =  null;
		try {
			lstReferenciaBean = this.EmpresaDAO.getBuscarPorFiltros(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return lstReferenciaBean;
	}

	@Override
	public boolean existe(EmpresaBean t) throws ServiceException {
 
		return false;
	}
 
}
