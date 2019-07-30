package sigelab.core.service.interfaces.asistencial.laboratorio;
 
 
import java.util.List;

import sigelab.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import sigelab.core.service.exception.ServiceException;
import sigelab.core.service.interfaces.BaseService;

public interface OrdenDetalleItemService extends BaseService<OrdenDetalleItemBean>{
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean) throws ServiceException;
	
}
