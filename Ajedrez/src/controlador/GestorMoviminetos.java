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

	public Pieza[][] moverPiezaProvisional(char filaInicial, int columnaInicial, char filaFinal, int columnaFinal) {
		Traductor tr = new Traductor();
		Pieza piezaAMover;
		int fila;
		boolean puedeCapturar;
		for (char f = 'a'; f < 'i'; f++) {
			for (int columna = 0; columna < tablero.length; columna++) {
				fila = tr.charToInt(f);
				if (filaInicial == f && columnaInicial - 1 == columna) {
					// Sutituir 'f' y 1 por los valores del fichero de jugadas
					puedeCapturar = !tablero[filaFinal][columnaFinal - 1].getPieza().contains("_");
					piezaAMover = tablero[fila][columna].mover(filaFinal, columnaFinal - 1, puedeCapturar);
					if (piezaAMover == null) {
						System.err.println("Jugada no valida");
						return tablero;
					}
					tablero[fila][columna] = new Vacio("_", fila, columna);
					tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
				}
			}
		}
		return tablero;
	}

	public Pieza[][] moverPieza(String pieza, char filaFinal, int columnaFinal) {
		System.out.println("Inicio mover pieza " + turno);
		Traductor tr = new Traductor();
		Pieza piezaAMover;
		int fila, filaCapturar;
		seMueve = false;
		boolean puedeCapturar;
		String piezaMover = elegirPieza(pieza);
//		if(tableroObj.retaguardiaOcupada(turno)) {
//			System.out.println("No se puede hacer el enroque");
//		}
		if((pieza.equals("O-O-O") && turno) || (pieza.equals("O-O") && !turno)) {
			saltarTorre = false;
		}else if ((pieza.equals("O-O-O") && !turno) || (pieza.equals("O-O") && turno)){
			saltarTorre = true;
		}
		for (char f = 'a'; f < 'i'; f++) {
			for (int columna = 0; columna < tablero.length; columna++) {
				fila = tr.charToInt(f);
				switch (piezaMover) {
				case "O-O":
					if (turno) {
						if (tablero[fila][columna].getPieza().equals("K")) {
							moverRey(fila, false, columna);
						}
						if (tablero[fila][columna].getPieza().equals("R")) {
							moverPrimeraTorre(fila, columna, false);
						}
					} else {
						if (tablero[fila][columna].getPieza().equals("k")) {
							moverRey(fila, false, columna);
						}
						if (tablero[fila][columna].getPieza().equals("r")) {
							moverSegundaTorre(fila, columna, false);
						}
					}
					break;
				case "O-O-O":
					if (turno) {
						if (tablero[fila][columna].getPieza().equals("K")) {
							moverRey(fila, true, columna);
						}
						if (tablero[fila][columna].getPieza().equals("R")) {
							moverSegundaTorre(fila, columna, true);
						}
					} else {
						if (tablero[fila][columna].getPieza().equals("k")) {
							moverRey(fila, true, columna);
						}
						if (tablero[fila][columna].getPieza().equals("r")) {
							moverPrimeraTorre(fila, columna, true);
						}
					}
					break;
				default:
					if (tablero[fila][columna].getPieza().equals(piezaMover)) {
						filaCapturar = tr.charToInt(filaFinal);
						puedeCapturar = !tablero[filaCapturar][columnaFinal - 1].getPieza().contains("_");
						piezaAMover = tablero[fila][columna].mover(filaFinal, columnaFinal - 1, puedeCapturar);
						if (piezaAMover != null) {
							tablero[fila][columna] = new Vacio("_", fila, columna);
							tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
							seMueve = true;
							turno = !turno;
							System.out.println("Final mover pieza " + turno);
							return tablero;
						}
					}
				}
			}
		}
		turno = !turno;
		System.out.println("Final mover pieza " + turno);
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
		if (turno) {
			if (esLargo) {
				rey.setColumna(6);
			} else {
				rey.setColumna(1);
			}
		} else {
			if (esLargo) {
				rey.setColumna(1);
			} else {
				rey.setColumna(6);
			}
		}
		seMueve = true;
		return rey;
	}

	public Pieza enroqueTorre(boolean esLargo, Pieza torre) {

		if (turno) {
			if (esLargo) {
				torre.setColumna(5);
			} else {
				torre.setColumna(2);
			}
		} else {
			if (esLargo) {
				torre.setColumna(2);
			} else {
				torre.setColumna(5);
			}
		}

		seMueve = true;
		return torre;
	}
}
