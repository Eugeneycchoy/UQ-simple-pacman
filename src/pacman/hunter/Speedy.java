package pacman.hunter;

/**
 * A Speedy hunter that has a special ability that allows the hunter to travel twice as fast.
 * Implementation will take place in Part 2.
 */
public class Speedy extends Hunter {

    /**
     * Creates a Speedy Hunter with its special ability. see Hunter()
     */
    public Speedy() {
        super();
    }

    /**
     * Creates a Speedy Hunter by copying the internal state of another hunter.
     * see Hunter(Hunter)
     * @param original - hunter to copy from
     */
    public Speedy(Hunter original) {
        super(original);
    }
}
