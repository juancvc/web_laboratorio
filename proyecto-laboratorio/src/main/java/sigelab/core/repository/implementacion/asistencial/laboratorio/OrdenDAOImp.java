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

import sigelab.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import sigelab.core.bean.asistencial.laboratorio.OrdenBean;
import sigelab.core.bean.general.PersonaBean;
import sigelab.core.entity.asistencial.laboratorio.Analisis_formula_laboratorio;
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
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.eliminar"); 
			spq.setParameter("CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("NROPERIO", OrdenBean.getNumeroPeriodo()); 
			spq.setParameter("CODORDEN", OrdenBean.getCodigo());
		
			spq.setParameter("TG1TPANU", OrdenBean.getMotivoAnular().getCodReg()); 
			spq.setParameter("MOTIVOAN", OrdenBean.getDetalleMotivoAnular()); 
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
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.buscarPorFiltros");   
			spq.setParameter("FECDESDE", OrdenBean.getFechaDesde()); 
			spq.setParameter("FECHASTA", OrdenBean.getFechaHasta()); 
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
			bean.getSituacion().setNombreCorto(entity.getNOMBSITU()); 
			bean.setNroOrden(entity.getNroOrden());  
			bean.setImporteTotal(entity.getImpTotal());
			System.out.println("bean.setimportetotal"+bean.getImporteTotal());
			if(entity.getImpTotal()!=null) {
				bean.setsImporteTotal((getTwoDecimals(entity.getImpTotal()).replace(",", ".")));
			}
			
			
			bean.setStrFechaOrden(entity.getStrFechaOrd()); 
			bean.setHoraOrden(entity.getHora()); 
			bean.getPacienteBean().setPersona(new PersonaBean());
			bean.getPacienteBean().getPersona().setCodigo(entity.getCodPerso()); 
			bean.getPacienteBean().getPersona().setApellidoPaterno(entity.getAPEPATER()); 
			bean.getPacienteBean().getPersona().setApellidoMaterno(entity.getAPEMATER()); 
			bean.getPacienteBean().getPersona().setPrimerNombre(entity.getPRINOMBR()); 
			bean.getPacienteBean().getPersona().setSegundoNombre(entity.getSEGNOMBR()); 
			bean.getPacienteBean().getPersona().setNroDocumento(entity.getDETALLED()); 
			bean.getPacienteBean().getPersona().setEdad(entity.getEDAD());
			bean.getPacienteBean().getPersona().setCorreo(entity.getCORREOPE());
			bean.getPacienteBean().getPersona().setDireccion(entity.getDIRECCIO());
			bean.getPacienteBean().getPersona().getSexo().setCodReg(entity.getTG1SEXOP());
			bean.setNombreUsuarioCreacion(entity.getNOMUSUAR());
			bean.setCantidadOrdenes(entity.getCANTIDAD_ORDEN());
			bean.setCantidadVentas(entity.getTOTAL_VENTA());
			bean.setNombreExamen(entity.getEXAMEN());
			bean.setCantidadExamenesSolicitados(entity.getCANTIDAD());
			bean.setNombreMes(entity.getMES());
	 	}
		
		return bean;
	}

	
	
	@Override
	public boolean existe(OrdenBean t) throws DAOException {

		return false;
	}

	private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }

	@Override
	public OrdenBean reporteCantidadAnualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadAnualOrdenSituacion");
			spq.setParameter("ANIO", ordenBean.getNumeroPeriodo());
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadMensualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadMensualOrdenSituacion");
			spq.setParameter("MES", 	 ordenBean.getNroMes());
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadDiarioOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadDiarioOrdenSituacion");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadSemanalOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadSemanalOrdenSituacion");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaAnual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaAnual");
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaMensual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaMensual");
			spq.setParameter("MES", 	 ordenBean.getNroMes());	
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaDiaria(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaDiaria");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());			
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaSemanal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaSemanal");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());			
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaAnual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaAnual");   
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());	
			
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}
	

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaMensual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaMensual"); 
			spq.setParameter("MES", 	 ordenBean.getNroMes());	
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());	
			
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaDiaria(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaDiaria"); 
			spq.setParameter("FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaSemanal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaSemanal"); 
			spq.setParameter("FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteVentaMensualPeriodoArea(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaMensualPeriodoArea"); 
			spq.setParameter("MES", 	 ordenBean.getNroMes());	
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());		
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteVentaMensualPeriodoBarra(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaMensualPeriodoBarra"); 
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());		
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleAnualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleAnualOrdenSituacion"); 
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());		
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleMensualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleMensualOrdenSituacion");
			spq.setParameter("MES", 	 ordenBean.getNroMes());	
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());		
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleDiariaOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleDiariaOrdenSituacion");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());		
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleSemanalOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleSemanalOrdenSituacion");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());		
			spq.setParameter("SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadAnualExamenesSolicitados");
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadAnualExamenesSolicitadosTotal");
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadMensualExamenesSolicitados");
			spq.setParameter("MES", 	 ordenBean.getNroMes());	
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadMensualExamenesSolicitadosTotal");
			spq.setParameter("MES", 	 ordenBean.getNroMes());	
			spq.setParameter("ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadDiarioExamenesSolicitados");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadDiarioExamenesSolicitadosTotal");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadSemanalExamenesSolicitados");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadSemanalExamenesSolicitadosTotal");
			spq.setParameter("FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

}
