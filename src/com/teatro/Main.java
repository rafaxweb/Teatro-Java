package com.teatro;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		clearScreen();

		Teatro teatro = new Teatro("TEATRO LA BOMBILLA DE DON BLAS", "c/ Sol, 45", 300, 2);
		Obra obra = new Obra("La Cena de los Idiotas", "Comedia", 90, 15.0, 9, 4);
		Scanner keyboard = new Scanner(System.in);

		try {
			menu(keyboard, teatro, obra);

		} catch (Exception e) {
			System.out.println("Ha habido un error, por favor vuelva a intentarlo");
			System.out.println("Error: \n" + e);
		}

		keyboard.close();

	}

	public static void menu(Scanner keyboard, Teatro teatro, Obra obra) {

		int opcion;
		do {
			System.out.println("\n--------------MENU------------");
			System.out.println(teatro.getNombre());
			System.out.println("------------------------------" + "\n1. Ver la programación actual"
					+ "\n2. Mostrar todos las asientos" + "\n3. Mostrar asientos ocupados" + "\n4. Vender asiento"
					+ "\n5. Cancelar asiento" + "\n6. Consultar asiento" + "\n7. Consultar recaudación"
					+ "\n8. Terminar programa" + "\n------------------------------");

			System.out.print("Introduzca una opción:");
			opcion = keyboard.nextInt();

			if (opcion == 1) {
				clearScreen();
				System.out.println("-> Ver la programación actual\n");
				verProgramacion(obra, teatro);

			} else if (opcion == 2) {
				clearScreen();
				System.out.println("-> Mostrar todas las localidades\n");
				obra.mostrarLocalidades();

			} else if (opcion == 3) {
				clearScreen();
				System.out.println("-> Mostrar localidades ocupadas\n");
				mostrarLocalidadesOcupadas(obra);

			} else if (opcion == 4) {
				clearScreen();
				System.out.println("-> Vender localidad\n");
				venderLocalidad(keyboard, obra);

			} else if (opcion == 5) {
				clearScreen();
				System.out.println("-> Cancelar localidad\n");
				cancelarLocalidad(keyboard, obra);

			} else if (opcion == 6) {
				clearScreen();
				System.out.println("-> Consultar localidad\n");
				consultarLocalidad(keyboard, obra);

			} else if (opcion == 7) {
				clearScreen();
				System.out.println("-> Consultar recaudación\n");
				consultarRecaudacion(keyboard, obra);

			} else if (opcion == 8) {
				clearScreen();
				System.out.println("Hasta luego\n");

			} else {
				clearScreen();
				System.out.println("Por favor, introduzca un número válido");
			}

		} while (opcion != 8);

	}

	public static void verProgramacion(Obra obra, Teatro teatro) {
		obra.mostrarObra();
		teatro.mostrarTeatro();
		obra.mostrarPrecio();
	}

	public static void mostrarLocalidadesOcupadas(Obra obra) {
		System.out.println("Asientos ocupados:");

		boolean noOcupacion = true;
		for (Localidad localidad : obra.localidades) {

			if (localidad.isOcupado()) {
				localidad.consultarLocalidad();
				noOcupacion = false;
			}

		}
		if (noOcupacion) {
			System.out.println("Todos los asientos están libres");
		}
	}

	public static void venderLocalidad(Scanner keyboard, Obra obra) {

		int fila;
		int columna;
		
		System.out.printf("¿En qué fila quiere sentarse (0-%d)?", obra.getFilas());
		fila = keyboard.nextInt();

		System.out.printf("¿En qué asiento quiere sentarse (0-%d)?", obra.getColumnas());
		columna = keyboard.nextInt();


		if (obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).isOcupado()) {
			System.out.println("El asiento ya ha sido vendido");
			return;

		} else {
			System.out.print("Introduzca su nombre: ");
			String nombre = keyboard.next();

			System.out.print("Introduzca su teléfono: ");
			int telefono = keyboard.nextInt();
			

			System.out.print("¿Cuál es su edad?");
			int edad = keyboard.nextInt();
			
			// Validaciones
			if (columna >= obra.getColumnas() && fila <= obra.getFilas()) {
				System.err.println("Por favor, introduzca columnas y filas válidas");
				return;
			}
			
			if (nombre.length() == 0) {
				System.out.println("Por favor, introduce un nombre");
				return;
			}
			
			if (String.valueOf(telefono).toString().length() != 9) {
				System.err.println("Teléfono no válido");
				return;
			}
			
			if (edad<0 || edad>150) {
				System.err.println("Edad no válida");
			}

			// Validaciones finalizadas
			System.out.println(obra.getColumnas());
			obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).venderLocalidad(nombre, telefono, edad,
					obra.getPrecio());
			System.out.printf("Asiento %d.%d vendido a %s por un precio de: %f", fila, columna, obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).getNombre(), obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).getPrecioTotal()).println();;
		}

	}

	public static void cancelarLocalidad(Scanner keyboard, Obra obra) {

		System.out.printf("¿Qué fila (0-%d)?", obra.getFilas());
		int fila = keyboard.nextInt();

		System.out.printf("¿Qué asiento quiere cancelar (0-%d)?", obra.getColumnas());
		int columna = keyboard.nextInt();

		if (obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).isOcupado()) {
			System.out.printf("%s ha cancelado su reserva", obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).getNombre()).println();
			obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).cancelarLocalidad();

		} else
			System.out.println("La plaza ya está libre");

	}

	public static void consultarLocalidad(Scanner keyboard, Obra obra) {
		System.out.printf("¿Qué fila (0-%d)?", obra.getFilas());
		int fila = keyboard.nextInt();

		System.out.printf("¿Qué asiento (0-%d)?", obra.getColumnas());
		int columna = keyboard.nextInt();

		if (obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).isOcupado()) {
			obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).consultarLocalidad();

		} else
			System.out.println("La plaza está libre");

	}

	public static void consultarRecaudacion(Scanner keyboard, Obra obra) {
		double recaudacion = 0;

		for (Localidad localidad : obra.localidades) {
			if (localidad.isOcupado()) {
				recaudacion += localidad.getPrecioTotal();
			}
		}

		System.out.printf("La recaudación ha sido de: %f", recaudacion).println();

	}

	public static void clearScreen() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}
}
