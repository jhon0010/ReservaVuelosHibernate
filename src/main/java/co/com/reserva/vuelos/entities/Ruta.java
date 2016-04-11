package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the ruta database table.
 * 
 */
@Entity
@NamedQuery(name="Ruta.findAll", query="SELECT r FROM Ruta r")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name="aeropuerto_destino")
	private Aeropuerto aeropuertoDestino;

	//bi-directional many-to-one association to Aeropuerto
	@ManyToOne
	@JoinColumn(name="aeropuerto_origen")
	private Aeropuerto aeropuertoOrigen;
	
	@OneToMany(mappedBy="rutaCumplir")
	private List<Vuelo> vuelosAsociado;

	public Ruta() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Aeropuerto getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	/**
	 * Sobreescribimos el metodo toString
	 */
	@Override
	public String toString(){
		return aeropuertoOrigen.getNombre().concat(" -\n hasta \n - ").concat(aeropuertoDestino.getNombre());
	}

}