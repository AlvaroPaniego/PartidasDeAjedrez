package modelo;

import controlador.Traductor;

public class Tablero {
	private Pieza[][] tablero = new Pieza[8][8];
	public Tablero() {
		Traductor tr = new Traductor();
		int c;
		for (int fila = tablero.length-1; fila >= 0; fila--) {
			for (char columna = 'a'; columna < 'i'; columna++) {
				c = tr.charToInt(columna);
				colocarTablero(fila, c);
			}
		}
	}
	
	public Pieza[][] getTablero() {
		return tablero;
	}

	public void setTablero(Pieza[][] tablero) {
		this.tablero = tablero;
	}

	private void colocarTablero(int filas, int columna) {
		switch(filas) {
		case 0:
			colocarNoblezaBlancas(columna, filas);
			break;
		case 1:
			colocarPeones(columna, filas, true);
			break;
		case 6:
			colocarPeones(columna, filas, false);
			break;
		case 7:
			colocarNoblezaNegras(columna, filas);
			break;
		default:
			tablero[filas][columna] = new Vacio("_", filas, columna);
		}
	}
	private void colocarNoblezaBlancas(int columna, int filas) {
		switch(columna) {
		case 0:
		case 7:
			tablero[filas][columna] = new Torre("R", true, filas, columna);
			break;
		case 1:
		case 6:
			tablero[filas][columna] = new Caballo("N", true, filas, columna);
			break;
		case 2:
		case 5:
			tablero[filas][columna] = new Alfil("B", true, filas, columna);
			break;
		case 3:
			tablero[filas][columna] = new Reina("Q", true, filas, columna);
			break;
		case 4:
			tablero[filas][columna] = new Rey("K", true, filas, columna);
			break;
		}
	}
	private void colocarPeones(int columna, int filas, boolean esBlanca) {
		tablero[filas][columna] = new Peon("E", esBlanca, filas, columna);
	}
	private void colocarNoblezaNegras(int columna, int filas) {
		switch(columna) {
		case 0:
		case 7:
			tablero[filas][columna] = new Torre("R", false, filas, columna );
			break;
		case 1:
		case 6:
			tablero[filas][columna] = new Caballo("N", false, filas, columna);
			break;
		case 2:
		case 5:
			tablero[filas][columna] = new Alfil("B", false, filas, columna);
			break;
		case 3:
			tablero[filas][columna] = new Reina("Q", false, filas, columna);
			break;
		case 4:
			tablero[filas][columna] = new Rey("K", false, filas, columna);
			break;
		}
	}
	public boolean retaguardiaOcupada(boolean turno) {
		//Se podria pasar tambien la posicion del rey para que solo recorra hasta ahi.
		boolean ocupada = false;
		int i = 0;
		if(turno) {
			while(i < tablero.length && !ocupada) {
				if(tablero[7][i].getPieza().equals("N") || tablero[7][i].getPieza().equals("B") || tablero[7][i].getPieza().equals("Q")) {
					i++;
					ocupada = true;
				}
			}
		}else {
			if(tablero[0][i].getPieza().equals("n") || tablero[0][i].getPieza().equals("b") || tablero[0][i].getPieza().equals("q")) {
				i++;
				ocupada = true;
			}
		}
		return ocupada;
	}
}
