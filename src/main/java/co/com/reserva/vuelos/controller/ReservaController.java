package co.com.reserva.vuelos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.reserva.vuelos.entities.Pasajero;
import co.com.reserva.vuelos.entities.Reserva;
import co.com.reserva.vuelos.entities.Vuelo;
import co.com.reserva.vuelos.service.GenericService;
import co.com.reserva.vuelos.service.ReservaVuelosService;

/**
 * Clase encargarda de controlar las peticiones que viajan desde le navegador a
 * las paginas y manipular los datos hacia la vista y backend
 * 
 * @author jhon
 *
 */
@Controller
public class ReservaController {

	private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
	private GenericService genericService;
	private ReservaVuelosService reservaVuelosService;
	private String error;

	@Autowired(required = true)
	@Qualifier(value = "genericService")
	public void setGenericService(GenericService gs) {
		this.genericService = gs;
	}

	@Autowired(required = true)
	@Qualifier(value = "reservaVuelosService")
	public void setReservaVuelosService(ReservaVuelosService rvs) {
		this.reservaVuelosService = rvs;
	}

	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public String listAll(Model model) {

		long vueloId = 0;
		long pasajeroId = 0;

		model.addAttribute("error", error);

		model.addAttribute("reserva", new Reserva());
		model.addAttribute("pasajeros", this.genericService.listAll(Pasajero.class));
		model.addAttribute("vuelo", new Vuelo());
		model.addAttribute("pasajero", new Pasajero());
		model.addAttribute("vuelos", this.genericService.listAll(Vuelo.class));
		model.addAttribute("vueloId", vueloId);
		model.addAttribute("pasajeroId", pasajeroId);
		model.addAttribute("listReservas", this.genericService.listAll(Reserva.class));
		return "reservas";
	}

	@RequestMapping(value = "/reserva/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("") Reserva r, @ModelAttribute("vueloId") long vueloId,
			@ModelAttribute("pasajeroId") long pasajeroId) {

			Pasajero pasa = (Pasajero) this.genericService.getById(pasajeroId, Pasajero.class);
			Vuelo vuelo = (Vuelo) this.genericService.getById(vueloId, Vuelo.class);

			model.addAttribute("vuelo", vuelo);
			model.addAttribute("pasajero", pasa);
			r.setPasajero(pasa);
			r.setVuelo(vuelo);

			if (r.getId() == 0) {

				logger.info("Agregar a una nueva reserva con id " + r.getId());
				
				
				if (this.reservaVuelosService.actualizarAsientosOcupados(r)) {
					this.genericService.add(r);
					error = "";
				} else {
					error = "Los asientos a reservar superan la capacidad del avion";
				}				
			} else {
				logger.info("Actualizar a una nueva reserva con id " + r.getId());
				
				if (this.reservaVuelosService.actualizarAsientosOcupados(r)) {
					this.genericService.update(r);
					error = "";
				} else {
					error = "Los asientos a reservar superan la capacidad del avion";
				}
			}

		return "redirect:/reservas";

	}

	@RequestMapping("/reserva/remove/{id}")
	public String remove(@PathVariable("id") int id) {

		this.genericService.remove(id, Reserva.class);
		error = "";
		return "redirect:/vuelos";
	}

	@RequestMapping("/reserva/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {

		Reserva reservaSelected = (Reserva) this.genericService.getById(id, Reserva.class);
	
		model.addAttribute("reserva", reservaSelected);
		model.addAttribute("pasajeros", this.genericService.listAll(Pasajero.class));
		model.addAttribute("vuelos", this.genericService.listAll(Vuelo.class));
		model.addAttribute("listReservas", this.genericService.listAll(Reserva.class));
		model.addAttribute("vuelo", reservaSelected.getVuelo());
		model.addAttribute("pasajero", reservaSelected.getPasajero());
		
		error = "";
		return "reservas";
	}

}
