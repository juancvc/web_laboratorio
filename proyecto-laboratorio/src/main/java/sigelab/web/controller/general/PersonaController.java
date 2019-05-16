package sigelab.web.controller.general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import gob.hnch.systems.ws.hnch.client.imp.PersonaServiceImp;
import sigelab.base.bean.BaseBean;
import sigelab.core.bean.asistencial.banco.CampaniaBean;
import sigelab.core.bean.asistencial.banco.DonanteBean;
import sigelab.core.bean.asistencial.banco.EstadoFisicoBean;
import sigelab.core.bean.asistencial.banco.PostulanteBean;
import sigelab.core.bean.asistencial.laboratorio.OrdenBean;
import sigelab.core.bean.general.PersonaBean;
import sigelab.core.bean.general.TablaBean;
import sigelab.core.bean.general.UbigeoBean;
import sigelab.core.bean.seguridad.UsuarioBean;
import sigelab.core.entity.asistencial.banco.InterUAL;
import sigelab.core.entity.general.PacienteReniec;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.asistencial.maestra.MaestraAsis01Service;
import sigelab.core.service.interfaces.general.Maestra1Service;
import sigelab.core.service.interfaces.general.PacienteReniecService;
import sigelab.core.service.interfaces.general.PersonaService;
import sigelab.core.service.interfaces.general.UbigeoService;
import sigelab.web.utilitarios.NetUtil;
import sigelab.web.utilitarios.VO; 

@Controller
@RequestMapping(value = "personaController")
public class PersonaController {
	
	
	
	PostulanteBean postulanteBean = new PostulanteBean();
	PersonaBean  opersonaBean = new PersonaBean();
	PersonaBean personaBean = new PersonaBean(); 
	
	String nombreUser = "";
	List<TablaBean> lstSituacion = new ArrayList<TablaBean>();
	List<TablaBean> lstDocumento = new ArrayList<TablaBean>();
	List<TablaBean> lstSexo = new ArrayList<TablaBean>();
	List<TablaBean> lstEstadoCivil = new ArrayList<TablaBean>();
	List<TablaBean> lstOcupacion = new ArrayList<TablaBean>();
	List<TablaBean> lstNacionalidad = new ArrayList<TablaBean>();
	List<TablaBean> lstNivelInstrucion = new ArrayList<TablaBean>();
	List<UbigeoBean> lstUbigeoBean = new ArrayList<UbigeoBean>();
	List<TablaBean> lstTipoExamen = new ArrayList<TablaBean>();
	
	@Autowired
	private MaestraAsis01Service maestraAsis01Service;
	
	@Autowired
	private PacienteReniecService pacienteReniecService; 
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private UbigeoService ubigeoService;
	
	
	@Autowired
	private Maestra1Service maestraGene01Services;
	
	

