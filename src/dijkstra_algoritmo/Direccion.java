package dijkstra_algoritmo;

import java.util.ArrayList;

public class Direccion {

	private double distancia_minima;
	private ArrayList<Integer> ruta = new ArrayList<Integer>();
	
	public double getDistancia() {
		return distancia_minima;
	}
	
	public void setDistancia(double distancia_minima) {
		this.distancia_minima = distancia_minima;
	}
	
	public ArrayList<Integer> getRuta() {
		return ruta;
	}
	
	public Integer getLastRuta() {
		int tamaño = ruta.size();
		return ruta.get(tamaño - 1);
	}
	
	public void setRuta(int x) {
		ruta.add(x);
	}
	
	public void setRutaCompleta(ArrayList<Integer> x) {
		ruta.addAll(x);
	}
	
	public boolean contains(int x) {
		return ruta.contains(x);
	}
	
	public boolean isLast(int x) {
		int tamaño = ruta.size();
		if(ruta.get(tamaño - 1) == x){
			return true;
		}else{
			return false;
		}
	}
	
	public int tamaño(){
		return ruta.size();
	}
}
