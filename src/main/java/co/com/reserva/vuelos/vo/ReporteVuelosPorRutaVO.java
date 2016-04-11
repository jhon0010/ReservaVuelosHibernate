/**
 * 
 */
package co.com.reserva.vuelos.vo;

import co.com.reserva.vuelos.entities.Ruta;

/**
 * Clase creada para transportar la informacion desde 
 * la capa de persistencia a la capa de vista 
 * al consultar para un informe
 * @author jhon
 *
 */
public class ReporteVuelosPorRutaVO {
	
	private int cantidadVuelos;
	private int cantidadPasajeros;
	private String ruta;

	/**
	 * Constructor por defecto
	 */
	public ReporteVuelosPorRutaVO() {
	
	}

	public int getCantidadVuelos() {
		return cantidadVuelos;
	}

	public void setCantidadVuelos(int cantidadVuelos) {
		this.cantidadVuelos = cantidadVuelos;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public void setCantidadPasajeros(int cantidadPasajeros) {
		this.cantidadPasajeros = cantidadPasajeros;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
