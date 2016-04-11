package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aeropuertos database table.
 * 
 */
@Entity
@Table(name="aeropuertos")
@NamedQuery(name="Aeropuerto.findAll", query="SELECT a FROM Aeropuerto a")
public class Aeropuerto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String ciudad;

	private String nombre;

	//bi-directional many-to-one association to Ruta
	@OneToMany(mappedBy="aeropuertoOrigen")
	private List<Ruta> rutas;
	
	//bi-directional many-to-one association to Ruta
	@OneToMany(mappedBy="aeropuertoDestino")
	private List<Ruta> rutas2;

	public Aeropuerto() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ruta> getRutas() {
		return this.rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public Ruta addRuta(Ruta ruta) {
		getRutas().add(ruta);
		ruta.setAeropuertoOrigen(this);

		return ruta;
	}

	public Ruta removeRuta(Ruta ruta) {
		getRutas().remove(ruta);
		ruta.setAeropuertoOrigen(null);

		return ruta;
	}
	
	public List<Ruta> getRutas2() {
		return rutas2;
	}

	public void setRutas2(List<Ruta> rutas2) {
		this.rutas2 = rutas2;
	}

	@Override
	public String toString() {
	    return this.getNombre();
	}

}