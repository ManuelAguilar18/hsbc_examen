package com.hsbc.dto;

import java.io.Serializable;

public class ReservaRespDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5095844692758326213L;

	private int id;
	
	private String nombreCliente;
	
	private String fecha;
	
	private String hora;
	
	private int activo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

}
