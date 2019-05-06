package sigelab.core.repository.implementacion.asistencial.laboratorio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sigelab.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import sigelab.core.bean.general.PersonaBean;
import sigelab.core.entity.asistencial.laboratorio.Orden_detalle_laboratorio;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.asistencial.laboratorio.OrdenDetalleDAO; 

@Transactional
@Repository("OrdenDetalleDAOImp")
public class OrdenDetalleDAOImp implements OrdenDetalleDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em; 
	
	
	public OrdenDetalleBean getOrdenDetalleBean(Object object){
		return (OrdenDetalleBean)object;
	}
	
	@Override
	public boolean insertar(OrdenDetalleBean OrdenDetalleBean) throws DAOException {

		Object id= null;   
		
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("OrdenDetalle.insertar"); 
			spq.setParameter("CODORGAN", OrdenDetalleBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenDetalleBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenDetalleBean.getCodigoSede()); 
			 
			spq.setParameter("SITUACRG", OrdenDetalleBean.getSituacion().getCodReg());
			
			spq.setParameter("AUCDUSCR", OrdenDetalleBean.getCodigoUsuarioCreacion());
			spq.setParameter("AUPCIPCR", OrdenDetalleBean.getIpCreacion());
			 
			
			
			spq.execute();
			
			id = spq.getOutputParameterValue(4); 
			
			if (id != null) {
				OrdenDetalleBean.setCodigo(id.toString()); 
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
	public boolean actualizar(OrdenDetalleBean OrdenDetalleBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("OrdenDetalle.modificar"); 
			spq.setParameter("CODORGAN", OrdenDetalleBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenDetalleBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenDetalleBean.getCodigoSede()); 
			spq.setParameter("CODCAMPA", OrdenDetalleBean.getCodigo());
			spq.setParameter("NROPERIO", OrdenDetalleBean.getNumeroPeriodo()); 
			spq.setParameter("SITUACRG", OrdenDetalleBean.getSituacion().getCodReg()); 
			
			spq.setParameter("AUCDUSMO", OrdenDetalleBean.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", OrdenDetalleBean.getIpModificacion()); 
			
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
	public boolean eliminar(OrdenDetalleBean OrdenDetalleBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("OrdenDetalle.eliminar"); 
			spq.setParameter("CODORGAN", OrdenDetalleBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenDetalleBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenDetalleBean.getCodigoSede()); 
			spq.setParameter("CODCAMPA", OrdenDetalleBean.getCodigo());
			spq.setParameter("NROPERIO", OrdenDetalleBean.getNumeroPeriodo()); 
			
			spq.setParameter("AUCDUSMO", OrdenDetalleBean.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", OrdenDetalleBean.getIpModificacion()); 
			
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
	public OrdenDetalleBean getBuscarPorObjecto(OrdenDetalleBean t) throws DAOException {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenDetalleBean> getBuscarPorFiltros(OrdenDetalleBean OrdenDetalleBean) throws DAOException {
		List<Orden_detalle_laboratorio> lstOrdenDetalle = null;	
		List<OrdenDetalleBean> lstOrdenDetalleBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("OrdenDetalle.buscarPorFiltros");   
			spq.setParameter("SITUACRG", OrdenDetalleBean.getSituacion().getCodReg()); 
			
			 if (spq.execute()) {
				 lstOrdenDetalle =  spq.getResultList(); 
			 }
			 
			if (lstOrdenDetalle != null && lstOrdenDetalle.size() > 0) {
				lstOrdenDetalleBean = deListaObjetoAListaObjetoBean(lstOrdenDetalle);
			 }
			
			em.close();
			
		   
		return lstOrdenDetalleBean;
	}
	
	 

private List<OrdenDetalleBean> deListaObjetoAListaObjetoBean(List<Orden_detalle_laboratorio> lstOrdenDetalle) {
		
		List<OrdenDetalleBean> lstOrdenDetalleBean = null;
		
		if (lstOrdenDetalle != null && lstOrdenDetalle.size() > 0) {
			
			lstOrdenDetalleBean = new ArrayList<OrdenDetalleBean>();
			
			for (int i = 0; i < lstOrdenDetalle.size(); i++) { 
				Orden_detalle_laboratorio entity = lstOrdenDetalle.get(i);
				OrdenDetalleBean bean = deObjetoAObjetoBean(entity);
				
				lstOrdenDetalleBean.add(bean);
			}
		}
		
		return lstOrdenDetalleBean;
	}
	
	private OrdenDetalleBean deObjetoAObjetoBean(Orden_detalle_laboratorio entity) {
		
		OrdenDetalleBean bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (entity != null) {
			
			bean = new OrdenDetalleBean();
			bean.setCodigo(entity.getId().getCodOrdde());  
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
	public boolean existe(OrdenDetalleBean t) throws DAOException {

		return false;
	}

	 

}
