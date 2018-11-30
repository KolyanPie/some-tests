package SD.only_for_test;

import java.util.ArrayList;
import java.util.Stack;

public class MapGenerator {
    private int width;
    private int height;
    private int homes;
    private Elements[][] field;
    private int count = 0;
    private House[] houses;

    public House[] getHouses() {
        return houses;
    }

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
            } else {
                currentPoint = stack.pop();
            }
        }
        generateHouses();
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

    private void generateHouses() {
        ArrayList<Point> arrayList = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals(Elements.ROAD)) {
                    arrayList.add(new Point(i, j));
                }
            }
        }
        int randNum;
        ArrayList<Point> neighbours;
        Point house;
        houses = new House[homes];
        for (int i = 0; i < homes && arrayList.size() > 0; ) {
            randNum = (int) (Math.random() * arrayList.size());
            neighbours = getEmptyNeighbours(arrayList.get(randNum));
            if (neighbours.size() != 0) {
                house = neighbours.get((int) (Math.random() * neighbours.size()));
                field[house.x][house.y] = Elements.HOUSE;
                houses[i] = new House(arrayList.get(randNum), house);
                i++;
            }
            else {
                arrayList.remove(randNum);
            }
        }
    }

    private ArrayList<Point> getEmptyNeighbours(Point currentPoint) {
        ArrayList<Point> arrayList = new ArrayList<>();
        if (currentPoint.x > 0 && field[currentPoint.x - 1][currentPoint.y].equals(Elements.GRASS)) {
            arrayList.add(new Point(currentPoint.x - 1, currentPoint.y));
        }
        if (currentPoint.y > 0 && field[currentPoint.x][currentPoint.y - 1].equals(Elements.GRASS)) {
            arrayList.add(new Point(currentPoint.x, currentPoint.y - 1));
        }
        if (currentPoint.x < width - 1 && field[currentPoint.x + 1][currentPoint.y].equals(Elements.GRASS)) {
            arrayList.add(new Point(currentPoint.x + 1, currentPoint.y));
        }
        if (currentPoint.y < height - 1 && field[currentPoint.x][currentPoint.y + 1].equals(Elements.GRASS)) {
            arrayList.add(new Point(currentPoint.x, currentPoint.y + 1));
        }
        return arrayList;
    }
}
