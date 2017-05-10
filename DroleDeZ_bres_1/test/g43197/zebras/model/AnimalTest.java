package g43197.zebras.model;

import org.junit.Test;

/**
 *
 * @author Philippe
 */
public class AnimalTest {

    /**
     * Case this = lion, checks other's state is good.
     */
    @Test
    public void testAction1() {
        AnimalState[] states;
        states = new AnimalState[]{AnimalState.RUN, AnimalState.HIDDEN,
            AnimalState.REST, AnimalState.REST, AnimalState.REST};
        Species[] species = Species.values();
        Animal instance = new Animal(Species.LION, Color.RED);
        for (int s = 0; s < species.length; s++) {
            Animal other = new Animal(species[s], Color.RED);
            instance.action(other);
            assert (other.getState() == states[s]);
        }
    }

    /**
     * Case this = gazelle, other = lion, this's state is hidden.
     */
    @Test
    public void testAction2() {
        Animal other = new Animal(Species.LION, Color.GREEN);
        Animal instance = new Animal(Species.GAZELLE, Color.RED);
        instance.action(other);
        assert (instance.getState() == AnimalState.HIDDEN);
    }

    /**
     * Case this = zebra, other = lion, this's state is hidden.
     */
    @Test
    public void testAction3() {
        Animal other = new Animal(Species.LION, Color.GREEN);
        Animal instance = new Animal(Species.ZEBRA, Color.RED);
        instance.action(other);
        assert (instance.getState() == AnimalState.HIDDEN);
    }
}
