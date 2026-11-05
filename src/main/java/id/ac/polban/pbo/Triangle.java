package id.ac.polban.pbo;

public class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        super("Triangle");
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Alas dan tinggi harus positif");
        }
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }

    public double getBase() { return base; }
    public double getHeight() { return height; }
}