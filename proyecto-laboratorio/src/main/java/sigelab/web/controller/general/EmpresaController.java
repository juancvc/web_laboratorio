package sigelab.web.controller.general;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;   
import sigelab.core.bean.general.EmpresaBean;
import sigelab.core.bean.seguridad.PerfilBean;
import sigelab.core.bean.seguridad.UsuarioBean;
import sigelab.core.bean.seguridad.UsuarioRenaesBean;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.asistencial.maestra.MaestraAsis14Service;
import sigelab.core.service.interfaces.general.EmpresaService;
import sigelab.web.controller.base.BaseController;
import sigelab.web.utilitarios.ResourceUtil; 

@Controller
@Scope(value = "session")
@RequestMapping(value = "empresaController")
public class EmpresaController extends BaseController{
	
	List<EmpresaBean> lstTablas = new ArrayList<EmpresaBean>();
	final java.util.Random rand = new java.util.Random();
	final Set<String> identifiers = new HashSet<String>();
	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	private String				tmpUrlImagen;
	@Autowired
	private EmpresaService empresaService;
	
	
  
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public ModelAndView doListado(@ModelAttribute("empresaBean") EmpresaBean empresaBean, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("configuracion/empresa/registro-empresa", "command", empresaBean); 
		return mav;
	}

	
	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView listado(@ModelAttribute("empresaBean") EmpresaBean empresaBean, HttpServletRequest request){
	 
		ModelAndView mav = new ModelAndView("configuracion/empresa/registro-empresa", "command", empresaBean); 
		return mav;
	}
  
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView modificar(){   
		
		EmpresaBean oEmpresaBean = new EmpresaBean();  
		EmpresaBean empresaBean = new EmpresaBean();  
	
			try { 
				empresaBean = empresaService.getBuscarPorObjecto(oEmpresaBean);   
				if (empresaBean.getLogo()!= null ) {
					String bphoto = Base64.encodeBase64String(empresaBean.getLogo());  
					empresaBean.setLogoCadena(bphoto);  
					tmpUrlImagen = empresaBean.getNombreLogo();
				}
				
			} catch (ServiceException e) {
				
				e.printStackTrace();
			}
			ModelAndView mav = new ModelAndView("configuracion/empresa/registro-empresa", "command",empresaBean);  
			mav.addObject("empresaBean", empresaBean);
			return mav;
	}
	
	@RequestMapping(value = "/grabar", method = RequestMethod.POST)
	//@ResponseBody
	public ModelAndView grabar(@ModelAttribute("empresaBean") EmpresaBean empresaBean, HttpServletRequest request) {
		 
		System.out.println("doGrabar @ModelAttribute "+empresaBean.getLogo());
		System.out.println("doGrabar @ModelAttribute FILE "+ empresaBean.getLogoFile());
		boolean sw = true;
		try {
				System.out.println("actualiza empresaBean " + empresaBean.getNroRUC());
				sw = (empresaService.actualizar(empresaBean)); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
		System.out.println("sw " + sw);
		if (sw) {
			empresaBean = new EmpresaBean() ;
			 return this.listado(empresaBean, request);
			 
		}else{
			ModelAndView mav = new ModelAndView("configuracion/empresa/registro-empresa", "command",empresaBean); 
			return mav ;
		} 
		 
	} 
	
	@RequestMapping(value = "/grabarEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public String doGrabarEmpresa(@RequestParam("nombre") String nombre,
								  @RequestParam("nombreComercial") String nombreComercial,
			 					   @RequestParam("telefono") String telefono,
			 					   @RequestParam("direccion") String direccion,
			 					   @RequestParam("correo") String correo, 
			 					  @RequestParam("imgLogo") MultipartFile[] imgLogo, HttpServletRequest request ){
		System.out.println("-----Ingreso a grabarTextoTexto ----");
 
	EmpresaBean empresaBean = new EmpresaBean();
	empresaBean.setNombreEmpresa(nombre);
	empresaBean.setNombreComercial(nombreComercial);
	empresaBean.setTelefonoCelular(telefono);
	empresaBean.setCorreo(correo);
	empresaBean.setDomicilioFiscal(direccion);
		boolean swImgMate = false;
		boolean swNuevaUrlImagen = false; 
		MultipartFile lfImageMa = null;
		
		if (imgLogo.length>0) {
			swNuevaUrlImagen = (imgLogo[0]!=null) && (imgLogo[0].getOriginalFilename()!=null ) && (imgLogo[0].getOriginalFilename().trim().length()>0 );		
		}
		
		if (swNuevaUrlImagen) {
			empresaBean.setLogoFile(imgLogo[0]);
	    	if (imgLogo[0].getOriginalFilename()!=null) {
	    		empresaBean.setNombreLogo(imgLogo[0].getOriginalFilename());
	    	}	    					
		}else {				
			empresaBean.setNombreLogo(tmpUrlImagen);
		} 
		
		 
		 
		boolean sw = true;
		try {
				System.out.println("actualiza empresaBean " + empresaBean.getNroRUC());
				sw = (empresaService.actualizar(empresaBean)); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
		String liCodRela = "1";
	 
		if (sw) {
			if (swNuevaUrlImagen) {
				super.cargarArchivo(this.getRootPath(), empresaBean.getNombreLogo(), empresaBean.getLogoFile());    			    	
				tmpUrlImagen = empresaBean.getNombreLogo();
			} 
		}
		return liCodRela;

	}
 
	public String getRootPath() {
		return ResourceUtil.getKey("ruta.teamsoftti.archivos.empresaLogo");
	}
	
	public String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}
/**	@RequestMapping(value = "/refrescarLista", method = RequestMethod.GET)
	public @ResponseBody List<EmpresaBean> refrescarLista(@RequestParam("tabla") String tabla)throws Exception { 
		 EmpresaBean empresaBean = new EmpresaBean();
		 empresaBean.setTabla(tabla);
		List<EmpresaBean> lstEmpresaBean =new ArrayList<EmpresaBean>(); 
		try {
			lstEmpresaBean = maestraAsis14Service.getBuscarPorFiltros(empresaBean); 
		
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		 
			return lstEmpresaBean; 
	}*/
}