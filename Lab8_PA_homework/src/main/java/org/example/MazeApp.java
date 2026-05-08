package org.example;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MazeApp extends JFrame {
    private DrawingPanel drawingPanel;

    public MazeApp() {
        super("Maze Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
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
        JButton validateBtn = new JButton("Validate");
        JButton exportBtn = new JButton("Export");
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");
        JButton resetBtn = new JButton("Reset");
        JButton exitBtn = new JButton("Exit");

        controlPanel.add(createBtn);
        controlPanel.add(validateBtn);
        controlPanel.add(exportBtn);
        controlPanel.add(saveBtn);
        controlPanel.add(loadBtn);
        controlPanel.add(resetBtn);
        controlPanel.add(exitBtn);
        add(controlPanel, BorderLayout.SOUTH);

        drawBtn.addActionListener(e -> {
            try {
                int r = Integer.parseInt(rowsField.getText());
                int c = Integer.parseInt(colsField.getText());
                drawingPanel.initGrid(r, c);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid numbers");
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

        validateBtn.addActionListener(e -> {
            Cell[][] grid = drawingPanel.getGrid();
            if (grid == null) return;
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            Queue<Cell> queue = new LinkedList<>();
            queue.add(grid[0][0]);
            visited[0][0] = true;

            boolean found = false;
            while (!queue.isEmpty()) {
                Cell current = queue.poll();
                int r = current.getRow();
                int c = current.getCol();
                if (r == rows - 1 && c == cols - 1) {
                    found = true;
                    break;
                }
                if (!current.hasTopWall() && r > 0 && !visited[r - 1][c]) {
                    visited[r - 1][c] = true;
                    queue.add(grid[r - 1][c]);
                }
                if (!current.hasBottomWall() && r < rows - 1 && !visited[r + 1][c]) {
                    visited[r + 1][c] = true;
                    queue.add(grid[r + 1][c]);
                }
                if (!current.hasLeftWall() && c > 0 && !visited[r][c - 1]) {
                    visited[r][c - 1] = true;
                    queue.add(grid[r][c - 1]);
                }
                if (!current.hasRightWall() && c < cols - 1 && !visited[r][c + 1]) {
                    visited[r][c + 1] = true;
                    queue.add(grid[r][c + 1]);
                }
            }
            if (found) JOptionPane.showMessageDialog(this, "Traversable!");
            else JOptionPane.showMessageDialog(this, "Not traversable.");
        });

        exportBtn.addActionListener(e -> {
            if (drawingPanel.getGrid() == null) return;
            BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            drawingPanel.paint(g2d);
            g2d.dispose();
            try {
                ImageIO.write(image, "png", new File("maze.png"));
                JOptionPane.showMessageDialog(this, "Exported to maze.png");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        saveBtn.addActionListener(e -> {
            if (drawingPanel.getGrid() == null) return;
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("maze.ser"))) {
                oos.writeObject(drawingPanel.getGrid());
                JOptionPane.showMessageDialog(this, "Saved to maze.ser");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        loadBtn.addActionListener(e -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("maze.ser"))) {
                Cell[][] grid = (Cell[][]) ois.readObject();
                drawingPanel.setGrid(grid);
                JOptionPane.showMessageDialog(this, "Loaded from maze.ser");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        resetBtn.addActionListener(e -> {
            if (drawingPanel.getGrid() != null) {
                drawBtn.doClick();
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MazeApp().setVisible(true));
    }
}