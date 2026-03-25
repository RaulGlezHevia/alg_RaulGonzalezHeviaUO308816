package main.java.labs.es._26.UO308816.s5;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ferry {

    private int length; // long carriles barco
    private List<Integer> vehicles; //lista vehiculos
    private boolean[][] DP; //matriz posibles soluciones
    private int[] sumatorio; //sumatorio (suma acumulada) de las longitudes de los vehiculos

    private List<Step> path;
    
    public Ferry(int boatLength, List<Integer> vehicles) {
        this.length = boatLength;
        this.vehicles = vehicles;
        this.DP = new boolean[vehicles.size()+1][boatLength+1];

        DP[0][0] = true;

        this.sumatorio = new int[vehicles.size()+1];

        sumatorio[0]=0;
        for (int i = 1; i<=vehicles.size(); i++) {
            sumatorio[i] = vehicles.get(i-1) + sumatorio[i-1];
        }

        this.path = new ArrayList<Step>();
    }

    public void run() {

        for (int i = 1; i<=vehicles.size();i++) {
            for (int j = 0; j<=length;j++) {
                if (DP[i-1][j]==false) {
                    continue;
                }
                
                else {
                	//meter coche en babor
                    if (j+vehicles.get(i-1)<=length) {
                        DP[i][j+vehicles.get(i-1)] = true;
                    }
                    //meter coche en estribor
                    if (sumatorio[i]-j<=length) {
                        DP[i][j] = true;
                    }
                }

            }
        }
    }

    /**
    * Devuelve el numero máximo de vehiculos posibles
    * l (siendo l < boatlength) con dp[i][l] = true. es el maximo número de coches que pueden entrar.
    */
    public int getMaximumNumberOfVehicles() {
        int lastVehicle = 0;
        for (int i = 1; i<=vehicles.size(); i++) {
            for (int l = lastVehicle; l<=length; l++) {
                if (DP[i][l]==true) {
                    lastVehicle=i;
                    break;
                }
            }
        }
        return lastVehicle;
    }

    public void printData() {
		System.out.printf("Length of parallel lanes for starboard and port on the ferry: %d\n", length);
		System.out.printf("The vehicles have the following lengths:\n");
		for (int i = 0; i < vehicles.size(); i++) {
			System.out.printf("\tVehicle %d: %d\n", i+1, vehicles.get(i));
		}
	}
	
	
    public void printPossibleAssignation() {
            boolean found = false;
            System.out.printf("\nPossible assignation:\n");
            for (int i = getMaximumNumberOfVehicles(); i > 0; i--) {
            	if (found) {
            		break;
            	}
            	for (int p = length; p>=0;p--) {
            		if (found) {
            			break;
            		}
            		if (DP[i-1][p-vehicles.get(i-1)]) {
            			found = true;
            			processAssignation(i,p);
            		}
            	}
                //si found es true -> rompo la ejecución
                //para cada p de la longitud del barco
                //		si found es true -> rompo la ejecución
                //		si dp[i][p-v(i)] es true -> found = true; llamo a processAssignation()
            }
        }

    private void processAssignation(int i, int l) {
        if ((i == 0) && (l == 0)) { 
        	printPath();
        	return;// llamo a printPath y acabo la ejecución (return)
        }
        
        if (DP[i-1][l]) {
        	Step estribor = new Step(i,l,i-1,l,i,"estribor");
        	path.addFirst(estribor);
        	processAssignation(i-1,l);
        //	añado al path (path.addFirst) un nuevo Step llamado estribor; llamo a processAssignation(i-1, l);
        } 
        else if (DP[i-1][l-vehicles.get(i-1)] ) {
		        	Step babor = new Step(i,l,i-1,l-vehicles.get(i-1),i,"babor");
		    		path.addFirst(babor);
		    		processAssignation(i-1,l-vehicles.get(i-1));
	        //		añado al path (path.addFirst) un nuevo Step llamado babor; llamo a processAssignation(i-1, l-vehicles.get(i-1));
	    }
        
        

    }
        
    public void printSolutionTable() {
        System.out.printf("\nTable with calculations:\n");
            
        System.out.printf("%4s", "V/L");
        for (int i = 0; i <= length; i++) {
            System.out.printf("%4d", i);	
        }
        System.out.printf("\n");
            
        for (int i = 0; i <= vehicles.size(); i++) {
            System.out.printf("%4d", i);
            for (int l = 0; l <= length; l++) {
                if (DP[i][l]){				
                    System.out.printf("%4s", "T");
                }
                else{ 
                        System.out.printf("%4s", "F");
                    }
                }
                System.out.printf("\n");
            }
        }

    private void printPath() {
        int portLength = 0;
        int starboardLength = 0;
        for (var step : path) {		
            if (step.movement().equals("babor")){
                portLength += vehicles.get(step.vehicle()-1);
            }
            else{
                starboardLength += vehicles.get(step.vehicle()-1);
            }
            System.out.printf("Vehicle %d (length %d) -- From (%d, %d) -- To (%d, %d) -- Position: %s -- Port lengh: %d -- Starboard length: %d\n", 
                    step.vehicle(), vehicles.get(step.vehicle()-1),
                    step.previousI(), step.previousL(),
                    step.currentI(), step.currentL(), 
                    step.movement(), portLength, starboardLength);
        }
    }
    
    private void loadData(String file) {
		this.vehicles = new ArrayList<Integer>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			this.length = Integer.valueOf(reader.readLine());		
			for (String s : reader.readLine().split(" ")) {
				this.vehicles.add(Integer.valueOf(s));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
    public static void main(String[] args) throws Exception {
        Ferry ferry = new Ferry(0, new ArrayList<Integer>());
    	ferry.loadData(args[0]);
    	
    	ferry.printData();
		ferry.run();
		ferry.printSolutionTable();
		ferry.printPossibleAssignation();
    }

}



record Step(int previousI, int previousL, 
		int currentI, int currentL, 
		int vehicle, String movement) {}