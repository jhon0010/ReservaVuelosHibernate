package co.com.reserva.vuelos.controller;

import java.math.BigDecimal;

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
import co.com.reserva.vuelos.entities.TipoIdentificacion;
import co.com.reserva.vuelos.service.GenericService;

/**
 * Clase encargarda de controlar las peticiones
 * que viajan desde le navegador a las paginas 
 * y manipular los datos hacia la vista y backend
 * @author jhon
 *
 */
@Controller
public class PasajeroController {
	
	private static final Logger logger = LoggerFactory.getLogger(PasajeroController.class);
	private GenericService genericService;
	
	@Autowired(required=true)
	@Qualifier(value="genericService")
	public void setGenericService(GenericService gs){
		this.genericService = gs;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listAll(Model model) {

		BigDecimal tipoId = new BigDecimal("0");		
		model.addAttribute("tiposIdentificaciones", this.genericService.listAll(TipoIdentificacion.class));
		model.addAttribute("tipoId",tipoId);
		model.addAttribute("pasajero", new Pasajero());
		model.addAttribute("listPasajeros", this.genericService.listAll(Pasajero.class));
		return "pasajero";
	}
	
	//For add and update  both
	@RequestMapping(value= "/pasajero/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("") Pasajero p, @ModelAttribute("tipoId") BigDecimal tipoId){
		
		if(p.getId() == 0){
			p.setTipoIdentificacion(tipoId);
			model.addAttribute("identificacion",this.genericService.getById(tipoId.longValue(), TipoIdentificacion.class));
			logger.info("Agregar a un nuevo pasajero con identificación " + p.getIdentificacion() + " y id " + p.getId());
			this.genericService.add(p);
		}else{
			logger.info("Actualizar a un nuevo pasajero con id " + p.getId());
			this.genericService.update(p);
		}
		
		return "redirect:/pasajeros";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id){
		
        this.genericService.remove(id, Pasajero.class);
        return "redirect:/pasajeros";
    }
 
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        model.addAttribute("pasajero", this.genericService.getById(id, Pasajero.class));
        model.addAttribute("listPasajeros", this.genericService.listAll(Pasajero.class));
        return "pasajero";
    }
	
}
