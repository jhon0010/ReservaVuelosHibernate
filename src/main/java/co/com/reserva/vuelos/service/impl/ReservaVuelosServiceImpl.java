package co.com.reserva.vuelos.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.reserva.vuelos.dao.GenericDAO;
import co.com.reserva.vuelos.dao.ReservaVuelosDAO;
import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.entities.Reserva;
import co.com.reserva.vuelos.service.ReservaVuelosService;
import co.com.reserva.vuelos.vo.ReporteVuelosPorRutaVO;

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
	
	private GenericDAO genericDAO;

	/**
	 * Para la inyeccion del genericDAO
	 * 
	 * @param genericDAO
	 */
	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
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
	
	/**
	 * Metodo que permite actualizar los asientos ocupados
	 * en un vuelo dada una reserva, si el numero de asientos
	 * a reservar superan el maximo de asientos libres
	 * returna false, si se puede realziar la reserva retorna true
	 * 
	 * @param reserva Reserva a ser efectuada
	 * 
	 * 
	 * @return false si el numero de asientos
	 * a reservar superan el maximo de asientos libres
	 * 
	 * @return true si se puede realizar la reserva
	 */
	@Override
	@Transactional
	public boolean actualizarAsientosOcupados(Reserva reserva) {
		
		int capacidadAvion = reserva.getVuelo().getAvion().getCapacidad().intValue();
		int asientosOcupados = reserva.getVuelo().getAsientosOcupados();
		int asientosAReservar = reserva.getAsientosReservados();
		
		if (capacidadAvion > (asientosAReservar + asientosOcupados)){
			
			reserva.getVuelo().setAsientosOcupados(asientosAReservar + asientosOcupados);
			this.genericDAO.update(reserva.getVuelo());
			return true;
		}
		
		return false;
	}


	/**
	 * Metodo que permite conocer cuantos vuelos y cuantos pasajeros en total 
	 * se tienen en un rango de fechas y un avion agrupados por ruta realizada
	 * 
	 * @param avionAsignado avionq ue realizo la ruta
	 * @param fechaInicial fecha inicial para el reporte
	 * @param fechaInicial fecha final para el reporte
	 * 
	 * @return List<ReporteVuelosPorRutaVO> reporList lista con los objetos encontrados
	 * 
	 */
	@Override
	@Transactional
	public List<ReporteVuelosPorRutaVO> numVuelosPasajerosByRuta(Avion avionAsignado, Date fechaInicial,
			Date fechaFinal) {
		
		return this.reservaVuelosDAO.numVuelosPasajerosByRuta(avionAsignado, fechaInicial, fechaFinal);
	}

}
