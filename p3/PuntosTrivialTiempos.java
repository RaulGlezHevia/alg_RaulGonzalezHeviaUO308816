package p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; 
import java.io.IOException;
import java.io.FileNotFoundException;


public class PuntosTrivialTiempos
{
static int []v;
	
// main para probar funcionamiento y medir tiempos
public static void main (String arg []) 
{
	for (int n=1024;n<1200000;n*=2) {
	List<double[]> lista = generarStructura(n);
	int pos1 = 0;
	int pos2 = 0;
	double distancia = Double.POSITIVE_INFINITY;
	
	long t1,t2;
	t1= System.currentTimeMillis();
	for (int i = 0; i<lista.size(); i++) {
		for (int j = 0; j<lista.size();j++) {
			if (i!=j) {
				double distanciaLocal = compararPos(lista.get(i), lista.get(j));
				if (distanciaLocal<distancia) {
				pos1 = i;
				pos2 = j;
				distancia = distanciaLocal;
				}
			}
		}
	}
	t2= System.currentTimeMillis();
	System.out.println ("n = "+n+" | TIEMPO= "+(t2-t1)+ " ms"); 

	System.out.print("PUNTOS MÁS CERCANOS: ["+lista.get(pos1)[0]+","+lista.get(pos1)[1]+"] ["+lista.get(pos2)[0]+","+lista.get(pos2)[1]+"] \n");
	System.out.print("SU DISTANCIA MÍNIMA: "+distancia+"\n");
	
	
	}
}  // main 
	

public static double compararPos(double[] pos1, double[] pos2) {
	return (Math.sqrt(Math.pow(pos1[0]-pos2[0], 2)+Math.pow(pos1[1]-pos2[1], 2)));
}

public static List<double[]> generarStructura(int n){
	List<double[]> lista = new ArrayList<double[]>();
	for (int i = 0 ; i<=n; i++) {
		double x = Math.random()*100;
		double y = Math.random()*100;
		double[] tupla = {x, y};
		lista.add(tupla);
	}
	return lista;
}   
	


}  //  clase 
