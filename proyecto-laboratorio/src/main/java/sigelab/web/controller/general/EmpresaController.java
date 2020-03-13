package sigelab.web.controller.general;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(value = "empresaController")
public class EmpresaController extends BaseController{
	
	List<EmpresaBean> lstTablas = new ArrayList<EmpresaBean>();
	
	
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
					System.out.println("logo: "+ empresaBean.getLogo());
					System.out.println("logoCadena: "+ empresaBean.getLogoCadena());
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
			 					   MultipartFile[] imgLogo, HttpServletRequest request ){
		System.out.println("-----Ingreso a grabarTextoTexto ----");
 
	EmpresaBean empresaBean = new EmpresaBean();
	empresaBean.setNombreEmpresa(nombre);
	empresaBean.setNombreComercial(nombreComercial);
	empresaBean.setTelefonoCelular(telefono);
	empresaBean.setCorreo(correo);
	empresaBean.setDomicilioFiscal(direccion);
		boolean swImgMate = false;
		MultipartFile lfImageMa = null;
		if (imgLogo!=null) {
		if (imgLogo.length > 0) {
			 swImgMate = (imgLogo[0] != null) && (imgLogo[0].getOriginalFilename() != null) && (imgLogo[0].getOriginalFilename().trim().length() > 0);
			 System.out.println(swImgMate);
			 System.out.println(imgLogo[0].getOriginalFilename());
		}
	}
		if (imgLogo!=null) {
		if(imgLogo[0].getOriginalFilename().contains("img--Vacio---")){
			 swImgMate = false;
			 System.out.println("imgLogo[0].getOriginalFilename().split(\"---\")[1] " +
					 imgLogo[0].getOriginalFilename().split("---")[1]);
			// relacionBean.setImagen(imgLogo[0].getOriginalFilename().split("---")[1]);
			 
			 try {
				System.out.println("imgLogo[0] " + imgLogo[0].getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		if (swImgMate) {
			lfImageMa = imgLogo[0] ;
			if (imgLogo[0].getOriginalFilename() != null) { 
			}
			
		//	File dir = new File(this.getRootPath() + File.separator + relacionBean.getImagen());                
	    /*    if (dir.exists()){
	            //dir.mkdirs();
	        	System.out.println("img"+ dir.getName());
	        	String fileNameWithOutExt = FilenameUtils.removeExtension(relacionBean.getImagen());
	        	String fileNameExt = FilenameUtils.getExtension(relacionBean.getImagen());
	        	System.out.println("imgnefile" + randomIdentifier());
	        	relacionBean.setImagen(randomIdentifier()+"."+fileNameExt);
	        }   */
//			super.cargarArchivo(this.getRootPath(),renombrarImagen(imgLogo,relacionBean.getCodigo())/* relacionBean.getImagen()*/,lfImageMa);
		}
		
		 
		boolean sw = true;
		try {
				System.out.println("actualiza empresaBean " + empresaBean.getNroRUC());
				sw = (empresaService.actualizar(empresaBean)); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
		String liCodRela = "1";
	 

		return liCodRela;

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