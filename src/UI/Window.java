/*
Name: Window
Author: Alex, R
Purpose: Window to pose as main user display and interface
Details: 
*/

package UI;

//import java.awt.*;
import javax.swing.JFrame;


public class Window extends JFrame {
    private JFrame window; //JFrame used as the window
    private GamePanel main;
    private int windowHeight, windowWidth; //used to control window dimensions 

    //private Color bk = new  Color(71,184,188);              //background color. Modify values as needed 
    //private Font basic = new Font("Consolas", Font.BOLD, 20);  // basic font. Modify as needed
    

    /**
     * Window constructor - instantiates window object instance 
     */
    public Window(KeyHandler keys, GamePanel gamePanel) {

        window = new JFrame();
        windowHeight = 800;
        windowWidth = 1400;
        window.setSize(windowWidth, windowHeight);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setFocusable(true);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.addKeyListener(keys);

        // Creating the JPanel to containerize content 
        this.main = gamePanel;
        main.setBounds(0,0,windowWidth, windowHeight);
        main.setVisible(true);
        window.add(main);
        
    }
    /**
     * Sets window visibility true
     */
    public void showWindow(){
        
        window.setVisible(true);
    }
    /**
     * Sets window visibility false
     */
    public void hideWindow(){
        window.setVisible(false);
    }
    
    /**
     * Used to control the windows dimensions 
     * @param width int of the indented window width
     * @param height int of the intended window height
     */

    public void setWindowSize(int width, int height){
        this.windowWidth = width;
        this.windowHeight = height;
        window.setVisible(false);

        main.setBounds(0,0,windowWidth, windowHeight);
        window.setSize(windowWidth, windowHeight);
        window.setVisible(true);
    }

}
