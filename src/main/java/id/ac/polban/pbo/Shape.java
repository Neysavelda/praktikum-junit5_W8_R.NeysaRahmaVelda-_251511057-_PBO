package id.ac.polban.pbo;

public abstract class Shape {
    private final String name;

    public Shape(String name) {
        this.name = name;
    }

    public abstract double area();

    public String getName() {
        return name;
    }
}