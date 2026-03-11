package p3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;



public class PuntosDYV
{
static int []v;
static double x1 = 0;
static double x2 = 0;
static double y1 = 0;
static double y2 = 0;
static double distance = Double.POSITIVE_INFINITY;

// main para probar funcionamiento y medir tiempos
public static void main (String arg []) 
{
	List<Punto> lista = new ArrayList<Punto>();
	try {
		lista = leerArchivo(arg[0]);
	}
	catch (Exception e) {
		e.printStackTrace();
	}

	sortList(lista);

	int listaRealLastPos = lista.size()-1;

	recursiveSearch(lista.subList(listaRealLastPos/2,listaRealLastPos), 0, listaRealLastPos/2-1);
	recursiveSearch(lista.subList(0, listaRealLastPos/2), 0, listaRealLastPos/2-1);

	System.out.print("PUNTOS MÁS CERCANOS: ["+x1+","+y1+"] ["+x2+","+y2+"] \n");
	System.out.print("SU DISTANCIA MÍNIMA: "+distance+"\n");
}  // main 

private static void recursiveSearch(List<Punto> lista, int pos1, int pos2) {
	if (lista.size()==1) {
		Punto xy = lista.get(0);
		double localDistance1 = Math.sqrt((Math.pow(xy.x-x1, 2)+Math.pow(xy.y-y1, 2)));
		double localDistance2 = Math.sqrt((Math.pow(xy.x-x2, 2)+Math.pow(xy.y-y2, 2)));

		if (localDistance1<localDistance2 && localDistance1 < distance) {
			distance = localDistance1;
			x2=xy.x;
			y2=xy.y;
		}
		else if (localDistance2 < distance) {
			x1=xy.x;
			y1=xy.y;
		}
	}
	else if (lista.size()==2) {
		double xpos1 = lista.get(pos1).x;
		double xpos2 = lista.get(pos2).x;
		double ypos1 = lista.get(pos1).y;
		double ypos2 = lista.get(pos2).y;
		double localDistance = Math.sqrt((Math.pow(xpos1-xpos2, 2)+Math.pow(ypos1-ypos2, 2)));
		if (localDistance<distance) {
			distance = localDistance;
			x1 = xpos1;
			x2 = xpos2;
			y1 = ypos1;
			y2 = ypos2;
		}
	}
	else {
		recursiveSearch(lista.subList(pos2/2,pos2), 0, pos2/2);
		recursiveSearch(lista.subList(pos1, pos2/2), 0, pos2/2);
	}
}
	

private static void sortList(List<Punto> lista) {
	Collections.sort(lista);
}

public static List<Punto> leerArchivo(String filename) throws FileNotFoundException, IOException {
	List<Punto> list = new ArrayList<Punto>();
      
        // load data from file
        BufferedReader bf = new BufferedReader(
            new FileReader(filename));
      
        // read entire line as string
        String line = bf.readLine();
		
		line = bf.readLine();
        // checking for end of file
        while (line != null) {
			String[] split = line.split(",");
			Punto punto = new Punto(Double.valueOf(split[0]), Double.valueOf(split[1]));
            list.add(punto);
			line = bf.readLine();
        }
      
        // closing bufferreader object
        bf.close();
		return list;
}   





}  //  clase 

