package co.com.reserva.vuelos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.service.GenericService;
import co.com.reserva.vuelos.service.ReservaVuelosService;
import co.com.reserva.vuelos.vo.ReporteVuelosPorRutaVO;

/**
 * Clase encargarda de controlar las peticiones
 * que viajan desde le navegador a las paginas 
 * y manipular los datos hacia la vista y backend
 * @author jhon
 *
 */
@Controller
public class ReporteController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReporteController.class);
	private ReservaVuelosService reservaVuelosService;
	private GenericService genericService;
	String error = "";
	List<ReporteVuelosPorRutaVO> listReport;
	
	@Autowired(required = true)
	@Qualifier(value = "reservaVuelosService")
	public void setReservaVuelosService(ReservaVuelosService rvs) {
		this.reservaVuelosService = rvs;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "genericService")
	public void setGenericService(GenericService gs) {
		this.genericService = gs;
	}
	
	@RequestMapping(value = "/reportes", method = RequestMethod.GET)
	@Transactional
	public String listAll(Model model) {
		
		model.addAttribute("reporte", "");
		model.addAttribute("avion", new Avion());
		model.addAttribute("aviones", genericService.listAll(Avion.class));
		model.addAttribute("fechaVuelo", "");
		model.addAttribute("listReport",listReport);
		model.addAttribute("error", error);
		return "reporte";
	}
	
	@RequestMapping(value= "/reporte/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("fechaVuelo") String fechaVuelo,
			@ModelAttribute("fechaArrivo") String fechaArrivo, @ModelAttribute("avionId") long avionId){
		
		Date dateInicial = null;
		Date dateFinal = null;

		try {
			dateInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaVuelo);

		} catch (Exception e) {
			error = "La fecha de inicio vuelo no tiene el formato indicado yyyy-MM-dd HH:mm:ss";
		}

		try {

			dateFinal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaArrivo);
		} catch (Exception e) {
			error = "La fecha de arrivo no tiene el formato indicado yyyy-MM-dd HH:mm:ss";
		}

		if (dateFinal != null && dateInicial != null) {
			
			Avion avionVuelo = (Avion) this.genericService.getById(avionId, Avion.class);
			listReport = this.reservaVuelosService.numVuelosPasajerosByRuta(avionVuelo, dateInicial, dateFinal);
			error = "";
			model.addAttribute("listReport",listReport);
			logger.info("Se a consultado exitosamente para el reporte");
		}

		model.addAttribute("error", error);
		
		return "redirect:/reportes";
		
	}
	
	
}
