package com.teatro;

public class Localidad {
	
	// // Atributos
	private boolean ocupado;
	
	private final int fila;
	private final int columna;
	
	// Atributos de la persona
	private String nombre;
	private int telefono;
	private int edad;
	private String tipo;
	private double precioTotal;
	// Tipo: MAYOR/MENOR (dependiendo de la edad)

	
	// // Constructores
	public Localidad(int fila, int columna) {
		this.ocupado = false;
		this.fila = fila;
		this.columna = columna;
		this.nombre = null;
		this.telefono = 0;
		this.edad = 0;
		this.tipo = null;
		this.precioTotal = 0;
	}
	
	// // Métodos
	String calcularTipo(int edad) {
		if (edad>=0 && edad<=12) {
			return "INFANTIL";
		
		} else if (edad>=13 && edad<=17) {
			return "MENOR";
		
		} else if (edad>=18 && edad<=64) {
			return "MAYOR";
		
		} else if (edad>=65) {
			return "JUBILADO";
		
		} else {
			return null;
		}
	}
 
	double precioDescuento(double precio) {
		if (this.tipo =="INFANTIL") {
			return precio*0.5;
		
		} else if (this.tipo =="MENOR") {
			return precio*0.8;
		
		} else if (this.tipo =="MAYOR") {
			return precio;
		
		} else if (this.tipo =="JUBILADO") {
			return precio*0.34;
		
		} else return precio;
	}
	
	// Ver si la localidad está ocupada
	boolean localidadOcupada() {
		if (this.ocupado) {
			System.out.println("Esta plaza está ocupada”");
		}
		
		return this.ocupado;
	}
	
	// Vender localidad (según el tipo hay descuento, necesita precio
	void venderLocalidad(String nombre, int telefono, int edad, double precio) {
		this.ocupado = true;
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
		this.tipo = this.calcularTipo(edad);
		this.precioTotal = this.precioDescuento(precio);
	}
	
	// Cancelar localidad: añade null a la posición en la matriz
	void cancelarLocalidad() {
		this.ocupado = false;
		this.nombre = null;
		this.telefono = 0;
		this.edad = 0;
		this.precioTotal = 0;
		this.tipo = null;
	}
	
	// Consultar localidad (mostrar)
	void consultarLocalidad() {
		if (this.ocupado) { 
			String ocupación = "Localidad " + this.fila + "." + this.columna 
					+ " ocupada por " + this.nombre 
					+ ", tlf: " + this.telefono 
					+ ", Tipo: " + this.tipo 
					+ ", Precio: " + this.precioTotal;
			System.out.println(ocupación);
		
		} else {
			System.out.println("Localidad no ocupada");
		}
		
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public String getTipo() {
		return tipo;
	}
	
	// // Getter y setter
	
	
	
	
	
	
}
