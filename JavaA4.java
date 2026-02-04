package p0 ;
import java.util.ArrayList;
public class JavaA4
{

public static void primoA4(int m)
{
ArrayList<Boolean> lista = new ArrayList<Boolean>();
for (int i=0;i<m+1;i++) {
   lista.add(true);
}

int x = 2;
while (Math.pow(x, 2)<=m) {
   if (lista.get(x)) {
      int paso = 2*x;
      while (paso<=m) {
         lista.remove(paso);
         lista.add(paso, false);
         paso=paso+x;
      } 
      x+=1;
   }
}

ArrayList<Integer> salida = new ArrayList<Integer>();
int contPrimos=0;
for (int i=2;i<=m;i++) {
   if (lista.get(i)) {
      salida.add(i);
      contPrimos+=1;
   }

}
System.out.println("Hay "+contPrimos+" primos hasta "+m); 
}


public static void main (String arg [] )
{

System.out.println("TIEMPO EN JAVA DEL ALGORITMO A1");

long t1,t2;                  //obligatoriamente de tipo long para no desbordar
// la toma de tiempos en Java se vera con mas profundidad en la sesion siguiente


for (int n=5000; n<=1000000; n*=2)
   {
    t1=System.currentTimeMillis();	// milisegundos (sin decimales) 

    primoA4(n); 

    t2=System.currentTimeMillis();	

    System.out.println(t1+"///"+t2);

    System.out.println ("n="+n+"**** tiempo = "+(t2-t1)+" milisegundos \n");
    
   }
} // de main

}  // de clase

