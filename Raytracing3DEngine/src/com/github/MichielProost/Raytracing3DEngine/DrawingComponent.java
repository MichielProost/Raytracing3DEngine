package com.github.MichielProost.Raytracing3DEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class DrawingComponent extends JComponent{

    int xLoc;   // x location.
    int yLoc;   // y location.

    // Constructor.
    public DrawingComponent(int x, int y){
        xLoc = x;
        yLoc = y;
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // Settings.
        g2d.setColor(Color.red);

        // Draw point.
        g2d.drawLine(xLoc, yLoc, xLoc, yLoc);

    }

}
