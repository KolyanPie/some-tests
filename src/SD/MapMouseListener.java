package SD;

import SD.only_for_test.House;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MapMouseListener implements MouseListener {

    private MapPanel mapPanel;
    private House[] houses;
    private int x1;
    private int y1;
    private int squareSize;
    private int id;
    private boolean isLeftClick;

    MapMouseListener(MapPanel mapPanel, House[] houses, int x1, int y1, int squareSize) {
        this.mapPanel = mapPanel;
        this.houses = houses;
        this.x1 = x1;
        this.y1 = y1;
        this.squareSize = squareSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < houses.length; i++) {
            if (e.getX() < x1 + (houses[i].house.x + 1) * squareSize &&
                    e.getX() > x1 + houses[i].house.x * squareSize &&
                    e.getY() < y1 + (houses[i].house.y + 1) * squareSize &&
                    e.getY() > y1 + houses[i].house.y * squareSize) {
                id = i;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getX() < x1 + (houses[id].house.x + 1) * squareSize &&
                e.getX() > x1 + houses[id].house.x * squareSize &&
                e.getY() < y1 + (houses[id].house.y + 1) * squareSize &&
                e.getY() > y1 + houses[id].house.y * squareSize) {
            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    mapPanel.click(id, true);
                    break;
                case MouseEvent.BUTTON3:
                    mapPanel.click(id, false);
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
