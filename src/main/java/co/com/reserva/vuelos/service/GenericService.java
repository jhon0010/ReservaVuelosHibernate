package co.com.reserva.vuelos.service;

import java.util.List;

public interface GenericService {

	public void add(Object o);
	public void update(Object o);
	public List<Object> listAll(Class<?> c);
	public Object getById(long id,Class<?> c);
	public void remove(long id, Class<?> c);
}
