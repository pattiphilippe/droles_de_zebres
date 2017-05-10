package g43197.zebras.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent the sectors in the reserve. Those are used when counting
 * the player's score.
 *
 * @author Philippe
 */
public class Sector {

    private final int number;
    private final List<Coordinates> coordinates;
    private final Reserve reserve;

    /**
     * Constructor of a new sector.
     *
     * @param number
     * @param reserve
     * @param coordinates
     */
    public Sector(int number, Reserve reserve, Coordinates... coordinates) {
        this.number = number;
        this.coordinates = new ArrayList<>();
        for (Coordinates coordinate : coordinates) {
            this.coordinates.add(coordinate);
        }
        this.reserve = reserve;
    }

    /**
     * Returns the number of the sector.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Checks if the sector contains a specific position.
     *
     * @param position the specified position
     * @return true if it contains it
     */
    public boolean contains(Coordinates position) {
        return coordinates.contains(position);
    }

    /**
     * Checks if this sector is full.
     *
     * @return true if it's full
     */
    public boolean isFull() {
        return coordinates.parallelStream()
                .noneMatch((coordinate) -> (reserve.isFree(coordinate)));
    }

    /**
     * Checks if the sector has a majority of animals of a given color.
     *
     * @param color the specified color
     * @return true if it has
     */
    public boolean hasMajority(Color color) {
        int goodColor = 0, otherColor = 0;
        Animal animal;
        for (Coordinates coordinate : this.coordinates) {
            animal = reserve.getAnimal(coordinate);
            if (animal != null) {
                if (animal.getColor() == color) {
                    goodColor++;
                } else {
                    otherColor++;
                }
            }
        }
        return goodColor > otherColor;
    }

    /**
     * Returns the current score of this sector.
     *
     * @return the score
     */
    public int getScore() {
        return coordinates.parallelStream().map(coord -> reserve.getAnimal(coord))
                .filter(Objects::nonNull).mapToInt(a -> a.getValue()).sum();
    }
}
