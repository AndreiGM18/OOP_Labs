package task3;

import java.util.*;

class CandyBox {
    protected String flavor, origin;

    CandyBox() {
        this.flavor = "sugar-free";
        this.origin = "Switzerland";
    }

    CandyBox(String flavor, String origin) {
        this.flavor = flavor;
        this.origin = origin;
    }

    public float getVolume() {
        return 0;
    }

    public String toString() {
        return "The " + this.origin + " " + this.flavor + " chocolate";
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandyBox candyBox)) return false;
        return Objects.equals(flavor, candyBox.flavor) && Objects.equals(origin, candyBox.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavor, origin);
    }
}

class Lindt extends CandyBox {
    private float length, width, height;

    public Lindt() {
        this.length = 0;
        this.width = 0;
        this.height = 0;
    }

    public Lindt(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Lindt(String flavor, String origin, float length, float width, float height) {
        super(flavor, origin);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public float getVolume() {
        return length * width * height;
    }

    public String toString() {
        return "Lindt: " + super.toString() + " has volume " + this.getVolume();
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}

class Baravelli extends CandyBox {

    private float radius, height;

    public Baravelli() {
        this.radius = 0;
        this.height = 0;
    }

    public Baravelli(float radius, float height) {
        this.radius = radius;
        this.height = height;
    }

    public Baravelli(String flavor, String origin, float radius, float height) {
        super(flavor,origin);
        this.radius = radius;
        this.height = height;
    }

    public float getVolume() {
        return (float)Math.PI * (float)Math.pow(radius, 2) * height;
    }

    public String toString() {
        return "Baravelli: " + super.toString() + " has volume " + this.getVolume();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}

class ChocAmor extends CandyBox{
    private float length;

    public ChocAmor() {
        this.length = 0;
    }

    public ChocAmor(float length) {
        this.length = length;
    }

    public ChocAmor(String flavor, String origin, float length) {
        super(flavor, origin);
        this.length = length;
    }

    public float getVolume() {
        return (float)Math.pow(length, 3);
    }

    public String toString() {
        return "ChocAmor: " + super.toString() + " has volume " + this.getVolume();
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String flavor = scanner.next();
        String origin = scanner.next();

        CandyBox candyBox = new CandyBox(flavor, origin);
        CandyBox candyBoxCopy = new CandyBox(flavor, origin);
        CandyBox candyBox2 = new CandyBox("vanilla", "Belgium");

        Lindt lindtBox = new Lindt();
        Lindt lindtBoxCopy = new Lindt(0.0f, 0.0f, 0.0f);
        Lindt lindtBox2 = new Lindt("vanilla", "Belgium", 2.0f, 2.0f, 4.0f);

        System.out.println(candyBox.equals(candyBoxCopy));
        System.out.println(candyBox.equals(candyBox2));

        System.out.println(lindtBox.equals(lindtBoxCopy));
        System.out.println(lindtBox.equals(lindtBox2));
    }
}