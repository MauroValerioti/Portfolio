package com.course.ecommerce.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios") //esto renombra a la entidad para cuando se setee en la DB, de no agregarse se setea el nombre de la clase.
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //esto hace que el id se autogenere y se autoincremente en la DB.
	private Integer id;
	private String nombre;
	private String username;
	private String email;
	private String direccion;
	private String telefono;
	private String tipo;
	private String password;
	
	@OneToMany(mappedBy = "usuario") //esto es para mapear la relacion enrte las tablas. la lista de productos se mapea directamente con el atributo usuario de la clase Producto 
	private List<Producto> productos;

	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;
	
	
	public Usuario() {
	}

	@Override
	public String toString() {
		return "usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", email=" + email
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", Password: " + password + "]";
	}

	public Usuario(Integer id, String nombre, String username, String email, String direccion, String telefono,
			String tipo, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipo = tipo;
		this.password = password;
	}
	
	
	
}
