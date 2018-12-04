package SD;

import SD.only_for_test.*;
import SD.only_for_test.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

class MapPanel extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private int width;
    private int height;
    private int housesNum;
    private int x1;
    private int y1;
    private int squareSize;
    private Elements[][] field;
    private int i;
    private int j;
    private House[] houses;
    private House houseBeginPoint;
    private House houseEndPoint;
    private MapGenerator mapGenerator;
    private WayFinder wayFinder;
    private int manId;
    private Point[] way;

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
    private Image houseR = new ImageIcon("res/map/houseR.png").getImage();
    private Image houseB = new ImageIcon("res/map/houseB.png").getImage();
    private Image houseL = new ImageIcon("res/map/houseL.png").getImage();
    private Image houseT = new ImageIcon("res/map/houseT.png").getImage();
    private Image houseBegin = new ImageIcon("res/map/houseBegin.png").getImage();
    private Image houseEnd = new ImageIcon("res/map/houseEnd.png").getImage();
    private Image wayImg = new ImageIcon("res/map/way.png").getImage();


    MapPanel() {
        width = 15;
        height = 15;
        housesNum = 30;
        squareSize = Math.min(WIDTH / width, HEIGHT / height);
        x1 = (WIDTH - width * squareSize) / 2;
        y1 = (HEIGHT - height * squareSize) / 2;
        mapGenerator = new MapGenerator(width, height, housesNum);
        field = mapGenerator.getField();
        houses = mapGenerator.getHouses();
        int randNum = (int) (Math.random() * houses.length);
        houseBeginPoint = houses[randNum];
        randNum = (int) (Math.random() * houses.length);
        houseEndPoint = houses[randNum];
        wayFinder = new WayFinder(field);
        way = new Point[0];
    }

    void generateMap(int width, int height, int housesNum) {
        this.width = width;
        this.height = height;
        this.housesNum = housesNum;
    }

    void regenerate() {
        way = new Point[0];
        mapGenerator = new MapGenerator(width, height, housesNum);
        field = mapGenerator.getField();
        houses = mapGenerator.getHouses();
        int randNum = (int) (Math.random() * houses.length);
        houseBeginPoint = houses[randNum];
        randNum = (int) (Math.random() * houses.length);
        houseEndPoint = houses[randNum];
        repaint();
    }

    void randBeginHouse() {
        int randNum = (int) (Math.random() * houses.length);
        houseBeginPoint = houses[randNum];
        repaint();
    }

    void randEndHouse() {
        int randNum = (int) (Math.random() * houses.length);
        houseEndPoint = houses[randNum];
        repaint();
    }

    void displayWay() {
        wayFinder = new WayFinder(field);
        way = wayFinder.getWay(houseBeginPoint.road, houseEndPoint.road);
        repaint();
    }

    void displayMan() {

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
                    case HOUSE:
                        g.drawImage(grass, x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize, null);
                        break;
                }
            }
        }
        for (House h : houses) {
            if (h.road.x - h.house.x == 1) {
                g.drawImage(houseR, x1 + h.house.x * squareSize, y1 + h.house.y * squareSize, squareSize, squareSize, null);
            }
            if (h.road.x - h.house.x == -1) {
                g.drawImage(houseL, x1 + h.house.x * squareSize, y1 + h.house.y * squareSize, squareSize, squareSize, null);
            }
            if (h.road.y - h.house.y == 1) {
                g.drawImage(houseB, x1 + h.house.x * squareSize, y1 + h.house.y * squareSize, squareSize, squareSize, null);
            }
            if (h.road.y - h.house.y == -1) {
                g.drawImage(houseT, x1 + h.house.x * squareSize, y1 + h.house.y * squareSize, squareSize, squareSize, null);
            }
        }
        g.drawImage(houseBegin, x1 + houseBeginPoint.house.x * squareSize, y1 + houseBeginPoint.house.y * squareSize, squareSize, squareSize, null);
        g.drawImage(houseEnd, x1 + houseEndPoint.house.x * squareSize, y1 + houseEndPoint.house.y * squareSize, squareSize, squareSize, null);
        drawWay(g);
        for (MouseListener m : getMouseListeners()) {
            removeMouseListener(m);
        }
        addMouseListener(new MapMouseListener(this, houses, x1, y1, squareSize));
    }

    private void drawWay(Graphics g) {
        for (Point point : way) {
            g.drawImage(wayImg, x1 + point.x * squareSize, y1 + point.y * squareSize, squareSize, squareSize, null);
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

    public void click(int id, boolean isLeftClick) {
        if (isLeftClick) {
            houseBeginPoint = houses[id];
        } else {
            houseEndPoint = houses[id];
        }
        repaint();
    }
}
