








package sigelab.core.repository.implementacion.general;
  
import sigelab.core.bean.general.EmpresaBean;  
import sigelab.core.entity.general.Empresa;
import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.general.EmpresaDAO; 
import java.util.ArrayList;
import java.util.List; 
import javax.persistence.EntityManager; 
import javax.persistence.PersistenceContext; 
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository("EmpresaBeanDAO")
public class EmpresaDAOImpl implements EmpresaDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em;
	
	@Override
	public boolean insertar(EmpresaBean EmpresaBean) throws DAOException { 

		//System.out.println("EmpresaBean DAO "+EmpresaBean);
		Object idPersona= null; 
		Object nroPeriodo= null; 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empresa.insertar");
			 
			spq.setParameter("CODORGAN",  EmpresaBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI",  EmpresaBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI",  EmpresaBean.getCodigoSede());
			  
			spq.setParameter("AUCDUSCR",  EmpresaBean.getCodigoUsuarioCreacion());
			spq.setParameter("AUPCIPCR",  EmpresaBean.getIpCreacion()); 
				
			spq.execute();
			
			idPersona = spq.getOutputParameterValue(1);
			nroPeriodo = spq.getOutputParameterValue(2);
			if (idPersona != null) {
				EmpresaBean.setCodigo(idPersona.toString());
				EmpresaBean.setNumeroPeriodo(nroPeriodo.toString());
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
	public boolean actualizar(EmpresaBean EmpresaBean) throws DAOException {
		//LenguaBean lengua= this.getLenguaBean(objeto); 
		System.out.println("EmpresaBean DAO "+EmpresaBean);
 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empresa.actualizar");
			 
			spq.setParameter("NRORUC", EmpresaBean.getNroRUC());
			spq.setParameter("LOGO", EmpresaBean.getLogo());
			spq.setParameter("DOMICILIO", EmpresaBean.getDomicilioFiscal());
			spq.setParameter("DESCRIPCI", EmpresaBean.getDescripcion());
			spq.setParameter("TELFCELU", EmpresaBean.getTelefonoCelular());
			spq.setParameter("CORREO", EmpresaBean.getCorreo());
			spq.setParameter("AUPCIPMO", EmpresaBean.getIpModificacion());
			spq.setParameter("AUCDUSMO", EmpresaBean.getCodigoUsuarioModificacion());
			spq.setParameter("SITUACRG", EmpresaBean.getSituacion().getCodReg());
			spq.setParameter("NOMBLOGO", EmpresaBean.getNombreLogo());
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
	public boolean eliminar(EmpresaBean t) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmpresaBean getBuscarPorObjecto(EmpresaBean t) throws DAOException {
		List<Empresa> lstEmpresa = null;
		EmpresaBean lstEmpresaBean = null;

		StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empresa.select"); 
		
		if (spq.execute()) {
			lstEmpresa = spq.getResultList();
		}
		if (lstEmpresa != null && lstEmpresa.size() > 0) {

			lstEmpresaBean = deEmpresaAEmpresaBean(lstEmpresa.get(0));
		}
		 em.close();

		return lstEmpresaBean;
	}

	@Override
	public List<EmpresaBean> getBuscarPorFiltros(EmpresaBean renaesBean)
			throws DAOException {
		List<Empresa> lstEmpresa = null;
		List<EmpresaBean> lstEmpresaBean = null;

		StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("empresa.select");
		/*spq.setParameter("UBIGEO", renaesBean.getUbiEmpresa());
		spq.setParameter("NOMRENEA", renaesBean.getNomEmpresa());
		spq.setParameter("TG14CATE", renaesBean.getCategoria().getCodReg());
		spq.setParameter("CODIGO_DISA", renaesBean.getCodigoDisa());*/
		
		if (spq.execute()) {
			lstEmpresa = spq.getResultList();
		}
		if (lstEmpresa != null && lstEmpresa.size() > 0) {

			lstEmpresaBean = deListaReferenciaAListaReferenciaBean(lstEmpresa);
		}
		 em.close();

		return lstEmpresaBean;
	}

	@Override
	public boolean existe(EmpresaBean t) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	} 
	
	private List<EmpresaBean> deListaReferenciaAListaReferenciaBean(List<Empresa> lstEmpresa) {

		List<EmpresaBean> lstReferenciaBean = null;

		if (lstEmpresa != null && lstEmpresa.size() > 0) {

			lstReferenciaBean = new ArrayList<EmpresaBean>();

			for (int i = 0; i < lstEmpresa.size(); i++) {
				Empresa entity = lstEmpresa.get(i);
				EmpresaBean bean = deEmpresaAEmpresaBean(entity);

				lstReferenciaBean.add(bean);
			}
		}

		return lstReferenciaBean;
	}
	
	private EmpresaBean deEmpresaAEmpresaBean(Empresa entity) {
		
		EmpresaBean bean = null;
		
		if (entity != null) {
			bean = new EmpresaBean();
			bean.setCodigo(entity.getCodempre()); 
			bean.setNombreEmpresa(entity.getNombre());
			bean.setNombreComercial(entity.getNomcom());
			bean.setDomicilioFiscal(entity.getDomicilio());
			bean.setLogo(entity.getLogo());
			bean.setNroRUC(entity.getNroruc());
			bean.setTelefonoCelular(entity.getTELFCELU());
			bean.setCorreo(entity.getCORREO());
			bean.setDescripcion(entity.getDescripci());
			bean.setNombreLogo(entity.getNOMBLOGO());
		}
		
		return bean;
	} 

 
}
