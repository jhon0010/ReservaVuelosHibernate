package co.com.reserva.vuelos.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.com.reserva.vuelos.dao.GenericDAO;

/**
 * Implementacion de las consultas genericas
 * @author jhon
 *
 */
@Repository
public class GenericDAOImpl implements GenericDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);
	private SessionFactory sessionFactory;
	
	/**
	 * Metodo para la inyeccion del sessionFactory
	 * @param sf
	 */
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	/**
	 * Metodo que permite agregar un objeto de las entidades
	 * @param Object o entidad a persistir
	 */
	@Override
	public void add(Object o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(o);
		logger.info(o.getClass() + " saved successfully");
	}

	/**
	 * Metodo que permite actualizar un objeto de las entidades
	 * @param Object o entidad a persistir
	 */
	@Override
	public void update(Object o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(o);
		logger.info(o.getClass() + " updated successfully");
	}

	/**
	 * Metodo que permite listar todos los objetos de un tipo de las entidades
	 * @param c class clase a la cual se el hara la consulta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listAll(Class<?> c) {
		Session session = this.sessionFactory.getCurrentSession();
		logger.debug("Intentando obtener todos los datos de " + c.getName());
		List<Object> list = session.createQuery("from " + c.getName()).list();
		for(Object o : list){
			logger.info(" List::" + o);
		}
		return list;
	}

	/**
	 * Metodo que permite obtener un objeto  entidade mediante su id
	 * @param c class clase a la cual se el hara la consulta
	 * @param id identificador del objeto
	 */
	@Override
	public Object getById(long id, Class<?> c) {
		Session session = this.sessionFactory.getCurrentSession();		
		Object o =  session.load(c, id);
		logger.info(c.getName() + " loaded successfully " + o);
		return o;
	}

	/**
	 * Metodo que permite eliminar un objeto  entidad mediante su id
	 * @param c class clase a la cual se el hara la consulta
	 * @param id identificador del objeto
	 */
	@Override
	public void remove(long id, Class<?> c) {
		Session session = this.sessionFactory.getCurrentSession();
		Object o = session.load(c, id);
		if(null != o){
			session.delete(o);
		}
		logger.info(c.getName() + " deleted " + o);
	}

}
