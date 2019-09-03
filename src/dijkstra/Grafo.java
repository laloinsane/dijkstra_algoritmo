package dijkstra;

import java.util.ArrayList;

public class Grafo {

	private Vertice[] vertices;
	private double[][] matriz_inicial;
	private Point[][] resultados;
	private ArrayList<Integer> orden;
	private Direccion direcciones[];
	
	public Grafo(Vertice[] vertices, double[][] matriz_inicial){
		this.vertices = vertices;
		this.matriz_inicial = matriz_inicial;
		orden = new ArrayList<Integer>();
	}
		
	public Direccion camino_mas_corto(int id_vertice_origen, int id_vertice_destino){
		if(existeId(id_vertice_origen) == true && existeId(id_vertice_destino) == true && id_vertice_origen != id_vertice_destino){
			for(int fila = 0; fila < vertices.length; fila++){
				if(vertices[fila].getId_vertice() == id_vertice_origen){
					id_vertice_origen = fila;
				}
			}
			
			resultados = new Point[vertices.length-1][vertices.length];
			orden.add(id_vertice_origen);
			direcciones = new Direccion[vertices.length-1];
			
			for(int i=0; i<vertices.length-1; i++){
				Point point = new Point();
				int posicion_distancia_mas_baja = -1;
				
				for(int j=0; j<vertices.length; j++){
					resultados[i][j]= new Point();
					
					//si ArrayLis orden contiene j
					if(orden.contains(j)){
						resultados[i][j].setDistancia(0);
					}else{
						//si el valor[S][j] matriz_inicial es 0 && orden != 1 && != infinito
						if(matriz_inicial[orden.get(orden.size()-1)][j] == 0.0){
							if(orden.size() != 1 && resultados[i-1][j].getDistancia() != Double.POSITIVE_INFINITY){
								resultados[i][j].setValues(resultados[i-1][j].getDistancia(), resultados[i-1][j].getDesde_donde());
								if(resultados[i][j].getDistancia() < point.getDistancia()){
									point.setValues(resultados[i][j].getDistancia(), resultados[i][j].getDesde_donde());
									posicion_distancia_mas_baja=j;
								}
							}	
						}else{
							//si el valor[S][j] matriz_inicial es != 0
							if(orden.size() == 1){
								resultados[i][j].setValues(matriz_inicial[orden.get(orden.size()-1)][j], orden.get(orden.size()-1));
								if(resultados[i][j].getDistancia() < point.getDistancia()){
									point.setValues(resultados[i][j].getDistancia(), resultados[i][j].getDesde_donde());
									posicion_distancia_mas_baja=j;
								}
							}else{
								if(matriz_inicial[orden.get(orden.size()-1)][j]+lastDistanciaMinima() < resultados[i-1][j].getDistancia()){
									resultados[i][j].setValues(matriz_inicial[orden.get(orden.size()-1)][j]+lastDistanciaMinima(), orden.get(orden.size()-1));
									if(resultados[i][j].getDistancia() < point.getDistancia()){
										point.setValues(resultados[i][j].getDistancia(), resultados[i][j].getDesde_donde());	
										posicion_distancia_mas_baja=j;
									}
								}else{
									resultados[i][j].setValues(resultados[i-1][j].getDistancia(), resultados[i-1][j].getDesde_donde());
									if(resultados[i][j].getDistancia() < point.getDistancia()){
										point.setValues(resultados[i][j].getDistancia(), resultados[i][j].getDesde_donde());
										posicion_distancia_mas_baja=j;
									}
								}
							}
						}
					}
				}
				orden.add(posicion_distancia_mas_baja);
				setDireccion(point, i, posicion_distancia_mas_baja);
			}
			
			Direccion result = new Direccion();
			for(int i = 0; i < direcciones.length; i++){
				if(direcciones[i].getLastRuta() == id_vertice_destino){
					result.setDistancia(direcciones[i].getDistancia());
					result.setRutaCompleta(direcciones[i].getRuta());
				}
			}
			return result;
		}else{
			Direccion result = new Direccion();
			return result;
		}
	}
	
	public void setDireccion(Point point, int i, int posicion_distancia_mas_baja){
		boolean agregar_ruta = false;
		for(int x=0; x<direcciones.length && agregar_ruta==false;x++){
			if(direcciones[x] != null){
				if(direcciones[x].getLastRuta() == vertices[point.getDesde_donde()].getId_vertice()){
				direcciones[i]=new Direccion();
					ArrayList<Integer> ruta = direcciones[x].getRuta();
					for (int y = 0; y < ruta.size(); y++) {
						direcciones[i].setRuta(ruta.get(y));
					}
					agregar_ruta = true;
				}
			}
		}
		
		if(agregar_ruta == false){
			direcciones[i]=new Direccion();
			direcciones[i].setRuta(vertices[point.getDesde_donde()].getId_vertice());
		}
		
		direcciones[i].setDistancia(point.getDistancia());
		direcciones[i].setRuta(vertices[posicion_distancia_mas_baja].getId_vertice());
	}
	
	public double lastDistanciaMinima(){
		double last=0;
		for(int i=0;i<direcciones.length;i++){
			if(direcciones[i] != null)
				last=direcciones[i].getDistancia();
		}
		return last;	
	}
	
	public boolean existeId(int id){
		boolean existe = false;
		for(int fila = 0; fila < vertices.length; fila++){
			if(vertices[fila].getId_vertice() == id){
				existe = true;
			}
		}
		return existe;
	}
	
	public String toString_indices(){
		String salida="";
		for(int i=0;i<vertices.length;i++){
			salida+="|"+vertices[i].getId_vertice()+"|";
		}
		return salida+"\n";	
	}
	
	public String toString_matriz_inicial(){
		String salida="";
		for(int i=0;i<matriz_inicial.length;i++){
			for(int j=0;j<matriz_inicial.length;j++){
					salida+="|"+matriz_inicial[i][j]+"|";
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
		if(resultados != null){
			for(int i=0;i<resultados.length;i++){
				for(int j=0;j<resultados.length+1;j++){
						salida+="|"+ resultados[i][j].getDistancia()+"("+resultados[i][j].getDesde_donde()+")|";
				}
				salida+="\n";
			}
		}
		return salida;	
	}
	
	public String toString_direcciones(){
		String salida="";
		if(direcciones != null){
			for(int i=0;i<direcciones.length;i++){
				if(direcciones[i] != null)
					salida+="|"+direcciones[i].getDistancia()+" "+direcciones[i].getRuta()+"|";
			}
		}
		return salida+"\n";	
	}
}
