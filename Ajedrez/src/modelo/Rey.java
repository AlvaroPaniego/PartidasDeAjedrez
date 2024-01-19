package modelo;

import controlador.Traductor;

public class Rey extends Pieza {
	public Rey(String pieza, boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}

	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		// TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int columnaInt = traductor.charToInt(columna);
		System.out.println("Estoy moviendo un rey");
		int v1 = fila - super.getFila(), v2 = columnaInt - super.getColumna();
		boolean diagonal = Math.abs(v1) == Math.abs(v2);
		boolean moverRecto = super.getFila() == fila || super.getColumna() == columnaInt;
		boolean distancia1 = Math.abs(v1) <= 1 && Math.abs(v2) <= 1;
		if ((diagonal || moverRecto) && distancia1) {
			System.out.println("Se puede mover");
			super.setFila(fila);
			super.setColumna(columnaInt);

			return this;
		}
		return null;
	}
}
