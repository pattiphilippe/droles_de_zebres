package g43197.zebras.model;

/**
 * Enum of Species of animals
 *
 * @author Philippe
 */
public enum Species {
    GAZELLE(2), ZEBRA(6), LION(1), ELEPHANT(5), CROCODILE(0);
    
    private final int value;

    private Species(int value) {
        this.value = value;
    }

    /**
     * Gives the value of the specie.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}
