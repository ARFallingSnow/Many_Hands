/*
Name: GridHandler
Author: Alex, R
Purpose: 
Details: 
*/
package Grid;
import Logic.*;

public class GridHandler {
    Grid grid; 
    Generator genny; 

    int rows = 30;
    int cols = 40;
    int mcx = 0;
    int mcy = 0;
    RNG rng;
    boolean free = true;

    public GridHandler(){}
    
    public boolean setup(){
        return false;
    }
    public boolean start(){
        return false;
    }
    public void passKey(String key){}
    public void passCommand(){}
    public void setPlayer(){
        boolean flag = false;
        for (int a = 1; a < rows-2; a++){
            for(int b = 1; b < cols-2; b++){
                if ((!flag)&&(grid.getSpot(b-1, a) == 30)  && (grid.getSpot(b+1, a) == 30)&& (grid.getSpot(b, a-1) == 30) && (grid.getSpot(b, a) == 30) && (grid.getSpot(b, a+1) == 30)) {
                    grid.setSpot(b,a,1);
                    flag = true;
                    mcx = b;
                    mcy = a; 
                }
            }
        }
    
    }
    public void moveWindow(){
        int x = mcx;
        int y = mcy;
        for (int a = -2; a < 3; a++){
            for (int b = -2; b < 3; b++){
                if(grid.inBounds(x+b, y+a)){
                    int test = grid.getSpot(x+b, y+a);
                    if (test > 9){
                        grid.setSpot(x+b, y+a, test/10);
                    }
                    
                }
            }
        }
        grid.setSpot(x, y, 1);
    }
    public void clearFog(){
        for (int a = 0; a < rows; a++){
            for(int b = 0; b < cols; b++){
                if(grid.getSpot(b,a) == 30){grid.setSpot(b,a, 3);}
                if(grid.getSpot(b,a) == 40){grid.setSpot(b,a, 4);}
                if(grid.getSpot(b,a) == 50){grid.setSpot(b,a, 5);}
                if(grid.getSpot(b,a) == 10){grid.setSpot(b,a, 0);}
            }
        }
    }

    public String getDisplay(){
        return null; 
    }


}
