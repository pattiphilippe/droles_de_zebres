package g43197.zebras.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Reserve class
 *
 * @author Philippe
 */
public class ReserveTest {

    /**
     * Checks free on corner (0,0) if nothing was added yet.
     */
    @Test
    public void testIsFree1() {
        Coordinates pos = new Coordinates(1, 1);
        Reserve res = new Reserve();
        boolean expResult = true;
        boolean result = res.isFree(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks free on pos(2,2) if nothing was added yet.
     */
    @Test
    public void testIsFree2() {
        Coordinates pos = new Coordinates(2, 2);
        Reserve instance = new Reserve();
        boolean expResult = true;
        boolean result = instance.isFree(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks free on corner (4,5) if nothing was added yet.
     */
    @Test
    public void testIsFree3() {
        Coordinates pos = new Coordinates(4, 5);
        Reserve instance = new Reserve();
        boolean expResult = true;
        boolean result = instance.isFree(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks used box (2,2) if animal added.
     */
    @Test
    public void testIsFree4() {
        int row = 2, col = 2;
        Coordinates pos = new Coordinates(2, 2);
        Reserve res = new Reserve();
        boolean expResult = false;
        Animal animal = new Animal(Species.ZEBRA, Color.RED);
        res.put(animal, row, col);
        boolean result = res.isFree(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks used box corner (2,2) if animal added.
     */
    @Test
    public void testIsFree5() {
        int row = 4, col = 0;
        Coordinates pos = new Coordinates(4, 0);
        Reserve res = new Reserve();
        boolean expResult = false;
        Animal animal = new Animal(Species.ZEBRA, Color.RED);
        res.put(animal, row, col);
        boolean result = res.isFree(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks used box (2,2) if animal added.
     */
    @Test
    public void testIsFree6() {
        int row = 0, col = 5;
        Coordinates pos = new Coordinates(0, 5);
        Reserve res = new Reserve();
        boolean expResult = false;
        Animal animal = new Animal(Species.ZEBRA, Color.RED);
        res.put(animal, row, col);
        boolean result = res.isFree(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks returns exception if pos is out of the reserve
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testIsFree7() {
        Coordinates pos = new Coordinates(10, 10);
        Reserve res = new Reserve();
        res.isFree(pos);
    }

    /**
     * Checks if gazelle well put
     */
    @Test
    public void testPut1() {
        int row = 0, col = 0;
        Animal animal = new Animal(Species.GAZELLE, Color.RED);
        Coordinates pos = new Coordinates(0, 0);
        Reserve res = new Reserve();
        res.put(animal, row, col);
        assertEquals(animal, res.getAnimal(pos));
    }

    /**
     * Checks if zebra well put
     */
    @Test
    public void testPut2() {
        int row = 0, col = 5;
        Animal animal = new Animal(Species.ZEBRA, Color.RED);
        Coordinates pos = new Coordinates(0, 5);
        Reserve res = new Reserve();
        res.put(animal, row, col);
        assertEquals(animal, res.getAnimal(pos));
    }

    /**
     * Checks if lion well put
     */
    @Test
    public void testPut3() {
        int row = 4, col = 0;
        Animal animal = new Animal(Species.LION, Color.RED);
        Coordinates pos = new Coordinates(4, 0);
        Reserve res = new Reserve();
        res.put(animal, row, col);
        assertEquals(animal, res.getAnimal(pos));
    }

    /**
     * Checks if elephant well put
     */
    @Test
    public void testPut4() {
        int row = 4, col = 5;
        Animal animal = new Animal(Species.ELEPHANT, Color.RED);
        Coordinates pos = new Coordinates(4, 5);
        Reserve res = new Reserve();
        res.put(animal, row, col);
        assertEquals(animal, res.getAnimal(pos));
    }

    /**
     * Checks if crocodile well put
     */
    @Test
    public void testPut5() {
        int row = 2, col = 2;
        Animal animal = new Animal(Species.CROCODILE, Color.RED);
        Coordinates pos = new Coordinates(2, 2);
        Reserve res = new Reserve();
        res.put(animal, row, col);
        assertEquals(animal, res.getAnimal(pos));
    }

    // Pour éviter la redondance de code, les tests de cas normaux pour
    // la méthode getAnimal ne sont pas écrits, la méthode étant déja  testées
    // dans les tests précédents.
    /**
     * Checks that animals are different on species
     */
    @Test
    public void testGetAnimal1() {
        int row = 1, col = 1;
        Coordinates pos = new Coordinates(1, 1);
        Reserve res = new Reserve();
        Animal animal = new Animal(Species.CROCODILE, Color.RED);
        Animal expResult = new Animal(Species.ELEPHANT, Color.RED);
        res.put(animal, row, col);
        Animal result = res.getAnimal(pos);
        Assert.assertNotEquals(expResult, result);
    }

    /**
     * Checks that animals are different on color
     */
    @Test
    public void testGetAnimal2() {
        int row = 4, col = 5;
        Coordinates pos = new Coordinates(4, 5);
        Reserve res = new Reserve();
        Animal animal = new Animal(Species.CROCODILE, Color.GREEN);
        Animal expResult = new Animal(Species.CROCODILE, Color.RED);
        res.put(animal, row, col);
        Animal result = res.getAnimal(pos);
        Assert.assertNotEquals(expResult, result);
    }

    /**
     * Checks that animals are equal.
     */
    @Test
    public void testGetAnimal3() {
        int row = 4, col = 5;
        Coordinates pos = new Coordinates(4, 5);
        Reserve res = new Reserve();
        Animal animal = new Animal(Species.CROCODILE, Color.RED);
        Animal expResult = new Animal(Species.CROCODILE, Color.RED);
        res.put(animal, row, col);
        Animal result = res.getAnimal(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks if receive the good adjacent positions when in left upper corner.
     */
    @Test
    public void testGetAdjacents1() {
        Coordinates position = new Coordinates(0, 0);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(1, 0);
        result.remove(adj);
        adj = new Coordinates(0, 1);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions when in right upper corner.
     */
    @Test
    public void testGetAdjacents2() {
        Coordinates position = new Coordinates(0, 5);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(1, 5);
        result.remove(adj);
        adj = new Coordinates(0, 4);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions when in left down corner.
     */
    @Test
    public void testGetAdjacents3() {
        Coordinates position = new Coordinates(4, 0);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(3, 0);
        result.remove(adj);
        adj = new Coordinates(4, 1);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions when in right down corner.
     */
    @Test
    public void testGetAdjacents4() {
        Coordinates position = new Coordinates(4, 5);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(3, 5);
        result.remove(adj);
        adj = new Coordinates(4, 4);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions on right side.
     */
    @Test
    public void testGetAdjacents5() {
        Coordinates position = new Coordinates(3, 5);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(2, 5);
        result.remove(adj);
        adj = new Coordinates(4, 5);
        result.remove(adj);
        adj = new Coordinates(3, 4);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions on left side.
     */
    @Test
    public void testGetAdjacents6() {
        Coordinates position = new Coordinates(2, 0);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(1, 0);
        result.remove(adj);
        adj = new Coordinates(3, 0);
        result.remove(adj);
        adj = new Coordinates(2, 1);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions on upper side.
     */
    @Test
    public void testGetAdjacents7() {
        Coordinates position = new Coordinates(0, 1);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(0, 0);
        result.remove(adj);
        adj = new Coordinates(0, 2);
        result.remove(adj);
        adj = new Coordinates(1, 1);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions on down side.
     */
    @Test
    public void testGetAdjacents8() {
        Coordinates position = new Coordinates(4, 3);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(4, 4);
        result.remove(adj);
        adj = new Coordinates(4, 2);
        result.remove(adj);
        adj = new Coordinates(3, 3);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Checks if receive the good adjacent positions in normal case.
     */
    @Test
    public void testGetAdjacents9() {
        Coordinates position = new Coordinates(2, 2);
        Reserve reserve = new Reserve();
        List<Coordinates> result = reserve.getAdjacents(position);
        Coordinates adj = new Coordinates(2, 1);
        result.remove(adj);
        adj = new Coordinates(1, 2);
        result.remove(adj);
        adj = new Coordinates(3, 2);
        result.remove(adj);
        adj = new Coordinates(2, 3);
        result.remove(adj);
        assert (result.isEmpty());
    }

    /**
     * Test of getSector method, of class Reserve, normal case.
     */
    @Test
    public void testGetSector1() {
        Coordinates coord = new Coordinates(0, 0);
        Reserve reserve = new Reserve();
        int expResult = 1;
        int result = reserve.getSector(coord).getNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSector method, of class Reserve, normal case.
     */
    @Test
    public void testGetSector2() {
        Coordinates coord = new Coordinates(2, 4);
        Reserve reserve = new Reserve();
        int expResult = 3;
        int result = reserve.getSector(coord).getNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSector method, of class Reserve, exception with invalid
     * coordinate.
     */
    @Test(expected = GameException.class)
    public void testGetSector3() {
        Coordinates coord = new Coordinates(7, 8);
        Reserve reserve = new Reserve();
        reserve.getSector(coord).getNumber();
    }
}
