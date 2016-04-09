package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the pasajeros database table.
 * 
 */
@Entity
@Table(name="pasajeros")
@NamedQuery(name="Pasajero.findAll", query="SELECT p FROM Pasajero p")
public class Pasajero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="identificacion", unique=true)
	private String identificacion;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	@Column(name="numero_tarjeta")
	private String numeroTarjeta;

	private String telefono;

	@Column(name="tipo_identificacion")
	private BigDecimal tipoIdentificacion;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="pasajeroBean")
	private List<Reserva> reservas;

	public Pasajero() {
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNumeroTarjeta() {
		return this.numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public BigDecimal getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(BigDecimal tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setPasajeroBean(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setPasajeroBean(null);

		return reserva;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}