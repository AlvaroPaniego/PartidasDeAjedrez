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
		for (int fila = 0; fila < tablero.length; fila++) {
			System.out.print(tr.intToChar(fila) + "   ");
			for (int columna = 0; columna < tablero.length; columna++) {
				System.out.print(tablero[fila][columna].getPieza() + " ");
			}
			System.out.println();
		}
		System.out.println("\n    1 2 3 4 5 6 7 8");
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
