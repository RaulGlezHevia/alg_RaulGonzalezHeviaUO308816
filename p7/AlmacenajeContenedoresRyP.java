import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlmacenajeContenedoresRyP {
    private int capacidadC;
    private Integer[] conjuntoS;
    private int mejorK;

    private Integer sumaConjuntoS = 0;

    private int numeroDeLlamadasRecursivas = 0;

    private List<List<Integer>> mejorDistribucion;

    public AlmacenajeContenedoresRyP(int capacidadC2, Integer[] conjuntoS2) {
        this.capacidadC = capacidadC2;
        this.conjuntoS = conjuntoS2;
        mejorK = Integer.MAX_VALUE;
        Arrays.sort(this.conjuntoS,Collections.reverseOrder());
        

        sumaConjuntoS = 0;
        for (int i = 0 ; i<conjuntoS.length ; i++) {
            sumaConjuntoS += conjuntoS[i];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner("test00.txt");
        int capacidadC = sc.nextInt();
        
        List<Integer> parts = new ArrayList<Integer>();

        while (sc.hasNextInt()) {
            parts.add(sc.nextInt());
        }
        Integer[] conjuntoS = new Integer[parts.size()];

        for (int i = 0; i<conjuntoS.length; i++) {
            conjuntoS[i] = parts.get(i);
        }
        sc.close();

        //int capacidadC = 10;
        //Integer[] conjuntoS = new Integer[] {7, 6, 5, 5, 4, 3, 2};
        
        

        AlmacenajeContenedoresRyP ac = new AlmacenajeContenedoresRyP(capacidadC, conjuntoS);
        ac.solve();
    }

    private void preSolve() {

        if (conjuntoS.length%2==0) {
            Integer[] tuplasDeIntegers = new Integer[conjuntoS.length/2];
            for (int i = 1; i<=conjuntoS.length; i+=2) {
                tuplasDeIntegers[i/2] = conjuntoS[i-1] + conjuntoS[i];
            }
            
            int kActual = 0;

            for (int i = 0; i<tuplasDeIntegers.length; i++) {
                if (tuplasDeIntegers[i]%capacidadC!=0) {
                    kActual+=(tuplasDeIntegers[i]/capacidadC) +1;
                }
                else {
                    kActual+=(tuplasDeIntegers[i]/capacidadC);
                }
            }

            this.mejorK = kActual;

        }

        else {
            Integer[] tuplasDeIntegers = new Integer[conjuntoS.length/2];
            for (int i = 1; i<=conjuntoS.length-1;i+=2) {
                tuplasDeIntegers[i/2] = conjuntoS[i-1] + conjuntoS[i];
            }

            int kActual = 0;

            for (int i = 0; i<tuplasDeIntegers.length; i++) {
                if (tuplasDeIntegers[i]%capacidadC!=0) {
                    kActual+=(tuplasDeIntegers[i]/capacidadC) +1;
                }
                else {
                    kActual+=(tuplasDeIntegers[i]/capacidadC);
                }
            }

            kActual+=1;

            this.mejorK = kActual;
        }
        
    }


    public void solve() {
        preSolve();
        backtracking(0, new ArrayList<List<Integer>>(), sumaTotal(conjuntoS));
        print();
    }

    public void print() {
        System.out.println("Lista de contenedores y objetos contenidos: ");
        for (int i = 0 ; i<mejorDistribucion.size();i++) {
            System.out.println("Contenedor "+i+": "+mejorDistribucion.get(i).toString());
        }
        System.out.println();
        System.out.println("El número de contenedores necesarios es "+mejorDistribucion.size());

        System.out.println();
        System.out.println("El número de llamadas recursivas necesarias son "+numeroDeLlamadasRecursivas);
    }

    private int sumaTotal(Integer[] lista) {
        int i = 0;
        for (Integer num : lista) {
            i+=num;
        }
        return i;
    }

    private void backtracking(int index, List<List<Integer>> contenedores, int sumaRestante) {
        numeroDeLlamadasRecursivas++;
        
        // LowerBound
        // calcular el numero de contenedores minimo teórico adicionales necesarios
        int lowerBound = (sumaRestante + capacidadC-1) / capacidadC;

        
        if (contenedores.size()+lowerBound >= mejorK) return;
        // caso base
        if (index==conjuntoS.length) {
            if (mejorK>contenedores.size()) {
                mejorK = contenedores.size();
                mejorDistribucion = copy(contenedores);
            }
            return;
        }

        

        
        for (int i = 0; i<contenedores.size(); i++) {
            if (suma(contenedores.get(i))+conjuntoS[index] <= capacidadC) {
                contenedores.get(i).add(conjuntoS[index]);
                //Avanzar
                backtracking(index+1, copy(contenedores), sumaRestante-conjuntoS[index]);
                //Retroceder
                contenedores.get(i).remove(contenedores.get(i).size() -1);
            }
        }
        
        if (contenedores.size()+1 <mejorK) {
            List<Integer> nuevoContenedor = new ArrayList<Integer>();
            nuevoContenedor.add(conjuntoS[index]);
            contenedores.add(nuevoContenedor);
            //Avanzar
            backtracking(index+1, copy(contenedores), sumaRestante-conjuntoS[index]);
            //Retroceder
            contenedores.remove(contenedores.size()-1);
        }
    }

    private boolean sumaContenedores(List<List<Integer>> contenedores) {
        int contenedoresSumaTotal = 0;
        for (int i = 0; i<contenedores.size(); i++) {
            contenedoresSumaTotal+=suma(contenedores.get(i));
        }
        return sumaConjuntoS.equals(contenedoresSumaTotal);
    }

    private List<List<Integer>> copy(List<List<Integer>> contenedores) {
        List<List<Integer>> newContenedores = new ArrayList<List<Integer>>();

        for (List<Integer> e : contenedores) {
            List<Integer> newList = new ArrayList<Integer>();
            for (int i = 0; i<e.size();i++) {
                newList.add(e.get(i)); 
            }
            newContenedores.add(newList);
        }

        return newContenedores;
    }

    private Integer suma(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum+=list.get(i);
        }
        return sum;
    }

}
