package primitives;

import org.junit.jupiter.api.Test;
import java.lang.Math;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p = new Point(1, 1, 1);


    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0,-1,4), p.subtract(new Point(1, 2, -3)), "wrong subtract!");
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->p.subtract(p), "vector (0,0,0) is illegal"  ) ;
    }

    @Test
    void testAdd() {
        assertEquals(new Point(2, 3, 4), new Point(1,1,1).add(new Vector(1,2,3)), "wrong add");
    }

    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(14d, p.distanceSquared(new Point(2,3,4)), 0.0001, "wrong squared distance between 2 points" );
        // =============== Boundary Values Tests ==================
        assertEquals(0d, p.distanceSquared(p), 0.0001, "Wrong squared distance between the point and itself");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(Math.sqrt(14), p.distance(new Point(2,3,4)), 0.0001, "Wrong distance between 2 points");
        // =============== Boundary Values Tests ==================
        assertEquals(0d, p.distance(p), 0.0001, "Wrong squared distance between the point and itself");

    }
}