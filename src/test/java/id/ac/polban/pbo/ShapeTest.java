package id.ac.polban.pbo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pengujian Kelas Shape dan Polymorphism")
class ShapeTest {

    @Test
    @DisplayName("Sphere menghitung luas permukaan dengan benar")
    void area_sphere_menghitungLuasPermukaan() {
        Shape shape = new Sphere(10.0); // Polimorfisme: referensi Shape menunjuk objek Sphere
        double expectedArea = 4 * Math.PI * 100.0;
        assertEquals(expectedArea, shape.area(), 0.001);
    }
}