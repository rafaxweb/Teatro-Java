

import java.io.Console;
import java.util.Scanner;

import com.teatro.Localidad;
import com.teatro.Obra;
import com.teatro.Teatro;

public class Main {

	public static void main(String[] args) {
		Console consola = System.console();

		if (consola == null) {
			System.out.println("Este programa necesita una consola para ejecutarse, por favor, utilice una consola");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		} else {
		
			clearScreen(consola);
	
			Teatro teatro = new Teatro("TEATRO LA BOMBILLA DE DON RAFA", "c/ Sol, 45", 300, 2);
			Obra obra = new Obra("La Cena de los Ni�os", "Comedia", 90, 15.0, 9, 4);
			Scanner keyboard = new Scanner(System.in);
	
			try {
				menu(keyboard, teatro, obra, consola);
	
			} catch (Exception e) {
				consola.writer().println("Ha habido un error, por favor vuelva a intentarlo");
				consola.writer().println("Error: \n" + e);
			}
	
			keyboard.close();
		}

	}

	public static void menu(Scanner keyboard, Teatro teatro, Obra obra, Console consola) {
		
		int opcion = 0;
		do {
			consola.writer().println("\n--------------MENU------------");
			consola.writer().println(teatro.getNombre());
			consola.writer().println("------------------------------" + "\n1. Ver la programaci�n actual"
					+ "\n2. Mostrar todos las asientos" + "\n3. Mostrar asientos ocupados" + "\n4. Vender asiento"
					+ "\n5. Cancelar asiento" + "\n6. Consultar asiento" + "\n7. Consultar recaudaci�n"
					+ "\n8. Terminar programa" + "\n------------------------------");

			consola.writer().print("Introduzca una opci�n:");
			
			String opcionTemp = keyboard.next();
			try {
			
				opcion = Integer.parseInt(opcionTemp);
				
			} catch (Exception e) {
				clearScreen(consola);
				consola.writer().println("Por favor, introduzca un n�mero v�lido");
				continue;
			}
			
			
			if (opcion == 1) {
				clearScreen(consola);
				consola.writer().println("-> Ver la programaci�n actual\n");
				verProgramacion(obra, teatro, consola);

			} else if (opcion == 2) {
				clearScreen(consola);
				consola.writer().println("-> Mostrar todos los asientos\n");
				obra.mostrarLocalidades(consola);

			} else if (opcion == 3) {
				clearScreen(consola);
				consola.writer().println("-> Mostrar asientos ocupados\n");
				mostrarLocalidadesOcupadas(obra, consola);

			} else if (opcion == 4) {
				clearScreen(consola);
				consola.writer().println("-> Vender asiento\n");
				venderLocalidad(keyboard, obra, consola);

			} else if (opcion == 5) {
				clearScreen(consola);
				consola.writer().println("-> Cancelar asiento\n");
				cancelarLocalidad(keyboard, obra, consola);

			} else if (opcion == 6) {
				clearScreen(consola);
				consola.writer().println("-> Consultar asiento\n");
				consultarLocalidad(keyboard, obra, consola);

			} else if (opcion == 7) {
				clearScreen(consola);
				consola.writer().println("-> Consultar recaudaci�n\n");
				consultarRecaudacion(keyboard, obra, consola);

			} else if (opcion == 8) {
				clearScreen(consola);
				consola.writer().println("Hasta luego\n");

			} else {
				clearScreen(consola);
				consola.writer().println("Por favor, introduzca un n�mero v�lido");
			}

		} while (opcion != 8);

	}

	public static void verProgramacion(Obra obra, Teatro teatro, Console consola) {
		obra.mostrarObra(consola);
		teatro.mostrarTeatro(consola);
		obra.mostrarPrecio(consola);
	}

	public static void mostrarLocalidadesOcupadas(Obra obra, Console consola) {
		consola.writer().println("Asientos ocupados:");

		boolean noOcupacion = true;
		for (Localidad localidad : obra.localidades) {

			if (localidad.isOcupado()) {
				localidad.consultarLocalidad(consola);
				noOcupacion = false;
			}

		}
		if (noOcupacion) {
			consola.writer().println("Todos los asientos est�n libres");
		}
	}

