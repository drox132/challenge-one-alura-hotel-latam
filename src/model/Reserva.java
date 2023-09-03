package model;

import java.util.Date;

public class Reserva {

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Float valor;
	private String formaPago;
	
	
	
	
	public Reserva() {
		this.setValor(20f);
	}


	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, Float valor, String formaPago) {
		this.setId(id); 
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida) ;
		this.setValor(valor);
		this.setFormaPago(formaPago); 
	}
	
	
	public Reserva(Date fechaEntrada, Date fechaSalida, float valor, String formaPago) {
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida) ;
		this.setValor(valor);
		this.setFormaPago(formaPago); 
		}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}


	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaPago=" + formaPago + "]";
	}
	
	

}
