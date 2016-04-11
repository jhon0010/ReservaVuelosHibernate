package co.com.reserva.vuelos.dao;

import java.util.Date;
import java.util.List;

import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.vo.ReporteVuelosPorRutaVO;

/**
 * Interface con la cual se tendran consultas
 * relacionadas al negocio
 * @author jhon
 *
 */
public interface ReservaVuelosDAO {

	public int numVuelosEnIgualHorario(Avion avionAsignado, Date fechaSalida);
	public List<ReporteVuelosPorRutaVO> numVuelosPasajerosByRuta(Avion avionAsignado, Date fechaInicial, Date fechaFinal);
}
