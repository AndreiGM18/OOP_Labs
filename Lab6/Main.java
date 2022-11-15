import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Car {
    private int price;
    public CarType type;
    private int year;

    public enum CarType {
        MERCEDES,
        FIAT,
        SKODA
    }

    public Car(int price, CarType type, int year) {
        this.price = price;
        this.type = type;
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", carType=" + type +
                ", year=" + year +
                '}';
    }
}

interface Offer {
    int getDiscount(Car car);
}

class Dealership {
    private class BrandOffer implements Offer {
        private int brandDiscount;

        public BrandOffer(Car car) {
            if (car.type == Car.CarType.MERCEDES) {
                this.brandDiscount = car.getPrice() * 5 / 100;
            }
            if (car.type == Car.CarType.FIAT) {
                this.brandDiscount = car.getPrice() * 10 / 100;
            }
            if (car.type == Car.CarType.SKODA) {
                this.brandDiscount = car.getPrice() * 15 / 100;
            }
        }

        public int getDiscount(Car car) {
            System.out.println("Applying Brand discount: " + brandDiscount + " euros");
            return brandDiscount;
        }
    }

    private class DealerOffer implements Offer {
        private int dealerDiscount;

        public DealerOffer(Car car) {
            if (car.type == Car.CarType.MERCEDES) {
                this.dealerDiscount = 300 * (2022 - car.getYear());
            }
            if (car.type == Car.CarType.FIAT) {
                this.dealerDiscount = 100 * (2022 - car.getYear());
            }
            if (car.type == Car.CarType.SKODA) {
                this.dealerDiscount = 150 * (2022 - car.getYear());
            }
        }

        public int getDiscount(Car car) {
            System.out.println("Applying Dealer discount: " + dealerDiscount + " euros");
            return dealerDiscount;
        }
    }

    private class SpecialOffer implements Offer {
        private int specialDiscount;

        public SpecialOffer(Car car) {
            this.specialDiscount = Main.rand.nextInt(1000);
            }

        public int getDiscount(Car car) {
            System.out.println("Applying Special discount: " + specialDiscount + " euros");
            return specialDiscount;
        }
    }

    public int getFinalPrice(Car car) {
        BrandOffer brandOffer = new BrandOffer(car);
        DealerOffer dealerOffer = new DealerOffer(car);
        SpecialOffer specialOffer = new SpecialOffer(car);

        int finalDiscount = brandOffer.getDiscount(car) + dealerOffer.getDiscount(car) + specialOffer.getDiscount(car);
        int finalPrice = car.getPrice() - finalDiscount;
        return finalPrice;
    }

    void negotiate(Car car, Offer offer) {
        System.out.println("Applying Client discount: " + offer.getDiscount(car) + " euros");
    }
}

public class Main {
    public static Random rand = new Random(20);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int taskNum = scanner.nextInt();

        switch(taskNum) {
            case 1:
                Car mercedes = new Car(20000, Car.CarType.MERCEDES, 2019);
                Car fiat = new Car(7000, Car.CarType.FIAT, 2020);
                Car skoda = new Car(12000, Car.CarType.SKODA, 2022);
                Dealership dealership = new Dealership();

                break;
            case 2:
                dealership = new Dealership();
                Car mercedesOne = new Car(20000, Car.CarType.MERCEDES, 2010);
                Car mercedesTwo = new Car(35000, Car.CarType.MERCEDES, 2015);
                Car fiatOne = new Car(3500, Car.CarType.FIAT, 2008);
                Car fiatTwo = new Car(7000, Car.CarType.FIAT, 2010);
                Car skodaOne = new Car(12000, Car.CarType.SKODA, 2015);
                Car skodaTwo = new Car(25000, Car.CarType.SKODA, 2021);

                dealership.getFinalPrice(mercedesOne);
                dealership.getFinalPrice(mercedesTwo);
                dealership.getFinalPrice(fiatOne);
                dealership.getFinalPrice(fiatTwo);
                dealership.getFinalPrice(skodaOne);
                dealership.getFinalPrice(skodaTwo);

                break;
            case 3:
                dealership = new Dealership();

                Car mercedesThree = new Car(20000, Car.CarType.MERCEDES, 2019);

                Offer offer = new Offer() {
                    @Override
                    public int getDiscount(Car car) {
                        return car.getPrice() * 5 / 100;
                    }
                };

                dealership.negotiate(mercedesThree, offer);

                break;
            case 4:
                ArrayList<Car> carArrayList = new ArrayList<>();
                Car skodaThree = new Car(20000, Car.CarType.SKODA, 2019);
                Car mercedesFour = new Car(30000, Car.CarType.MERCEDES, 2019);
                Car mercedesFive = new Car(50000, Car.CarType.MERCEDES, 2021);
                Car fiatThree = new Car(10000, Car.CarType.FIAT, 2018);

                carArrayList.add(skodaThree);
                carArrayList.add(mercedesFour);
                carArrayList.add(mercedesFive);
                carArrayList.add(fiatThree);
                for (Car car : carArrayList)
                    System.out.println(car.toString());

                carArrayList.removeIf(car -> car.getPrice() > 25000);

                for (Car car : carArrayList)
                    System.out.println(car.toString());

                break;
        }
    }
}
