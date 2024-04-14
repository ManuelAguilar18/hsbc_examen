package com.hsbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.hsbc.connection.JdbcConnection;
import com.hsbc.dao.ReservaDAO;
import com.hsbc.dto.ReservaRespDTO;
import com.hsbc.dto.ReservaRestDTO;

public class ReservaDaoImpl implements ReservaDAO{

	/**
	 * 
	 */
	private JdbcConnection SQL = null;
	
	/**
	 * 
	 */
	private Connection conn = null;

	
	@Override
	public ReservaRespDTO addReserva(ReservaRestDTO req) {

		ReservaRespDTO resp = new ReservaRespDTO();
		SQL = new JdbcConnection();
		conn = SQL.conectarMySQL();

		ResultSet resultSet = null;

		String insert = "INSERT INTO reserva (nombre, numero_personas, fecha, hora, activo) VALUES ('"+req.getNombreCliente()+"', '"+req.getNumPersonas()+"', '"+req.getFecha()+"', '"+req.getHora()+"', '1');";

		try {
			PreparedStatement pstm = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			// Ejecutar la consulta de inserción
			int affectedRows = pstm.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("La inserción falló, no se agregó ningún registro");
			}

			// Obtener el ID generado para el nuevo registro
			resultSet = pstm.getGeneratedKeys();
			int nuevoID = -1;
			if (resultSet.next()) {
				nuevoID = resultSet.getInt(1);
				System.out.println("Nuevo ID generado: " + nuevoID);
			}

			// Preparar la consulta de selección
			String selectQuery = "SELECT id, nombre, numero_personas, fecha, hora, activo FROM reserva WHERE id = ?";
			PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
			selectStatement.setInt(1, nuevoID);

			// Ejecutar la consulta de selección
			resultSet = selectStatement.executeQuery();

			// Procesar los resultados de la consulta
			while (resultSet.next()) {
				resp.setId(resultSet.getInt("id"));
				resp.setNombreCliente(resultSet.getString("nombre"));
				resp.setFecha(resultSet.getString("fecha"));
				resp.setHora(resultSet.getString("hora"));
				resp.setActivo(resultSet.getInt("activo"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return resp;
	}


	@Override
	public ArrayList<ReservaRespDTO> buscarReserva(String fecha) {
		
		ArrayList<ReservaRespDTO> lstResp = new ArrayList<>();
		
		ReservaRespDTO resp = null;
		SQL = new JdbcConnection();
		conn = SQL.conectarMySQL();

		ResultSet resultSet = null;
		
		try {
			
			// Preparar la consulta de selección
			String selectQuery = "SELECT id, nombre, numero_personas, fecha, hora, activo FROM reserva WHERE fecha = ?";
			PreparedStatement selectStatement;
			
			selectStatement = conn.prepareStatement(selectQuery);
			
			selectStatement.setString(1, fecha);

			// Ejecutar la consulta de selección
			resultSet = selectStatement.executeQuery();

			// Procesar los resultados de la consulta
			while (resultSet.next()) {
				resp = new ReservaRespDTO();
				resp.setId(resultSet.getInt("id"));
				resp.setNombreCliente(resultSet.getString("nombre"));
				resp.setFecha(resultSet.getString("fecha"));
				resp.setHora(resultSet.getString("hora"));
				resp.setActivo(resultSet.getInt("activo"));
				
				lstResp.add(resp);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return lstResp;
	}

}
