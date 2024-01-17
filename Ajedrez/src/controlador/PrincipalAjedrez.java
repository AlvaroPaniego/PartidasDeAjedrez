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
//		ArrayList<String> arrLFichero = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		Tablero tablero = new Tablero();
		GestorMoviminetos gm = new GestorMoviminetos(tablero);
//		LectorPartidas lp = new LectorPartidas();
		Consola c = new Consola();
//		Partida partida;
//		String[] dirPartidas = new File("partidas").list();
//		for (int i = 0; i < dirPartidas.length; i++) {
//			if (new File("partidas/" + dirPartidas[i]).isFile()) {
//				arrLFichero.add(dirPartidas[i]);
//			}
//		}
//
//		partida = lp.lectorPartidas(c.menuSeleccionFichero(arrLFichero));
		
		
		
//		System.out.println("Introduce el numero de la partida");
		c.mostrarTablero(tablero.getTablero());
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
		c.mostrarTablero(gm.moverPieza("N", 'f', 3));
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
		c.mostrarTablero(gm.moverPieza("", 'd', 1));
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
		c.mostrarTablero(gm.moverPieza("N", 'f', 8));
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
		c.mostrarTablero(gm.moverPieza("", 'e', 1));
		System.out.println("Introduce un carácter para continuar");
		scanner.next();
		c.mostrarTablero(gm.moverPieza("O-O-O", 'i', -1));

	}
}
