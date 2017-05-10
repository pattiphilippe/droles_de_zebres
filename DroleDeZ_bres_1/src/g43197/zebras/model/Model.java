package g43197.zebras.model;

import java.util.List;

/**
 * Facade of Funny Zebras.
 * <a href=https://en.wikipedia.org/wiki/Facade_pattern3>Facade Pattern</a>
 * <a href=https://fr.wikipedia.org/wiki/Fa%C3%A7ade>Patron de conception</a>
 * @author esiProf and Patti Philippe
 */
public interface Model {

    /**
     * Start a match and reset attributes.
     */
    void start();

    /**
     * Set Impala Jones first position.
     *
     * @param position of Impala Jones at the beginning of game
     * @throws GameException if game's status isn't GameStatus.INIT.
     */
    void setImpalaJonesFirstPosition(int position);

    /**
     * Put an animal in the Board. Put an animal of the given species for the
     * current player.
     *
     * @param position position on the board
     * @param species species of an animal
     * @throws GameException if
     * <ul>
     * <li>game's status isn't Status.ANIMAL</li>
     * <li>or Impala Jones isn't on the the same row or column</li>
     * <li>or that position is not free</li>
     * <li>or the current player doesn't have that a tile of that species to
     * play anymore</li>
     * </ul>
     */
    void putAnimal(Coordinates position, Species species);

    /**
     * Move Impala Jones some steps forward.
     *
     * @param distance number of step
     * @throws GameException if
     * <ul>
     * <li>game's status isn't Status.IMPALA</li>
     * <li>or ImpalaJones will arrive on a full row or column</li>
     * <li>or the distance is too large</li>
     * </ul>
     */
    void moveImpalaJones(int distance);

    /**
     * Return true if the game is over.
     *
     * @return true if the game is over
     */
    boolean isOver();

    /**
     * Return the state of the game.
     *
     * @return the state of the game
     */
    GameStatus getStatus();

    /**
     * Return the current player color.
     *
     * @return the current player color
     */
    Color getCurrentColor();

    /**
     * Return the list of all player.
     *
     * @return the list of all player
     */
    List<Player> getPlayers();

    /**
     * Return the reserve.
     *
     * @return the reserve.
     */
    Reserve getReserve();

    /**
     * Return the amount of animals of the specified species that the curent
     * player can put in the reserve.
     *
     * @param species of the animal searched
     * @return the amount of animals of the specified species for the current
     * player, left in the stock.
     */
    int getNb(Species species);

    /**
     * Return Impala Jones.
     *
     * @return Impala Jones
     */
    ImpalaJones getImpalaJones();

    /**
     * Get the score of the player of the given color.
     *
     * @param color the color of the player
     * @return the score of the player of the given color.
     */
    int getScore(Color color);
}
