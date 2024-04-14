package com.hsbc.dao;

import java.util.ArrayList;

import com.hsbc.dto.ReservaRespDTO;
import com.hsbc.dto.ReservaRestDTO;

public interface ReservaDAO {

	/**
	 * 
	 * @param req
	 * @return
	 */
	ReservaRespDTO addReserva(ReservaRestDTO req);

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	ArrayList<ReservaRespDTO> buscarReserva(String fecha);

}
