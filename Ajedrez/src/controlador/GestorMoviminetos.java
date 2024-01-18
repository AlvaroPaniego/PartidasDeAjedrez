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

	public Pieza[][] peonCaptura(String pieza, char columnaFinal, int filaFinal, char columnaInicial) {
		System.out.println("Inicio mover pieza " + turno);
		Traductor tr = new Traductor();
		Pieza piezaAMover;
		int columnaFinalComparar = tr.charToInt(columnaFinal), columnaInicialComparar = tr.charToInt(columnaInicial);
		seMueve = false;
		String piezaMover = elegirPieza(pieza);
		System.out.println(piezaMover);
		for (int fila = tablero.length-1; fila >= 0 ; fila++) {
			System.out.println(tablero[fila][columnaInicialComparar].getPieza());
			if (tablero[fila][columnaInicialComparar].getPieza().equals(piezaMover)) {
				System.out.println("igual se mueve");
				piezaAMover = tablero[fila][columnaInicialComparar].mover(columnaFinalComparar, columnaInicial, true);
				System.out.println(piezaAMover.getPieza());
				if (piezaAMover != null) {
					System.out.println("se puede mover");
					System.out.println(fila + " " + columnaFinalComparar);
					tablero[fila][columnaInicialComparar] = new Vacio("_", fila, columnaInicialComparar);
					System.out.println(tablero[fila][columnaInicialComparar].getPieza());
					tablero[piezaAMover.getFila()][piezaAMover.getColumna()] = piezaAMover;
					System.out.println(piezaAMover.getFila() + " " + piezaAMover.getColumna());
					System.out.println(tablero[piezaAMover.getFila()][piezaAMover.getColumna()].getPieza());
					seMueve = true;
					turno = !turno;
					System.out.println("Final mover pieza " + turno);
					return tablero;
				}
			}
		}
//		for (char f = 'a'; f < 'i'; f++) {
//			for (int columna = 0; columna < tablero.length; columna++) {
//				fila = tr.charToInt(f);
//			}
//		}

		turno = !turno;
		System.out.println("Final mover pieza " + turno);
		if (!seMueve) {
			System.out.println(Colores.RED + "Jugada no valida" + Colores.RESET);
		}
		seMueve = false;
		return tablero;
	}

	public Pieza[][] moverPieza(String pieza, char columnaFinal, int filaFinal) {
		System.out.println("Inicio mover pieza " + turno);
		Traductor tr = new Traductor();
		Pieza piezaAMover;
		int columna;
		seMueve = false;
		boolean puedeCapturar;
		String piezaMover = elegirPieza(pieza);
//		if(tableroObj.retaguardiaOcupada(turno)) {
//			System.out.println("No se puede hacer el enroque");
//		}
		if ((pieza.equals("O-O-O") && turno) || (pieza.equals("O-O") && !turno)) {
			saltarTorre = false;
		} else if ((pieza.equals("O-O-O") && !turno) || (pieza.equals("O-O") && turno)) {
			saltarTorre = true;
		}
		for (int f = tablero.length - 1; f >= 0; f--) {
			for (char col = 'a'; col < 'i'; col++) {
				puedeCapturar = false;
				columna = tr.charToInt(col);
				switch (piezaMover) {
				case "O-O":
					if (turno) {
						if (tablero[f][columna].getPieza().equals("K")) {
							moverRey(columna, false, f);
						}
						if (tablero[f][columna].getPieza().equals("R")) {
							moverPrimeraTorre(columna, f, false);
						}
					} else {
						if (tablero[f][columna].getPieza().equals("k")) {
							moverRey(columna, false, f);
						}
						if (tablero[f][columna].getPieza().equals("r")) {
							moverSegundaTorre(columna, f, false);
						}
					}
					break;
				case "O-O-O":
					if (turno) {
						if (tablero[f][columna].getPieza().equals("K")) {
							moverRey(columna, true, f);
						}
						if (tablero[f][columna].getPieza().equals("R")) {
							moverSegundaTorre(columna, f, true);
						}
					} else {
						if (tablero[f][columna].getPieza().equals("k")) {
							moverRey(columna, true, f);
						}
						if (tablero[f][columna].getPieza().equals("r")) {
							moverPrimeraTorre(columna, f, true);
						}
					}
					break;
				default:
					if (tablero[f][columna].getPieza().equals(piezaMover)) {
//						columnaCapturar = tr.charToInt(columnaFinal);
//						System.out.println(columnaCapturar);
//						puedeCapturar = !(tablero[filaFinal][columnaCapturar - 1].getPieza().contains("_") && tablero[filaFinal][columnaCapturar + 1].getPieza().contains("_"));
						piezaAMover = tablero[f][columna].mover(filaFinal - 1, columnaFinal, puedeCapturar);
						if (piezaAMover != null) {
							tablero[f][columna] = new Vacio("_", columna, f);
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
