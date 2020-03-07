package dijkstra_algoritmo;

public class Arista {
	
	private int id_vertice_origen;
	private int id_vertice_destino;
	private double distancia;
	
	public Arista(int id_vertice_origen, int id_vertice_destino, double distancia){
		this.id_vertice_origen = id_vertice_origen;
		this.id_vertice_destino = id_vertice_destino;
		this.distancia = distancia;
	}

	public int getId_vertice_origen() {
		return id_vertice_origen;
	}

	public void setId_vertice_origen(int id_vertice_origen) {
		this.id_vertice_origen = id_vertice_origen;
	}

	public int getId_vertice_destino() {
		return id_vertice_destino;
	}

	public void setId_vertice_destino(int id_vertice_destino) {
		this.id_vertice_destino = id_vertice_destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
}
