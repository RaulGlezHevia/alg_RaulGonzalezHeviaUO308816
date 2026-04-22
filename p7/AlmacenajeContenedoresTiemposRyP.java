import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlmacenajeContenedoresTiempos {

    public static void main(String[] args) throws FileNotFoundException {
        int capacidadC = 0;
        List<Integer> parts = new ArrayList<Integer>();
        Integer[] conjuntoS = new Integer[1];

        for (int i = 0; i<=9;i++) {

            System.out.println("ARCHIVO - - - "+"test0"+i+".txt");

            Scanner sc = new Scanner(new FileReader("test0"+i+".txt"));
            capacidadC = sc.nextInt();
            parts = new ArrayList<Integer>();
            while (sc.hasNextInt()) {
                parts.add(sc.nextInt());
            }
            conjuntoS = new Integer[parts.size()];
            for (int j = 0; j<conjuntoS.length; j++) {
                conjuntoS[j] = parts.get(j);
            }
            sc.close();

            AlmacenajeContenedoresRyP ac = new AlmacenajeContenedoresRyP(capacidadC, conjuntoS);

            long t1 = System.currentTimeMillis();
            ac.solve();
            long t2 = System.currentTimeMillis();

            System.out.println("Tiempo: " + ((t2-t1)) + "ms");
            System.out.println();
        }
        
        

        
    }

}
