/*
Name:
Author: Alex, R
Purpose: 
Details: 
*/

package Grid;
public class Grid {
    int gridHeight, gridWidth;
    int[][] arr; 
    
    public Grid(int width, int height){
        this.gridWidth = width;
        this.gridHeight = height;
        arr = new int[gridHeight][gridWidth];
        
    }
    //=============== tests ====================
    public boolean inBounds(int xPos, int yPos){
        if((xPos < 0)||(xPos > gridWidth-1)){return false;}
        if((yPos < 0)||(yPos > gridHeight-1)){return false;}
        return true; 
    }
    //=============== output ====================
    public int[][] getGrid(){
        return this.arr;
    }
    public int getSpot(int xPos, int yPos){
        if(!inBounds(xPos, yPos)){return -1;}
        return arr[yPos][xPos];
    }
    public void setSpot(int xPos, int yPos, int val){
         if(!inBounds(xPos, yPos)){
            return;
         } else {
            arr[yPos][xPos] = val;
         }
    }

    
}
