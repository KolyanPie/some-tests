package SD;

import SD.only_for_test.Elements;
import SD.only_for_test.MapGenerator;

import javax.swing.*;
import java.awt.*;

class MapPanel extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private int width;
    private int height;
    private int houses;
    private int x1;
    private int y1;
    private int squareSize;
    private Elements[][] field;
    private int i, j;
    private Image grass = new ImageIcon("res/map/grass.png").getImage();
    private Image roadH = new ImageIcon("res/map/roadH.png").getImage();
    private Image roadV = new ImageIcon("res/map/roadV.png").getImage();
    private Image roadRB = new ImageIcon("res/map/roadRB.png").getImage();
    private Image roadRT = new ImageIcon("res/map/roadRT.png").getImage();
    private Image roadLB = new ImageIcon("res/map/roadLB.png").getImage();
    private Image roadLT = new ImageIcon("res/map/roadLT.png").getImage();
    private Image roadR = new ImageIcon("res/map/roadR.png").getImage();
    private Image roadB = new ImageIcon("res/map/roadB.png").getImage();
    private Image roadL = new ImageIcon("res/map/roadL.png").getImage();
    private Image roadT = new ImageIcon("res/map/roadT.png").getImage();
    private Image house = new ImageIcon("res/map/house.png").getImage();

    MapPanel() {
        width = 15;
        height = 15;
        squareSize = Math.min(WIDTH / width, HEIGHT / height);
        x1 = (WIDTH - width * squareSize) / 2;
        y1 = (HEIGHT - height * squareSize) / 2;
        addMouseListener(new MapMouseListener(this, width, height, x1, y1, squareSize));
    }

    void generateMap(int width, int height, int houses) {
        this.width = width;
        this.height = height;
        this.houses = houses;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawField(g);
    }

    private void drawFieldOld(Graphics g) {
        byte[][] arr = new byte[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                arr[i][j] = (byte) (Math.random() * 2);
            }
        }
        squareSize = Math.min(WIDTH / width, HEIGHT / height);
        x1 = (WIDTH - width * squareSize) / 2;
        y1 = (HEIGHT - height * squareSize) / 2;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (arr[i][j]) {
                    case 0:
                        g.fillRect(x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize);
                        break;
                    case 1:
                        g.drawRect(x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize);
                        break;
                }
            }
        }
    }

    private void drawField(Graphics g) {
        MapGenerator mapGenerator = new MapGenerator(width, height, houses);
        field = mapGenerator.getField();

        squareSize = Math.min(WIDTH / width, HEIGHT / height);
        x1 = (WIDTH - width * squareSize) / 2;
        y1 = (HEIGHT - height * squareSize) / 2;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (field[i][j]) {
                    case ROAD:
                        drawRoad(g, i, j);
                        break;
                    case GRASS:
                        g.drawImage(grass, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
                        break;
                }
            }
        }
    }

    private void drawRoad(Graphics g, int i, int j) {
        this.i = i;
        this.j = j;
        if (right() && left() && !bottom() && !top()) {
            g.drawImage(roadH, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (top() && bottom() && !right() && !left()) {
            g.drawImage(roadV, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (right() && bottom() && !left() && !top()) {
            g.drawImage(roadRB, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (right() && top() && !left() && !bottom()) {
            g.drawImage(roadRT, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (left() && bottom() && !right() && !top()) {
            g.drawImage(roadLB, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (left() && top() && !right() && !bottom()) {
            g.drawImage(roadLT, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (right() && left() && bottom()) {
            g.drawImage(roadT, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (right() && left() && top()) {
            g.drawImage(roadB, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (right() && bottom() && top()) {
            g.drawImage(roadL, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (left() && bottom() && top()) {
            g.drawImage(roadR, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (right() || left()) {
            g.drawImage(roadH, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        } else if (bottom() || top()) {
            g.drawImage(roadV, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
        }
    }

    private boolean right() {
        return i < width - 1 && field[i + 1][j].equals(Elements.ROAD);
    }

    private boolean bottom() {
        return j < height - 1 && field[i][j + 1].equals(Elements.ROAD);
    }

    private boolean left() {
        return field[i - 1][j].equals(Elements.ROAD);
    }

    private boolean top() {
        return field[i][j - 1].equals(Elements.ROAD);
    }
}
