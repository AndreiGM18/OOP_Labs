package task5;

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

    public boolean equals(CandyBox c) {
        if (this.origin == c.origin && this.flavor == c.flavor)
            return true;

        return false;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CandyBox)) {
            return false;
        }

        return equals((CandyBox) o);
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

    public void printLindtDim() {
        System.out.println("Lindt: " + this.length + " " + this.width + " " + this.height);
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

    public void printBaravelliDim() {
        System.out.println("Baravelli: " + this.radius + " " + this.height);
    }
}

class ChocAmor extends CandyBox {
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

    public void printChocAmorDim() {
        System.out.println("ChocAmor: " + this.length);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String flavor = scanner.next();
        String origin = scanner.next();
        float length = scanner.nextFloat();
        float width = scanner.nextFloat();
        float height = scanner.nextFloat();
        float radius = scanner.nextFloat();

        CandyBox lindtBox = new Lindt(flavor, origin, length, width, height);
        CandyBox baravelliBox = new Baravelli(flavor, origin, radius, height);
        CandyBox chocAmorBox = new ChocAmor(flavor, origin, length);

        ((Lindt)lindtBox).printLindtDim();
        ((Baravelli)baravelliBox).printBaravelliDim();
        ((ChocAmor)chocAmorBox).printChocAmorDim();
    }
}