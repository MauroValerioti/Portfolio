package com.course.ecommerce.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String Descripcion;
	private String imagen;
	private double precio;
	// private double total;
	private int cantidad;

	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "producto")
	private List<DetalleOrden> detalleOrdenes; // no es necesario crear este mapeo ya que solo se ve las ordenes desde
												// producto.

	public Producto(Integer id, String nombre, String descripcion, String imagen, double precio, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		Descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public Producto() {
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", Descripcion=" + Descripcion + ", imagen=" + imagen
				+ ", precio=" + precio + ", cantidad=" + cantidad + ", usuario=" + usuario + ", detalleOrdenes="
				+ detalleOrdenes + "]";
	}

}
