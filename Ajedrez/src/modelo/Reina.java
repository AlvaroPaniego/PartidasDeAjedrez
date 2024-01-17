package modelo;

import controlador.Traductor;

public class Reina extends Pieza{
	public Reina(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}
	@Override
	public Pieza mover(char fila, int columna, boolean capturar) {
		//TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int filaInt = traductor.charToInt(fila);
		System.out.println("Estoy moviendo una reina");
		
		boolean moverDiagonal = filaInt + columna == super.getFila() + super.getColumna() || filaInt - columna == super.getFila() - super.getColumna();
		boolean moverRecto = super.getFila() == filaInt || super.getColumna() == columna;
		if(moverDiagonal || moverRecto) {
			System.out.println("Se puede mover");
			super.setFila(filaInt);
			super.setColumna(columna);
			
			return this;
		}
		return null;
	}
}
