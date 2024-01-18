package modelo;

import controlador.Traductor;

public class Caballo extends Pieza{
	public Caballo(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}
	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		//TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int columnaInt = traductor.charToInt(columna);
		System.out.println("Estoy moviendo un caballo");
		int v1 = fila - super.getFila(), v2 = columnaInt - super.getColumna();
		boolean horizontal = Math.abs(v1) == 2 && Math.abs(v2) == 1;
		boolean vertical = Math.abs(v1) == 1 && Math.abs(v2) == 2;
		if(horizontal || vertical) {
			System.out.println("Se puede mover");
			super.setFila(fila);
			super.setColumna(columnaInt);
			
			return this;
		}
		return null;
	}
}
