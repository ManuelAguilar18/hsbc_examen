package com.hsbc.service;

import org.json.JSONObject;

public interface ReservaService {

	/**
	 * 
	 * @param reservaReq
	 * @return
	 */
	JSONObject addReserva(String reservaReq);

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	JSONObject buscarReserva(String fecha);

}
