package g43197.zebras.model;

/**
 * Coordinates of a place. Destined to be used with the reserve.
 *
 * @author Philippe
 */
public class Coordinates {

    private final int row;
    private final int column;

    /**
     * Creates new Coordinates with row and column
     *
     * @param row specified row
     * @param column specified column
     */
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the value of row
     *
     * @return the value of row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the value of column
     *
     * @return the value of column
     */
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.row;
        hash = 73 * hash + this.column;
        return hash;
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
        final Coordinates other = (Coordinates) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
}
