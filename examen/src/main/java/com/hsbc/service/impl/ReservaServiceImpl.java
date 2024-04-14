package com.hsbc.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.dao.ReservaDAO;
import com.hsbc.dao.impl.ReservaDaoImpl;
import com.hsbc.dto.ReservaRespDTO;
import com.hsbc.dto.ReservaRestDTO;
import com.hsbc.service.ReservaService;

/**
 * 
 */
public class ReservaServiceImpl implements ReservaService {
	
	/**
	 * JsonNode
	 */
    JsonNode jsonNode;
    
    /**
     * 
     */
    ObjectMapper objectMapper;
    
    /**
     * 
     */
    JSONObject json;
    
    /**
     * 
     */
    ReservaDAO reservaDAO;

	@Override
	public JSONObject addReserva(String reservaReq) {

		objectMapper = new ObjectMapper();
		json = new JSONObject();
		reservaDAO = new ReservaDaoImpl();

		ReservaRestDTO req = new ReservaRestDTO();
		ReservaRespDTO resp = new ReservaRespDTO();

		try {
			jsonNode = objectMapper.readTree(reservaReq);

			req.setNombreCliente(jsonNode.get("nombreCliente").toString().replace("\"", ""));
			req.setNumPersonas(jsonNode.get("numPersonas").asInt());
			req.setFecha(jsonNode.get("fecha").toString().replace("\"", ""));
			req.setHora(jsonNode.get("hora").toString().replace("\"", ""));

			if ("".equals(req.getNombreCliente()) || req.getNombreCliente() == null) {
				return json.put("Error", "Enviar el nombre");
			}

			if (!"".equals(req.getFecha()) || req.getFecha() != null) {
				LocalDate fechaActual = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String dateString = req.getFecha().replace("\"", "");
				LocalDate fechaProporcionada = LocalDate.parse(dateString);

				if (!fechaProporcionada.isAfter(fechaActual) || fechaProporcionada.isEqual(fechaActual)) {
					return json.put("Error", "La fecha proporcionada es anterior a la fecha actual");
				}

			}else {
				return json.put("Error", "Debes al menos colocar la fecha de la reserva");
			}

			if (req.getNumPersonas() == 0) {
				return json.put("Error", "El numero de personas debe ser mayor a 0");
			}
			resp = reservaDAO.addReserva(req);

			json.put("id", resp.getId());
			json.put("cliente", resp.getNombreCliente().replace("\"", ""));
			json.put("fecha", resp.getFecha().replace("\"", ""));
			json.put("hora", resp.getHora().replace("\"", ""));
			json.put("reservaActiva", resp.getActivo());

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			json.put("Error", "Validar bien la informacion");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			json.put("Error", "Validar bien la informacion");
		}

		return json;
	}

	@Override
	public JSONObject buscarReserva(String fecha) {
		objectMapper = new ObjectMapper();
		json = new JSONObject();
		reservaDAO = new ReservaDaoImpl();
		
		ArrayList<ReservaRespDTO> lstResp = reservaDAO.buscarReserva(fecha);
		
	     json.put("reservas", lstResp);
		
		
		return json;
	}

}
