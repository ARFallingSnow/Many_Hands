package Tiles;

import javax.imageio.ImageIO;


public class TileManager {

    private Tile wall;
    private Tile floor;
    private Tile door;
    private Tile hidden;
    private Tile guy;
    private Tile box;
    

    public TileManager(){
        try {
            floor = new Tile(ImageIO.read(getClass().getResourceAsStream("/resources/tiles/FLOOR.png")), false);
            wall = new Tile(ImageIO.read(getClass().getResourceAsStream("/resources/tiles/WALL.png")),true);
            hidden = new Tile(ImageIO.read(getClass().getResourceAsStream("/resources/tiles/HIDDEN.png")),true);
            door = new Tile(ImageIO.read(getClass().getResourceAsStream("/resources/tiles/DOOR.png")),true);
            guy = new Tile(ImageIO.read(getClass().getResourceAsStream("/resources/tiles/GUY.png")),true);
            box = new Tile(ImageIO.read(getClass().getResourceAsStream("/resources/tiles/BOX.png")),true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Tile getFloor() {return floor;}
    public Tile getWall() {return wall;}
    public Tile getDoor() {return door;}
    public Tile getGuy() {return guy;}
    public Tile getHidden() {return hidden;}
    public Tile getBox(){return box;}
}
