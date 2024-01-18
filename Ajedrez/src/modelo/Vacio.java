package modelo;


public class Vacio extends Pieza{
	public Vacio(String pieza, int fila, int columna) {
		super(pieza, fila, columna);
	}
	@Override
	public Pieza mover(int fila, char columna, boolean capturar) {
		System.out.println("Aqui, aqui, aqui, aqui no hay quien viva");
		return null;
	}
}
