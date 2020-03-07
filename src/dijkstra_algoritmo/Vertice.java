package dijkstra_algoritmo;

import java.util.ArrayList;

public class Vertice {

	private int id_vertice;
	private ArrayList<Arista> aristas;

	public Vertice(int id_vertice){
		this.id_vertice = id_vertice;
		aristas = new ArrayList<Arista>();
	}
	
	public int getId_vertice() {
		return id_vertice;
	}

	public void setId_vertice(int id_vertice) {
		this.id_vertice = id_vertice;
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(ArrayList<Arista> aristas) {
		this.aristas = aristas;
	}
	
	public void add_arista(int origen, int destino, double distancia){
		Arista arista = new Arista(origen, destino, distancia);
		aristas.add(arista);
	}
	
}
