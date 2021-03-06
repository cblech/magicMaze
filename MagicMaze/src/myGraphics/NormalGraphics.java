/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myGraphics;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author cblech
 */
public class NormalGraphics {

    private Graphics g;
    private int x;
    private int y;
    private float scale;

    public NormalGraphics(NormalGraphics g, float x, float y, float scale) {
        this.g = g.g;
        this.x = g.x+(int)(x*g.scale);
        this.y = g.y+(int)(y*g.scale);
        this.scale = g.scale*scale;
    }
    public NormalGraphics(Graphics g, int x, int y, float scale) {
        this.g = g;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public void setColor(Color c) {
        g.setColor(c);
    }

    public void drawLine(float x1, float y1, float x2, float y2) {
        g.drawLine(
                this.x + (int) (x1 * scale),
                this.y + (int) (y1 * scale),
                this.x + (int) (x2 * scale),
                this.y + (int) (y2 * scale));
    }

    public void fillRect(float x, float y, float width, float height) {
        g.fillRect(
                this.x + (int) (x * scale),
                this.y + (int) (y * scale),
                (int) (width * scale),
                (int) (height * scale));
    }
    public void drawRect(float x, float y, float width, float height) {
        g.drawRect(
                this.x + (int) (x * scale),
                this.y + (int) (y * scale),
                (int) (width * scale),
                (int) (height * scale));
    }

    public void fillCirc(float x, float y, float radius) {
        g.fillOval(
                this.x - (int) (radius * scale) + (int) (x * scale),
                this.y - (int) (radius * scale) + (int) (y * scale),
                (int) (radius * 2 * scale),
                (int) (radius * 2 * scale));
    }
    public void drawOutline(Color c){
      g.setColor(c);
        drawRect(0, 0, 1, 1);
    }
    public void drawOutline(){
        drawOutline(Color.red);
    }

}
