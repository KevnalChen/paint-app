package p2_taoyu_chen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {

    // Current color
    private Color color = Color.BLACK;
    // Stroke width
    private int strokeWidth = 10;
    // Variables for prevX, prevY
    private int prevX;
    private int prevY;

    public Canvas() {
        // Setup the drag listener
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    // Set current color
    public void setColor(Color c) {
        System.out.println("Here");
        System.out.println(c.toString());
        this.color = c;
    }

    // Increase stroke width
    public void incStrokeWidth() {
        this.strokeWidth += 5;
    }

    // Decrease stroke width
    public void decStrokeWidth() {
        this.strokeWidth -= 5;
        if (this.strokeWidth < 0) {
            this.strokeWidth = 0;
        }
    }

    // Clear panel
    public void clear() {
        super.paintComponent(getGraphics());
    }

    // Record initial (x,y) for drawing
    @Override
    public void mousePressed(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();
    }

    // Handle drawing
    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(this.color);
        BasicStroke pen = new BasicStroke(this.strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(pen);
        g2d.drawLine(prevX, prevY, e.getX(), e.getY());
        this.prevX = e.getX();
        this.prevY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
