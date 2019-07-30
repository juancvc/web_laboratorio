package sigelab.core.repository.interfaces.asistencial.laboratorio;
  
 

import java.util.List;

import sigelab.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;

import sigelab.core.repository.DAOException;
import sigelab.core.repository.interfaces.BaseDAO;

public interface OrdenDetalleItemDAO  extends BaseDAO<OrdenDetalleItemBean> {
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException;
}
