package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Partida;
import modelo.Tablero;
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
		String[] dirPartidas = new File("partidas").list();
		int numeroTurno = 1;
		for (int i = 0; i < dirPartidas.length; i++) {
			if (new File("partidas/" + dirPartidas[i]).isFile()) {
				arrLFichero.add(dirPartidas[i]);
			}
		}
		System.out.println("Introduce el numero de la partida");

		partida = lp.lectorPartidas(c.menuSeleccionFichero(arrLFichero));
		
		partida.setMovimientos(fm.filtradorDeMoviminetos(partida.getMovimientos()));
		int turnos = partida.getMovimientos().size(), i = 0, columna;
		boolean partidaTerminada = false, turno = true;
		c.mostrarTablero(tablero.getTablero());
		String[] jugadas;
		String pieza;
		char fila;
		while(!partidaTerminada && i < turnos) {
			jugadas = partida.getMovimientos().get(i).split(" ");
			if(jugadas.length < 1) {
				System.out.println("¡Tablas!");
				break;
			}
			if(turno) {
				partidaTerminada = movimientos(gm, c, fm, jugadas[0], p, partidaTerminada);
				System.out.println(partidaTerminada);
				turno = !turno;
			}else {
				partidaTerminada = movimientos(gm, c, fm, jugadas[1], p, partidaTerminada);
				turno = !turno;
				i++;
			}
			pasarTurno(scanner);
		}
	}

	private static boolean movimientos(GestorMoviminetos gm, Consola c, FiltradorMoviminetos fm, String jugadas, Pattern patronColumnas, boolean partidaTerminada) {
		int columna;
		String pieza;
		char fila, filaInicial;
		System.out.println(jugadas);
		if(!jugadas.contains("O")) {
			if(jugadas.contains("+")) {
				System.out.println("¡Jaque!");
				jugadas = jugadas.substring(0, jugadas.length() - 1);
			}
			if(jugadas.contains("#")) {
				System.out.println("¡Jaque Mate!");
				jugadas = jugadas.substring(0, jugadas.length() - 1);
				partidaTerminada = true;
			}
			pieza = fm.cogePieza(jugadas);
			fila = fm.cogeFila(jugadas);
			columna = fm.cogeColumna(jugadas);
			if(patronColumnas.matcher(pieza).matches()) {
				filaInicial = fm.cogeFilaInicial(jugadas);
				if(jugadas.length() == 3) {
					System.out.println(fila + " " + columna + " " + pieza.charAt(0));
					c.mostrarTablero(gm.peonCaptura("", fila, columna, pieza.charAt(0)));;
					return partidaTerminada;
				}else {
					c.mostrarTablero(gm.peonCaptura(pieza, fila, columna, filaInicial));
					return partidaTerminada;
				}
			}
		}else {
			pieza = jugadas;
			fila = 'i';
			columna = -1;
		}
		System.out.println(pieza + " " +fila + " " + columna);
		c.mostrarTablero(gm.moverPieza(pieza, fila, columna));
		return partidaTerminada;
	}
	
	private static void pasarTurno(Scanner scanner) {
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
	}
}
