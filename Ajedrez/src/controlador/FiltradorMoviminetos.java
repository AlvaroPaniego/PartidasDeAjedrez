package controlador;

import java.util.ArrayList;

public class FiltradorMoviminetos {
	public ArrayList<String> filtradorDeMoviminetos(ArrayList<String> arrLMovimientos) {
		ArrayList<String> arrLMovimientosFiltrados = new ArrayList<>();
		String[] movimientoSinX;
		for (String movimiento : arrLMovimientos) {
			if(movimiento.contains("x")) {
				movimientoSinX = movimiento.split("x");
				movimiento = movimientoSinX[0] + movimientoSinX[1];
			}
			arrLMovimientosFiltrados.add(movimiento);
		}
		return arrLMovimientosFiltrados;
	}
	
	public String cogePieza(String movimiento) {
		String pieza = "";
		if(movimiento.length() > 2) {
			pieza = String.valueOf(movimiento.charAt(0));
		}
		return pieza;
	}
	
	public char cogeFila(String movimiento) {
		char fila = movimiento.charAt(0);
		if(movimiento.length() > 2) {
			fila = movimiento.charAt(1);
		}
		return fila;
	}
	
	public int cogeColumna(String movimiento) {
		int columna = -1;
		if(movimiento.length() > 2) {
			columna = Integer.parseInt(movimiento.charAt(2)+"");
		}else {
			columna = Integer.parseInt(movimiento.charAt(1)+"");
		}
		return columna;
	}
}
