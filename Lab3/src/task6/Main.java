package task6;

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

    public void printDim() {

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

    public void printDim() {
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

    public void printDim() {
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

    public void printDim() {
        System.out.println("ChocAmor: " + this.length);
    }
}

class CandyBag {
    ArrayList<CandyBox> candies = new ArrayList<>();

    CandyBag() {
        Lindt Lindt1 = new Lindt( "cherry", "Austria",20, (float)5.4, (float)19.2);
        Lindt Lindt2 = new Lindt("apricot", "Austria",20, (float)5.4, (float)19.2);
        Lindt Lindt3 = new Lindt("strawberry","Austria", 20, (float)5.4, (float)19.2);
        Baravelli Bara = new Baravelli("grape", "Italy", (float)6.7, (float)8.7);
        ChocAmor Choc1 = new ChocAmor("coffee", "France", (float)5.5);
        ChocAmor Choc2 = new ChocAmor("vanilla", "France", (float)5.5);

        candies.add(Lindt1);
        candies.add(Lindt2);
        candies.add(Lindt3);
        candies.add(Bara);
        candies.add(Choc1);
        candies.add(Choc2);
    }

    public ArrayList<CandyBox> getCandies() {
        return candies;
    }

    public void setCandies(ArrayList<CandyBox> candies) {
        this.candies = candies;
    }
}

class Area {
    private CandyBag candybag;
    private int number;
    private String street, message;

    public Area() {
        this.number = 0;
        this.street = "nowhere";
    }

    public Area(int number, String street, String message, CandyBag candybag) {
        this.candybag = candybag;
        this.number = number;
        this.street = street;
        this.message = message;
    }

    public void getBirthdayCard() {
        System.out.print("Street " + this.street + ", number " + this.number + "\n\n" + this.message + "\n\n");

        for (CandyBox candybox : candybag.candies) {
            if (candybox instanceof Lindt)
                ((Lindt)candybox).printLindtDim();
            if (candybox instanceof Baravelli)
                ((Baravelli)candybox).printBaravelliDim();
            if (candybox instanceof ChocAmor)
                ((ChocAmor)candybox).printChocAmorDim();
        }
    }

    public void getBirthdayCardv2() {
        System.out.print("Street " + this.street + ", number " + this.number + "\n\n" + this.message + "\n\n");

        for (CandyBox candybox : candybag.candies) {
            candybox.printDim();
        }
    }


    public CandyBag getCandybag() {
        return candybag;
    }

    public void setCandybag(CandyBag candybag) {
        this.candybag = candybag;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String street = scanner.next();
        String message = scanner.next();

        Area area = new Area(number, street, message, new CandyBag());

        System.out.println("With instanceof:");
        area.getBirthdayCard();
        System.out.println();
        System.out.println("Without instanceof:");
        area.getBirthdayCardv2();
    }
}
