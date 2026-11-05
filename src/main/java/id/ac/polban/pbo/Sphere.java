package id.ac.polban.pbo;

public class Sphere extends Shape {
    private final double radius;

    public Sphere(double radius) {
        super("Sphere");
        if (radius <= 0) {
            throw new IllegalArgumentException("Jari-jari harus positif");
        }
        this.radius = radius;
    }

    @Override
    public double area() {
        return 4 * Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }
}