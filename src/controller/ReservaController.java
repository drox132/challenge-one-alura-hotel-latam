package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import conectionFactory.ConectionFactory;
import dao.ReservaDao;
import model.Reserva;

public class ReservaController {

	private final ReservaDao reservaDao ;
	
	
	  public ReservaController() { 
		  ConectionFactory conectionFactory = new  ConectionFactory();
		  this.reservaDao = new ReservaDao(conectionFactory.establecerConexion());
	  
	  }
	 
	
	public Integer save(Reserva reserva) {
		return reservaDao.save(reserva);
	}
	
	public List<Reserva> listar(){
		return reservaDao.listar();
	}


	public void modificar(Reserva reserva) {
		reservaDao.modificar(reserva);
	}


	public String eliminar(Integer idReservaEliminar) {
		return reservaDao.eliminar(idReservaEliminar);
		
	}
	

}
