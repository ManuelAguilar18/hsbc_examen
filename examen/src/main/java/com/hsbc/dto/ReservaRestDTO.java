package com.hsbc.dto;

import java.io.Serializable;

public class ReservaRestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429124127055652113L;
	
	private String nombreCliente;
	
	private int numPersonas;
	
	private String fecha;
	
	private String hora;

	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}
