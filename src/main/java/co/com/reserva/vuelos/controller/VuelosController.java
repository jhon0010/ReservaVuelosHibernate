package co.com.reserva.vuelos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.entities.Ruta;
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
public class VuelosController {

	private static final Logger logger = LoggerFactory.getLogger(VuelosController.class);
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

	@RequestMapping(value = "/vuelos", method = RequestMethod.GET)
	public String listAll(Model model) {

		long rutaCumplirId = 0;
		long avionId = 0;

		model.addAttribute("error", error);
		model.addAttribute("fechaVuelo", "");
		model.addAttribute("rutaCumplir", new Ruta());
		model.addAttribute("avion", new Avion());
		model.addAttribute("aviones", this.genericService.listAll(Avion.class));
		model.addAttribute("rutas", this.genericService.listAll(Ruta.class));
		model.addAttribute("rutaCumplirId", rutaCumplirId);
		model.addAttribute("avionId", avionId);
		model.addAttribute("vuelo", new Vuelo());
		model.addAttribute("listVuelos", this.genericService.listAll(Vuelo.class));
		return "vuelos";
	}

	@RequestMapping(value = "/vuelo/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("") Vuelo v, @ModelAttribute("fechaVuelo") String fechaVuelo,
			@ModelAttribute("fechaArrivo") String fechaArrivo, @ModelAttribute("avionId") long avionId,
			@ModelAttribute("rutaCumplirId") long rutaCumplirId) {

		Date dateVuelo = null;
		Date dateArrivo = null;

		try {
			dateVuelo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaVuelo);

		} catch (Exception e) {
			error = "La fecha de inicio vuelo no tiene el formato indicado yyyy-MM-dd HH:mm:ss";
		}

		try {

			dateArrivo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaArrivo);
		} catch (Exception e) {
			error = "La fecha de arrivo no tiene el formato indicado yyyy-MM-dd HH:mm:ss";
		}

		if (dateArrivo != null && dateVuelo != null) {
			
			Avion avionVuelo = (Avion) this.genericService.getById(avionId, Avion.class);
			Ruta rutaACubrir = (Ruta) this.genericService.getById(rutaCumplirId, Ruta.class);

			
			int vuelosConConflicto = this.reservaVuelosService.numVuelosEnIgualHorario(avionVuelo, dateVuelo);
			
			if (vuelosConConflicto == 0){
				
				model.addAttribute("rutaCumplir", rutaACubrir);
				model.addAttribute("avion", avionVuelo);
				v.setAvion(avionVuelo);
				v.setRutaCumplir(rutaACubrir);
				v.setFechaArriboEstimada(dateArrivo);
				v.setFechaSalida(dateVuelo);

				if (v.getId() == 0) {

					logger.info("Agregar a una nueva vuelo con id " + v.getId());
					error = "";
					this.genericService.add(v);
				} else {
					logger.info("Actualizar a un nuevo vuelo con id " + v.getId());
					error = "";
					this.genericService.update(v);
				}

			} else {
				error = "El vuelo que intenta crear tiene asigando el avión en otra ruta dentro del mismo horario";
			}
		}

		return "redirect:/vuelos";

	}

	@RequestMapping("/vuelo/remove/{id}")
	public String remove(@PathVariable("id") int id) {

		this.genericService.remove(id, Vuelo.class);
		error = "";
		return "redirect:/vuelos";
	}

	@RequestMapping("/vuelo/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {

		Vuelo vueloSelected = (Vuelo) this.genericService.getById(id, Vuelo.class);
		Avion avionVuelo = vueloSelected.getAvion();
		Ruta rutaACubrir = vueloSelected.getRutaCumplir();

		model.addAttribute("vuelo", vueloSelected);
		model.addAttribute("rutaCumplir", rutaACubrir);
		model.addAttribute("avion", avionVuelo);
		model.addAttribute("aviones", this.genericService.listAll(Avion.class));
		model.addAttribute("rutas", this.genericService.listAll(Ruta.class));
		model.addAttribute("rutaCumplirId", rutaACubrir.getId());
		model.addAttribute("avionId", avionVuelo.getId());
		model.addAttribute("listVuelos", this.genericService.listAll(Vuelo.class));
		error = "";
		return "vuelos";
	}

}
