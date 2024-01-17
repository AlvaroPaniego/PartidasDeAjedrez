package modelo;

import controlador.Traductor;

public class Peon extends Pieza{
	private boolean primerMovimiento;
	public Peon(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
		this.primerMovimiento = true;
	}

	@Override
	public Pieza mover(char fila, int columna, boolean capturar) {
		//TODO Gestionar capturar en diagonal
		Traductor traductor = new Traductor();
		System.out.println("Estoy moviendo un peon");
		int filaInt = traductor.charToInt(fila);
		int v1 = filaInt - super.getFila(), v2 = columna - super.getColumna();
		boolean vertical1 = Math.abs(v1) == 1 && super.getColumna() == columna;
		boolean vertical2 = Math.abs(v1) == 2 && super.getColumna() == columna;
//		boolean avanza1 = ((super.getFila() + 1) == filaInt && super.getColumna() == columna) || ((super.getFila() - 1) == filaInt && super.getColumna() == columna);
//		boolean avanza2 = ((super.getFila() + 2) == filaInt && super.getColumna() == columna) || ((super.getFila() - 2) == filaInt && super.getColumna() == columna);
		boolean diagonal = Math.abs(v1) == 1 && Math.abs(v2) == 1;
		if(vertical1 || (vertical2 && primerMovimiento) || (diagonal && capturar)) {
			System.out.println("Se puede mover");
			primerMovimiento = false;
			super.setFila(filaInt);
			super.setColumna(columna);
			
			return this;
		}
		return null;
	}

}
