import java.util.*;

class Point {
    private float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void changeCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public void showPoint() {
        System.out.println(toString());
    }

}

class Polygon {
    private int n;
    private Point[] points;

    public Polygon(int n) {
        this.n = n;
        this.points = new Point[n];
    }

    public Polygon(float[] v) {
        this(v.length/2);

        for (int i = 0; i < this.n; ++i) {
            this.points[i]= new Point(v[2 * i], v[2 * i + 1]);
        }
    }

    public void showPolygon() {
        for (int i = 0; i < this.n; ++i)
            this.points[i].showPoint();
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float x1 = scanner.nextFloat();
        float y1 = scanner.nextFloat();
        float x2 = scanner.nextFloat();
        float y2 = scanner.nextFloat();

        Point point = new Point(x1, y1);
        point.showPoint();

        point.changeCoords(x2, y2);
        point.showPoint();

        int n = scanner.nextInt();
        float[] points = new float[n];

        for(int i = 0; i < n; i++) {
            points[i] = i;
        }

        Polygon polygon = new Polygon(points);
        System.out.println("Poligonul are urmatoarele coordonate:");
        polygon.showPolygon();

    }
}