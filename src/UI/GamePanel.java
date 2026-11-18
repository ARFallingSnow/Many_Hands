/*
Name: GamePanel
Author: Alex, R
Purpose: Main object used to display grid objects as a tile based map 
Details: required to perform 2D navigation
Requirements:
    - core: collective object management 
    - 
*/

package UI;

import javax.swing.*; //package containing user interface objects
import java.awt.*; //package containing graphics methods and objects
import Grid.Grid; //main data structure used to pass information reguarding the game map
import Logic.Core; //Core object gives the requested data 
import Tiles.*; //package containing tile information 



public class GamePanel extends JPanel{
    Core core;

    private static final int TILE_SIZE = 24; //sets the pixel size for each tile 


    public GamePanel(Core core){
        setBackground(new  Color(71,184,188)); //fill empty space with the background color
        this.core = core;
        System.out.println("GamePanel initialized");
        
    }

    /*
    purpose: paintComponent is called to draw all parts of the panel scene
            the order of objects drawn is set within the method and all
            child methods are called within this method utilizing the
            graphics2D object
    */
    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g); //call JPanels native paintComponent 
        Graphics2D g2 = (Graphics2D) g; //cast Graphics to Graphics2D
        // Draw content
        drawMap(g2);

    }
    /**
     * retrieves grid from core, iterates though x and y coords and draws tiles accordingly 
     * @param g2 graphics 2d object 
     */
    private void drawMap(Graphics2D g2){
        Grid grid = core.getGrid(); //retrieve up-to-date grid from core 
        for(int y = 0; y < core.getRows(); y++){
            for(int x = 0; x < core.getCols(); x++){
                int tile = grid.getSpot(x,y);

                switch(tile){
                    case TileTypes.WALL:
                        g2.setColor(Color.GRAY);
                        break;
                    case TileTypes.PLAYER: 
                        g2.setColor(Color.YELLOW);
                        break;
                    case TileTypes.FLOOR: 
                        g2.setColor(Color.GREEN);
                        break;
                    case TileTypes.DOOR: 
                        g2.setColor(Color.ORANGE);
                        break;
                    case TileTypes.HIDDEN_THING:
                        g2.setColor(Color.GRAY);
                        break;
                    case TileTypes.HIDDEN_DOOR:
                        g2.setColor(Color.GRAY);
                        break;
                    case TileTypes.HIDDEN_FLOOR:
                        g2.setColor(Color.GRAY);
                        break;
                    default:
                        g2.setColor(Color.RED);
                        break;
                }

                g2.fillRect(
                    x * TILE_SIZE,
                    y * TILE_SIZE,
                    TILE_SIZE,
                    TILE_SIZE
                );
                
                g2.setColor(Color.BLACK);

                g2.drawRect(
                    x * TILE_SIZE,
                    y * TILE_SIZE,
                    TILE_SIZE,
                    TILE_SIZE
                );
            }
        }
    }
}
