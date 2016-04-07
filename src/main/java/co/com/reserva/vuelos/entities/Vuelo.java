package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the vuelo database table.
 * 
 */
@Entity
@NamedQuery(name="Vuelo.findAll", query="SELECT v FROM Vuelo v")
public class Vuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="fecha_arribo_estimada")
	private Timestamp fechaArriboEstimada;

	@Column(name="fecha_salida")
	private Timestamp fechaSalida;

	@Column(name="ruta_a_cumplir")
	private BigDecimal rutaACumplir;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="vueloBean")
	private List<Reserva> reservas;

	//bi-directional many-to-one association to Avione
	@ManyToOne
	@JoinColumn(name="avion")
	private Avion avione;

	public Vuelo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getFechaArriboEstimada() {
		return this.fechaArriboEstimada;
	}

	public void setFechaArriboEstimada(Timestamp fechaArriboEstimada) {
		this.fechaArriboEstimada = fechaArriboEstimada;
	}

	public Timestamp getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getRutaACumplir() {
		return this.rutaACumplir;
	}

	public void setRutaACumplir(BigDecimal rutaACumplir) {
		this.rutaACumplir = rutaACumplir;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setVueloBean(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setVueloBean(null);

		return reserva;
	}

	public Avion getAvione() {
		return this.avione;
	}

	public void setAvione(Avion avione) {
		this.avione = avione;
	}

}