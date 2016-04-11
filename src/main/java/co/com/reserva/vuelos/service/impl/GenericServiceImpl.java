package co.com.reserva.vuelos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.reserva.vuelos.dao.GenericDAO;
import co.com.reserva.vuelos.service.GenericService;

/**
 * Clase que permite inyectar el genericDAO y mediante este implementar los
 * metodos expuestos para el acceso a datos
 * 
 * @author jhon
 *
 */
@Service
public class GenericServiceImpl implements GenericService {

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
	 * Metodo que permite agregar un objeto de las entidades
	 * 
	 * @param Object
	 *            o entidad a persistir
	 */
	@Override
	@Transactional
	public void add(Object o) {
		this.genericDAO.add(o);
	}

	/**
	 * Metodo que permite actualizar un objeto de las entidades
	 * 
	 * @param Object
	 *            o entidad a persistir
	 */
	@Override
	@Transactional
	public void update(Object o) {
		this.genericDAO.update(o);
	}

	/**
	 * Metodo que permite listar todos los objetos de un tipo de las entidades
	 * 
	 * @param c
	 *            class clase a la cual se el hara la consulta
	 */
	@Override
	@Transactional
	public List<Object> listAll(Class<?> c) {
		return this.genericDAO.listAll(c);
	}

	/**
	 * Metodo que permite obtener un objeto entidade mediante su id
	 * 
	 * @param c
	 *            class clase a la cual se el hara la consulta
	 * @param id
	 *            identificador del objeto
	 */
	@Override
	@Transactional
	public Object getById(long id, Class<?> c) {
		return this.genericDAO.getById(id, c);
	}

	/**
	 * Metodo que permite eliminar un objeto entidad mediante su id
	 * 
	 * @param c
	 *            class clase a la cual se el hara la consulta
	 * @param id
	 *            identificador del objeto
	 */
	@Override
	@Transactional
	public void remove(long id, Class<?> c) {
		this.genericDAO.remove(id, c);
	}

}
