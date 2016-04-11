package co.com.reserva.vuelos.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.reserva.vuelos.dao.ReservaVuelosDAO;
import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.service.ReservaVuelosService;

/**
 * Clase que implementa el contrato de ReservaVuelosService 
 * implementanto la logica necesaria para los servicios de negocio
 * @author jhon
 *
 */
@Service
public class ReservaVuelosServiceImpl implements ReservaVuelosService {


	private ReservaVuelosDAO reservaVuelosDAO;

	/**
	 * Para la inyeccion del ReservaVuelosDAO
	 * 
	 * @param ReservaVuelosDAO
	 */
	public void setReservaVuelosDAO(ReservaVuelosDAO reservaVuelosDAO) {
		this.reservaVuelosDAO = reservaVuelosDAO;
	}
	
	/**
	 * Metodo que permite concoer cuantos vuelos existe asiganos
	 * para el mismo avion con horario truncado
	 * 
	 * @param avionAsignado avion asignado al vuelo
	 * @param fechaSalida fecha en la que saldra el avion a realizar el vuelo
	 * 
	 * @return numero de vuelos encontrados
	 */
	@Override
	@Transactional
	public int numVuelosEnIgualHorario(Avion avionAsignado, Date fechaSalida) {
		
		return reservaVuelosDAO.numVuelosEnIgualHorario(avionAsignado, fechaSalida);
	}

}
