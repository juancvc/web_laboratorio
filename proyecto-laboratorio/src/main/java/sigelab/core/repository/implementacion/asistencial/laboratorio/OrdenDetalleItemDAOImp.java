package sigelab.core.repository.implementacion.asistencial.laboratorio;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sigelab.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import sigelab.core.entity.asistencial.laboratorio.Orden_laboratorio_detalle_item;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.asistencial.laboratorio.OrdenDetalleItemDAO; 

@Transactional
@Repository("OrdenDetalleItemDAO")
public class OrdenDetalleItemDAOImp implements OrdenDetalleItemDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em; 
	
	
	public OrdenDetalleItemBean getOrdenDetalleItemBean(Object object){
		return (OrdenDetalleItemBean)object;
	}
	
	@Override
	public boolean insertar(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException {

		Object id= null;   
		Object nroPeriodo= null;   
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.insertar"); 
			spq.setParameter("CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", ordenDetalleItemBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("CODORDDE", ordenDetalleItemBean.getOrdenDetalleBean().getCodigo()); 
			spq.setParameter("NRVEORDE", ordenDetalleItemBean.getOrdenDetalleBean().getNumeroVersion()); 
			spq.setParameter("NROPORDE", ordenDetalleItemBean.getOrdenDetalleBean().getNumeroPeriodo()); 
			spq.setParameter("AUCDUSCR", ordenDetalleItemBean.getCodigoUsuarioCreacion());
			spq.setParameter("AUPCIPCR", ordenDetalleItemBean.getIpCreacion());
			 
			
			
			spq.execute();
			
			id = spq.getOutputParameterValue(4); 
			nroPeriodo = spq.getOutputParameterValue(9); 
			if (id != null) {
				ordenDetalleItemBean.setCodigo(id.toString());
				ordenDetalleItemBean.setNumeroPeriodo(nroPeriodo.toString()); 
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
	public boolean actualizar(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Orden.modificar"); 
			spq.setParameter("CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", ordenDetalleItemBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("CODCAMPA", ordenDetalleItemBean.getCodigo());
			spq.setParameter("NROPERIO", ordenDetalleItemBean.getNumeroPeriodo()); 
			spq.setParameter("SITUACRG", ordenDetalleItemBean.getSituacion().getCodReg()); 
			
			spq.setParameter("AUCDUSMO", ordenDetalleItemBean.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", ordenDetalleItemBean.getIpModificacion()); 
			
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
	public boolean eliminar(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.eliminar"); 
			spq.setParameter("CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", ordenDetalleItemBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("NROPERIO", ordenDetalleItemBean.getNumeroPeriodo()); 
			spq.setParameter("CODORDEN", ordenDetalleItemBean.getCodigo());
		
			spq.setParameter("TG1TPANU", ordenDetalleItemBean.getMotivoAnular().getCodReg()); 
			spq.setParameter("MOTIVOAN", ordenDetalleItemBean.getDetalleMotivoAnular()); 
			spq.setParameter("AUCDUSMO", ordenDetalleItemBean.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", ordenDetalleItemBean.getIpModificacion()); 
			
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
	public OrdenDetalleItemBean getBuscarPorObjecto(OrdenDetalleItemBean t) throws DAOException {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenDetalleItemBean> getBuscarPorFiltros(OrdenDetalleItemBean OrdenDetalleItemBean) throws DAOException {
		List<Orden_laboratorio_detalle_item> lstOrden = null;	
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.buscarPorFiltros");   
			spq.setParameter("FECDESDE", OrdenDetalleItemBean.getFechaDesde()); 
			spq.setParameter("FECHASTA", OrdenDetalleItemBean.getFechaHasta()); 
			spq.setParameter("SITUACRG", OrdenDetalleItemBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenDetalleItemBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenDetalleItemBean;
	}
	
	 

private List<OrdenDetalleItemBean> deListaObjetoAListaObjetoBean(List<Orden_laboratorio_detalle_item> lstOrden) {
		
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
		if (lstOrden != null && lstOrden.size() > 0) {
			
			lstOrdenDetalleItemBean = new ArrayList<OrdenDetalleItemBean>();
			
			for (int i = 0; i < lstOrden.size(); i++) { 
				Orden_laboratorio_detalle_item entity = lstOrden.get(i);
				OrdenDetalleItemBean bean = deObjetoAObjetoBean(entity);
				
				lstOrdenDetalleItemBean.add(bean);
			}
		}
		
		return lstOrdenDetalleItemBean;
	}
	
	private OrdenDetalleItemBean deObjetoAObjetoBean(Orden_laboratorio_detalle_item entity) {
		
		OrdenDetalleItemBean bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (entity != null) {
			
			bean = new OrdenDetalleItemBean();
			bean.setCodigo(entity.getId().getCodOrddetalleItem());  
			bean.setCodigoInstitucion(entity.getId().getCodinsti());  
			bean.setCodigoSede(entity.getId().getCodsedei());  
			bean.setCodigoOrganizacion(entity.getId().getCodorgan());
			bean.setNumeroVersion(entity.getId().getNroversi());
			bean.setNumeroPeriodo(entity.getId().getNroperio()); 
			bean.getSituacion().setCodReg(entity.getSituacRg()); 
			bean.setResultado(entity.getResultado());
			bean.getExamenesLaboratorioBean().setCodigo(entity.getCodigoExamenLaboratorio());
			bean.getExamenesLaboratorioBean().setNumeroPeriodo(entity.getNroPeriodoExamenLaboratorio());
			bean.getExamenesLaboratorioBean().setNumeroVersion(entity.getNroVersionExamenLaboratorio());
			bean.getOrdenDetalleBean().setCodigo(entity.getCodigoOrdenDetalle());
			bean.getOrdenDetalleBean().setNumeroPeriodo(entity.getNroPeriodoOrdenDetalle());
			bean.getOrdenDetalleBean().setNumeroVersion(entity.getNroVersionOrdenDetalle());
			bean.setResultadoFormula(entity.getResultadoFormula());
			bean.setCodigoUsuarioCreacion(entity.getUsuarioCreacion());
			bean.setFechaCreacion(entity.getAufechcr());
	 	}
		
		return bean;
	}

	
	
	@Override
	public boolean existe(OrdenDetalleItemBean t) throws DAOException {

		return false;
	}

	private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }

	@Override
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean)
			throws DAOException {
		List<Orden_laboratorio_detalle_item> lstOrden = null;	
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.buscarPorCodigoDetalle");   
			spq.setParameter("CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion()); 
			spq.setParameter("CODINSTI", ordenDetalleItemBean.getCodigoInstitucion()); 
			spq.setParameter("CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("CODORDDE", ordenDetalleItemBean.getOrdenDetalleBean().getCodigo()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenDetalleItemBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenDetalleItemBean;
	}

}
