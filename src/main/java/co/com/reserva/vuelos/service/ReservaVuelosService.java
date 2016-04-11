package co.com.reserva.vuelos.service;

import java.util.Date;
import java.util.List;

import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.entities.Reserva;
import co.com.reserva.vuelos.vo.ReporteVuelosPorRutaVO;

/**
 * Servicio que se encarga de exponer los metodos de negocio de reserva vuelos
 * @author jhon
 *
 */
public interface ReservaVuelosService {

	public int numVuelosEnIgualHorario(Avion avionAsignado, Date fechaSalida);
	public boolean actualizarAsientosOcupados(Reserva reserva);
	public List<ReporteVuelosPorRutaVO> numVuelosPasajerosByRuta(Avion avionAsignado, Date fechaInicial, Date fechaFinal);
}
