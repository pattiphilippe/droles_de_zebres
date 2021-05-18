package g43197.zebras.model;

/**
 * Impala Jones piece with all his particularities. Some of the rules concerning
 * him are only checked in Game class.
 *
 * @author Philippe
 */
public class ImpalaJones {

    private int position;
    private static final int MINPOS = 0;
    private static final int MAXPOS = (Reserve.MAX_COLUMNS + Reserve.MAX_ROWS) * 2 - 1;
    private static final int MINPOSUP = 0;
    private static final int MAXPOSUP = Reserve.MAX_COLUMNS - 1;
    private static final int MINPOSRIGHT = MAXPOSUP + 1;
    private static final int MAXPOSRIGHT = MINPOSRIGHT + Reserve.MAX_ROWS - 1;
    private static final int MINPOSDOWN = MAXPOSRIGHT + 1;
    private static final int MAXPOSDOWN = MINPOSDOWN + Reserve.MAX_COLUMNS - 1;
    private static final int MINPOSLEFT = MAXPOSDOWN + 1;
    private static final int MAXPOSLEFT = MINPOSLEFT + Reserve.MAX_ROWS - 1;

    /**
     * Creates a new Impala Jones.
     */
    public ImpalaJones() {
        this.position = -1;
    }

    /**
     * Initialise the position of Impala to a number given in parametres.
     *
     * @param nb specified position
     * @throws IllegalArgumentException if position out of bord.
     */
    public void init(int nb) {
        if (nb < MINPOS || nb > MAXPOS) {
            throw new IllegalArgumentException(
                    "Position Impala impossible");
        }
        this.position = nb;
    }

    /**
     * Gives the position of Impala Jones
     *
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Moves the Impala Jones of a certain distance. The rules are not checked
     * in this method.
     *
     * @param distance specified distance
     */
    public void move(int distance) {
        if (distance < 0 && (position + distance) < 0) {
            this.position += (MAXPOS + 1);
            this.position += distance;
        } else {
            this.position += distance;
            this.position %= (MAXPOS + 1);
        }
    }

    /**
     * Compares the position of Impala with the bord.
     *
     * @return true is Impala is up
     */
    public boolean isUp() {
        return isBetween(position, MINPOSUP, MAXPOSUP);
    }

    /**
     * Compares the position of Impala with the bord.
     *
     * @return true is Impala is down
     */
    public boolean isRight() {
        return isBetween(position, MINPOSRIGHT, MAXPOSRIGHT);
    }

    /**
     * Compares the position of Impala with the bord.
     *
     * @return true is Impala is left
     */
    public boolean isDown() {
        return isBetween(position, MINPOSDOWN, MAXPOSDOWN);
    }

    /**
     * Compares the position of Impala with the bord.
     *
     * @return true is Impala is right
     */
    public boolean isLeft() {
        return isBetween(position, MINPOSLEFT, MAXPOSLEFT);
    }

    private boolean isBetween(int position, int min, int max) {
        return min <= position && position <= max;
    }

    /**
     * Gives the column of Impala. If he's not on a column, returns -1.
     *
     * @return the column of Impala, [0;5]
     */
    public int getColumn() {
        if (isUp()) {
            return position;
        } else if (isDown()) {
            return MAXPOSDOWN - position;
        } else {
            return -1;
        }
    }

    /**
     * Gives the line of Impala. If he's not on a line, returns -1.
     *
     * @return the line of Impala, [0;4]
     */
    public int getRow() {
        if (isRight()) {
            return position - 6;
        } else if (isLeft()) {
            return MAXPOSLEFT - position;
        } else {
            return -1;
        }
    }

    /**
     * Checks if Impala's movement would be fine, which means that it would put
     * him on a row/column that is not full.
     *
     * @param reserve given reserve
     * @param distance specified distance
     * @return true if the row / columnn wouldn't be full
     */
    public boolean checkMove(Reserve reserve, int distance) {
        this.move(distance);
        boolean full;
        if (isUp() || isDown()) {
            full = reserve.isFullCol(getColumn());
        } else {
            full = reserve.isFullRow(getRow());
        }
        this.move(-distance);
        return !full;
    }

    /*
    * IsFull checks if, after movement, the row/column of Impala would be full .
     */
    private boolean isFull(Reserve reserve, int distance) {
        this.move(distance);
        boolean isFull = true;
        Coordinates pos;
        if (getRow() != -1) {
            //TODO method for checking, twice the same
            int col = 0;
            while (col < Reserve.MAX_COLUMNS && isFull) {
                pos = new Coordinates(getRow(), col);
                isFull = reserve.getAnimal(pos) != null;
                col++;
            }
        } else {
            int row = 0;
            while (row < Reserve.MAX_ROWS && isFull) {
                pos = new Coordinates(row, getColumn());
                isFull = reserve.getAnimal(pos) != null;
                row++;
            }
        }
        this.move(-distance);
        return isFull;
    }

    /**
     * Checks if Impala is on the row/column of the given position.
     *
     * @param pos specified position
     * @return true if Impala is on the row/column of the given position
     */
    public boolean valid(Coordinates pos) {
        if (getRow() != -1) {
            return this.getRow() == pos.getRow();
        } else {
            return this.getColumn() == pos.getColumn();
        }
    }

    /**
     * Finds the minimum possible movement for Impala. If Impala is on position
     * 15 and that the next valid position is 19, it returns 4.
     *
     * @param reserve given reserve
     * @return the first ditance
     * @throws GameException if no possible place left for impala
     */
    public int findFirst(Reserve reserve) {
        int distance = 1;
        while (isFull(reserve, distance)) {
            distance++;
        }
        return distance;
    }
}
