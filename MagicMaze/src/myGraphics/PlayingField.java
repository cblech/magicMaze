/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author cblech
 */
public class PlayingField extends JPanel {
    private ArrayList<ArrayList<Tile>> tiles;
    
    Tile t = new Tile("1a");

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        
        g.setColor(Color.black);
        g.drawRect(20, 20, 100, 100);
        System.out.println("printing");
        
    }
}
