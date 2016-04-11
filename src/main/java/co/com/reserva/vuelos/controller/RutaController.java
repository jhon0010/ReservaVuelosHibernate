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

import co.com.reserva.vuelos.entities.Aeropuerto;
import co.com.reserva.vuelos.entities.Ruta;
import co.com.reserva.vuelos.service.GenericService;

/**
 * Clase encargarda de controlar las peticiones
 * que viajan desde le navegador a las paginas 
 * y manipular los datos hacia la vista y backend
 * @author jhon
 *
 */
@Controller
public class RutaController {
	
	private static final Logger logger = LoggerFactory.getLogger(RutaController.class);
	private GenericService genericService;
	
	@Autowired(required=true)
	@Qualifier(value="genericService")
	public void setGenericService(GenericService gs){
		this.genericService = gs;
	}
	
	@RequestMapping(value = "/rutas", method = RequestMethod.GET)
	public String listAll(Model model) {

		long aeropuertoOrigenId = 0;
		long aeropuertoDestinoId = 0;	
		model.addAttribute("aeropuertoOrigen", new Aeropuerto());
		model.addAttribute("aeropuertoDestino", new Aeropuerto());
		model.addAttribute("aeropuertos", this.genericService.listAll(Aeropuerto.class));
		model.addAttribute("aeropuertoOrigenId",aeropuertoOrigenId);
		model.addAttribute("aeropuertoDestinoId",aeropuertoDestinoId);
		model.addAttribute("ruta", new Ruta());
		model.addAttribute("listRutas", this.genericService.listAll(Ruta.class));
		return "rutas";
	}
	
	//For add and update  both
	@RequestMapping(value= "/ruta/add", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute("") Ruta r, @ModelAttribute("aeropuertoOrigenId") long aeropuertoOrigenId, 
			@ModelAttribute("aeropuertoDestinoId") long aeropuertoDestinoId){
		
		
		Aeropuerto aeropuertoOrig = (Aeropuerto) this.genericService.getById(aeropuertoOrigenId, Aeropuerto.class);
		Aeropuerto aeropuertoDest = (Aeropuerto) this.genericService.getById(aeropuertoDestinoId, Aeropuerto.class);
		
		model.addAttribute("aeropuertoOrigen", aeropuertoOrig);
		model.addAttribute("aeropuertoDestino",aeropuertoDest);
		r.setAeropuertoOrigen(aeropuertoOrig);
		r.setAeropuertoDestino(aeropuertoDest);
		
		if(r.getId() == 0){

			logger.info("Agregar a una nueva ruta con id " + r.getId());
			this.genericService.add(r);
		}else{
			logger.info("Actualizar a un nuevo ruta con id " + r.getId());
			this.genericService.update(r);
		}
		
		return "redirect:/rutas";
		
	}
	
	@RequestMapping("/ruta/remove/{id}")
    public String remove(@PathVariable("id") int id){
		
        this.genericService.remove(id, Ruta.class);
        return "redirect:/rutas";
    }
 
    @RequestMapping("/ruta/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
    	
    	Ruta rutaSelected = (Ruta) this.genericService.getById(id, Ruta.class);
    	
        model.addAttribute("ruta", rutaSelected);
        Aeropuerto aeroOrig = rutaSelected.getAeropuertoOrigen();
        Aeropuerto aeroDest = rutaSelected.getAeropuertoDestino();
        
        model.addAttribute("listRutas", this.genericService.listAll(Ruta.class));
        
		model.addAttribute("aeropuertoOrigen", aeroOrig);
		model.addAttribute("aeropuertoDestino", aeroDest);
		model.addAttribute("aeropuertos", this.genericService.listAll(Aeropuerto.class));
		model.addAttribute("aeropuertoOrigenId",aeroOrig.getId());
		model.addAttribute("aeropuertoDestinoId",aeroDest.getId());

        return "rutas";
    }
	
}
