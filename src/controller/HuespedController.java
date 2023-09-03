package controller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import conectionFactory.ConectionFactory;
import dao.HuespedDao;
import model.Huesped;

public class HuespedController {

	private HuespedDao huespedDao;
	
	public HuespedController() {
		Connection factory = new ConectionFactory().establecerConexion();
		this.huespedDao = new HuespedDao(factory);
	}
	
	public Integer save(Huesped huesped) {
		return huespedDao.save(huesped);
	}

	public List<Huesped> listar() {
		
		return huespedDao.listar();
	}

	public String eliminar(Integer idHuespedEliminar) {
		return huespedDao.eliminar(idHuespedEliminar);
	}

	public void modificar(Huesped huesped) {
		huespedDao.modificar(huesped);
		
	}

	
	
}
