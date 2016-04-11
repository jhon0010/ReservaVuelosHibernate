/**
 * 
 */
package co.com.reserva.vuelos.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.com.reserva.vuelos.dao.ReservaVuelosDAO;
import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.entities.Ruta;
import co.com.reserva.vuelos.entities.Vuelo;
import co.com.reserva.vuelos.vo.ReporteVuelosPorRutaVO;

/**
 * Implementacion de las consultas relacionadas al negocio
 * @author jhon
 *
 */
@Repository
public class ReservaVuelosDAOImpl implements ReservaVuelosDAO {

	private static final Logger logger = LoggerFactory.getLogger(ReservaVuelosDAOImpl.class);
	private SessionFactory sessionFactory;
	
	/**
	 * Metodo para la inyeccion del sessionFactory
	 * @param sf
	 */
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
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
	@SuppressWarnings("unchecked")
	public int numVuelosEnIgualHorario(Avion avionAsignado, Date fechaSalida) {

		Session session = this.sessionFactory.getCurrentSession();

		String stringquery = "FROM Vuelo WHERE avion.id = :idAvion AND :fechaSalida BETWEEN fechaSalida AND fechaArriboEstimada";
		Query query = session.createQuery(stringquery);
		query.setParameter("idAvion", avionAsignado.getId());
		query.setParameter("fechaSalida", fechaSalida);
		
		List<Vuelo> list = (List<Vuelo>) query.list();
		
		logger.info("Se obtuvieron " + list.size() + " registros en numVuelosEnIgualHorario");
		return list.size();
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
	@SuppressWarnings("unchecked")
	public List<ReporteVuelosPorRutaVO>  numVuelosPasajerosByRuta(Avion avionAsignado, Date fechaInicial, Date fechaFinal) {

		List<ReporteVuelosPorRutaVO> reporList = new ArrayList<ReporteVuelosPorRutaVO>();
		
		Session session = this.sessionFactory.getCurrentSession();
		String stringquery = "SELECT V.RUTA_A_CUMPLIR, COUNT(V.ID) AS NUMERO_DE_VUELOS, SUM(V.ASIENTOS_OCUPADOS) AS ASIENTOS_OCUPADOS "
				+ " FROM VUELO V WHERE V.AVION = :idAvion AND V.FECHA_SALIDA BETWEEN :fechaInicial AND :fechaFinal GROUP BY V.RUTA_A_CUMPLIR";
		Query query = session.createSQLQuery(stringquery);
		query.setParameter("idAvion", avionAsignado.getId());
		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		List<Object[]> results = query.list();
		
		
		for (Object[] result : results) {
			
			ReporteVuelosPorRutaVO vo = new ReporteVuelosPorRutaVO();
			Integer idRuta =  (Integer) result[0];
			Ruta ruta =  (Ruta) session.load(Ruta.class, idRuta.longValue());
			BigInteger numeroVuelos = (BigInteger) result[1];
			BigInteger asientosOcupados = (BigInteger) result[2];
		    
		    vo.setRuta(ruta.toString());
		    vo.setCantidadPasajeros(asientosOcupados.intValue());
		    vo.setCantidadVuelos(numeroVuelos.intValue());
		    
		    reporList.add(vo);
		}
		
		return reporList;
	}

}
