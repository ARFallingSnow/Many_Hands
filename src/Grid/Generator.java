/*
Name: Generator
Author: Alex, R
Purpose: 
Details: 
*/
package Grid;
import Logic.RNG;

public class Generator {
    Grid grid;
    boolean bounded, collision, zoop, invalid;
    RNG rng;
    int rows, cols, curX , curY, move, tries, jumps;
    /**
     * 
     * @param cols
     * @param rows
     */
    public Generator(int cols, int rows ){
        this.rows = rows;
        this.cols = cols; 
        grid = new Grid(cols, rows); 
        rng = new RNG();
    }
    /**
     * 
     */
    public void buildGrid(){
        bounded = true; 
        collision = true;
        zoop = true;
        curX = 5;
        curY = 5;
        grid.setSpot(curX,curY,1);
        move = 0;
        tries = 0;
        jumps = 0; 
        while (jumps < 25){
            drawRand();
        }
        replaceThin();
        addBorders();
        addDoors();
        removePlayer();
        shadowMap();
        addExit();
        
    }
    /**
     * 
     */
    public void drawRand(){
        invalid = true;
        int testX = 0; 
        int testY = 0; 
        int dir = 0;
        boolean tryJump = false;

        while(invalid){
        tryJump = false;
        dir = rng.roll(4);
        tries++;
        testX = curX;
        testY = curY;
        switch(dir){
            case 0:
                testX++;
                break;
            case 1:
                testX--;
                break;
            case 2:
                testY++;
                break; 
            case 3:
                testY--;
                break;
            default:
                break; 
        }

        if(zoop){
            if(testX < 0){testX = cols-1;}
            if(testX > cols){testX = 0;}
            if(testY < 0){testY = rows -1;}
            if(testY > rows){testY = 0;}
        }

        if(bounded){if(!grid.inBounds(testX,testY)){testX = curX; testY = curY;}}

        if(!collision){
            break;
        } else {
            if(isBlocked()){
                testY = rng.roll(rows)+1;
                testX = rng.roll(cols)+1;
                if(bounded){if(!grid.inBounds(testX,testY)){testX = curX; testY = curY;}}
                tryJump = true;
            }
            if(grid.getSpot(testX, testY) == 0){ invalid = false;}
        }
        }
        if (tryJump){
            grid.setSpot(curX,curY,4);
        }else {
            grid.setSpot(curX,curY,3);
        }
        
        curX = testX; 
        curY = testY;
        grid.setSpot(curX,curY,1);
        if(tryJump){jumps++;}
        move++;
    }
    /**
     * 
     * @return
     */
    public boolean isBlocked(){
        if(grid.getSpot(curX+1, curY) == 0){return false;}
        if(grid.getSpot(curX-1, curY) == 0){return false;}
        if(grid.getSpot(curX, curY+1) == 0){return false;}
        if(grid.getSpot(curX, curY-1) == 0){return false;}
        return true;
    }
    /**
     * 
     * @return
     */
    public Grid getGrid(){
        return this.grid; 
    }
    /**
     * 
     * @param xPos
     * @param yPos
     * @return
     */
    public boolean inBounds(int xPos, int yPos){
        if((xPos < 0)||(xPos > cols-1)){return false;}
        if((yPos < 0)||(yPos > rows-1)){return false;}
        return true; 
    }
    /**
     * 
     */
    public void replaceThin(){
        for (int a = 1; a < rows-2; a++){
            for(int b = 1; b < cols-2; b++){
                if ((grid.getSpot(b-1, a) == 3) && (grid.getSpot(b, a) == 0) && (grid.getSpot(b+1, a) == 3)) {
                    grid.setSpot(b,a,3);
                }
            }
        }
        for (int a = 1; a < rows-2; a++){
            for(int b = 1; b < cols-2; b++){
                if ((grid.getSpot(b, a-1) == 3) && (grid.getSpot(b, a) == 0) && (grid.getSpot(b, a+1) == 3)) {
                    grid.setSpot(b,a,3);
                }
            }
        }

    }
    /**
     * 
     */
    public void addDoors(){
        for (int a = 1; a < rows-2; a++){
            for(int b = 1; b < cols-2; b++){
                if ((grid.getSpot(b-1, a) == 0) && (grid.getSpot(b, a) == 3) && (grid.getSpot(b+1, a) == 0)&& (grid.getSpot(b, a-1) == 3) && (grid.getSpot(b, a) == 3) && (grid.getSpot(b, a+1) == 3)) {
                    grid.setSpot(b,a,5);
                }
            }
        }
        for (int a = 1; a < rows-2; a++){
            for(int b = 1; b < cols-2; b++){
                if ((grid.getSpot(b-1, a) == 3) && (grid.getSpot(b, a) == 3) && (grid.getSpot(b+1, a) == 3)&& (grid.getSpot(b, a-1) == 0) && (grid.getSpot(b, a) == 3) && (grid.getSpot(b, a+1) == 0)) {
                    grid.setSpot(b,a,5);
                }
            }
        }

    }
    /**
     * 
     */
    public void addBorders(){
        for (int a = 0; a < rows; a++){
            for(int b = 0; b < cols; b++){
               if(a == 0){grid.setSpot(b,a,0);}
               if(b == 0){grid.setSpot(b,a,0);}
               if(a == rows-1){grid.setSpot(b,a,0);}
               if(b == cols-1){grid.setSpot(b,a,0);}
            }
        }
    }
    /**
     * 
     */
    public void removePlayer(){
        for (int a = 0; a < rows; a++){
            for(int b = 0; b < cols; b++){
               if(grid.getSpot(b,a) ==1){grid.setSpot(b,a, 3);}
            }
        }
    }
    /**
     * 
     */
    public void shadowMap(){
        for (int a = 0; a < rows; a++){
            for(int b = 0; b < cols; b++){
                if(grid.getSpot(b,a) == 3){grid.setSpot(b,a, 30);}
                if(grid.getSpot(b,a) == 4){grid.setSpot(b,a, 40);}
                if(grid.getSpot(b,a) == 5){grid.setSpot(b,a, 50);}
                if(grid.getSpot(b,a) == 0){grid.setSpot(b,a, 10);}
            }
        }
    }
    /**
     * 
     */
    public void addExit(){
        int num = rng.roll(3)+2;
        if(num > 4){num = 3;}
        int[] list = new int[num];
        while(num > 0){
            int test = rng.roll(3)+1;
            if (!numInList(test, list)){
                list[num-1] = test;
                num--;
            }

        }
        for(int that : list){
            int a = 0;
            int b = 0;
            int n = 99;
            switch(that){
                case 1:
                    a = 0;
                    b = cols/2;
                    n = grid.getSpot(b, a);
                    while(n != 30){
                        grid.setSpot(b,a,30);
                        a++;
                        n = grid.getSpot(b, a);
                        if(!grid.inBounds(a, b)){n = 30;}
                    }
                    break;
                case 2:
                    a = rows-1;
                    b = cols/2;
                    n = grid.getSpot(b, a);
                    while(n != 30){
                        grid.setSpot(b,a,30);
                        a--;
                        n = grid.getSpot(b, a);
                        if(!grid.inBounds(a, b)){n = 30;}
                    }
                    break;
                case 3:
                    a = rows/2;
                    b = 0;
                    n = grid.getSpot(b, a);
                    while(n != 30){
                        grid.setSpot(b,a,30);
                        b++;
                        n = grid.getSpot(b, a);
                        if(!grid.inBounds(a, b)){n = 30;}
                    }
                    break;
                case 4:
                    a = rows/2;
                    b = cols-1;
                    n = grid.getSpot(b, a);
                    while(n != 30){
                        grid.setSpot(b,a,30);
                        b--;
                        n = grid.getSpot(b, a);
                        if(!grid.inBounds(a, b)){n = 30;}
                    }
                    break; 
            }
        }
    }
    //=============== helpers ==============
    /**
     * 
     * @param test
     * @param list
     * @return
     */
    public boolean numInList(int test, int[] list){
        for (int that:list){
            if (test == that){return true;}
        }
        return false;
    }
    
}
