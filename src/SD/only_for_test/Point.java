package SD.only_for_test;

public class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point obj) {
        return (x == obj.x) && (y == obj.y);
    }
}
