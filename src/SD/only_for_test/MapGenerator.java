package SD.only_for_test;

import java.util.ArrayList;
import java.util.Stack;

public class MapGenerator {
    private int width;
    private int height;
    private int homes;
    private Elements[][] field;
    private int count = 0;

    public MapGenerator(int width, int height, int homes) {
        this.width = width;
        this.height = height;
        this.homes = homes;
        field = new Elements[width][height];
        generateEmptyField();
        createLabyrinth();
    }

    public Elements[][] getField() {
        return field;
    }

    private void generateEmptyField() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + 1) % 2 == 0 && (j + 1) % 2 == 0) {
                    field[i][j] = Elements.ROAD;
                    count++;
                } else {
                    field[i][j] = Elements.GRASS;
                }
            }
        }
    }

    private void createLabyrinth() {
        Stack<Point> stack = new Stack<>();
        boolean[][] visited = new boolean[width][height];
        Point currentPoint = new Point(1, 1);
        visited[1][1] = true;
        count--;
        while (count > 0) {
            ArrayList<Point> neighbours = getNeighbours(currentPoint, visited);
            if (neighbours.size() != 0) {
                stack.push(currentPoint);
                int randNum = (int) (Math.random() * neighbours.size());
                field[(currentPoint.x + neighbours.get(randNum).x) / 2][(currentPoint.y + neighbours.get(randNum).y) / 2] = Elements.ROAD;
                currentPoint = neighbours.get(randNum);
                visited[currentPoint.x][currentPoint.y] = true;
                count--;
            }
            else {
                currentPoint = stack.pop();
            }
        }
    }

    private ArrayList<Point> getNeighbours(Point currentPoint, boolean[][] visited) {
        ArrayList<Point> arrayList = new ArrayList<>();
        if (currentPoint.x > 1 && !visited[currentPoint.x - 2][currentPoint.y]) {
            arrayList.add(new Point(currentPoint.x - 2, currentPoint.y));
        }
        if (currentPoint.y > 1 && !visited[currentPoint.x][currentPoint.y - 2]) {
            arrayList.add(new Point(currentPoint.x, currentPoint.y - 2));
        }
        if (currentPoint.x < width - width % 2 - 1 && !visited[currentPoint.x + 2][currentPoint.y]) {
            arrayList.add(new Point(currentPoint.x + 2, currentPoint.y));
        }
        if (currentPoint.y < height - height % 2 - 1 && !visited[currentPoint.x][currentPoint.y + 2]) {
            arrayList.add(new Point(currentPoint.x, currentPoint.y + 2));
        }
        return arrayList;
    }
}
