package g43197.zebras.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philippe
 */
public class PiecesTest {

    /*
    idées de tests:
    constr:
        check with getnbAnimals
    getAnimal:
        check g R, L V, pls en une fois, tous de un, trop de un, mauvais param
    has available:
        vider liste et tester, liste pleine,
    getnbAnimals:
        voir bon nb début, après tirer un ou pls, aucune occurence
     */
    /**
     * Checks if the inventory is well initialised with the right number of
     * animals in both colors.
     */
    @Test
    public void testPieces() {
        Pieces inv = new Pieces();
        int[] expNbAnimals = new int[]{6, 5, 1, 1, 2};
        Species[] species = Species.values();
        int result;
        for (Color color : Color.values()) {
            for (int s = 0; s < species.length; s++) {
                result = inv.getNbAnimals(color, species[s]);
                assertEquals(expNbAnimals[s], result);
            }
        }
    }

    /**
     * Checks normal case: getting one animal.
     */
    @Test
    public void testGetAnimal1() {
        Color color = Color.RED;
        Species species = Species.GAZELLE;
        Pieces inv = new Pieces();
        Animal expResult = new Animal(species, color);
        Animal result = inv.getAnimal(color, species);
        assertEquals(expResult, result);
    }

    /**
     * Tries to get a unique animal.
     */
    @Test
    public void testGetAnimal2() {
        Color color = Color.GREEN;
        Species species = Species.LION;
        Pieces inv = new Pieces();
        Animal expResult = new Animal(species, color);
        Animal result = inv.getAnimal(color, species);
        assertEquals(expResult, result);
    }

    /**
     * Checks if can get to many animals of the same type.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetAnimal3() {
        Color color = Color.RED;
        Species species = Species.CROCODILE;
        Pieces inv = new Pieces();
        for (int i = 0; i < 3; i++) {
            Animal expResult = new Animal(species, color);
            Animal result = inv.getAnimal(color, species);
            assertEquals(expResult, result);
        }
    }

    /**
     * Checks method returns true if there's an animal left.
     */
    @Test
    public void testHasAvailable1() {
        Pieces inv = new Pieces();
        boolean expResult = true;
        boolean result = inv.hasAvailable();
        assertEquals(expResult, result);
    }

    /**
     * Checks method returns false if there isn't any animal left.
     */
    @Test
    public void testHasAvailable2() {
        Pieces inv = new Pieces();
        boolean expResult = false;
        for (Color color : Color.values()) {
            for (Species species : Species.values()) {
                /*In next for, i is not increased, because the limit is reducing*/
                for (int i = 0; i < inv.getNbAnimals(color, species);) {
                    inv.getAnimal(color, species);
                }
            }
        }
        boolean result = inv.hasAvailable();
        assertEquals(expResult, result);
    }

    /**
     * Checks normal case.
     */
    @Test
    public void testGetNbAnimals1() {
        Color color = Color.GREEN;
        Species species = Species.CROCODILE;
        Pieces inv = new Pieces();
        int expResult = 2;
        int result = inv.getNbAnimals(color, species);
        assertEquals(expResult, result);
    }

    /**
     * Checks after an animal was taken out of the inventory.
     */
    @Test
    public void testGetNbAnimals2() {
        Color color = Color.RED;
        Species species = Species.GAZELLE;
        Pieces inv = new Pieces();
        int expResult = 5;
        inv.getAnimal(color, species);
        int result = inv.getNbAnimals(color, species);
        assertEquals(expResult, result);
    }

    /**
     * Checks when there's no animal of that type left.
     */
    @Test
    public void testGetNbAnimals3() {
        Color color = Color.GREEN;
        Species species = Species.ELEPHANT;
        Pieces inv = new Pieces();
        inv.getAnimal(color, species);
        int expResult = 0;
        int result = inv.getNbAnimals(color, species);
        assertEquals(expResult, result);
    }
}
