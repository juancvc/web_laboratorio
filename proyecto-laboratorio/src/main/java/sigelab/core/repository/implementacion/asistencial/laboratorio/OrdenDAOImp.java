package sigelab.core.repository.implementacion.asistencial.laboratorio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sigelab.core.bean.asistencial.laboratorio.OrdenBean;
import sigelab.core.bean.general.PersonaBean;
import sigelab.core.entity.asistencial.laboratorio.Orden_laboratorio;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.asistencial.laboratorio.OrdenDAO; 

@Transactional
@Repository("OrdenDAOImp")
public class OrdenDAOImp implements OrdenDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em; 
	
	
	public OrdenBean getOrdenBean(Object object){
		return (OrdenBean)object;
	}
	
	@Override
	public boolean insertar(OrdenBean OrdenBean) throws DAOException {

		Object id= null;   
		Object nroPeriodo= null;   
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.insertar"); 
			spq.setParameter("CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("CODPERSO", OrdenBean.getPacienteBean().getPersona().getCodigo());
			spq.setParameter("CANT_ITEMS", OrdenBean.getCantidadItems());
			spq.setParameter("CADENA_CODTARIF", OrdenBean.getCadenaCodigoTarifario());
			spq.setParameter("CADENA_CANTIDAD", OrdenBean.getCadenaCantidad());
			
			spq.setParameter("AUCDUSCR", OrdenBean.getCodigoUsuarioCreacion());
			spq.setParameter("AUPCIPCR", OrdenBean.getIpCreacion());
			 
			
			
			spq.execute();
			
			id = spq.getOutputParameterValue(4); 
			nroPeriodo = spq.getOutputParameterValue(9); 
			if (id != null) {
				OrdenBean.setCodigo(id.toString());
				OrdenBean.setNumeroPeriodo(nroPeriodo.toString()); 
				sw=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			sw=false; 
		}finally{
			em.close();
		}
		return sw;
	}

	@Override
	public boolean actualizar(OrdenBean OrdenBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Orden.modificar"); 
			spq.setParameter("CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("CODCAMPA", OrdenBean.getCodigo());
			spq.setParameter("NROPERIO", OrdenBean.getNumeroPeriodo()); 
			spq.setParameter("SITUACRG", OrdenBean.getSituacion().getCodReg()); 
			
			spq.setParameter("AUCDUSMO", OrdenBean.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", OrdenBean.getIpModificacion()); 
			
			spq.execute();  
			sw=true;
			 
		} catch (Exception e) {
			e.printStackTrace();
			sw=false; 
		}finally{
			em.close();
		}
		return sw;
	}

	@Override
	public boolean eliminar(OrdenBean OrdenBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Orden.eliminar"); 
			spq.setParameter("CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("CODCAMPA", OrdenBean.getCodigo());
			spq.setParameter("NROPERIO", OrdenBean.getNumeroPeriodo()); 
			
			spq.setParameter("AUCDUSMO", OrdenBean.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", OrdenBean.getIpModificacion()); 
			
			spq.execute();  
			sw=true;
			 
		} catch (Exception e) {
			e.printStackTrace();
			sw=false; 
		}finally{
			em.close();
		}
		return sw;
	}

	@Override
	public OrdenBean getBuscarPorObjecto(OrdenBean t) throws DAOException {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenBean> getBuscarPorFiltros(OrdenBean OrdenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Orden.buscarPorFiltros");   
			spq.setParameter("SITUACRG", OrdenBean.getSituacion().getCodReg()); 
			
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}
	
	 

private List<OrdenBean> deListaObjetoAListaObjetoBean(List<Orden_laboratorio> lstOrden) {
		
		List<OrdenBean> lstOrdenBean = null;
		
		if (lstOrden != null && lstOrden.size() > 0) {
			
			lstOrdenBean = new ArrayList<OrdenBean>();
			
			for (int i = 0; i < lstOrden.size(); i++) { 
				Orden_laboratorio entity = lstOrden.get(i);
				OrdenBean bean = deObjetoAObjetoBean(entity);
				
				lstOrdenBean.add(bean);
			}
		}
		
		return lstOrdenBean;
	}
	
	private OrdenBean deObjetoAObjetoBean(Orden_laboratorio entity) {
		
		OrdenBean bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (entity != null) {
			
			bean = new OrdenBean();
			bean.setCodigo(entity.getId().getCodOrden());  
			bean.setCodigoInstitucion(entity.getId().getCodinsti());  
			bean.setCodigoSede(entity.getId().getCodsedei());  
			bean.setCodigoOrganizacion(entity.getId().getCodorgan());
			bean.setNumeroVersion(entity.getId().getNroversi());
			bean.setNumeroPeriodo(entity.getId().getNroperio()); 
			bean.getSituacion().setCodReg(entity.getSituacRg()); 
			  
	 	}
		
		return bean;
	}

	
	
	@Override
	public boolean existe(OrdenBean t) throws DAOException {

		return false;
	}

	 

}
