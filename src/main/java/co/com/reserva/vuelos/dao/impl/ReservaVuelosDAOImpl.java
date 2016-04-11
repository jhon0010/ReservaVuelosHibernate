/**
 * 
 */
package co.com.reserva.vuelos.dao.impl;

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
import co.com.reserva.vuelos.entities.Vuelo;

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
	
	
	
}