	public static void venderLocalidad(Scanner keyboard, Obra obra, Console consola) {

		int fila = 10;
		int columna = 10;
		
		consola.writer().printf("�En qu� fila quiere sentarse (0-%d)?", obra.getFilas());
		String filaTemp = keyboard.next();

		consola.writer().printf("�En qu� asiento quiere sentarse (0-%d)?", obra.getColumnas());
		String columnaTemp = keyboard.next();
		
		// Validar n�meros
		try {
			fila = Integer.parseInt(filaTemp);
			columna = Integer.parseInt(columnaTemp);
		
		} catch (Exception e) {
			consola.writer().println("Por favor, introduzca solo n�meros");
			return;
		}

		// Validar tama�o
		if (columna<0 || fila <0) {
			consola.writer().println("Por favor, introduzca un n�mero v�lido");
			return;
		}
		
		if (columna + fila * (obra.getColumnas() + 1) > obra.getColumnas() + obra.getFilas() * (obra.getColumnas() + 1)) {
			consola.writer().println("Por favor, introduzca solo n�meros v�lidos");
			return;
		}
		// - Fin validar tama�o

		// Validar vendido
		if (obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).isOcupado()) {
			consola.writer().println("El asiento ya ha sido vendido");
			return;

		} 
		consola.writer().println("Introduzca su nombre: ");
		String nombre = keyboard.next();

		consola.writer().println("Introduzca su tel�fono: ");
		String tempTelefono = keyboard.next();
		
		// Validar n�meros
		int telefono;
		try {
			telefono = Integer.parseInt(tempTelefono);

		
		} catch (Exception e) {
			consola.writer().println("Por favor, introduzca un tel�fono formado de solo n�meros");
			return;
		}
		

		consola.writer().println("�Cu�l es su edad?");
		String edadTemp = keyboard.next();
		
		int edad;
		try {
			edad = Integer.parseInt(edadTemp);

		
		} catch (Exception e) {
			consola.writer().println("Por favor, introduzca una edad num�rica");
			return;
		}
		
		// Validaciones
		if (columna >= obra.getColumnas() && fila <= obra.getFilas()) {
			System.err.println("Por favor, introduzca columnas y filas v�lidas");
			return;
		}
		
		if (nombre.length() == 0) {
			consola.writer().println("Por favor, introduce un nombre");
			return;
		}
		
		if (String.valueOf(telefono).toString().length() != 9) {
			System.err.println("Tel�fono no v�lido");
			return;
		}
		
		if (edad<0 || edad>150) {
			System.err.println("Edad no v�lida");
		}

		// Validaciones finalizadas
		consola.writer().println(obra.getColumnas());
		obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).venderLocalidad(nombre, telefono, edad,
				obra.getPrecio());
		consola.writer().printf("Asiento %d.%d vendido a %s por un precio de: %f", fila, columna, obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).getNombre(), obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).getPrecioTotal()).println();;
	

	}

	public static void cancelarLocalidad(Scanner keyboard, Obra obra, Console consola) {

		int fila;
		int columna;
		
		consola.writer().printf("�Qu� fila (0-%d)?", obra.getFilas());
		String filaTemp = keyboard.next();

		consola.writer().printf("�Qu� asiento quiere cancelar (0-%d)?", obra.getColumnas());
		String columnaTemp = keyboard.next();
		
		// Validar n�meros
		try {
			fila = Integer.parseInt(filaTemp);
			columna = Integer.parseInt(columnaTemp);
		
		} catch (Exception e) {
			consola.writer().println("Por favor, introduzca solo n�meros");
			return;
		}

		// Validar tama�o
		if (columna<0 || fila <0) {
			consola.writer().println("Por favor, introduzca un n�mero v�lido");
			return;
		}
		
		if (columna + fila * (obra.getColumnas() + 1) > obra.getColumnas() + obra.getFilas() * (obra.getColumnas() + 1)) {
			consola.writer().println("Por favor, introduzca solo n�meros v�lidos");
			return;
		}
		// - Fin validar tama�o
		
		if (obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).isOcupado()) {
			consola.writer().printf("%s ha cancelado su reserva", obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).getNombre()).println();
			obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).cancelarLocalidad();

		} else
			consola.writer().println("La plaza ya est� libre");

	}

	public static void consultarLocalidad(Scanner keyboard, Obra obra, Console consola) {
		consola.writer().printf("�Qu� fila (0-%d)?", obra.getFilas());
		int fila = keyboard.nextInt();

		consola.writer().printf("�Qu� asiento (0-%d)?", obra.getColumnas());
		int columna = keyboard.nextInt();

		if (obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).isOcupado()) {
			obra.localidades.get(columna + fila * (obra.getColumnas() + 1)).consultarLocalidad(consola);

		} else
			consola.writer().println("La plaza est� libre");

	}

	public static void consultarRecaudacion(Scanner keyboard, Obra obra, Console consola) {
		double recaudacion = 0;

		for (Localidad localidad : obra.localidades) {
			if (localidad.isOcupado()) {
				recaudacion += localidad.getPrecioTotal();
			}
		}

		consola.writer().printf("La recaudaci�n ha sido de: %f", recaudacion).println();

	}

	public static void clearScreen(Console consola) {
		for (int i = 0; i < 50; ++i)
			consola.writer().println();
	}
}
