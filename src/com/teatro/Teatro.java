package com.teatro;

import java.io.Console;

public class Teatro {

	// // Atributos
	private String nombre;
	private String calle;
	private int superficie;
	private int accesos;
	
	// Constructores
	public Teatro(String nombre, String calle, int superficie, int accesos) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.superficie = superficie;
		this.accesos = accesos;
	}
	
	
	// // Métodos
	public void mostrarTeatro(Console consola) {
		String info = "En calle " + this.calle
				+ " local de " + this.superficie + " metros," 
				+ " con "+ this.accesos + " accesos";
		consola.writer().println(info);
	}


	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	
	public String getCalle() {
		return calle;
	}
	
	public int getSuperficie() {
		return superficie;
	}
	
	public int getAccesos() {
		return superficie;
	}
	
}
