package modelo;

import java.util.ArrayList;

public class Partida {
	private String jugadores;
	private ArrayList<String> arrLMovimientos;
	
	
	
	public Partida() {
		arrLMovimientos = new ArrayList<>();
	}
	
	public String getJugadores() {
		return jugadores;
	}

	public void setJugadores(String jugadores) {
		this.jugadores = jugadores;
	}
	public ArrayList<String> getMovimientos() {
		return arrLMovimientos;
	}
	public void setMovimientos(ArrayList<String> movimientosBlancas) {
		this.arrLMovimientos = movimientosBlancas;
	}
}
