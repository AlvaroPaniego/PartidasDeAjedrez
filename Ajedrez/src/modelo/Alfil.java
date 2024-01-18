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
		System.out.println("Estoy moviendo un alfil");
		
		boolean mover = fila + columnaInt == super.getFila() + super.getColumna() || fila - columnaInt == super.getFila() - super.getColumna();
		if(mover) {
			System.out.println("Se puede mover");
			super.setFila(fila);
			super.setColumna(columnaInt);
			
			return this;
		}
		return null;
	}

}
