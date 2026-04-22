import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 */
public class Laberinto {
    public int[][] lab;
    public int bestSol;

    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};

    public int initialPos;
    public int finalXPos;
    public int finalYPos;


    public Laberinto(int initialPosition, int finalPos) {
        
    }


    public void backtracking(int x, int y, int[][] backLab, int numPas) {
        if (numPas>bestSol) {
            return;
        }
        if (finalXPos == x && finalYPos == y && numPas<=bestSol) {
            bestSol = numPas;
            lab = backLab.clone();
            return;
        }

        for (int i = 0; i<4; i++) {
            if (isValid(x+dx[i], y+dy[i], backLab)) {
                backLab[x+dx[i]][y+dy[i]] = 2;
                backtracking(x+dx[i], y+dy[i], backLab.clone(),numPas+1);
                backLab[x+dx[i]][y+dy[i]] = 0;
            }
        }

        return;

    }


    private boolean isValid(int x, int y, int[][] tablero) {
        if (x<lab.length && y<lab.length && tablero[x][y] == 0) {
            return true;
        }
        return false;
    }

    

}