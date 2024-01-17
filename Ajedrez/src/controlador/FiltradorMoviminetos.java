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
}
