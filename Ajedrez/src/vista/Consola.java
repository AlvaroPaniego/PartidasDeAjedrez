package vista;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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
		System.out.println("*********Elige una partida*********");
		for (int i = 0; i < partidas.size(); i++) {
			System.out.println((i+1) + ". " + partidas.get(i));
		}
		System.out.println("0. Salir.");
		System.out.println("***********************************");
		return pideNum("Introduce una opción");
	}
	
	public int menuPrincipal() {
		System.out.println("***************Menu****************");
		System.out.println("1. Visualizar partidas.");
		System.out.println("2. Exportar partida a Xml.");
		System.out.println("0. Salir.");
		System.out.println("***********************************");
		return pideNum("Introduce una opción");
	}
	
	private static int pideNum(String texto) {
		System.out.println(texto);
		Scanner scanner = new Scanner(System.in);
		int num = -1;
		boolean esNumero = false;
		do {
			try {
				num = scanner.nextInt();
				esNumero = true;
			} catch (InputMismatchException e) {
				System.out.println(Colores.RED + "Introduce un numero por favor" + Colores.RESET);
			}
		} while (!esNumero);
		return num;
	}
	
	public ArrayList<String> cogePartidas() {
		ArrayList<String> arrLFichero = new ArrayList<>(); 
		String[] dirPartidas = new File("partidas").list();
		for (int i = 0; i < dirPartidas.length; i++) {
			if (new File("partidas/" + dirPartidas[i]).isFile()) {
				arrLFichero.add(dirPartidas[i]);
			}
		}
		return arrLFichero;
	}
	
}
