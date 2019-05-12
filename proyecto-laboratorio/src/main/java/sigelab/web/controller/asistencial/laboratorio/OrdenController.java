package sigelab.web.controller.asistencial.laboratorio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;

import sigelab.core.bean.asistencial.banco.PostulanteBean; 
import sigelab.core.bean.asistencial.laboratorio.OrdenBean;
import sigelab.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import sigelab.core.bean.general.PersonaBean;
import sigelab.core.bean.general.TablaBean;
import sigelab.core.bean.general.TarifarioBean;
import sigelab.core.bean.general.UbigeoBean;
import sigelab.core.bean.seguridad.AccesoBean;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.asistencial.laboratorio.OrdenService;
import sigelab.core.service.interfaces.asistencial.maestra.MaestraAsis01Service; 
import sigelab.core.service.interfaces.general.Maestra1Service; 
import sigelab.core.service.interfaces.general.PersonaService;
import sigelab.core.service.interfaces.general.TarifarioService; 
import sigelab.web.controller.base.BaseController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader; 

@Controller
@RequestMapping(value = "ordenController")
public class OrdenController  extends BaseController {
	
	List<TablaBean> lstSituacion = new ArrayList<TablaBean>();
	List<TablaBean> lstDocumento = new ArrayList<TablaBean>();
	List<TablaBean> lstSexo = new ArrayList<TablaBean>();
	List<TablaBean> lstEstadoCivil = new ArrayList<TablaBean>();
	List<TablaBean> lstOcupacion = new ArrayList<TablaBean>();
	List<TablaBean> lstNacionalidad = new ArrayList<TablaBean>();
	List<TablaBean> lstNivelInstrucion = new ArrayList<TablaBean>();
	List<UbigeoBean> lstUbigeoBean = new ArrayList<UbigeoBean>();
	List<TablaBean> lstTipoExamen = new ArrayList<TablaBean>();
	
	PersonaBean personaBean = new PersonaBean();  
	private List<TarifarioBean> lstTarifarioBean ; 
	private List<OrdenDetalleBean> lstOrdenDetalleBean ;  
	 
	@Autowired
	private MaestraAsis01Service maestraAsis01Service;
	 
	@Autowired
	private OrdenService ordenService;
	
	@Autowired
	private TarifarioService tarifarioService;
	
	@Autowired
	private Maestra1Service maestraGene01Services;
	
	@Autowired
	private PersonaService personaService;
	 
	      
	@PostConstruct
	public void init() { 
		
	}
	
	private void cargarCombos(ModelAndView mav) {
		try { 
			lstDocumento = maestraAsis01Service.listarPorCodigoTabla("000003", 1);
			lstSexo = maestraAsis01Service.listarPorCodigoTabla("000004", 1);
			lstEstadoCivil = maestraGene01Services.listarPorCodigoTabla("000005", 0);
			lstOcupacion = maestraGene01Services.listarPorCodigoTabla("000007", 0);
			lstNacionalidad = maestraGene01Services.listarPorCodigoTabla("000003", 0);
			lstNivelInstrucion = maestraGene01Services.listarPorCodigoTabla("000006", 0);
			
		} catch (ServiceException e) {
			System.out.println("printStackTrace");
			e.printStackTrace();
		}
		
		mav.addObject("lstSituacion", lstSituacion);
		mav.addObject("lstDocumento", lstDocumento); 
		mav.addObject("lstSexo", lstSexo); 
		mav.addObject("lstEstadoCivil", lstEstadoCivil); 
		mav.addObject("lstOcupacion", lstOcupacion); 
		mav.addObject("lstNacionalidad", lstNacionalidad); 
		mav.addObject("lstNivelInstrucion", lstNivelInstrucion); 
	}
	
 
	 
	
	@RequestMapping(value = "/consultarPersonaPorDocumento", method = RequestMethod.GET)
	public @ResponseBody PersonaBean consultarPorNroDocumento(
			@RequestParam("tipoDocumento") String tipoDocumento,
			@RequestParam("numero") String numero)throws Exception {
		this.setPersonaBean(new PersonaBean());  
		personaBean = new PersonaBean();
		PersonaBean prmPersona = new PersonaBean();
		prmPersona.setNroDocumento(numero);
		prmPersona.getTipoDocumento().setCodReg(tipoDocumento);
		try { 
				personaBean = personaService.buscarxTipoDocumentoNumeroDocumento(prmPersona);
				if(personaBean!=null){   
					System.out.println(personaBean.getNombreCompleto());  
					System.out.println("personaBean.getCodigo() " + personaBean.getCodigo());
					PostulanteBean postulante = new PostulanteBean();
					postulante.setPersona(personaBean);  
					
				}else{   
				} 
		} catch (Exception e) {  
		} 
		 
	    this.setPersonaBean(personaBean);
		return personaBean;
	}
	 
	
	 
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public ModelAndView nuevoPostulante(HttpServletRequest request) {
		OrdenBean objOrdenBean = new OrdenBean();  
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/registro-orden", "command", objOrdenBean);  
		this.cargarCombos(mav); 
		return mav;
	} 
	 
	
	@RequestMapping(value = "rptListadoPostulantes", method = RequestMethod.GET)
	@ResponseBody
	public void getRpt1(@RequestParam("nombreCampania") String nombreCampania,
						HttpServletResponse response, 
						HttpServletRequest request) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/rptListadoPostulantes.jasper");
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("usuario", getUsuarioSesion(request).getNombreUsuario());
		parametro.put("nombreCampania", nombreCampania);
		System.out.println("jasperStream " + jasperStream);
	//	List<ReferenciaBean> rates = new ArrayList<ReferenciaBean>();
	
