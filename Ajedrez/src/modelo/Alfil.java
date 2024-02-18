package modelo;

import controlador.Traductor;

public class Alfil extends Pieza{

	public Alfil(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}

	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		//TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int columnaInt = traductor.charToInt(columna);
		
		boolean mover = fila + columnaInt == super.getFila() + super.getColumna() || fila - columnaInt == super.getFila() - super.getColumna();
		if(mover) {
			super.setFila(fila);
			super.setColumna(columnaInt);
			
			return this;
		}
		return null;
	}

}
