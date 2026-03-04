package p3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.FileNotFoundException;



public class PuntosDYV
{
static int []v;
	
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
	
	int pos1 = 0;
	int pos2 = 0;
	double distancia = Double.POSITIVE_INFINITY;

	//llamada recursiva n/2
	//llamada recursiva n/2

	//revisar limite inicial

	System.out.print("PUNTOS MÁS CERCANOS: ["+lista.get(pos1).x+","+lista.get(pos1).y+"] ["+lista.get(pos2).x+","+lista.get(pos2).y+"] \n");
	System.out.print("SU DISTANCIA MÍNIMA: "+distancia+"\n");
}  // main 

public recursiveSearch(lista, ) {

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

