package modelo;

import controlador.Traductor;

public class Reina extends Pieza{
	public Reina(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}
	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		//TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int columnaInt = traductor.charToInt(columna);
		System.out.println("Estoy moviendo una reina");
		
		boolean moverDiagonal = fila + columnaInt == super.getFila() + super.getColumna() || fila - columnaInt == super.getFila() - super.getColumna();
		boolean moverRecto = super.getFila() == fila || super.getColumna() == columnaInt;
		if(moverDiagonal || moverRecto) {
			System.out.println("Se puede mover");
			super.setFila(fila);
			super.setColumna(columnaInt);
			
			return this;
		}
		return null;
	}
}
