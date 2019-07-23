package sigelab.core.repository.implementacion.general;
 
import sigelab.core.bean.general.TablaBean;
import sigelab.core.bean.general.TarifarioDetalleBean;
import sigelab.core.entity.asistencial.laboratorio.Tarifario_unidadValor;
import sigelab.core.entity.general.Tgemae02;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.general.TarifarioDAO;
import sigelab.core.repository.interfaces.general.TarifarioDetalleDAO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository("TarifarioDetalleDAO")
public class TarifarioDetalleDAOImpl implements TarifarioDetalleDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em;
	
	@Override
	public boolean insertar(TarifarioDetalleBean Tarifario) throws DAOException {
  
		Object idTarifario= null; 
		Object nroPeriodo= null; 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("tarifarioUnidad.insertar");
			spq.setParameter("CODTARIF", Tarifario.getTarifarioBean().getCodigo()); 
			spq.setParameter("COORGPRA", Tarifario.getExamenesLaboratorioBean().getCodigoOrganizacion());  
			spq.setParameter("COINSPRA", Tarifario.getExamenesLaboratorioBean().getCodigoInstitucion());  
			spq.setParameter("COSEDPRA", Tarifario.getExamenesLaboratorioBean().getCodigoSede());  
			spq.setParameter("COPROASO", Tarifario.getExamenesLaboratorioBean().getCodigo());  
			spq.setParameter("UNIDADES", Tarifario.getUnidades());  
			spq.setParameter("OBSERVAC", Tarifario.getObservacion());  
			spq.setParameter("VALORINI", Tarifario.getValoresRefIni()); 
			spq.setParameter("VALORFIN", Tarifario.getValoresRefFin());  
			spq.setParameter("AUCDUSCR", Tarifario.getCodigoUsuarioCreacion()); 
			spq.setParameter("AUPCIPCR", Tarifario.getIpCreacion());  
			
			spq.execute();
			
			idTarifario = spq.getOutputParameterValue(1);
		//	nroPeriodo = spq.getOutputParameterValue(16);
			if (idTarifario != null) {
				Tarifario.setCodigo(idTarifario.toString());
			//	Tarifario.setNumeroPeriodo(nroPeriodo.toString());
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
	public boolean actualizar(TarifarioDetalleBean Tarifario) throws DAOException {  
 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("tarifarioUnidad.actualizar");
			spq.setParameter("CODUNVAL", Tarifario.getCodigo());  
			spq.setParameter("CODTARIF", Tarifario.getTarifarioBean().getCodigo());  
			spq.setParameter("UNIDADES", Tarifario.getUnidades());  
			spq.setParameter("OBSERVAC", Tarifario.getObservacion());  
			spq.setParameter("VALORINI", Tarifario.getValoresRefIni()); 
			spq.setParameter("VALORFIN", Tarifario.getValoresRefFin());  
			spq.setParameter("AUCDUSMO", Tarifario.getCodigoUsuarioModificacion()); 
			spq.setParameter("AUPCIPMO", Tarifario.getIpModificacion());  

			
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
	public boolean eliminar(TarifarioDetalleBean Tarifario) throws DAOException {
		
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("tarifarioUnidad.eliminar");
			spq.setParameter("CODUNVAL", Tarifario.getCodigo());  
			spq.setParameter("CODTARIF", Tarifario.getTarifarioBean().getCodigo());   
			spq.setParameter("AUCDUSMO", Tarifario.getCodigoUsuarioModificacion()); 
			spq.setParameter("AUPCIPMO", Tarifario.getIpModificacion());  

			
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
	public TarifarioDetalleBean getBuscarPorObjecto(TarifarioDetalleBean t) throws DAOException {
		
		return null;
	}

	@Override
	public List<TarifarioDetalleBean> getBuscarPorFiltros(TarifarioDetalleBean TarifarioDetalleBean)
			throws DAOException {
		
		List<Tarifario_unidadValor> lstMaestra = null;	
		List<TarifarioDetalleBean> lstTablaBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("tarifarioUnidad.listarPorFiltros"); 
			spq.setParameter("CODTARIF", TarifarioDetalleBean.getTarifarioBean().getCodigo());
			if (spq.execute()) {
				lstMaestra =  spq.getResultList(); 
			} 
			if (lstMaestra != null && lstMaestra.size() > 0) {
				
				lstTablaBean = deListaTarifariolaboratorioAListaTarifarioDetalleBean(lstMaestra);
			}
			em.close();
			
		   
		return lstTablaBean;
	}

	@Override
	public boolean existe(TarifarioDetalleBean t) throws DAOException {
		
		return false;
	}
 
	private List<TarifarioDetalleBean> deListaTarifariolaboratorioAListaTarifarioDetalleBean(List<Tarifario_unidadValor> lstTarifario_unidadValor) {
		
		List<TarifarioDetalleBean> lstlstTarifarioDetalleBean = null;
		
		if (lstTarifario_unidadValor != null && lstTarifario_unidadValor.size() > 0) {
			
			lstlstTarifarioDetalleBean = new ArrayList<TarifarioDetalleBean>();
			
			for (int i = 0; i < lstTarifario_unidadValor.size(); i++) { 
				Tarifario_unidadValor entity = lstTarifario_unidadValor.get(i);
				TarifarioDetalleBean bean = deTarifarioATarifarioDetalleBean(entity);
				
				lstlstTarifarioDetalleBean.add(bean);
			}
		}
		
		return lstlstTarifarioDetalleBean;
	}
	private TarifarioDetalleBean deTarifarioATarifarioDetalleBean(Tarifario_unidadValor entity) {
		
		TarifarioDetalleBean bean = null;
		
		if (entity != null) {
			bean = new TarifarioDetalleBean(); 
			bean.setCodigo(entity.getCODUNVAL());
			bean.getTarifarioBean().setCodigo(entity.getCODTARIF());
			bean.setUnidades(entity.getUnidades());
			bean.setValoresRefFin(entity.getValoresRefFin());
			bean.setValoresRefIni(entity.getValoresRefIni());
			bean.setObservacion(entity.getObservacion());
		}
		
		return bean;
	}
  
}
