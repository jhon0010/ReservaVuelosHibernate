package co.com.reserva.vuelos.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.com.reserva.vuelos.dao.GenericDAO;

@Repository
public class GenericDAOImpl implements GenericDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void add(Object o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(o);
		logger.info(o.getClass() + " saved successfully");
	}

	@Override
	public void update(Object o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(o);
		logger.info(o.getClass() + " updated successfully");
	}

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

	@Override
	public Object getById(long id, Class<?> c) {
		Session session = this.sessionFactory.getCurrentSession();		
		Object o =  session.load(c, id);
		logger.info(c.getName() + " loaded successfully " + o);
		return o;
	}

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
