package dijkstra;

import java.util.ArrayList;

public class matriz {

	private ArrayList<Integer> S;
	private point resultados[][];
	private direccion direcciones[];
	
	matriz(double datos[][]){
		S = new ArrayList<Integer>();
		
		resultados=new point[datos.length][datos.length];
		for(int j=0;j<datos.length;j++)
			for(int k=0;k<datos.length;k++)
				resultados[j][k]= new point();
		
		direcciones=new direccion[datos.length-1];
		for(int j=0;j<datos.length-1;j++)
			direcciones[j]= new direccion();
	}
	
	public String toString_S(){
		String salida="";
		for(int i=0;i<S.size();i++){
			salida+="|"+S.get(i)+"|";
		}
		return salida+"\n";	
	}

	public String toString_resultados(){
		String salida="";
		for(int i=0;i<resultados.length;i++){
			for(int j=0;j<resultados.length;j++){
					salida+="|"+ resultados[j][i].getDistancia()+"("+resultados[j][i].getDesde_donde()+")|";
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
	
	public String dijkstra(int x, double datos[][]){
		int cont = 0;
		while(cont<datos.length-1){
			if(cont == 0){
				S.add(x);
				double infinito = 100;
				int position_start = 100;
				int position_end = 100;
				for(int j=0;j<datos.length;j++){
					if(x == j){
						resultados[j][cont].setDistancia(88);
						resultados[j][cont].setDesde_donde(88);
					}else{
						if(datos[j][x] == 0.0){
							resultados[j][cont].setDistancia(99);
							resultados[j][cont].setDesde_donde(99);
						}else{
							if(datos[j][x] != 0){
								resultados[j][cont].setDistancia(datos[j][x]);
								resultados[j][cont].setDesde_donde(x);
								if(resultados[j][cont].getDistancia() < infinito){
									infinito = resultados[j][cont].getDistancia();
									position_start = resultados[j][cont].getDesde_donde();
									position_end = j;
								}
							}
						}
					}
				}
				direcciones[cont].setDistancia(infinito);
				direcciones[cont].setRuta(position_start);
				direcciones[cont].setRuta(position_end);
			}else{
				if(cont < 7){
					S.add(direcciones[cont - 1].getLastRuta());
					double infinito = 100;
					int position_start = 100;
					int position_end = 100;
					int position_actual = 100;
					for(int j=0;j<datos.length;j++){
						if(S.contains(j)){
							resultados[j][cont].setDistancia(88);
							resultados[j][cont].setDesde_donde(88);
						}else{
							if(datos[j][direcciones[cont - 1].getLastRuta()] == 0){
								resultados[j][cont].setDistancia(resultados[j][cont-1].getDistancia());
								resultados[j][cont].setDesde_donde(resultados[j][cont-1].getDesde_donde());
								if(resultados[j][cont].getDistancia() < infinito){
									infinito = resultados[j][cont].getDistancia();
									position_end = j;
									position_actual = resultados[j][cont].getDesde_donde();
								}
							}else{
								if(datos[j][direcciones[cont - 1].getLastRuta()] != 0){
									if(direcciones[cont - 1].getDistancia()+datos[j][direcciones[cont - 1].getLastRuta()] > resultados[j][cont-1].getDistancia()){
										resultados[j][cont].setDistancia(resultados[j][cont-1].getDistancia());
										resultados[j][cont].setDesde_donde(resultados[j][cont-1].getDesde_donde());
										
										if(resultados[j][cont].getDistancia() < infinito){
											infinito = resultados[j][cont].getDistancia();
											position_end = j;
											position_actual = resultados[j][cont].getDesde_donde();
										}
									}else{
										resultados[j][cont].setDistancia(direcciones[cont - 1].getDistancia()+datos[j][direcciones[cont - 1].getLastRuta()]);
										resultados[j][cont].setDesde_donde(direcciones[cont - 1].getLastRuta());
										if(resultados[j][cont].getDistancia() < infinito){
											infinito = resultados[j][cont].getDistancia();
											position_end = j;
											position_actual = resultados[j][cont].getDesde_donde();
										}
									}
								}	
							}
						}
					}
					direcciones[cont].setDistancia(infinito);
					
					boolean incorporo_ruta = false;
						for(int i = 0; i < cont; i++){
							if(direcciones[i].getLastRuta() == position_actual){
								ArrayList<Integer> ruta_nueva = direcciones[i].getRuta();
								 for (int j = 0; j <= ruta_nueva.size() - 1; j++) {
									 direcciones[cont].setRuta(ruta_nueva.get(j));
								 }
								 incorporo_ruta = true;
							}
						}
						if(incorporo_ruta == false){
							direcciones[cont].setRuta(position_actual);
						}
					direcciones[cont].setRuta(position_end);
				}
			}
			cont++;
		}
		return "ejemplo\n";
	}
	
	
	//string matriz
	public String toString_datos(double datos[][]){
		String salida="";
		for(int i=0;i<datos.length;i++){
			for(int j=0;j<datos.length;j++){
					salida+="|"+datos[i][j]+"|";
			}
			salida+="\n";
		}
		return salida;	
	}
}
