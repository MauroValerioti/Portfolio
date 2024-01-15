package es.dsrroma.school.springboot.reuniones.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // una vez que tengo la conexion a la DB en el yaml o properties ya puedo
		// declarar mis entidades y crearlas con JPA
@Table(name = "persona") // indico el nombre que quiero que tenga la tabla en la DB
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // genero el id autoincremental
	private long id;
	private String nombre;
	private String apellidos;

	public Persona() {
		super();
	}

	public Persona(long id, String nombre, String apellidos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}

	
}
