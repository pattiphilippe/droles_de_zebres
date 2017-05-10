package g43197.zebras.model;

/**
 * Class for representation of the players.
 *
 * @author Philippe
 */
public class Player {

    private final Color color;

    /**
     * Constructor of Player instance.
     *
     * @param color the color of the player
     */
    public Player(Color color) {
        this.color = color;
    }

    /**
     * Getter of color.
     *
     * @return this.color
     */
    public Color getColor() {
        return this.color;
    }

}
