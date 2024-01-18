package vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controlador.Traductor;
import modelo.Partida;
import modelo.Pieza;

public class Consola {
	Traductor tr = new Traductor();
	public void mostrarTablero(Pieza[][] tablero) {
		for (int fila = tablero.length-1; fila >= 0 ; fila--) {
			System.out.print((fila + 1) + "   ");
			for (int columna = 0; columna < tablero.length; columna++) {
				System.out.print(tablero[fila][columna].getPieza() + " ");
			}
			System.out.println();
		}
		System.out.println("\n    a b c d e f g h");
	}
	
	public int menuSeleccionFichero(ArrayList<String> partidas) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********Elige una partida*********");
		for (int i = 0; i < partidas.size(); i++) {
			System.out.println((i+1) + ". " + partidas.get(i));
		}
		System.out.println("***********************************");
		return scanner.nextInt();
	}
	
}
