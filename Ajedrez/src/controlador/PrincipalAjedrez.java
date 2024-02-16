package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import modelo.Partida;
import modelo.Tablero;
import vista.Colores;
import vista.Consola;

public class PrincipalAjedrez {
	public static void main(String[] args) {
		HashMap<Integer, Tablero> mapaMovimiento = new HashMap<>();
		ArrayList<String> arrLFichero = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		Tablero tablero = new Tablero();
		GestorMoviminetos gm = new GestorMoviminetos(tablero);
		LectorPartidas lp = new LectorPartidas();
		Consola c = new Consola();
		FiltradorMoviminetos fm = new FiltradorMoviminetos();
		Pattern p = Pattern.compile("[a-h]");
		Partida partida;
		GestorDom gDom = new GestorDom();
		int opcion = -1, partidaSeleccionada = -1;
		do {
			opcion = c.menuPrincipal();
			switch (opcion) {
			case 1:
				arrLFichero = c.cogePartidas();
				System.out.println("Introduce el numero de la partida");
				partidaSeleccionada = scanner.nextInt();
				if(partidaSeleccionada <= arrLFichero.size() && partidaSeleccionada != 0) {
					partida = lp.lectorPartidas(partidaSeleccionada);
					verPartida(scanner, tablero, gm, c, fm, p, partida);
				}
				break;
			case 2:
				arrLFichero = c.cogePartidas();
				System.out.println("Introduce el numero de la partida");
				partidaSeleccionada = c.menuSeleccionFichero(arrLFichero);
				if(partidaSeleccionada <= arrLFichero.size() && partidaSeleccionada != 0) {
					partida = lp.lectorPartidas(partidaSeleccionada);
					gDom.crearXml(partida.getJugadores() + ".xml");
					gDom.crearDocumentos();
					gDom.insertarPartida(partida, partidaSeleccionada);
					gDom.crearXml(partida.getJugadores() + ".xml");
				}
				break;
			case 0:
				System.out.println("Saliendo del programa.");
				break;

			default:
				System.err.println(Colores.RED + "Introduce un aopcion valida." + Colores.RESET);
				break;
			}
		} while (opcion != 0);
	}

	private static void verPartida(Scanner scanner, Tablero tablero, GestorMoviminetos gm, Consola c,
		FiltradorMoviminetos fm, Pattern p, Partida partida) {
		partida.setMovimientos(fm.filtradorDeMoviminetos(partida.getMovimientos()));
		int turnos = partida.getMovimientos().size(), i = 0;
		boolean partidaTerminada = false, turno = true;
		c.mostrarTablero(tablero.getTablero());
		String[] jugadas;
		while (!partidaTerminada && i < turnos) {
			jugadas = partida.getMovimientos().get(i).split(" ");
			if (jugadas.length < 2) {
				System.out.println("¡Tablas!");
				break;
			}
			if (turno) {
				partidaTerminada = movimientos(gm, c, fm, jugadas[0], p, partidaTerminada);
				System.out.println(partidaTerminada);
				turno = !turno;
			} else {
				partidaTerminada = movimientos(gm, c, fm, jugadas[1], p, partidaTerminada);
				turno = !turno;
				i++;
			}
			pasarTurno(scanner);
		}
	}

	private static boolean movimientos(GestorMoviminetos gm, Consola c, FiltradorMoviminetos fm, String jugadas,
			Pattern patronColumnas, boolean partidaTerminada) {
		int columna;
		String pieza;
		char fila, filaInicial;
		System.out.println(jugadas);
		if (!jugadas.contains("O")) {
			if (jugadas.contains("+")) {
				System.out.println("¡Jaque!");
				jugadas = jugadas.substring(0, jugadas.length() - 1);
			}
			if (jugadas.contains("#")) {
				System.out.println("¡Jaque Mate!");
				jugadas = jugadas.substring(0, jugadas.length() - 1);
				partidaTerminada = true;
			}
			pieza = fm.cogePieza(jugadas);
			fila = fm.cogeFila(jugadas);
			columna = fm.cogeColumna(jugadas);
			if (patronColumnas.matcher(pieza).matches() || jugadas.length() == 4) {
				filaInicial = fm.cogeFilaInicial(jugadas);
				if (jugadas.length() == 3) {
					System.out.println(fila + " " + columna + " " + pieza.charAt(0));
					c.mostrarTablero(gm.moverPiezaEspecifica("", fila, columna, pieza.charAt(0)));
					;
					return partidaTerminada;
				} else {
					c.mostrarTablero(gm.moverPiezaEspecifica(pieza, fila, columna, filaInicial));
					return partidaTerminada;
				}
			}
		} else {
			pieza = jugadas;
			fila = 'i';
			columna = -1;
		}
		System.out.println(pieza + " " + fila + " " + columna);
		c.mostrarTablero(gm.moverPieza(pieza, fila, columna));
		return partidaTerminada;
	}

	private static void pasarTurno(Scanner scanner) {
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
	}
}
