package modelo;

import controlador.Traductor;

public class Rey extends Pieza {
	public Rey(String pieza, boolean isBlanca, int fila, int columna) {
		super(pieza, isBlanca, fila, columna);
	}

	@Override
	public Pieza mover(char fila, int columna, boolean capturar) {
		// TODO Gestionar el movimiento
		Traductor traductor = new Traductor();
		int filaInt = traductor.charToInt(fila);
		System.out.println("Estoy moviendo un rey");
		int v1 = filaInt - super.getFila(), v2 = columna - super.getColumna();
		boolean moverDiagonal = filaInt + columna == super.getFila() + super.getColumna()
				|| filaInt - columna == super.getFila() - super.getColumna();
		boolean moverRecto = super.getFila() == filaInt || super.getColumna() == columna;
		boolean distancia1 = Math.abs(v1) == 1 && Math.abs(v2) == 1;
		if ((moverDiagonal || moverRecto) && distancia1) {
			System.out.println("Se puede mover");
			super.setFila(filaInt);
			super.setColumna(columna);

			return this;
		}
		return null;
	}
}
