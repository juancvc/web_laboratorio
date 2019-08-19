package sigelab.core.service.interfaces.asistencial.laboratorio;
 
 
import java.util.List;

import sigelab.core.bean.asistencial.laboratorio.OrdenBean;
import sigelab.core.service.interfaces.BaseService;
import sigelab.core.service.exception.ServiceException;
public interface OrdenService extends BaseService<OrdenBean>{
	
	public OrdenBean reporteCantidadAnualOrdenSituacion(OrdenBean ordenBean)throws ServiceException;
	public OrdenBean reporteCantidadMensualOrdenSituacion(OrdenBean ordenBean)throws ServiceException;
	public OrdenBean reporteCantidadDiarioOrdenSituacion(OrdenBean ordenBean)throws ServiceException;
	public OrdenBean reporteCantidadSemanalOrdenSituacion(OrdenBean ordenBean)throws ServiceException;
	
	public OrdenBean reporteVentaAnual(OrdenBean ordenBean)throws ServiceException;
	public OrdenBean reporteVentaMensual(OrdenBean ordenBean)throws ServiceException;
	public OrdenBean reporteVentaDiaria(OrdenBean ordenBean)throws ServiceException;
	public OrdenBean reporteVentaSemanal(OrdenBean ordenBean)throws ServiceException;
	
	public List<OrdenBean> reporteDetalleOrdenVentaAnual(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteDetalleOrdenVentaMensual(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteDetalleOrdenVentaDiaria(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteDetalleOrdenVentaSemanal(OrdenBean ordenBean) throws ServiceException;
	
	public List<OrdenBean> reporteVentaMensualPeriodoArea(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteVentaMensualPeriodoBarra(OrdenBean ordenBean) throws ServiceException;
	
	public List<OrdenBean> reporteDetalleAnualOrdenSituacion(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteDetalleMensualOrdenSituacion(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteDetalleDiariaOrdenSituacion(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteDetalleSemanalOrdenSituacion(OrdenBean ordenBean) throws ServiceException;
	
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitados(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitados(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitados(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitados(OrdenBean ordenBean) throws ServiceException;
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException;
 
	
}
