package g43197.zebras.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class ImpalaJonesTest {

    public ImpalaJonesTest() {
        ImpalaJones imp = new ImpalaJones();
        assertTrue(imp != null);
        assertEquals(imp.getPosition(), -1);
    }

    /**
     * Test of init method, of class ImpalaJones.
     */
    @Test
    public void testInit1() {
        int nb = 0;
        ImpalaJones instance = new ImpalaJones();
        instance.init(nb);
        assertEquals(instance.getPosition(), 0);
    }

    /**
     * Test of init method, of class ImpalaJones.
     */
    @Test
    public void testInit2() {
        int nb = 0;
        ImpalaJones instance = new ImpalaJones();
        instance.init(nb);
        assertEquals(instance.getPosition(), 0);
    }

    /**
     * Test of getPosition method, of class ImpalaJones.
     */
    @Test
    public void testGetPosition1() {
        ImpalaJones instance = new ImpalaJones();
        int expResult = -1;
        int result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method after movement.
     */
    @Test
    public void testGetPosition2() {
        ImpalaJones instance = new ImpalaJones();
        int expResult = 7;
        instance.move(8);
        int result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test with distance = 0.
     */
    @Test
    public void testMove1() {
        int distance = 0;
        ImpalaJones instance = new ImpalaJones();
        instance.move(distance);
        assertEquals(-1, instance.getPosition());
    }

    /**
     * Test with normal case.
     */
    @Test
    public void testMove2() {
        int distance = 5;
        ImpalaJones instance = new ImpalaJones();
        instance.move(distance);
        assertEquals(4, instance.getPosition());
    }

    /**
     * Test after dubbel move.
     */
    @Test
    public void testMove3() {
        int distance = 5;
        ImpalaJones instance = new ImpalaJones();
        instance.move(distance);
        instance.move(distance);
        assertEquals(9, instance.getPosition());
    }

    /**
     * Test after negative move.
     */
    @Test
    public void testMove4() {
        int distance = -5;
        ImpalaJones instance = new ImpalaJones();
        instance.init(0);
        instance.move(distance);
        assertEquals(17, instance.getPosition());
    }

    /**
     * Test with position that is not up.
     */
    @Test
    public void testIsUp1() {
        ImpalaJones instance = new ImpalaJones();
        boolean expResult = false;
        boolean result = instance.isUp();
        assertEquals(expResult, result);
    }

    /**
     * Test with minimal upper position.
     */
    @Test
    public void testIsUp2() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(1);
        boolean expResult = true;
        boolean result = instance.isUp();
        assertEquals(expResult, result);
    }

    /**
     * Test with maximal upper position.
     */
    @Test
    public void testIsUp3() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(6);
        boolean expResult = true;
        boolean result = instance.isUp();
        assertEquals(expResult, result);
    }

    /**
     * Test with position that is not down.
     */
    @Test
    public void testIsDown1() {
        ImpalaJones instance = new ImpalaJones();
        boolean expResult = false;
        boolean result = instance.isDown();
        assertEquals(expResult, result);
    }

    /**
     * Test with position that is down, on min position.
     */
    @Test
    public void testIsDown2() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(12);
        boolean expResult = true;
        boolean result = instance.isDown();
        assertEquals(expResult, result);
    }

    /**
     * Test with position that is down, on max position.
     */
    @Test
    public void testIsDown3() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(17);
        boolean expResult = true;
        boolean result = instance.isDown();
        assertEquals(expResult, result);
    }

    /**
     * Test with position that is not on the left side.
     */
    @Test
    public void testIsLeft1() {
        ImpalaJones instance = new ImpalaJones();
        boolean expResult = false;
        boolean result = instance.isLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test with position on the left side, on min position.
     */
    @Test
    public void testIsLeft2() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(18);
        boolean expResult = true;
        boolean result = instance.isLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test with position on the left side, on max position.
     */
    @Test
    public void testIsLeft3() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(22);
        boolean expResult = true;
        boolean result = instance.isLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test with position that is not on the right.
     */
    @Test
    public void testIsRight1() {
        ImpalaJones instance = new ImpalaJones();
        boolean expResult = false;
        boolean result = instance.isRight();
        assertEquals(expResult, result);
    }

    /**
     * Test with position on the right, on min position.
     */
    @Test
    public void testIsRight2() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(7);
        boolean expResult = true;
        boolean result = instance.isRight();
        assertEquals(expResult, result);
    }

    /**
     * Test with position on the right, on max position.
     */
    @Test
    public void testIsRight3() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(11);
        boolean expResult = true;
        boolean result = instance.isRight();
        assertEquals(expResult, result);
    }

    /**
     * Checks returns -1 if Impala is on a row.
     */
    @Test
    public void testGetColumn1() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(8);
        int expResult = -1;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns good column when isUp.
     */
    @Test
    public void testGetColumn2() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(3);
        int expResult = 2;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns good column when isUp.
     */
    @Test
    public void testGetColumn3() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(15);
        int expResult = 2;
        int result = instance.getColumn();
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns -1 when he is on a column.
     */
    @Test
    public void testGetRow1() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(2);
        int expResult = -1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns good row when is right.
     */
    @Test
    public void testGetRow2() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(8);
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns good row when is right.
     */
    @Test
    public void testGetRow3() {
        ImpalaJones instance = new ImpalaJones();
        instance.move(20);
        int expResult = 2;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns true when move on a empty column/row.
     */
    @Test
    public void testCheckMove1() {
        Reserve reserve = new Reserve();
        int distance = 2;
        ImpalaJones impala = new ImpalaJones();
        boolean expResult = true;
        boolean result = impala.checkMove(reserve, distance);
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns false when move on a full column/row.
     */
    @Test
    public void testCheckMove2() {
        Reserve reserve = new Reserve();
        Pieces pieces = new Pieces();
        Animal animal;
        int col;
        for (col = 0; col < 6; col++) {
            animal = pieces.getAnimal(Color.RED, Species.GAZELLE);
            reserve.put(animal, 0, col);
        }
        int distance = 7;
        ImpalaJones impala = new ImpalaJones();
        boolean expResult = false;
        boolean result = impala.checkMove(reserve, distance);
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns true when move on a column/row which has some animals
     * on it but is not full.
     */
    @Test
    public void testCheckMove3() {
        Reserve reserve = new Reserve();
        Pieces pieces = new Pieces();
        Animal animal;
        int row, col;
        for (int i = 0; i < 3; i++) {
            animal = pieces.getAnimal(Color.RED, Species.GAZELLE);
            row = i + 1;
            col = 2 + 1;
            reserve.put(animal, row, col);
        }
        ImpalaJones impala = new ImpalaJones();
        impala.move(14);
        int distance = 1;
        boolean expResult = true;
        boolean result = impala.checkMove(reserve, distance);
        assertEquals(expResult, result);
    }

    /**
     * Checks return true when impala is on same column.
     */
    @Test
    public void testValid1() {
        Coordinates pos = new Coordinates(4, 0);
        ImpalaJones impala = new ImpalaJones();
        impala.move(1);
        boolean expResult = true;
        boolean result = impala.valid(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks return true when impala is on same row.
     */
    @Test
    public void testValid2() {
        Coordinates pos = new Coordinates(1, 3);
        ImpalaJones impala = new ImpalaJones();
        impala.move(8);
        boolean expResult = true;
        boolean result = impala.valid(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks return false when impala is not on same column.
     */
    @Test
    public void testValid3() {
        Coordinates pos = new Coordinates(1, 3);
        ImpalaJones impala = new ImpalaJones();
        impala.move(1);
        boolean expResult = false;
        boolean result = impala.valid(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks return false when impala is not on same row.
     */
    @Test
    public void testValid4() {
        Coordinates pos = new Coordinates(1, 3);
        ImpalaJones impala = new ImpalaJones();
        impala.move(10);
        boolean expResult = false;
        boolean result = impala.valid(pos);
        assertEquals(expResult, result);
    }

    /**
     * Checks if returns 1 when reserve empty.
     */
    @Test
    public void testFindFirst1() {
        Reserve reserve = new Reserve();
        ImpalaJones impala = new ImpalaJones();
        impala.move(1);
        int expResult = 1;
        int result = impala.findFirst(reserve);
        assertEquals(expResult, result);
    }

    /**
     * Checks normal case, reserve partially full.
     */
    @Test
    public void testFindFirst2() {
        Reserve reserve = new Reserve();
        Animal animal = new Animal(Species.GAZELLE, Color.RED);
        int row, col;
        for (row = 0; row < 5; row++) {
            for (col = 0; col < 6; col++) {
                reserve.put(animal, row, col);
            }
        }
        row = 1;
        col = 5;
        reserve.put(null, row, col);
        ImpalaJones impala = new ImpalaJones();
        impala.init(0);
        int expResult = 5;
        int result = impala.findFirst(reserve);
        assertEquals(expResult, result);
    }

}