	private void cargarCombos(ModelAndView mav) {
		try {
		//	lstSituacion =maestraBanc01Service.listarPorCodigoTabla("000034", 1);
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
	
	@RequestMapping(value = "/validapersona", method = RequestMethod.POST)
	public @ResponseBody PersonaBean validaPersona(@RequestParam("tipodocumento") Integer tipoDocumento, @RequestParam("numerodocumento") String numeroDocumento) {

		List<UbigeoBean> lstUbigeoBean;
		UbigeoBean ubigeoBean;
 		personaBean = new PersonaBean();
	//	personaBean.getTipoDocumento().setCodigoRegistro(tipoDocumento);
	//	personaBean.setNumeroDocumento(numeroDocumento);
	
		try {
			personaBean = this.getPersonaService().buscarxTipoDocumentoNumeroDocumento(personaBean);
			
			if(personaBean!=null){
				ubigeoBean=new UbigeoBean();
				//ubigeoBean.setCodigoUbigeo(personaBean.getCodigoUbigeo());
				lstUbigeoBean = (List<UbigeoBean>)ubigeoService.getBuscarPorFiltros(ubigeoBean);
				if(lstUbigeoBean!=null){
					ubigeoBean=lstUbigeoBean.get(0);
				//	personaBean.getUbigeoBean().setCodigoUbigeo(ubigeoBean.getCodigoUbigeo());
				//	personaBean.getUbigeoBean().setNombreUbigeo(ubigeoBean.getNombreUbigeo());
				}
			}
		} catch (ServiceException e) { 
			e.printStackTrace();
		}
		
		if(personaBean==null){
			personaBean=new  PersonaBean();
		}
		
		return personaBean;
	}

	@RequestMapping(value = "/buscarPersonaReniec", method = RequestMethod.GET)
	public ModelAndView doBuscarListado(@RequestParam("dni") String dni )
			throws Exception {
		try {
			
			PersonaServiceImp persona = new PersonaServiceImp();
			
			gob.hnch.systems.ws.ext.model.Persona perso =persona.getPersona(dni);
		} catch (Exception e) {
			// TODO: handle exception
		}
		ModelAndView mav = new ModelAndView("general/institucion/listado-institucion", "command",personaBean);
		
	/*	System.out.println("lstInstitucionBean"+lstInstitucionBean);
		mav.addObject("lstInstitucionBean", lstInstitucionBean);*/
	//	mav.addObject("personaRefBean", personaRefBean);
	//	this.listarCombos(mav);
		return mav;
		
	//	return this.getLista(institucionBean);
		
		
	}
	
	@RequestMapping(value = "/buscarPacienteReniec2", method = RequestMethod.GET)
	public @ResponseBody List<gob.hnch.systems.ws.ext.model.Persona> doBuscarProvincia(@RequestParam("nroDocumento") String nroDocumento)throws Exception {
		System.out.println("dni.. :"+nroDocumento);
		int codError=0;
		List<UbigeoBean> lstProvincia = new ArrayList<UbigeoBean>();
		List<gob.hnch.systems.ws.ext.model.Persona> lstPersona = new ArrayList<gob.hnch.systems.ws.ext.model.Persona>();
		PersonaServiceImp persona = new PersonaServiceImp();
		
		gob.hnch.systems.ws.ext.model.Persona perso =persona.getPersona(nroDocumento);
		lstPersona.add(perso);
		
		if (perso.getCodigoDeError().trim().equals("DNE")){
			System.out.println("perso.getCodigoDeError()"+perso.getCodigoDeError());
			codError = 1;
			//ubigeobean.setCodigoRegion(codigo);
			
	//		lstProvincia = ubigeoService.listarProvincia(ubigeobean);
			
	
			
		}
		return lstPersona;
	}
	
	
	
	@RequestMapping(value = "/consultarPersonaPorDocumento", method = RequestMethod.GET)
	public @ResponseBody PersonaBean consultarPorNroDocumento(@RequestParam("tipoDocumento") String tipoDocumento,@RequestParam("numero") String numero)throws Exception {
		  
		personaBean = new PersonaBean();
		PersonaBean prmPersona = new PersonaBean();
		prmPersona.setNroDocumento(numero);
		prmPersona.getTipoDocumento().setCodReg(tipoDocumento);
		try {
		/*	personaBean = this.getPersonaService().buscarxTipoDocumentoNumeroDocumento(prmPersona); 
			if(personaBean!=null){ 
			
			}else{*/
				personaBean = this.getPersonaService().buscarxTipoDocumentoNumeroDocumentoSigeho(prmPersona);
				if(personaBean!=null){  
					System.out.println("persona existe en sigehov2gene");
					System.out.println(personaBean.getNombreCompleto());
					 
					
					if (tipoDocumento.equals("000002")) {
						PacienteReniec pacienteReniec = new PacienteReniec();
						pacienteReniec.setNroDni(numero);
						//System.out.println("personaaaa"+perso.toString());
						List<PacienteReniec> lstPersona =  null; 	
						try {
							lstPersona = pacienteReniecService.pacienteReniecVerificacion(pacienteReniec) ;				
						} catch (Exception e) {
						}
						//lstPersona.add(perso); 
						if (lstPersona!= null ) {
							String bphoto = Base64.encodeBase64String(lstPersona.get(0).getFotoPaciente());
							System.out.println("bphoto " + bphoto);
							personaBean.setFoto(lstPersona.get(0).getFotoPaciente());
							personaBean.setFotoCadena(bphoto);
							personaBean.setSwReniec(false);
						}
					}
				
					
				}else{  
					PacienteReniec pacienteReniec = new PacienteReniec();
					pacienteReniec.setNroDni(numero); 
					List<PacienteReniec> lstPersona = null ; //new ArrayList<PacienteReniec>();
					try {
						lstPersona = pacienteReniecService.pacienteReniecVerificacion(pacienteReniec) ;		
					} catch (Exception e) {
						System.out.println("entro al catch");
						lstPersona =  null;
					}
								
					//lstPersona.add(perso); 
					if (lstPersona!= null ) {
						if (lstPersona.size() > 0) {
							if (lstPersona.get(0).getCodigoRespuesta().equals("0000")) {
								System.out.println("lstPersona size " + lstPersona.size());
								personaBean = new PersonaBean();
								System.out.println("personaBean RENIEC no es null " + lstPersona.get(0).getCodigoRespuesta());
								personaBean.setApellidoMaterno(lstPersona.get(0).getApellidoMaterno());
								personaBean.setApellidoPaterno(lstPersona.get(0).getApellidoPaterno());
								personaBean.getNacionalidad().setCodReg("000114");
								SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
								String strFecha = lstPersona.get(0).getFechaNacimiento();
								int ind = lstPersona.get(0).getNombres().indexOf(" ");
								System.out.println("ind " + ind);
								String primerNombre = "";
								if (ind > 0) {
									primerNombre = lstPersona.get(0).getNombres().substring(0, ind);
									String segundoNombre = lstPersona.get(0).getNombres().substring(ind + 1, lstPersona.get(0).getNombres().length());
									
									personaBean.setPrimerNombre(primerNombre);
									personaBean.setSegundoNombre(segundoNombre);
								}else{
									personaBean.setPrimerNombre(lstPersona.get(0).getNombres());
									personaBean.setSegundoNombre("");
								}
								
								if(lstPersona.get(0).getFotoPaciente() != null){
									personaBean.setFoto(lstPersona.get(0).getFotoPaciente());
								}
								/**
								personaBean.setUbiDepartamento(lstPersona.get(0).getUbiDepart());
								personaBean.setUbiProvincia(lstPersona.get(0).getUbiProv());
								personaBean.setUbiDistrito(lstPersona.get(0).getUbiDist());
								*/
								UbigeoBean oUbigeoBean = new UbigeoBean();
								oUbigeoBean.setCodigoUbigeo(lstPersona.get(0).getUbiDepart()+
															lstPersona.get(0).getUbiProv() + 
															lstPersona.get(0).getUbiDist());
								oUbigeoBean = ubigeoService.buscarEquivalenteReniecMinsa(oUbigeoBean);
								personaBean.setUbigeoDireccion(oUbigeoBean);
								
								UbigeoBean oUbigeoNacimiemtoBean = new UbigeoBean();
								oUbigeoNacimiemtoBean.setCodigoUbigeo(lstPersona.get(0).getUbiDepNac()+
															lstPersona.get(0).getUbiProvNac() + 
															lstPersona.get(0).getUbiDistNac());
								oUbigeoNacimiemtoBean = ubigeoService.buscarEquivalenteReniecMinsa(oUbigeoNacimiemtoBean);
								personaBean.setUbigeoNacimiento(oUbigeoNacimiemtoBean);
								
								Date fecha = null;
								try {

								fecha = formatoDelTexto.parse(strFecha);

								} catch (ParseException ex) {

								ex.printStackTrace();

								}
								personaBean.setDireccion(lstPersona.get(0).getDireccion());
								personaBean.setFechaNac(fecha);
								System.out.println("personaBean no es null " + personaBean.getNombreCompleto());
								System.out.println("personaBean no es null " + personaBean.getFechaNac());
								if (lstPersona.get(0).getSexo().equals("1")) {
									personaBean.getSexo().setCodReg("000002"); 
								}else{
									personaBean.getSexo().setCodReg("000001"); 
								}
								
								if (lstPersona.get(0).getEstCivil().equals("1")) {
									personaBean.getEstadoCivil().setCodReg("000002"); 
								}else if (lstPersona.get(0).getEstCivil().equals("2")){
									personaBean.getEstadoCivil().setCodReg("000002"); 
								} else if (lstPersona.get(0).getEstCivil().equals("3")) {
									personaBean.getEstadoCivil().setCodReg("000003"); 
								}else{
									personaBean.getEstadoCivil().setCodReg("000006"); 
								}
								
								System.out.println("tipoDocumento " + tipoDocumento);
							}else{
								System.out.println("no existe persona reniec");
								personaBean  = null;	
							}
							 
						}else{
							System.out.println("no existe persona reniec");
							personaBean  = null;
						} 
					}else{
						System.out.println("no existe persona ó no hay servicio de reniec");
					//	personaBean  = null;
						personaBean = new PersonaBean();
					}
					
				 
				} 
		} catch (Exception e) { 
			
		} 
		
	
		return personaBean;
	}
	
	
	
	
	/*****************************************************************/
	/************************** POSTULANTES **************************/
	
	@RequestMapping(value = "/consultarPersonaPorDocumentoLaboratorio", method = RequestMethod.GET)
	public @ResponseBody PersonaBean consultarPorNroDocumentoLaboratorio
	(@RequestParam("tipoDocumento") String tipoDocumento,
	 @RequestParam("numero") String numero)throws Exception {
		this.setPersonaBean(new PersonaBean());  
		personaBean = new PersonaBean();
		PersonaBean prmPersona = new PersonaBean();
		prmPersona.setNroDocumento(numero);
		prmPersona.getTipoDocumento().setCodReg(tipoDocumento);
		try {
		/*	personaBean = this.getPersonaService().buscarxTipoDocumentoNumeroDocumento(prmPersona); 
			if(personaBean!=null){ 
			
			}else{*/
			//	personaBean = personaService.buscarxTipoDocumentoNumeroDocumentoSigeho(prmPersona);
				personaBean = personaService.buscarxTipoDocumentoNumeroDocumento(prmPersona);
				if(personaBean!=null){  
					System.out.println("persona existe en sigehov2gene");
					System.out.println(personaBean.getNombreCompleto());
				//	personaBean.setOrigenDatos("LABMED");
					
					System.out.println("personaBean.getCodigo() " + personaBean.getCodigo());
					PostulanteBean postulante = new PostulanteBean();
					postulante.setPersona(personaBean);
					setPersonaBean(personaBean);
					/*
					try {
						postulante = postulanteService.buscarUltimaDonacion(postulante);
						if (postulante != null) {
							System.out.println("postulante.getUltimaFechaDonacion() "  + postulante.getUltimaFechaDonacion());
							personaBean.setUltimaFechaDonacion(postulante.getUltimaFechaDonacion());
						}
					} catch (Exception e) {
						 
					}
				*/
					
					if (tipoDocumento.equals("000002")) {
						PacienteReniec pacienteReniec = new PacienteReniec();
						pacienteReniec.setNroDni(numero);
						//System.out.println("personaaaa"+perso.toString());
						List<PacienteReniec> lstPersona =  null; 	
						try {
							lstPersona = pacienteReniecService.pacienteReniecVerificacion(pacienteReniec) ;				
						} catch (Exception e) {
						}
						//lstPersona.add(perso); 
						if (lstPersona!= null ) {
							String bphoto = Base64.encodeBase64String(lstPersona.get(0).getFotoPaciente()); 
							personaBean.setFoto(lstPersona.get(0).getFotoPaciente());
							personaBean.setFotoCadena(bphoto);
							personaBean.setSwReniec(false);
						}
					}
				
					
				}else{  
					personaBean = new PersonaBean();
					personaBean.getTipoDocumento().setCodReg("000002");
					personaBean.setNroDocumento(numero);
					personaBean.setOrigenDatos("");
					
					
					
					
					
					
					
					
					
					
					
					
					
					PacienteReniec pacienteReniec = new PacienteReniec();
					pacienteReniec.setNroDni(numero); 
					List<PacienteReniec> lstPersona = null ; //new ArrayList<PacienteReniec>();
					if (tipoDocumento.equals("000002")) {
						try {
							lstPersona = pacienteReniecService.pacienteReniecVerificacion(pacienteReniec) ;		
							System.out.println("lstPersona.get(0).getCodigoRespuesta() " + lstPersona.get(0).getCodigoRespuesta());
						} catch (Exception e) {
							System.out.println("entro al catch");
							lstPersona =  null;
						}	
					} 		
					//lstPersona.add(perso); 
					if (lstPersona!= null ) {
						if (lstPersona.size() > 0) {
							if (lstPersona.get(0).getCodigoRespuesta().equals("0000")) {
								System.out.println("lstPersona size " + lstPersona.size());
								personaBean = new PersonaBean();
								personaBean.getTipoDocumento().setCodReg("000002");
								personaBean.setNroDocumento(numero);
								personaBean.setOrigenDatos("RENIEC");
								personaBean.setSwReniec(true);
								personaBean.setCodigoErrorReniec(lstPersona.get(0).getCodigoRespuesta());
								System.out.println("personaBean RENIEC no es null " + lstPersona.get(0).getCodigoRespuesta());
								personaBean.setApellidoMaterno(lstPersona.get(0).getApellidoMaterno());
								personaBean.setApellidoPaterno(lstPersona.get(0).getApellidoPaterno());
								personaBean.getNacionalidad().setCodReg("000114");
								SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
								String strFecha = lstPersona.get(0).getFechaNacimiento();
								int ind = lstPersona.get(0).getNombres().indexOf(" ");
								System.out.println("ind " + ind);
								String primerNombre = "";
								if (ind > 0) {
									primerNombre = lstPersona.get(0).getNombres().substring(0, ind);
									String segundoNombre = lstPersona.get(0).getNombres().substring(ind + 1, lstPersona.get(0).getNombres().length());
									
									personaBean.setPrimerNombre(primerNombre);
									personaBean.setSegundoNombre(segundoNombre);
								}else{
									personaBean.setPrimerNombre(lstPersona.get(0).getNombres());
									personaBean.setSegundoNombre("");
								}
								
								if(lstPersona.get(0).getFotoPaciente() != null){
									personaBean.setFoto(lstPersona.get(0).getFotoPaciente());
								}
								/**
								personaBean.setUbiDepartamento(lstPersona.get(0).getUbiDepart());
								personaBean.setUbiProvincia(lstPersona.get(0).getUbiProv());
								personaBean.setUbiDistrito(lstPersona.get(0).getUbiDist());
								*/
								UbigeoBean oUbigeoBean = new UbigeoBean();
								oUbigeoBean.setCodigoUbigeo(lstPersona.get(0).getUbiDepart()+
															lstPersona.get(0).getUbiProv() + 
															lstPersona.get(0).getUbiDist());
								oUbigeoBean = ubigeoService.buscarEquivalenteReniecMinsa(oUbigeoBean);
								personaBean.setUbigeoDireccion(oUbigeoBean);
								
								UbigeoBean oUbigeoNacimiemtoBean = new UbigeoBean();
								oUbigeoNacimiemtoBean.setCodigoUbigeo(lstPersona.get(0).getUbiDepNac()+
															lstPersona.get(0).getUbiProvNac() + 
															lstPersona.get(0).getUbiDistNac());
								oUbigeoNacimiemtoBean = ubigeoService.buscarEquivalenteReniecMinsa(oUbigeoNacimiemtoBean);
								personaBean.setUbigeoNacimiento(oUbigeoNacimiemtoBean);
								
								Date fecha = null;
								try {

								fecha = formatoDelTexto.parse(strFecha);

								} catch (ParseException ex) {

								ex.printStackTrace();

								}
								personaBean.setDireccion(lstPersona.get(0).getDireccion());
								personaBean.setFechaNac(fecha);
								personaBean.setFechaNacStr(strFecha);
								System.out.println("personaBean no es null " + personaBean.getNombreCompleto());
								System.out.println("personaBean no es null " + personaBean.getFechaNac());
								if (lstPersona.get(0).getSexo().equals("1")) {
									personaBean.getSexo().setCodReg("000002"); 
								}else{
									personaBean.getSexo().setCodReg("000001"); 
								}
								
								if (lstPersona.get(0).getEstCivil().equals("1")) {
									personaBean.getEstadoCivil().setCodReg("000001"); 
								}else if (lstPersona.get(0).getEstCivil().equals("2")){
									personaBean.getEstadoCivil().setCodReg("000002"); 
								} else if (lstPersona.get(0).getEstCivil().equals("3")) {
									personaBean.getEstadoCivil().setCodReg("000003"); 
								}else{
									personaBean.getEstadoCivil().setCodReg("000006"); 
								}
								
								System.out.println("tipoDocumento " + tipoDocumento);
							}else{
								System.out.println("no existe persona reniec");
								personaBean  = null;	
							}
							 
						}else{
							System.out.println("no existe persona reniec");
							personaBean  = null;
						} 
					}else{
						System.out.println("no existe persona ó no hay servicio de reniec");
						personaBean  = null;
					//	personaBean  = new PersonaBean();
					} 
				} 
		} catch (Exception e) {  
		} 
		
	//    this.getPostulanteBean().setPersona(personaBean);
	    this.setPersonaBean(personaBean);
		return personaBean;
	}
	
	
	@RequestMapping(value = "/grabarPersonaLaboratorio", method = RequestMethod.POST)
	public @ResponseBody PersonaBean grabarPersonaLaboratorio(@ModelAttribute("personaBean")PersonaBean obpersonaBean,
											 HttpServletRequest request) throws Exception {  
	
		if (personaBean==null) {
			personaBean = new PersonaBean();
		}
		
	//	this.getPersonaBean().setOrigenDeRegistro("Sistema Labmed Web");
	// 	personaBean = new PersonaBean();
	//	this.getPostulanteBean().getTipoPostulate().setCodReg("000001");
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
		//	this.personaService.insertarPersonaBanco(this.getPostulanteBean().getPersona());
			
		}else{ 
			System.out.println("existe persona");
			 
				this.setAuditoria(postulanteBean.getPersona(), request, true); 
				System.out.println("postulanteBean.getCodigoCorreo " + obpersonaBean.getCodigoCorreo());
				System.out.println("postulanteBean.getCodigoDireccion " + obpersonaBean.getCodigoDireccion());
				System.out.println("postulanteBean.getCodigoTelefono " + obpersonaBean.getCodigoTelefono());
				this.personaService.actualizarPersonaLaboratorio(obpersonaBean);
			//	this.getPostulanteBean().setPersona(postulanteBean.getPersona());
		 
			
		} 
		System.out.println("postulanteBean.getCodigo()::" + postulanteBean.getCodigo().trim());
		
		if (!personaBean.getCodigo().trim().equals("")) {
			this.setAuditoria(this.getPersonaBean(), request, false); 
			System.out.println("actualiza postulanteBean " + obpersonaBean.getCodigo());
	
		} else {
			
			/*
			EstadoFisicoBean oeEstadoFisicoBean = new EstadoFisicoBean();
			oeEstadoFisicoBean.setPersonaBean(this.getPersonaBean());
			this.setAuditoria(oeEstadoFisicoBean, request, true); */
			this.setAuditoria(this.getPersonaBean(), request, true); 
			
		//	this.setAuditoria(odDonanteBean, request, true); 
		//	System.out.println("insert postulanteBean " + postulanteBean.getCodigo());
			
		//	odDonanteBean.getTipoRegistro().setCodReg("000003");
		//	odDonanteBean.setPersona(this.getPostulanteBean().getPersona());

		
			
		}  
	//	nuevoPostulante(request,obpersonaBean);
	//	return this.getPostulanteBean();
		return this.getPersonaBean();
	}
	
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public ModelAndView nuevoPostulante(HttpServletRequest request, PersonaBean personaBean) {
		OrdenBean objOrdenBean = new OrdenBean();  
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/orden/registro-orden", "command", personaBean);  
		this.cargarCombos(mav); 
		return mav;
	} 
	
	
	
	
	
	/*
	private ModelAndView getLista(InstitucionBean institucionBean) {
		
		List<InstitucionBean> lstInstitucionBean = new ArrayList<InstitucionBean>();
		
		try {
			PersonaServiceImp persona = new PersonaServiceImp();
			String nroDoc;
			gob.hnch.systems.ws.ext.model.Persona perso =persona.getPersona(nroDoc);
			
			
			lstInstitucionBean = (List<InstitucionBean>) this.getInstitucionService().getBuscarPorFiltros(institucionBean);
			
			
 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getLista " + e.getMessage());
		}

		ModelAndView mav = new ModelAndView("general/institucion/listado-institucion", "command",institucionBean);
	
		System.out.println("lstInstitucionBean"+lstInstitucionBean);
		mav.addObject("lstInstitucionBean", lstInstitucionBean);
		mav.addObject("institucionBean", institucionBean);
	//	this.listarCombos(mav);
		return mav;
	}
	
	*/
	
	/***NUEVA LINEA 19-04-2019****/
	@RequestMapping(value = "/nuevoPaciente", method = RequestMethod.GET)
	public ModelAndView nuevoPaciente(HttpServletRequest request) {
		PersonaBean personaBean = new PersonaBean(); 
	
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/registro-paciente-laboratorio", "command", personaBean); 
		
		this.cargarCombos(mav);
		return mav;
	}
	
	protected void setAuditoria(BaseBean baseBean,HttpServletRequest request,boolean swInsert){
		UsuarioBean usuario= (UsuarioBean) request.getSession().getAttribute("usuarioSesion");
		String idUsuario= !VO.isNull(usuario) ? usuario.getCodigo() :"0";	
		nombreUser = !VO.isNull(usuario) ? usuario.getNombreUsuario() :"0";	
		this.setAuditoriaLocal(baseBean, idUsuario, request, swInsert);		
	}
	private void setAuditoriaLocal(BaseBean baseBean,String iddUsuario,HttpServletRequest request,boolean swInsert){
		if (swInsert) {
			baseBean.setCodigoUsuarioCreacion(iddUsuario);
			baseBean.setNombreUsuarioCreacion(nombreUser);
			baseBean.setIpCreacion(NetUtil.getClientIpAddr(request));			
			
		} else {
			baseBean.setCodigoUsuarioModificacion(iddUsuario);
			baseBean.setNombreUsuarioCreacion(nombreUser);
			baseBean.setIpModificacion(NetUtil.getClientIpAddr(request));			
		}

	}
	

	public PersonaService getPersonaService() {
		return personaService;
	}


	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}


	public PersonaBean getPersonaBean() {
		return personaBean;
	}


	public void setPersonaBean(PersonaBean personaBean) {
		this.personaBean = personaBean;
	}

	public PostulanteBean getPostulanteBean() {
		return postulanteBean;
	}

	public void setPostulanteBean(PostulanteBean PostulanteBean) {
		this.postulanteBean = PostulanteBean;
	}
	
}
