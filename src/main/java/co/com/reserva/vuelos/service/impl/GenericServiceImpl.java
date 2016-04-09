package co.com.reserva.vuelos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.reserva.vuelos.dao.GenericDAO;
import co.com.reserva.vuelos.service.GenericService;

@Service
public class GenericServiceImpl implements GenericService {
	
	private GenericDAO genericDAO;

	public void setGenericDAO(GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	@Override
	@Transactional
	public void add(Object o) {
		this.genericDAO.add(o);
	}

	@Override
	@Transactional
	public void update(Object o) {
		this.genericDAO.update(o);
	}

	@Override
	@Transactional
	public List<Object> listAll(Class<?> c) {
		return this.genericDAO.listAll(c);
	}

	@Override
	@Transactional
	public Object getById(long id,Class<?> c) {
		return this.genericDAO.getById(id, c);
	}

	@Override
	@Transactional
	public void remove(long id,Class<?> c) {
		this.genericDAO.remove(id, c);
	}

}
