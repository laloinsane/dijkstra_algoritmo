package dijkstra;

public class Point {

	private double distancia;
	private int desde_donde;
	
	public Point(){
		distancia = Double.POSITIVE_INFINITY;
		desde_donde = 0;
	}
	
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public int getDesde_donde() {
		return desde_donde;
	}
	public void setDesde_donde(int desde_donde) {
		this.desde_donde = desde_donde;
	}
	public void setValues(double distancia, int desde_donde){
		this.distancia = distancia;
		this.desde_donde = desde_donde;
	}
}
