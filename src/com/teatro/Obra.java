package com.teatro;

import java.io.Console;
import java.util.ArrayList;

public class Obra {

	// // Atributos
	// Título
	private String titulo;
	// Género
	private String genero;
	// Duración
	private int duracion;
	// Precio
	private double precio;
	// Filas
	private int columnas;
	// Columnas
	private int filas;
	// localidades
	public ArrayList<Localidad> localidades = new ArrayList<Localidad>();
	
	// Constructores
	public Obra(String titulo, String genero, int duracion, double precio, int filas, int columnas) {
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.precio = precio;
		this.columnas = filas;
		this.filas = columnas;
		crearLocalidades();
	}
	
	
	// // Métodos
	public void mostrarObra(Console consola) {
		String info = "Hoy representamos '" + this.titulo 
				+ "', género: " + this.genero 
				+ ", Duración: " + this.duracion;
		consola.writer().println(info);
	}
	
	public void mostrarPrecio(Console consola) {
		consola.writer().println("Precio: " + this.precio);
	}
	
	// Crear localidades
	void crearLocalidades() {
		for (int i=0; i<=this.filas; i++) {
			for (int j=0; j<=this.columnas; j++) {
				Localidad asiento = new Localidad(i, j);
				localidades.add(asiento);
			}
		} 
		
	}
	
	public void mostrarLocalidades(Console consola) {
		for (Localidad localidad: this.localidades) {
			
			String ocupacion = (localidad.isOcupado()) ? "Ocupado" : "Libre"; 

			consola.writer().printf("%d.%d %s    ", localidad.getFila(), localidad.getColumna(), ocupacion);
			
			if (localidad.getColumna() == this.columnas) {
				consola.writer().println("");
			}
		}
	}
	
//	Localidad obtenerLocalidad(int fila, int columna) {
//		
//	}
	
	// // getters y setters 
	public String getTitulo() {
		return titulo;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public int getFilas() {
		return filas;
	}
	
	public int getColumnas() {
		return columnas;
	}
}
