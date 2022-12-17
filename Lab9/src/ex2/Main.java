package ex2;

import java.util.ArrayList;

class House {
    private String location; // mandatory
    private int numFloors; // mandatory
    private int numRooms; // mandatory
    private boolean pool; // optional
    private boolean appliances; // optional
    private boolean solarPanels; // optional
    private String securityCompany; // optional

    private House(HouseBuilder builder) {
        this.location = builder.location;
        this.numFloors = builder.numFloors;;
        this.numRooms = builder.numRooms;;
        this.pool = builder.pool;
        this.appliances = builder.appliances;;
        this.solarPanels = builder.solarPanels;;
        this.securityCompany = builder.securityCompany;;
    }

    public String getLocation() {
        return location;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public boolean isPool() {
        return pool;
    }

    public boolean isAppliances() {
        return appliances;
    }

    public boolean isSolarPanels() {
        return solarPanels;
    }

    public String getSecurityCompany() {
        return securityCompany;
    }

    @Override
    public String toString() {
        return "House{" +
                "location='" + location + '\'' +
                ", numFloors=" + numFloors +
                ", numRooms=" + numRooms +
                ", pool=" + pool +
                ", appliances=" + appliances +
                ", solarPanels=" + solarPanels +
                ", securityCompany='" + securityCompany + '\'' +
                '}';
    }

    static class HouseBuilder {
        private String location; // mandatory
        private int numFloors; // mandatory
        private int numRooms; // mandatory
        private boolean pool = false; // optional
        private boolean appliances = false; // optional
        private boolean solarPanels = false; // optional
        private String securityCompany = null; // optional

        public HouseBuilder(String location, int numFloors, int numRooms) {
            this.location = location;
            this.numFloors = numFloors;
            this.numRooms = numRooms;
        }

        public HouseBuilder pool(boolean pool) {
            this.pool = pool;
            return this;
        }

        public HouseBuilder appliances(boolean appliances) {
            this.appliances = appliances;
            return this;
        }

        public HouseBuilder solarPanels(boolean solarPanels) {
            this.solarPanels = solarPanels;
            return this;
        }

        public HouseBuilder securityCompany(String securityCompany) {
            this.securityCompany = securityCompany;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}

class Main {
    private static String spacerSymbols = new String(new char[40]).replace("\0", "-");

    public static void main(String[] args) {
        String spacerSymbols = new String(new char[40]).replace("\0", "-");

        House house = new House.HouseBuilder("Piata Unirii", 3, 10)
                .pool(true)
                .securityCompany("POO_Security")
                .build();

        printOutputSpacerFor("testHouse");
        testHouse(house);
    }

    private static void printOutputSpacerFor(String test) {
        System.out.println(spacerSymbols + test + spacerSymbols);
    }

    private static void testHouse(House house) {
        System.out.println(house.getLocation());
        System.out.println(house.getNumFloors());
        System.out.println(house.getNumRooms());
        System.out.println(house.isPool());
        System.out.println(house.getSecurityCompany());
        System.out.println(house.isAppliances());
        System.out.println(house.isSolarPanels());
    }
}
