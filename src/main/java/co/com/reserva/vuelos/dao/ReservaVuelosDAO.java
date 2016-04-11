package co.com.reserva.vuelos.dao;

import java.util.Date;

import co.com.reserva.vuelos.entities.Avion;

/**
 * Interface con la cual se tendran consultas
 * relacionadas al negocio
 * @author jhon
 *
 */
public interface ReservaVuelosDAO {

	public int numVuelosEnIgualHorario(Avion avionAsignado, Date fechaSalida);
}
