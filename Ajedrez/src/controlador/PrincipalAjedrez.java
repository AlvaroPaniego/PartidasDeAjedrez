package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import modelo.Partida;
import modelo.Tablero;
import vista.Consola;

public class PrincipalAjedrez {
	public static void main(String[] args) {
		ArrayList<String> arrLFichero = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		Tablero tablero = new Tablero();
		GestorMoviminetos gm = new GestorMoviminetos(tablero);
		LectorPartidas lp = new LectorPartidas();
		Consola c = new Consola();
		FiltradorMoviminetos fm = new FiltradorMoviminetos();
		Partida partida;
		String[] dirPartidas = new File("partidas").list();
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
				break;
			}
			if(turno) {
				System.out.println(jugadas[0]);
				if(!jugadas[0].contains("O")) {
					pieza = fm.cogePieza(jugadas[0]);
					fila = fm.cogeFila(jugadas[0]);
					columna = fm.cogeColumna(jugadas[0]);
				}else {
					pieza = fm.cogePieza(jugadas[0]);
					fila = 'i';
					columna = -1;
				}
				turno = !turno;
				c.mostrarTablero(gm.moverPieza(pieza, fila, columna));
			}else {
				System.out.println(jugadas[1]);
				if(!jugadas[1].contains("O")) {
					pieza = fm.cogePieza(jugadas[1]);
					fila = fm.cogeFila(jugadas[1]);
					columna = fm.cogeColumna(jugadas[1]);
				}else {
					pieza = fm.cogePieza(jugadas[1]);
					fila = 'i';
					columna = -1;
				}
				turno = !turno;
				i++;
				c.mostrarTablero(gm.moverPieza(pieza, fila, columna));
			}
			pasarTurno(scanner);
		}
//		c.mostrarTablero(gm.moverPieza("N", 'f', 3));
//		System.out.println("Introduce un carácter para continuar");
//		scanner.next();
//		c.mostrarTablero(gm.moverPieza("", 'd', 1));
//		System.out.println("Introduce un carácter para continuar");
//		scanner.next();
//		c.mostrarTablero(gm.moverPieza("N", 'f', 8));
//		System.out.println("Introduce un carácter para continuar");
//		scanner.next();
//		c.mostrarTablero(gm.moverPieza("", 'e', 1));
//		System.out.println("Introduce un carácter para continuar");
//		scanner.next();
//		c.mostrarTablero(gm.moverPieza("O-O-O", 'i', -1));

	}

	private static void pasarTurno(Scanner scanner) {
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
	}
}
