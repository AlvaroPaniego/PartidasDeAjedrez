package modelo;

import controlador.Traductor;

public class Alfil extends Pieza{

	public Alfil(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}

	@Override
	public Pieza mover(char fila, int columna, boolean capturar) {
		//TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int filaInt = traductor.charToInt(fila);
		System.out.println("Estoy moviendo un alfil");
		
		boolean mover = filaInt + columna == super.getFila() + super.getColumna() || filaInt - columna == super.getFila() - super.getColumna();
		if(mover) {
			System.out.println("Se puede mover");
			super.setFila(filaInt);
			super.setColumna(columna);
			
			return this;
		}
		return null;
	}

}
