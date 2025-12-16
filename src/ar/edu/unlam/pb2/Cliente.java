package ar.edu.unlam.pb2;

import java.util.Objects;

public class Cliente implements Comparable<Cliente>{
	
	final private String dni;
	private String nombre;
	
	public Cliente(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	public String getDni() {
		return this.dni;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public int compareTo(Cliente o) {
		System.out.println("Comparando "+this+" con "+o);
		return this.dni.compareTo(o.dni);
	}
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + "]";
	}
}