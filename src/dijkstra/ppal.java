package dijkstra;

import java.util.ArrayList;
import java.util.List;


public class ppal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Nodo> lista_nodos = new ArrayList<Nodo>();

		
		ArrayList<Conexion> t0 = new ArrayList<Conexion>();
		Conexion t0_c1 = new Conexion(1, 16.0);
		t0.add(t0_c1);
		Conexion t0_c2 = new Conexion(2, 10.0);
		t0.add(t0_c2);
		Conexion t0_c3 = new Conexion(3, 5.0);
		t0.add(t0_c3);
		
		Nodo t0_n1 = new Nodo(0, 3, -41.0, -72.0, t0);
		lista_nodos.add(t0_n1);
		
		ArrayList<Conexion> t1 = new ArrayList<Conexion>();
		Conexion t1_c1 = new Conexion(0, 16.0);
		t1.add(t1_c1);
		Conexion t1_c2 = new Conexion(2, 2.0);
		t1.add(t1_c2);
		Conexion t1_c3 = new Conexion(5, 4.0);
		t1.add(t1_c3);
		Conexion t1_c4 = new Conexion(6, 6.0);
		t1.add(t1_c4);
		
		Nodo t1_n1 = new Nodo(1, 3, -41.1, -72.1, t1);
		lista_nodos.add(t1_n1);
		
		ArrayList<Conexion> t2 = new ArrayList<Conexion>();
		Conexion t2_c1 = new Conexion(0, 10.0);
		t2.add(t2_c1);
		Conexion t2_c2 = new Conexion(1, 2.0);
		t2.add(t2_c2);
		Conexion t2_c3 = new Conexion(3, 4.0);
		t2.add(t2_c3);
		Conexion t2_c4 = new Conexion(4, 10.0);
		t2.add(t2_c4);
		Conexion t2_c5 = new Conexion(5, 12.0);
		t2.add(t2_c5);
		
		Nodo t2_n1 = new Nodo(2, 3, -41.2, -72.2, t2);
		lista_nodos.add(t2_n1);	
		
		ArrayList<Conexion> t3 = new ArrayList<Conexion>();
		Conexion t3_c1 = new Conexion(0, 5.0);
		t3.add(t3_c1);
		Conexion t3_c2 = new Conexion(2, 4.0);
		t3.add(t3_c2);
		Conexion t3_c3 = new Conexion(4, 15.0);
		t3.add(t3_c3);
		
		Nodo t3_n1 = new Nodo(3, 3, -41.3, -72.3, t3);
		lista_nodos.add(t3_n1);
		
		ArrayList<Conexion> t4 = new ArrayList<Conexion>();
		Conexion t4_c1 = new Conexion(2, 10.0);
		t4.add(t4_c1);
		Conexion t4_c2 = new Conexion(3, 15.0);
		t4.add(t4_c2);
		Conexion t4_c3 = new Conexion(5, 3.0);
		t4.add(t4_c3);
		Conexion t4_c4 = new Conexion(7, 5.0);
		t4.add(t4_c4);
		
		Nodo t4_n1 = new Nodo(4, 3, -41.4, -72.4, t4);
		lista_nodos.add(t4_n1);
		
		ArrayList<Conexion> t5 = new ArrayList<Conexion>();
		Conexion t5_c1 = new Conexion(1, 4.0);
		t5.add(t5_c1);
		Conexion t5_c2 = new Conexion(2, 12.0);
		t5.add(t5_c2);
		Conexion t5_c3 = new Conexion(4, 3.0);
		t5.add(t5_c3);
		Conexion t5_c4 = new Conexion(6, 8.0);
		t5.add(t5_c4);
		Conexion t5_c5 = new Conexion(7, 16.0);
		t5.add(t5_c5);
		
		Nodo t5_n1 = new Nodo(5, 3, -41.5, -72.5, t5);
		lista_nodos.add(t5_n1);
		
		ArrayList<Conexion> t6 = new ArrayList<Conexion>();
		Conexion t6_c1 = new Conexion(1, 6.0);
		t6.add(t6_c1);
		Conexion t6_c2 = new Conexion(5, 8.0);
		t6.add(t6_c2);
		Conexion t6_c3 = new Conexion(7, 7.0);
		t6.add(t6_c3);
		
		Nodo t6_n1 = new Nodo(6, 3, -41.6, -72.6, t6);
		lista_nodos.add(t6_n1);
		
		ArrayList<Conexion> t7 = new ArrayList<Conexion>();
		Conexion t7_c1 = new Conexion(4, 5.0);
		t7.add(t7_c1);
		Conexion t7_c2 = new Conexion(5, 16.0);
		t7.add(t7_c2);
		Conexion t7_c3 = new Conexion(6, 7.0);
		t7.add(t7_c3);
		
		Nodo t7_n1 = new Nodo(7, 3, -41.7, -72.7, t7);
		lista_nodos.add(t7_n1);
		
		
		Vertice[] vertices = new Vertice[lista_nodos.size()];
		for(int i = 0; i < vertices.length; i++){
			vertices[i] = new Vertice(lista_nodos.get(i).getId_nodo());
			
			List<Conexion> con = lista_nodos.get(i).getConexiones();
			for(int x = 0; x < con.size(); x++){
				vertices[i].add_arista(lista_nodos.get(i).getId_nodo(), con.get(x).getDestino(), con.get(x).getDistancia());
			}
		}
		
		double[][] matriz_adyacencia = new double[vertices.length][vertices.length];
		for(int i = 0; i < vertices.length; i++){
			for(int j = 0; j < vertices.length; j++){
				boolean is_empty = false;
				double distancia = 0;
				
				ArrayList<Arista> arr = vertices[i].getAristas();
				for(int x = 0; x < arr.size(); x++){
					if(vertices[j].getId_vertice() == arr.get(x).getId_vertice_destino()){
						is_empty = true;
						distancia = arr.get(x).getDistancia();
					}
				}
				if(is_empty == true){
					matriz_adyacencia[i][j] = distancia; 
				}else{
					matriz_adyacencia[i][j] = 0;
				}
			}
		}
		
		
		Grafo a = new Grafo(vertices, matriz_adyacencia);
		
		System.out.print("Indices:\n\n"+ a.toString_indices() +"\n");
		System.out.print("Grafo:\n\n"+ a.toString_grafo() +"\n");
		
		Direccion test = a.camino_mas_corto(3, 0);
		
		System.out.print("Orden:\n\n"+ a.toString_orden() +"\n");
		System.out.print("Grafo Resultante:\n\n"+ a.toString_resultados() +"\n");
		System.out.print("Distancia:\n\n"+ test.getDistancia() +"\n\n");
		System.out.print("Ruta:\n\n"+ test.getRuta() +"\n\n");
		System.out.print("Direcciones:\n\n"+ a.toString_direcciones() +"\n");
		System.out.print("Direcciones copy:\n\n"+ a.toString_direcciones_copy() +"\n");
	}

}
