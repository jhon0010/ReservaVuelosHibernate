package co.com.reserva.vuelos.service;

import java.util.Date;

import co.com.reserva.vuelos.entities.Avion;

/**
 * Servicio que se encarga de exponer los metodos de negocio de reserva vuelos
 * @author jhon
 *
 */
public interface ReservaVuelosService {

	public int numVuelosEnIgualHorario(Avion avionAsignado, Date fechaSalida);
}
