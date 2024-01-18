package modelo;

import controlador.Traductor;

public class Peon extends Pieza{
	private boolean primerMovimiento;
	public Peon(String pieza,  boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
		this.primerMovimiento = true;
	}

	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		//TODO Gestionar capturar en diagonal
		Traductor traductor = new Traductor();
		System.out.println("Estoy moviendo un peon");
		int columnaInt = traductor.charToInt(columna);
		int v1 = fila - super.getFila(), v2 = columnaInt - super.getColumna();
		boolean vertical1 = Math.abs(v1) == 1 && super.getColumna() == columnaInt;
		boolean vertical2 = Math.abs(v1) == 2 && super.getColumna() == columnaInt;
		boolean diagonal = Math.abs(v1) == Math.abs(v2);
		if(vertical1 || (vertical2 && primerMovimiento) || (diagonal && capturar)) {
			System.out.println("Se puede mover");
			primerMovimiento = false;
			super.setFila(fila);
			super.setColumna(columnaInt);
			
			return this;
		}
		return null;
	}

}
