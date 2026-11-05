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

    // LATIHAN 4: KELAS TRIANGLE
    @Test
    @DisplayName("L4: Triangle menghitung luas alas dan tinggi dengan benar")
    void area_triangle_menghitungLuasDenganBenar() {
        Shape shape = new Triangle(10.0, 5.0);
        assertEquals(25.0, shape.area(), 0.001);
    }

    @Test
    @DisplayName("L4: Triangle dengan alas atau tinggi tidak valid melempar IllegalArgumentException")
    void constructor_triangleInputTidakValid_melemparException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new Triangle(-5.0, 10.0)
        );
        assertEquals("Alas dan tinggi harus positif", ex.getMessage());
    }
}