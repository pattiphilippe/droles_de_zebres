package g43197.zebras.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory of the game.
 *
 * @author Philippe
 */
public class Pieces {

    private final List<Animal> animals;
    private static final int NBGAZELLES = 6;
    private static final int NBZEBRAS = 5;
    private static final int NBLIONS = 1;
    private static final int NBELEPHANTS = 1;
    private static final int NBCROCODILE = 2;

    /**
     * Creates new list of animals at start of game.
     */
    public Pieces() {
        animals = new ArrayList<>();

        addAnimals(NBGAZELLES, Species.GAZELLE);
        addAnimals(NBZEBRAS, Species.ZEBRA);
        addAnimals(NBLIONS, Species.LION);
        addAnimals(NBELEPHANTS, Species.ELEPHANT);
        addAnimals(NBCROCODILE, Species.CROCODILE);
    }

    private void addAnimals(int nb, Species species) {
        for (Color color : Color.values()) {
            for (int i = 0; i < nb; i++) {
                animals.add(new Animal(species, color));
            }
        }
    }

    /**
     * Gets an animal of the inventory. Takes him out of the inventory at the
     * same time.
     *
     * @param color specified color
     * @param species specified specie
     * @return the animal
     */
    public Animal getAnimal(Color color, Species species) {
        Animal animal = new Animal(species, color);
        int pos = animals.indexOf(animal);
        animal = animals.get(pos);
        animals.remove(pos);
        return animal;
    }

    /**
     * Checks if there's any animal left to place.
     *
     * @return true if there an animal left.
     */
    public boolean hasAvailable() {
        return !animals.isEmpty();
    }

    /**
     * Checks if there is still ananimal left of the player with the specified
     * color.
     *
     * @param color specified color
     * @return true if there's at least an animal
     */
    public boolean hasAvailable(Color color) {
        int nbAnimals = 0;
        for (Species species : Species.values()) {
            nbAnimals += getNbAnimals(color, species);
        }
        return nbAnimals > 0;
    }

    /**
     * Return the number of animals left in the inventory with specific color
     * and species.
     *
     * @param color specified color
     * @param species specified specie
     * @return nbAnimals
     */
    public int getNbAnimals(Color color, Species species) {
        int nbAnimals = 0;
        Animal a = new Animal(species, color);
        return this.animals.stream().filter((animal) -> (animal.equals(a)))
                .map((_item) -> 1).reduce(nbAnimals, Integer::sum);
    }

    /**
     * Puts an animal in the reserve.
     *
     * @param animal specified animal
     * @throws GameException if the animal isn't in run state
     */
    public void putAnimal(Animal animal) {
        if (animal.getState() != AnimalState.RUN) {
            throw new GameException("Animal shouldn't"
                    + " be placed back in pieces, not in run state");
        }
        animals.add(animal);
    }
}
