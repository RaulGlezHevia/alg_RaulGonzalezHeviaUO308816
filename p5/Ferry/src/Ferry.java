import java.util.List;

public class Ferry {

    private int boatLength; // long carriles barco
    private List<Integer> vehicles; //lista vehiculos
    private boolean[][] DP; //matriz posibles soluciones
    private int[] sumatorio; //sumatorio (suma acumulada) de las longitudes de los vehiculos

    public Ferry(int boatLength, List<Integer> vehicles) {
        this.boatLength = boatLength;
        this.vehicles = vehicles;
        this.DP = new boolean[vehicles.size()+1][boatLength+1];

        DP[0][0] = true;

        this.sumatorio = new int[vehicles.size()+1];

        sumatorio[0]=0;
        for (int i = 1; i<=vehicles.size(); i++) {
            sumatorio[i] = vehicles.get(i-1) + sumatorio[i-1];
        }
    }

    /*public void run() {
        for (int i = 1; i<DP.length;i++) {
            for (int j = 0 ; j< DP[i].length;j++) {
                if (j+vehicles.get(i-1) <= boatLength) {
                    DP[i][j+vehicles.get(i-1)-1] = true;
                }
                if (sumatorio[i]-j<=boatLength) {
                    DP[i][j] = true;
                }
            }
        }
    }*/

    public void run2() {
        DP[0][0] = true;
        for (int i = 1; i<=vehicles.size();i++) {
            for (int j = boatLength; boatLength>=0;j--) {
                if (!DP[i-1][j]==false) {
                    continue;
                }

                //meter coche en babor
                if (j+vehicles.get(i-1)<=boatLength) {
                    DP[i][j+vehicles.get(i-1)] = true;
                }
                //meter coche en estribor
                if (sumatorio[i]-j<=boatLength) {
                    DP[i][j] = true;
                }
            }
        }

    }

    public void printData() {
        System.out.printf("Longitud de los carriles: %d \n", boatLength);
        System.out.print("Longitud de los vehiculos:\n");
        for (int i = 0;i<vehicles.size();i++) {
            System.out.println();
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }


}
