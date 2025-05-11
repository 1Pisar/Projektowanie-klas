abstract class Vehicle {
    protected String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public abstract void start();
}

class Car extends Vehicle {
    public Car(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("Samochód: " + name);
    }
}

class Bicycle extends Vehicle {
    public Bicycle(String name) {
        super(name);
    }

    @Override
    public void start() {
        System.out.println("Rower " + name);
    }
}

public class Zad_1 {
    public static void main(String[] args) {
        Vehicle car = new Car("Citroen C3");
        Vehicle bicycle = new Bicycle("Giant");

        car.start();
        bicycle.start();
    }
}
