package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reserva database table.
 * 
 */
@Entity
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	//bi-directional many-to-one association to Pasajero
	@ManyToOne
	@JoinColumn(name="pasajero")
	private Pasajero pasajeroBean;

	//bi-directional many-to-one association to Vuelo
	@ManyToOne
	@JoinColumn(name="vuelo")
	private Vuelo vueloBean;

	public Reserva() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pasajero getPasajeroBean() {
		return this.pasajeroBean;
	}

	public void setPasajeroBean(Pasajero pasajeroBean) {
		this.pasajeroBean = pasajeroBean;
	}

	public Vuelo getVueloBean() {
		return this.vueloBean;
	}

	public void setVueloBean(Vuelo vueloBean) {
		this.vueloBean = vueloBean;
	}

}