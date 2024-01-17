package modelo;

public abstract class Pieza {
	private String pieza;
	private boolean isBlanca;
	private int fila;
	private int columna;

	public Pieza(String pieza, boolean isBlanca, int fila, int columna) {
		this.columna = columna;
		this.fila = fila;
		this.isBlanca = isBlanca;
		if(!isBlanca) {
			this.pieza = pieza.toLowerCase();
		}else {
			this.pieza = pieza;
		}
	}
	public Pieza(String pieza, int fila, int columna) {
		this.columna = columna;
		this.fila = fila;
		this.pieza = pieza;
	}
	public abstract Pieza mover(char fila, int columna, boolean capturar);
	
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public String getPieza() {
		return pieza;
	}
	public void setPieza(String pieza) {
		this.pieza = pieza;
	}
	public boolean isBlanca() {
		return isBlanca;
	}
	public void setBlanca(boolean isBlanca) {
		this.isBlanca = isBlanca;
	}
	
}
