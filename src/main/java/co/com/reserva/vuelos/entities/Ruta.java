package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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

	@Column(name="aeropuerto_destino")
	private BigDecimal aeropuertoDestino;

	//bi-directional many-to-one association to Aeropuerto
	@ManyToOne
	@JoinColumn(name="aeropuerto_origen")
	private Aeropuerto aeropuerto;

	public Ruta() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAeropuertoDestino() {
		return this.aeropuertoDestino;
	}

	public void setAeropuertoDestino(BigDecimal aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public Aeropuerto getAeropuerto() {
		return this.aeropuerto;
	}

	public void setAeropuerto(Aeropuerto aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

}