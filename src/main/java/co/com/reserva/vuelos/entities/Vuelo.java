package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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

	
	@Column(name="asientos_ocupados")
	private int asientosOcupados;
	
	@Column(name="fecha_arribo_estimada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaArriboEstimada;

	@Column(name="fecha_salida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;

	@ManyToOne
	@JoinColumn(name="ruta_a_cumplir")
	private Ruta rutaCumplir;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="vuelo", fetch = FetchType.EAGER)
	private List<Reserva> reservas;

	//bi-directional many-to-one association to Avione
	@ManyToOne
	@JoinColumn(name="avion")
	private Avion avion;

	public Vuelo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaArriboEstimada() {
		return fechaArriboEstimada;
	}

	public void setFechaArriboEstimada(Date fechaArriboEstimada) {
		this.fechaArriboEstimada = fechaArriboEstimada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setVuelo(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setVuelo(null);

		return reserva;
	}

	public Avion getAvion() {
		return this.avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Ruta getRutaCumplir() {
		return rutaCumplir;
	}

	public void setRutaCumplir(Ruta rutaCumplir) {
		this.rutaCumplir = rutaCumplir;
	}
	
	public int getAsientosOcupados() {
		return asientosOcupados;
	}

	public void setAsientosOcupados(int asientosOcupados) {
		this.asientosOcupados = asientosOcupados;
	}

	/**
	 * Se sobrescribe toStirng 
	 */
	@Override
	public String toString(){
		return this.getRutaCumplir().toString().concat(" a las ").concat(this.getFechaSalida().toString());
	}

}