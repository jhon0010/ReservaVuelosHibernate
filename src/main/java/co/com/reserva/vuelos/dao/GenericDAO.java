package co.com.reserva.vuelos.dao;

import java.util.List;

/**
 * Interface en la que se tiene acceso a datos tipo CRUD generico
 * @author jhon
 *
 */
public interface GenericDAO {

	public void add(Object o);
	public void update(Object o);
	public List<Object> listAll(Class<?> c);
	public Object getById(long id,Class<?> c);
	public void remove(long id, Class<?> c);
}
