# Dijkstra_algoritmo

### Descripción
Implementación del algoritmo de dijkstra para la búsqueda de la ruta más corta entre los vértices de un grafo. 

### Construcción / Modificación

``` bash
# creación de la lista de los vértices y sus conexiones
List<Nodo> lista_nodos = new ArrayList<Nodo>();
		
ArrayList<Conexion> t0 = new ArrayList<Conexion>();
Conexion t0_c1 = new Conexion(2, 0.089931);
t0.add(t0_c1);
Conexion t0_c2 = new Conexion(3, 0.119069);
t0.add(t0_c2);

Nodo t0_n1 = new Nodo(1, 1, -41.489356, -72.89595, t0);
lista_nodos.add(t0_n1);

ArrayList<Conexion> t1 = new ArrayList<Conexion>();
Conexion t1_c1 = new Conexion(1, 0.089931);
t1.add(t1_c1);

Nodo t1_n1 = new Nodo(2, 1, -41.490072, -72.895757, t1);
lista_nodos.add(t1_n1);

ArrayList<Conexion> t2 = new ArrayList<Conexion>();
Conexion t2_c1 = new Conexion(1, 0.119069);
t2.add(t2_c1);

Nodo t2_n1 = new Nodo(3, 1, -41.490168, -72.895816, t2);
lista_nodos.add(t2_n1);

# creación del array con los id de los vertices y de la matriz de adyacencia con los pesos de los vertices
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

# generación de la matriz inicial
Grafo a = new Grafo(vertices, matriz_adyacencia);

System.out.print("Indices:\n\n"+ a.toString_indices() +"\n");
System.out.print("Matriz Inicial:\n\n"+ a.toString_matriz_inicial() +"\n");

# obtención de la ruta más corta por medio del metodo camino_mas_corto(id_vertice_origen,id_vertice_destino)
Direccion test = a.camino_mas_corto(1, 2);

if(test.getDistancia() == 0.0 && test.getRuta().isEmpty()){
    System.out.print("No se pudo encontrar el camino más corto, verifique si el "+'"'+"vértice de origen"+'"'+" y el "+'"'+"vértice de fin"+'"'+" están correctos.\n\n");
}else{
    System.out.print("Orden:\n\n"+ a.toString_orden() +"\n");
    System.out.print("Grafo Resultante:\n\n"+ a.toString_resultados() +"\n");
    System.out.print("Direcciones:\n\n"+ a.toString_direcciones() +"\n");
    
    System.out.print("Distancia:\n\n"+ test.getDistancia() +"\n\n");
    System.out.print("Ruta:\n\n"+ test.getRuta() +"\n\n");
    }
}
```