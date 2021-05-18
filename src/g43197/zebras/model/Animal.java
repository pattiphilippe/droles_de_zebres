package g43197.zebras.model;

/**
 * Animal described by his specie and his color.
 *
 * @author Philippe
 */
public class Animal {

    private final Species species;
    private final Color color;
    private AnimalState state;

    /**
     * Creates new Animal based on species and color
     *
     * @param species specified specie
     * @param color specified color
     */
    public Animal(Species species, Color color) {
        this.species = species;
        this.color = color;
        this.state = AnimalState.REST;
    }

    /**
     * Get the value of species
     *
     * @return the value of species
     */
    public Species getSpecies() {
        return species;
    }

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the state of the animal.
     *
     * @return the state
     */
    public AnimalState getState() {
        return state;
    }

    /**
     * Sets the state of the animal to the state given in parametres.
     *
     * @param state specified state
     */
    public void setState(AnimalState state) {
        this.state = state;
    }

    /**
     * Verify the attributes to compare the objects.
     *
     * @param obj the object to compare with this one.
     * @return true if they've got the same attributes
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (this.species != other.species || this.color != other.color) {
            return false;
        }
        return true;
    }

    /**
     * Changes the state of the animal if necessary when this is posed next to
     * other. Only the lion does something to the others. If this is a lion,
     * then if
     * <ul>
     * <li>other is a zebra, other's state will be changed to hidden</li>
     * <li>other is a gazelle, other's state will be changed to run </li>
     * <li>other is something else, other's state won't change</li>
     * </ul>
     * If this is a gazelle or a zebra and other is a lion, then this's state
     * will be changed to hidden.
     *
     * @param other the other animal.
     */
    /*There's a difference between the game rules and the project. In the project,
    it's not asked that, when a gazelle has to be put next to a lion, it has to 
    be hidden. 
    On this point, i sticked to the game rules.*/
    public void action(Animal other) {
        lionAction(other);
    }

    private void lionAction(Animal other) {
        if (other != null) {
            if (this.species == Species.LION) {
                if (other.species == Species.GAZELLE) {
                    other.state = AnimalState.RUN;
                } else if (other.species == Species.ZEBRA) {
                    other.state = AnimalState.HIDDEN;
                }
            } else if (other.species == Species.LION) {
                if (species == Species.GAZELLE || species == Species.ZEBRA) {
                    this.state = AnimalState.HIDDEN;
                }
            }
        }
    }

    /**
     * Gives the value of this animal. If this animal is hidden, he has no
     * value.
     *
     * @return the animal value
     */
    public int getValue() {
        if (state == AnimalState.HIDDEN) {
            return 0;
        }
        return species.getValue();
    }
}
