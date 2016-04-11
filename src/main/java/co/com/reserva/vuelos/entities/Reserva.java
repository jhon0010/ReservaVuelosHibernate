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
	private Pasajero pasajero;
	
	@Column(name="asientos_reservados")
	private int asientosReservados;

	//bi-directional many-to-one association to Vuelo
	@ManyToOne
	@JoinColumn(name="vuelo")
	private Vuelo vuelo;

	public Reserva() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public int getAsientosReservados() {
		return asientosReservados;
	}

	public void setAsientosReservados(int asientosReservados) {
		this.asientosReservados = asientosReservados;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}



}