package modelo;

import controlador.Traductor;

public class Torre extends Pieza {
	public Torre(String pieza, boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}

	@Override
	public Pieza mover(char fila, int columna, boolean capturar) {
		// TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int filaInt = traductor.charToInt(fila);
		boolean mover = super.getFila() == filaInt || super.getColumna() == columna;
		System.out.println("Estoy moviendo una torre");
		if(mover) {
			System.out.println("Se puede mover");
			super.setFila(filaInt);
			super.setColumna(columna);
			
			return this;
		}
		return null;
	}
}
