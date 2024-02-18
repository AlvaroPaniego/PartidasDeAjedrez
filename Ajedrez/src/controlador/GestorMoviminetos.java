package controlador;

import modelo.Pieza;
import modelo.Tablero;
import modelo.Vacio;
import vista.Colores;

public class GestorMoviminetos {
	private boolean turno, seMueve, saltarTorre = false;
	private Pieza[][] tablero;
	private Tablero tableroObj;

	public GestorMoviminetos(Tablero tableroObj) {
		this.tableroObj = tableroObj;
		this.tablero = tableroObj.getTablero();
		this.turno = true;
	}

	public Pieza[][] getTablero() {
		return tablero;
	}

	public void setTablero(Pieza[][] tablero) {
		this.tablero = tablero;
	}

	public Pieza[][] moverPiezaEspecifica(String pieza, char columnaFinal, int filaFinal, char columnaInicial) {
		Traductor tr = new Traductor();
		Pieza piezaAMover;
		int columnaFinalComparar = tr.charToInt(columnaFinal), columnaInicialComparar = tr.charToInt(columnaInicial);
		seMueve = false;
		String piezaMover = elegirPieza(pieza);
		for (int fila = tablero.length - 1; fila >= 0; fila--) {
			if (tablero[fila][columnaInicialComparar].getPieza().equals(piezaMover)) {
				piezaAMover = tablero[fila][columnaInicialComparar].mover(filaFinal - 1, columnaFinal, true);
				if (piezaAMover != null) {
					tablero[fila][columnaInicialComparar] = new Vacio("_", fila, columnaInicialComparar);
					tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
					seMueve = true;
					turno = !turno;
					return tablero;
				}
			}
		}
		turno = !turno;
		if (!seMueve) {
			System.out.println(Colores.RED + "Jugada no valida" + Colores.RESET);
		}
		seMueve = false;
		return tablero;
	}

	public Pieza[][] moverPieza(String pieza, char columnaFinal, int filaFinal) {
		Traductor tr = new Traductor();
		Pieza piezaAMover;
		int columna;
		seMueve = false;
		boolean puedeCapturar;
		String piezaMover = elegirPieza(pieza);
		if ((pieza.equals("O-O-O"))) {
			saltarTorre = true;
		} else {
			saltarTorre = false;
		}
		for (int f = tablero.length - 1; f >= 0; f--) {
			for (char col = 'a'; col < 'i'; col++) {
				puedeCapturar = false;
				columna = tr.charToInt(col);
				switch (piezaMover) {
				case "O-O":
					if (turno) {
						if (tablero[f][columna].getPieza().equals("K")) {
							moverRey(f, false, columna);
						}
						if (tablero[f][columna].getPieza().equals("R")) {
							moverSegundaTorre(f, columna, false);
						}
					} else {
						if (tablero[f][columna].getPieza().equals("k")) {
							moverRey(f, false, columna);
						}
						if (tablero[f][columna].getPieza().equals("r")) {
							moverSegundaTorre(f, columna, false);
						}
					}
					break;
				case "O-O-O":
					if (turno) {
						if (tablero[f][columna].getPieza().equals("K")) {
							moverRey(f, true, columna);
						}
						if (tablero[f][columna].getPieza().equals("R")) {
							moverPrimeraTorre(f, columna, true);
						}
					} else {
						if (tablero[f][columna].getPieza().equals("k")) {
							moverRey(f, true, columna);
						}
						if (tablero[f][columna].getPieza().equals("r")) {
							moverPrimeraTorre(f, columna, true);
						}
					}
					break;
				default:
					if (tablero[f][columna].getPieza().equals(piezaMover)) {
						piezaAMover = tablero[f][columna].mover(filaFinal - 1, columnaFinal, puedeCapturar);
						if (piezaAMover != null) {
							tablero[f][columna] = new Vacio("_", columna, f);
							tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
							seMueve = true;
							turno = !turno;
							return tablero;
						}
					}
				}
			}
		}
		turno = !turno;
		if (!seMueve) {
			System.out.println(Colores.RED + "Jugada no valida" + Colores.RESET);
		}
		seMueve = false;
		return tablero;
	}

	private void moverRey(int fila, boolean esLargo, int columna) {
		Pieza piezaAMover;
		piezaAMover = enroqueRey(esLargo, tablero[fila][columna]);
		if (piezaAMover != null) {
			tablero[fila][columna] = new Vacio("_", fila, columna);
			tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
		}
	}

	private void moverSegundaTorre(int fila, int columna, boolean esLargo) {
		Pieza piezaAMover;
		if (saltarTorre) {
			piezaAMover = enroqueTorre(esLargo, tablero[fila][columna]);
			if (piezaAMover != null) {
				tablero[fila][columna] = new Vacio("_", fila, columna);
				tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
			}
		}
		saltarTorre = true;
	}

	private void moverPrimeraTorre(int fila, int columna, boolean esLargo) {
		Pieza piezaAMover;
		if (saltarTorre) {
			piezaAMover = enroqueTorre(esLargo, tablero[fila][columna]);
			if (piezaAMover != null) {
				tablero[fila][columna] = new Vacio("_", fila, columna);
				tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
			}
		}
		saltarTorre = false;
	}

	private String elegirPieza(String pieza) {
		String piezaMover = pieza;
		if (pieza.isEmpty()) {
			pieza = "E";
			piezaMover = pieza;
		}
		if (!turno && !(pieza.equals("O-O") || pieza.equals("O-O-O"))) {
			piezaMover = pieza.toLowerCase();
		}
		return piezaMover;
	}

	public Pieza enroqueRey(boolean esLargo, Pieza rey) {

		if (esLargo) {
			rey.setColumna(2);
		} else {
			rey.setColumna(6);
		}

		seMueve = true;
		return rey;
	}

	public Pieza enroqueTorre(boolean esLargo, Pieza torre) {

		if (esLargo) {
			torre.setColumna(3);
		} else {
			torre.setColumna(5);
		}

		seMueve = true;
		return torre;
	}
}
