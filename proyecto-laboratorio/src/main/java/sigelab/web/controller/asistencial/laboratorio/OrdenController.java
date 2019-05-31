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

import sigelab.core.bean.asistencial.banco.CampaniaBean;
import sigelab.core.bean.asistencial.banco.PostulanteBean; 
import sigelab.core.bean.asistencial.laboratorio.OrdenBean;
import sigelab.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import sigelab.core.bean.general.PersonaBean;
import sigelab.core.bean.general.TablaBean;
import sigelab.core.bean.general.TarifarioBean;
import sigelab.core.bean.general.UbigeoBean; 
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.asistencial.laboratorio.OrdenDetalleService;
import sigelab.core.service.interfaces.asistencial.laboratorio.OrdenService;
import sigelab.core.service.interfaces.asistencial.maestra.MaestraAsis01Service; 
import sigelab.core.service.interfaces.general.Maestra1Service; 
import sigelab.core.service.interfaces.general.PersonaService;
import sigelab.core.service.interfaces.general.TarifarioService;
import sigelab.core.service.interfaces.general.UbigeoService;
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
	private UbigeoBean ubigeobean;
	private List<TarifarioBean> lstTarifarioBean ; 
	private List<OrdenDetalleBean> lstOrdenDetalleBean ;  
	private List<OrdenBean> lstOrdenBean ;  
	
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
	 
	@Autowired
	private UbigeoService ubigeoService;
	
	@Autowired
	private OrdenDetalleService ordenDetalleService;
	
	
	@PostConstruct
	public void init() { 
		
	}
	
	private void cargarCombos(ModelAndView mav) {
		try { 
			lstDocumento = maestraAsis01Service.listarPorCodigoTabla("000003", 1);
			lstSexo = maestraAsis01Service.listarPorCodigoTabla("000004", 1);
			lstEstadoCivil = maestraGene01Services.listarPorCodigoTabla("000005", 0);
			lstSituacion = maestraAsis01Service.listarPorCodigoTabla("000015", 1);
			lstNacionalidad = maestraGene01Services.listarPorCodigoTabla("000003", 0);
			//lstNivelInstrucion = maestraGene01Services.listarPorCodigoTabla("000006", 0);
			
		} catch (ServiceException e) {
			System.out.println("printStackTrace");
			e.printStackTrace();
		}
		
		mav.addObject("lstSituacion", lstSituacion);
		mav.addObject("lstDocumento", lstDocumento); 
		mav.addObject("lstSexo", lstSexo); 
		mav.addObject("lstEstadoCivil", lstEstadoCivil); 
		//mav.addObject("lstOcupacion", lstOcupacion); 
		mav.addObject("lstNacionalidad", lstNacionalidad); 
		//mav.addObject("lstNivelInstrucion", lstNivelInstrucion); 
	}
	
 
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public ModelAndView listado(@ModelAttribute("ordenBean") OrdenBean ordenBean, HttpServletRequest request) throws Exception {
		return this.listadoPost(ordenBean, request) ;
	}
	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public ModelAndView listadoPost(@ModelAttribute("ordenBean") OrdenBean ordenBean, HttpServletRequest request) throws Exception {
		ordenBean.getSituacion().setCodReg("000001");
		lstOrdenBean = new ArrayList<OrdenBean>(); 
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/listado-orden", "command", ordenBean); 
		lstOrdenBean = ordenService.getBuscarPorFiltros(ordenBean);
		lstSituacion = maestraAsis01Service.listarPorCodigoTabla("000015", 1);
		
		mav.addObject("lstSituacion", lstSituacion);
		mav.addObject("lstOrdenBean", lstOrdenBean); 
		mav.addObject("ordenBean", ordenBean); 
		return mav;
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public ModelAndView buscarPOST(@ModelAttribute("ordenBean") OrdenBean ordenBean,
			HttpServletRequest request
			)
			throws Exception { 
		lstOrdenBean = new ArrayList<OrdenBean>(); 
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/listado-orden", "command", ordenBean); 
		lstOrdenBean = ordenService.getBuscarPorFiltros(ordenBean);
		mav.addObject("lstOrdenBean", lstOrdenBean); 
		mav.addObject("ordenBean", ordenBean); 
		return mav;
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscarGet(@ModelAttribute("ordenBean") OrdenBean ordenBean,
			HttpServletRequest request
			)
			throws Exception { 
		return this.buscarPOST(ordenBean, request) ;
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
	
	
	@RequestMapping(value = "/consultarPersonaPorDocumento", method = RequestMethod.POST)
	public @ResponseBody PersonaBean consultarPorNroDocumentoPOST(
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
	 
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ModelAndView nuevoPostulantePOST(PersonaBean personaBean, HttpServletRequest request) {
		OrdenBean objOrdenBean = new OrdenBean();  
		objOrdenBean.getPacienteBean().setPersona(personaBean);
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
	
	@RequestMapping(value = "/analisisModal", method = RequestMethod.POST)
	public ModelAndView AnalisisModalPost() throws Exception {
		System.out.println("modal analisis");
		PersonaBean objPersona = new  PersonaBean();
		objPersona.getNacionalidad().setCodReg("000114");
		objPersona.getEstadoCivil().setCodReg("000006");
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/analisis-registro-modal", "command",  objPersona);
	//	ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/persona-registro-modal", "command",  objPersona);
		
		ubigeobean = new UbigeoBean();
		ubigeobean.setVariable("");
		ubigeobean.setInstitucion("000003");
		ubigeobean.setCategoria("000003");
		lstUbigeoBean = new ArrayList<UbigeoBean>();
		try {
			
			lstUbigeoBean = ubigeoService.getBuscarPorFiltros(ubigeobean);
		} catch (Exception e) { 
		}
		mav.addObject("ubigeoBean", new UbigeoBean());
		mav.addObject("lstUbigeoBean", lstUbigeoBean);
		this.cargarCombos(mav);
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
		PersonaBean objPersona = new  PersonaBean();
		objPersona.getNacionalidad().setCodReg("000114");
		objPersona.getEstadoCivil().setCodReg("000006");
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/persona-registro-modal", "command",  objPersona);
	//	ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/persona-registro-modal", "command",  objPersona);
		
		ubigeobean = new UbigeoBean();
		ubigeobean.setVariable("");
		ubigeobean.setInstitucion("000003");
		ubigeobean.setCategoria("000003");
		lstUbigeoBean = new ArrayList<UbigeoBean>();
		try {
			
			lstUbigeoBean = ubigeoService.getBuscarPorFiltros(ubigeobean);
		} catch (Exception e) { 
		}
		mav.addObject("ubigeoBean", new UbigeoBean());
		mav.addObject("lstUbigeoBean", lstUbigeoBean);
		this.cargarCombos(mav);
		return mav;
	} 
	
	@RequestMapping(value = "/grabarPersonaLaboratorio", method = RequestMethod.POST)
	public @ResponseBody PersonaBean grabarPersonaLaboratorio(@ModelAttribute("personaBean")PersonaBean obpersonaBean,
											 HttpServletRequest request) throws Exception {  
	
		if (personaBean==null) {
			personaBean = new PersonaBean();
		} 
		if (this.getPersonaBean().getCodigo().equals("")) {
			if(!obpersonaBean.getTipoDocumento().getCodReg().equals("000002") ){ // extranjero
				this.setAuditoria(obpersonaBean, request, true); 
				this.personaService.insertarPersonaLaboratorio(obpersonaBean);
				setPersonaBean(obpersonaBean);
			}else{
				setPersonaBean(new PersonaBean());
				if(this.getPersonaBean().getSwReniec()){
					
					System.out.println("this.getPostulanteBean().getPersona(). " +this.getPersonaBean().getTipoDocumento().getCodReg());
					System.out.println("this.getPostulanteBean().getPersona() ::" + this.getPersonaBean());
					this.getPersonaBean().setTelefonoNumero(obpersonaBean.getTelefonoNumero());
					this.getPersonaBean().setCorreo(obpersonaBean.getCorreo());
					this.getPersonaBean().getNivelInstrucion().setCodReg(obpersonaBean.getNivelInstrucion().getCodReg());
					this.getPersonaBean().getOcupacion().setCodReg(obpersonaBean.getOcupacion().getCodReg());
					
					this.setAuditoria(this.getPersonaBean(), request, true); 
					this.personaService.insertarPersonaLaboratorio(this.getPersonaBean());
					System.out.println("persona reniec");
				}else{
					this.getPersonaBean().setTelefonoNumero(obpersonaBean.getTelefonoNumero());
					this.getPersonaBean().setCorreo(obpersonaBean.getCorreo());
					this.getPersonaBean().getNivelInstrucion().setCodReg(obpersonaBean.getNivelInstrucion().getCodReg());
					this.setAuditoria(obpersonaBean, request, true); 
					this.personaService.insertarPersonaLaboratorio(obpersonaBean);
					setPersonaBean(obpersonaBean);
				}
			} 
			System.out.println("persona no existe"); 
			
		}else{ 
			System.out.println("existe persona");
			 
				this.setAuditoria(this.getPersonaBean(), request, true); 
				System.out.println("postulanteBean.getCodigoCorreo " + obpersonaBean.getCodigoCorreo());
				System.out.println("postulanteBean.getCodigoDireccion " + obpersonaBean.getCodigoDireccion());
				System.out.println("postulanteBean.getCodigoTelefono " + obpersonaBean.getCodigoTelefono());
				this.personaService.actualizarPersonaLaboratorio(obpersonaBean); 
			
		}  
		if (!personaBean.getCodigo().trim().equals("")) {
			this.setAuditoria(this.getPersonaBean(), request, false); 
			System.out.println("actualiza postulanteBean " + obpersonaBean.getCodigo());
	
		} else {
			  
			
		}   
		return this.getPersonaBean();
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView doModificar(@RequestParam("index") Integer index, HttpServletRequest request) {

		System.out.println("modificar codigo: " + index); 
		OrdenBean objOrdenBean = new OrdenBean(); 
		objOrdenBean = this.lstOrdenBean.get(index);
		System.out.println("modificar objOrdenBean: " + objOrdenBean.getCodigo());
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/registro-orden", "command", objOrdenBean); 
		OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
		objOrdenDetalle.setOrdenBean(objOrdenBean);
		try {
			lstOrdenDetalleBean = ordenDetalleService.getBuscarPorFiltros(objOrdenDetalle);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("lstOrdenDetalleBean", lstOrdenDetalleBean); 
		mav.addObject("ordenBean", objOrdenBean); 
		this.cargarCombos(mav);
		return mav;
	}
	
	
	@RequestMapping(value = "/registroResultado", method = RequestMethod.POST)
	public ModelAndView doListaOrdenes(@RequestParam("index") Integer index, HttpServletRequest request) {

		System.out.println("modificar codigo: " + index); 
		OrdenBean objOrdenBean = new OrdenBean(); 
		objOrdenBean = this.lstOrdenBean.get(index);
		System.out.println("modificar objOrdenBean: " + objOrdenBean.getCodigo());
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/registro-orden-analisis", "command", objOrdenBean); 
		OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
		objOrdenDetalle.setOrdenBean(objOrdenBean);
		try {
			lstOrdenDetalleBean = ordenDetalleService.getBuscarPorFiltros(objOrdenDetalle);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("lstOrdenDetalleBean", lstOrdenDetalleBean); 
		mav.addObject("ordenBean", objOrdenBean); 
		this.cargarCombos(mav);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/modalAnularOrden", method = RequestMethod.POST)
	public ModelAndView modalAnularOrden(@RequestParam("index") Integer index) throws Exception {

		List<TablaBean> lstMotivos = maestraAsis01Service.listarPorCodigoTabla("000014", 1);
 
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/anular-orden", "command",lstOrdenBean.get(index));

		mav.addObject("lstMotivos", lstMotivos);
		mav.addObject("ordenBean", lstOrdenBean.get(index));

		return mav;
	}

	@RequestMapping(value = "/anularOrden", method = RequestMethod.GET)
	@ResponseBody
	public String anularOrden( 
			@ModelAttribute("ordenBean") OrdenBean ordenBean) throws Exception {

		System.out.println("referenciaBean descartar::" + ordenBean.getCodigo());
		 
		String valida = "";
		try {
			  this.ordenService.eliminar(ordenBean);
			valida = "1";

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return valida; 

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

