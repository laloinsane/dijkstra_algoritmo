package dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	private Vertice[] vertices;
	private double[][] matriz_adyacencia;
	private ArrayList<Integer> orden;
	private Point resultados[][];
	private Direccion direcciones[];
	private Direccion direcciones_copy[];
	
	public Grafo(Vertice[] vertices, double[][] matriz_adyacencia){
		this.vertices = vertices;
		this.matriz_adyacencia = matriz_adyacencia;
		
		orden = new ArrayList<Integer>();
		
		resultados=new Point[vertices.length][vertices.length];
		for(int j=0;j<vertices.length;j++)
			for(int k=0;k<vertices.length;k++)
				resultados[j][k]= new Point();
		
		direcciones=new Direccion[vertices.length-1];
		for(int j=0;j<vertices.length-1;j++)
			direcciones[j]= new Direccion();
		
		direcciones_copy=new Direccion[vertices.length-1];
		for(int j=0;j<vertices.length-1;j++)
			direcciones_copy[j]= new Direccion();
	}
	
	public Direccion camino_mas_corto(int id_vertice_origen, int id_vertice_destino){
		for(int i = 0; i < matriz_adyacencia.length; i++){
			if(i == 0){
				
				for(int fila = 0; fila < vertices.length; fila++)
					if(vertices[fila].getId_vertice() == id_vertice_origen)
						id_vertice_origen = fila;
				
				orden.add(id_vertice_origen);
				
				double infinito = 1000000;
				int position_start = 100;
				int position_end = 100;
				
				for(int j=0;j<matriz_adyacencia.length;j++){
					if(id_vertice_origen == j){
						resultados[i][j].setDistancia(88);
						resultados[i][j].setDesde_donde(88);
					}else{
						if(matriz_adyacencia[id_vertice_origen][j] == 0.0){
							resultados[i][j].setDistancia(99);
							resultados[i][j].setDesde_donde(99);
						}else{
							if(matriz_adyacencia[id_vertice_origen][j] != 0.0){
								resultados[i][j].setDistancia(matriz_adyacencia[id_vertice_origen][j]);
								resultados[i][j].setDesde_donde(id_vertice_origen);
								
								if(resultados[i][j].getDistancia() < infinito){
									infinito = resultados[i][j].getDistancia();
									position_start = resultados[i][j].getDesde_donde();
									position_end = j;
								}
								
							}
						}
					}
				}
				
				direcciones[i].setDistancia(infinito);
				direcciones[i].setRuta(position_start);
				direcciones[i].setRuta(position_end);
				
				direcciones_copy[i].setDistancia(infinito);
				direcciones_copy[i].setRuta(vertices[position_start].getId_vertice());
				direcciones_copy[i].setRuta(vertices[position_end].getId_vertice());
			
			}else{
				if(i < matriz_adyacencia.length-1){
					orden.add(direcciones[i - 1].getLastRuta());
					
					double infinito = 100;
					int position_start = 100;
					int position_end = 100;
					int position_actual = 100;
					
					for(int j=0;j<matriz_adyacencia.length;j++){
						if(orden.contains(j)){
							resultados[i][j].setDistancia(88);
							resultados[i][j].setDesde_donde(88);
						}else{
							if(matriz_adyacencia[direcciones[i - 1].getLastRuta()][j] == 0.0){
								resultados[i][j].setDistancia(resultados[i-1][j].getDistancia());
								resultados[i][j].setDesde_donde(resultados[i-1][j].getDesde_donde());
								if(resultados[i][j].getDistancia() < infinito){
									infinito = resultados[i][j].getDistancia();
									position_end = j;
									position_actual = resultados[i][j].getDesde_donde();
								}
							}else{
								if(matriz_adyacencia[direcciones[i - 1].getLastRuta()][j] != 0.0){
									if(direcciones[i - 1].getDistancia()+matriz_adyacencia[direcciones[i - 1].getLastRuta()][j] > resultados[i-1][j].getDistancia()){
										resultados[i][j].setDistancia(resultados[i-1][j].getDistancia());
										resultados[i][j].setDesde_donde(resultados[i-1][j].getDesde_donde());
										
										if(resultados[i][j].getDistancia() < infinito){
											infinito = resultados[i][j].getDistancia();
											position_end = j;
											position_actual = resultados[i][j].getDesde_donde();
										}
									}else{
										resultados[i][j].setDistancia(direcciones[i - 1].getDistancia()+matriz_adyacencia[direcciones[i - 1].getLastRuta()][j]);
										resultados[i][j].setDesde_donde(direcciones[i - 1].getLastRuta());
										if(resultados[i][j].getDistancia() < infinito){
											infinito = resultados[i][j].getDistancia();
											position_end = j;
											position_actual = resultados[i][j].getDesde_donde();
										}
									}
								}	
							}
						}
					}
					direcciones[i].setDistancia(infinito);
					
					direcciones_copy[i].setDistancia(infinito);
					
					boolean incorporo_ruta = false;
						for(int x = 0; x < i; x++){
							if(direcciones[x].getLastRuta() == position_actual){
								ArrayList<Integer> ruta_nueva = direcciones[x].getRuta();
								
								ArrayList<Integer> ruta_nueva_x = direcciones_copy[x].getRuta();
								 for (int y = 0; y <= ruta_nueva.size() - 1; y++) {
									 direcciones[i].setRuta(ruta_nueva.get(y));
									 
									 direcciones_copy[i].setRuta(ruta_nueva_x.get(y));
								 }
								 incorporo_ruta = true;
							}
						}
						if(incorporo_ruta == false){
							direcciones[i].setRuta(position_actual);
							
							direcciones_copy[i].setRuta(vertices[position_actual].getId_vertice());
						}
					direcciones[i].setRuta(position_end);
					
					direcciones_copy[i].setRuta(vertices[position_end].getId_vertice());
				}
			}
		}
		
		Direccion ejemplo = new Direccion();
		for(int i = 0; i < direcciones_copy.length; i++){
			if(direcciones_copy[i].getLastRuta() == id_vertice_destino){
				ejemplo.setDistancia(direcciones_copy[i].getDistancia());
				ejemplo.setRutaCompleta(direcciones_copy[i].getRuta());
			}
		}
		
		return ejemplo;
		
	}
	
	public Direccion find_direccion(int id_vertice_origen, int id_vertice_destino, Direccion direcciones[]){
		for(int i = 0; i < direcciones.length; i++){
			if(direcciones[i].getLastRuta() == id_vertice_destino){
				return direcciones[i];
			}
		}
		return null;
	}
	
	public String toString_indices(){
		String salida="";
		for(int i=0;i<vertices.length;i++){
			salida+="|"+vertices[i].getId_vertice()+"|";
		}
		return salida+"\n";	
	}
	
	public String toString_grafo(){
		String salida="";
		for(int i=0;i<matriz_adyacencia.length;i++){
			for(int j=0;j<matriz_adyacencia.length;j++){
					salida+="|"+matriz_adyacencia[i][j]+"|";
			}
			salida+="\n";
		}
		return salida+"\n";	
	}
	
	public String toString_orden(){
		String salida="";
		for(int i=0;i<orden.size();i++){
			salida+="|"+orden.get(i)+"|";
		}
		return salida+"\n";	
	}
	
	public String toString_resultados(){
		String salida="";
		for(int i=0;i<resultados.length;i++){
			for(int j=0;j<resultados.length;j++){
					salida+="|"+ resultados[i][j].getDistancia()+"("+resultados[i][j].getDesde_donde()+")|";
			}
			salida+="\n";
		}
		return salida;	
	}
	
	public String toString_direcciones(){
		String salida="";
		for(int i=0;i<direcciones.length;i++){
			salida+="|"+direcciones[i].getDistancia()+"-"+direcciones[i].getRuta()+"|";
		}
		return salida+"\n";	
	}
	
	public String toString_direcciones_copy(){
		String salida="";
		for(int i=0;i<direcciones_copy.length;i++){
			salida+="|"+direcciones_copy[i].getDistancia()+"-"+direcciones_copy[i].getRuta()+"|";
		}
		return salida+"\n";	
	}
}
