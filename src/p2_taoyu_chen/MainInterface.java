package p2_taoyu_chen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainInterface extends JFrame implements KeyListener {
    // Color buttons
    private String[] colorNames = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow"};
    private Color[] colors = {
        Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };
    private JButton[] colorButtons = new JButton[colors.length];
    // Brush size buttons
    private JButton incBrushSizeButton = new JButton("Increase brush size");
    private JButton decBrushSizeButton = new JButton("Decrease brush size");
    // Clear button
    private JButton clearButton = new JButton("Clear");

    // Variable to record the selected color
    private int colorButtonIndex = 0;
    // Canvas variable
    private Canvas canvas;

    public MainInterface() {
        super("Paint App");
        // Creating canvas
        canvas = new Canvas();
        // Set frame size to 1000x1000
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup color buttons
        for (int i = 0; i < colors.length; i++) {
            final Color c = colors[i];
            final int index = i;
            colorButtons[i] = new JButton(colorNames[i]);
            colorButtons[i].setFocusable(false);
            colorButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.setColor(c);
                    colorButtonIndex = index;
                }
            });

        }

        // Setup brush size buttons
        incBrushSizeButton.setFocusable(false);
        incBrushSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.incStrokeWidth();
            }
        });

        decBrushSizeButton.setFocusable(false);
        decBrushSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.decStrokeWidth();
            }
        });

        // Setup clear button
        clearButton.setFocusable(false);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.clear();
            }
        });

        // Add buttons
        JPanel buttonContainer = new JPanel(new BorderLayout());
        JPanel topRow = new JPanel();
        JPanel bottomRow = new JPanel();

        buttonContainer.setBackground(Color.BLACK);
        for (int i = 0; i < 11; i++) {
            topRow.add(colorButtons[i]);
        }
        for (int i = 11; i < colorButtons.length; i++) {
            bottomRow.add(colorButtons[i]);
        }
        bottomRow.add(incBrushSizeButton);
        bottomRow.add(decBrushSizeButton);
        bottomRow.add(clearButton);
        buttonContainer.add(topRow, BorderLayout.NORTH);
        buttonContainer.add(bottomRow, BorderLayout.SOUTH);

        // Add canvas and container to frame
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(buttonContainer, BorderLayout.NORTH);
        content.add(canvas, BorderLayout.CENTER);

        // Bind keylistener to frame
        addKeyListener(this);
        setFocusable(true);
        // Show the frame
        setVisible(true);
    }

    // Overriding keyPressed to enable the color and stroke change
    // when certain keys are pressed
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed");
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            canvas.incStrokeWidth();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            canvas.decStrokeWidth();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            colorButtonIndex = (colorButtonIndex + 1) % colorButtons.length;
            canvas.setColor(colors[colorButtonIndex]);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            colorButtonIndex--;
            if (colorButtonIndex < 0) {
                colorButtonIndex = colorButtons.length - 1;
            }
            canvas.setColor(colors[colorButtonIndex]);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            canvas.clear();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
