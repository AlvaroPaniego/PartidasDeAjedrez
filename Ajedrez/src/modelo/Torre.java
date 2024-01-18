package modelo;

import controlador.Traductor;

public class Torre extends Pieza {
	public Torre(String pieza, boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}

	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		// TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int columnaInt = traductor.charToInt(columna);
		boolean mover = super.getFila() == fila || super.getColumna() == columnaInt;
		System.out.println("Estoy moviendo una torre");
		if(mover) {
			System.out.println("Se puede mover");
			super.setFila(fila);
			super.setColumna(columnaInt);
			
			return this;
		}
		return null;
	}
}
