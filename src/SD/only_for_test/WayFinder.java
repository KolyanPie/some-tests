package SD.only_for_test;

import java.util.ArrayList;
import java.util.Stack;

public class WayFinder {
    private Elements[][] field;
    private int width;
    private int height;

    public WayFinder(Elements[][] field) {
        this.field = field;
        width = field.length;
        height = field[0].length;
    }

    public void setField(Elements[][] field) {
        this.field = field;
        width = field.length;
        height = field[0].length;
    }

    public Point[] getWay(Point begin, Point end) {
        Stack<Point> stack = new Stack<>();
        Point currentPoint = begin;
        ArrayList<Point> neighbours;
        boolean[][] visited = new boolean[width][height];
        visited[currentPoint.x][currentPoint.y] = true;
        while (true) {
            if (currentPoint.equals(end)) {
                break;
            }
            neighbours = getNeighbours(currentPoint, visited);
            if (neighbours.size() != 0) {
                stack.push(currentPoint);
                currentPoint = neighbours.get(0);
                visited[currentPoint.x][currentPoint.y] = true;
            } else {
                currentPoint = stack.pop();
            }
        }
        stack.push(end);
        int size = stack.size();
        Point[] result = new Point[size];
        for (int i = size - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    private ArrayList<Point> getNeighbours(Point currentPoint, boolean[][] visited) {
        ArrayList<Point> arrayList = new ArrayList<>();
        if (currentPoint.x > 0 && field[currentPoint.x - 1][currentPoint.y].equals(Elements.ROAD) && !visited[currentPoint.x - 1][currentPoint.y]) {
            arrayList.add(new Point(currentPoint.x - 1, currentPoint.y));
        }
        if (currentPoint.y > 0 && field[currentPoint.x][currentPoint.y - 1].equals(Elements.ROAD) && !visited[currentPoint.x][currentPoint.y - 1]) {
            arrayList.add(new Point(currentPoint.x, currentPoint.y - 1));
        }
        if (currentPoint.x < width - 1 && field[currentPoint.x + 1][currentPoint.y].equals(Elements.ROAD) && !visited[currentPoint.x + 1][currentPoint.y]) {
            arrayList.add(new Point(currentPoint.x + 1, currentPoint.y));
        }
        if (currentPoint.y < height - 1 && field[currentPoint.x][currentPoint.y + 1].equals(Elements.ROAD) && !visited[currentPoint.x][currentPoint.y + 1]) {
            arrayList.add(new Point(currentPoint.x, currentPoint.y + 1));
        }
        return arrayList;
    }
}
