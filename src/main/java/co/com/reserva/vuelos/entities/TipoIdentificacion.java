package co.com.reserva.vuelos.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipos_identificaciones database table.
 * 
 */
@Entity
@Table(name="tipos_identificaciones")
@NamedQuery(name="TiposIdentificacione.findAll", query="SELECT t FROM TipoIdentificacion t")
public class TipoIdentificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String descripcion;

	public TipoIdentificacion() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
	    return this.getDescripcion();
	}
	
}