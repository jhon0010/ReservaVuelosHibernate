package co.com.reserva.vuelos.service;

import java.util.List;

/**
 * Servicio que se encarga de exponer los metodos escenciales del CRUD
 * para las entidades
 * @author jhon
 *
 */
public interface GenericService {

	public void add(Object o);
	public void update(Object o);
	public List<Object> listAll(Class<?> c);
	public Object getById(long id,Class<?> c);
	public void remove(long id, Class<?> c);
}