		//	rates.add(getReferenciaBean());
		// data source
		// JRDataSource dataSource = getDataSource(rates);
		JRBeanCollectionDataSource dataSource =   null;//new JRBeanCollectionDataSource(lstPostulanteBean);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, dataSource);

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=listadoPostulantes.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	 
	 
	
	@RequestMapping(value = "/refrescarListTarifario", method = RequestMethod.GET)
	public @ResponseBody List<TarifarioBean> refrescarListTarifario(
							String tipo, 
							String descripcion
			)throws Exception { 
		
		System.out.println("descripcion== " + descripcion);
		System.out.println("tipo== " + tipo);
		
		TarifarioBean prmTarifarioBean = new TarifarioBean(); 
		prmTarifarioBean.setTipo(new TablaBean());
		prmTarifarioBean.getTipo().setCodReg(tipo);
		prmTarifarioBean.setDescripcion(descripcion);
		
		lstTarifarioBean = new ArrayList<TarifarioBean>(); 
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(prmTarifarioBean);   
		} catch (ServiceException e) { 
			e.printStackTrace();
		}
		 
		 
		return lstTarifarioBean; 
	} 
 
	@RequestMapping(value = "/tarifarioModal", method = RequestMethod.POST)
	public ModelAndView tarifarioModal() throws Exception {
		TarifarioBean prmTarifarioBean = new TarifarioBean(); 
		prmTarifarioBean.setTipo(new TablaBean());
		prmTarifarioBean.getTipo().setCodReg("");
		prmTarifarioBean.setDescripcion("");
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/listado-tarifario-modal", "command", new TarifarioBean());
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		lstTipoExamen = new ArrayList<TablaBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(prmTarifarioBean);  
			System.out.println("lstTarifarioBean.size() " + lstTarifarioBean.size());
			lstTipoExamen = maestraAsis01Service.listarPorCodigoTabla("000013", 1);
		} catch (ServiceException e) { 
			e.printStackTrace();
		}
		
		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		mav.addObject("lstTipoExamen", lstTipoExamen);
		return mav;
	} 
  
   @RequestMapping(value = "/refrescarListaOrden", method = RequestMethod.GET)
	public @ResponseBody OrdenDetalleBean refrescarListaOrden(@RequestParam("index") int index) throws Exception {
		System.out.println("index " + index);
		OrdenDetalleBean objOrdenDetalleBean = new OrdenDetalleBean(); 
		objOrdenDetalleBean.setExamen(lstTarifarioBean.get(index)); 
		objOrdenDetalleBean.setPrecio(lstTarifarioBean.get(index).getPrecio());
		objOrdenDetalleBean.setCantidad(1);
		objOrdenDetalleBean.ejecutarImporte();
		DecimalFormat df = new DecimalFormat("0.00"); 
		objOrdenDetalleBean.setsImporte((df.format(objOrdenDetalleBean.getImporte()).replace(",", ".")));
		//lstOrdenDetalleBean.add(objOrdenDetalleBean);
		return objOrdenDetalleBean;
	}

    @RequestMapping(value = "/grabarOrden", method = RequestMethod.POST)
	@ResponseBody
	public String grabarOrden(
		//	@RequestParam("personaCodigo")  String personaCodigo,
			@RequestBody OrdenDetalleBean[] ordenDetalleArray,  
			HttpServletRequest request) {
    		
    	System.out.println("personaCodigo:: " + this.getPersonaBean().getCodigo());
		String codigo = "";
		boolean sw = false;
		String cadenaCodigoTarifario = "@";
		String cadenaCantidad = "@"; 
		OrdenBean ordenBean = new OrdenBean();
		ordenBean.getPacienteBean().setPersona(this.getPersonaBean());
		for (OrdenDetalleBean prmOrdenDetalleBean : ordenDetalleArray) {
			System.out.println("getCodReg == >" + prmOrdenDetalleBean.getExamen().getCodigo());
			
			cadenaCantidad = cadenaCantidad + prmOrdenDetalleBean.getCantidad() + "@";
			cadenaCodigoTarifario = cadenaCodigoTarifario + prmOrdenDetalleBean.getExamen().getCodigo() + "@";
		}
		ordenBean.setCadenaCantidad(cadenaCantidad);
		ordenBean.setCadenaCodigoTarifario(cadenaCodigoTarifario);
		ordenBean.setCantidadItems(ordenDetalleArray.length);
		 
		try {

			if (ordenBean.getCodigo().equals("")) {
				System.out.println("referenciaForm.getReferenciaBean().getPaciente().getTipoSeguro().getCodReg() "
						+ ordenBean.getPacienteBean().getPersona().getCodigo());
				this.setAuditoria(ordenBean, request, true);
				  
				sw = (this.ordenService.insertar(ordenBean));

				if (sw) {
					codigo = ordenBean.getCodigo(); 

				} 

			} else {
				// UPDATE
				this.setAuditoria(ordenBean, request, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("sw " + sw);
		return codigo; 
		 
	}

	@RequestMapping(value = "/personaModal", method = RequestMethod.POST)
	public ModelAndView personaModalPost() throws Exception {
		
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/persona-registro-modal", "command", new PersonaBean());
		this.cargarCombos(mav);
		return mav;
	} 
	
	public PersonaBean getPersonaBean() {
		return personaBean;
	}

	public void setPersonaBean(PersonaBean personaBean) {
		this.personaBean = personaBean;
	} 

	public List<TarifarioBean> getLstTarifarioBean() {
		return lstTarifarioBean;
	}

	public void setLstTarifarioBean(List<TarifarioBean> lstTarifarioBean) {
		this.lstTarifarioBean = lstTarifarioBean;
	}

}

