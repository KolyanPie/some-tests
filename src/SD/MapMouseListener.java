package SD;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MapMouseListener implements MouseListener {

    private MapPanel mapPanel;
    private int width;
    private int height;
    private int x1;
    private int y1;
    private int squareSize;

    MapMouseListener(MapPanel mapPanel, int width, int height, int x1, int y1, int squareSize) {
        this.mapPanel = mapPanel;
        this.width = width;
        this.height = height;
        this.x1 = x1;
        this.y1 = y1;
        this.squareSize = squareSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
