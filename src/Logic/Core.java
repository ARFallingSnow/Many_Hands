/*
Name: Core
Author: Alex, R
Purpose: Core object acting as the main platform for the rest of the project. 
Details: All manager and handler objects report to the core. 
*/
package Logic;
import java.util.*;

import UI.*;
import Grid.*;

public class Core {
    Scanner input; //Allows input to be taken over CLI
    InterfaceManager im; 
    Loop loop;
    Grid grid;
    Generator genny;

    int rows = 30;
    int cols = 40;
    int mcx = 0;
    int mcy = 0;
    RNG rng;
    boolean free = true;

    

    /**
     * Core object constructor 
     */
    public Core(){
        loop = new Loop(this);
        input = new Scanner(System.in);
        genny = new Generator(cols, rows);
        genny.buildGrid();
        this.grid = genny.getGrid(); 
        im = new InterfaceManager(this);
        rng = new RNG();

    }
    /**
     * Performs initial functions and value setting
     */
    public void setup(){
        im.setup();
        setPlayer();
        moveWindow();
    }

    /**
     * Method to be called when all pre-start conditions are met and application is ready to execute 
     */
    public void start(){
        loop.startGameThread();
        im.repaintGame();
    }
    //================ interaction ====================

    public void passKey(String key){
        //System.out.println(key);
        if(free){
            switch(key){
                case "KEY_W":
                    if(grid.getSpot(mcx, mcy-1) != 0){
                    grid.setSpot(mcx, mcy, 3);
                    mcy--;
                    grid.setSpot(mcx, mcy, 1);}
                    break;
                case "KEY_A":
                    if(grid.getSpot(mcx-1, mcy) != 0){
                    grid.setSpot(mcx, mcy, 3);
                    mcx--;
                    grid.setSpot(mcx, mcy, 1);}
                    break;
                case "KEY_S":
                    if(grid.getSpot(mcx, mcy+1) != 0){
                    grid.setSpot(mcx, mcy, 3);
                    mcy++;
                    grid.setSpot(mcx, mcy, 1);}
                    break;
                case "KEY_D":
                    if(grid.getSpot(mcx+1, mcy) != 0){
                    grid.setSpot(mcx, mcy, 3);
                    mcx++;
                    grid.setSpot(mcx, mcy, 1);}
                    break;
                case "KEY_Q":
                    clearFog();
                    break;
            }
            moveWindow();
        }
    }

    public void passCommand(){}

    public void updateTick(int tick){
        im.updateTick(tick);
        if(tick%5 == 0){
            im.repaintGame();
        }
    }
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
            }
        }
    }
    
    //========== interactions for graphics ===========
    public Grid getGrid(){
        return this.grid; 
    }
    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols; 
    }
    public int getPlayerX(){
        return this.mcx;
    }
    public int getPlayerY(){
        return this.mcy;
    }
}
