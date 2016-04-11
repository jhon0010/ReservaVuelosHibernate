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

import co.com.reserva.vuelos.entities.Avion;
import co.com.reserva.vuelos.service.GenericService;

/**
 * Clase encargarda de controlar las peticiones
 * que viajan desde le navegador a las paginas 
 * y manipular los datos hacia la vista y backend
 * @author jhon
 *
 */
@Controller
public class AvionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AvionController.class);
	private GenericService genericService;
	
	@Autowired(required=true)
	@Qualifier(value="genericService")
	public void setGenericService(GenericService gs){
		this.genericService = gs;
	}
	
	@RequestMapping(value = "/aviones", method = RequestMethod.GET)
	public String listAll(Model model) {

		model.addAttribute("avion", new Avion());
		model.addAttribute("listAviones", this.genericService.listAll(Avion.class));
		return "aviones";
	}
	
	//For add and update  both
	@RequestMapping(value= "/avion/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("") Avion a){
		
		if(a.getId() == 0){
			
			logger.info("Agregar a un nuevo avion con id " + a.getId());
			this.genericService.add(a);
		}else{
			logger.info("Actualizar a un nuevo avion con id " + a.getId());
			this.genericService.update(a);
		}
		
		return "redirect:/aviones";
		
	}
	
	@RequestMapping("/avion/remove/{id}")
    public String remove(@PathVariable("id") int id){
		
        this.genericService.remove(id, Avion.class);
        return "redirect:/aviones";
    }
 
    @RequestMapping("/avion/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        model.addAttribute("avion", this.genericService.getById(id, Avion.class));
        model.addAttribute("listAviones", this.genericService.listAll(Avion.class));
        return "aviones";
    }
	
}
