package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class MazeApp extends JFrame {
    private DrawingPanel drawingPanel;

    public MazeApp() {
        super("Maze Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel configPanel = new JPanel();
        JTextField rowsField = new JTextField("10", 3);
        JTextField colsField = new JTextField("10", 3);
        JButton drawBtn = new JButton("Draw Grid");

        configPanel.add(new JLabel("Rows:"));
        configPanel.add(rowsField);
        configPanel.add(new JLabel("Cols:"));
        configPanel.add(colsField);
        configPanel.add(drawBtn);
        add(configPanel, BorderLayout.NORTH);

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton createBtn = new JButton("Create");
        JButton resetBtn = new JButton("Reset");
        JButton exitBtn = new JButton("Exit");

        controlPanel.add(createBtn);
        controlPanel.add(resetBtn);
        controlPanel.add(exitBtn);
        add(controlPanel, BorderLayout.SOUTH);

        drawBtn.addActionListener(e -> {
            try {
                int r = Integer.parseInt(rowsField.getText());
                int c = Integer.parseInt(colsField.getText());
                drawingPanel.initGrid(r, c);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Pune numere valide");
            }
        });

        createBtn.addActionListener(e -> {
            Cell[][] grid = drawingPanel.getGrid();
            if (grid == null) return;

            Random rand = new Random();
            int rows = grid.length;
            int cols = grid[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols - 1 && rand.nextBoolean()) {
                        grid[i][j].setRightWall(false);
                        grid[i][j + 1].setLeftWall(false);
                    }

                    if (i < rows - 1 && rand.nextBoolean()) {
                        grid[i][j].setBottomWall(false);
                        grid[i + 1][j].setTopWall(false);
                    }
                }
            }
            drawingPanel.repaint();
        });

        resetBtn.addActionListener(e -> {
            if (drawingPanel.getGrid() != null) {
                drawBtn.doClick();
            }
        });

         exitBtn.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MazeApp().setVisible(true);
        });
    }
}