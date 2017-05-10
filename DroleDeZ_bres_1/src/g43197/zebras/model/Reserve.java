package g43197.zebras.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that describes the plate of the game.
 *
 * @author Philippe
 */
public class Reserve {

    private final Animal[][] animals;
    private final List<Sector> sectors;
    public static final int MAX_ROWS = 5;
    public static final int MAX_COLUMNS = 6;

    /**
     * Creates a new Reserve of 5*6 animals
     */
    public Reserve() {
        this.animals = new Animal[MAX_ROWS][MAX_COLUMNS];
        sectors = new ArrayList<>();
        initSectors();
    }

    private void initSectors() {
        Coordinates[] positions = new Coordinates[7], partOfPositions;
        int nbOfPos;
        for (int i = 1; i <= 6; i++) {
            nbOfPos = initSectorsPos(i, positions);
            partOfPositions = Arrays.copyOf(positions, nbOfPos);
            sectors.add(new Sector(i, this, partOfPositions));
        }
    }

    private int initSectorsPos(int sectorNb, Coordinates[] pos) {
        switch (sectorNb) {
            case 1:
                pos[0] = new Coordinates(0, 0);
                pos[1] = new Coordinates(1, 0);
                pos[2] = new Coordinates(2, 0);
                pos[3] = new Coordinates(2, 1);
                pos[4] = new Coordinates(3, 1);
                return 5;
            case 2:
                pos[0] = new Coordinates(0, 1);
                pos[1] = new Coordinates(1, 1);
                pos[2] = new Coordinates(1, 2);
                pos[3] = new Coordinates(2, 2);
                pos[4] = new Coordinates(3, 2);
                pos[5] = new Coordinates(3, 3);
                pos[6] = new Coordinates(3, 4);
                return 7;
            case 3:
                pos[0] = new Coordinates(0, 2);
                pos[1] = new Coordinates(0, 3);
                pos[2] = new Coordinates(0, 4);
                pos[3] = new Coordinates(0, 5);
                pos[4] = new Coordinates(1, 3);
                pos[5] = new Coordinates(2, 3);
                pos[6] = new Coordinates(2, 4);
                return 7;
            case 4:
                pos[0] = new Coordinates(1, 4);
                pos[1] = new Coordinates(1, 5);
                pos[2] = new Coordinates(2, 5);
                return 3;
            case 5:
                pos[0] = new Coordinates(3, 0);
                pos[1] = new Coordinates(4, 0);
                pos[2] = new Coordinates(4, 1);
                return 3;
            case 6:
                pos[0] = new Coordinates(4, 2);
                pos[1] = new Coordinates(4, 3);
                pos[2] = new Coordinates(4, 4);
                pos[3] = new Coordinates(4, 5);
                pos[4] = new Coordinates(3, 5);
                return 5;
            default:
                throw new GameException("Sector Number invalid! 1-6!");
        }
    }

    /**
     * Checks if position in reserve is free.
     *
     * @param pos specified position
     * @return true if position is free
     */
    public boolean isFree(Coordinates pos) {
        return animals[pos.getRow()][pos.getColumn()] == null;
    }

    /**
     * Puts an animal in the reserve at a certain position
     *
     * @param animal specified animal
     * @param row specified row
     * @param column specified column
     */
    public void put(Animal animal, int row, int column) {
        animals[row][column] = animal;
    }

    /**
     * Returns the animal in a certain position in the reserve
     *
     * @param pos specified position
     * @return Animal
     */
    public Animal getAnimal(Coordinates pos) {
        return animals[pos.getRow()][pos.getColumn()];
    }

    /**
     * Checks if a column in the reserve is full.
     *
     * @param col the number of the column
     * @return true if it is full
     */
    public boolean isFullCol(int col) {
        int row = 0;
        while (row < MAX_ROWS && animals[row][col] != null) {
            row++;
        }
        return row == MAX_ROWS;
    }

    /**
     * Checks if a row in the reserve is full.
     *
     * @param row specified row
     * @return true if the row is full
     */
    public boolean isFullRow(int row) {
        int col = 0;
        while (col < MAX_COLUMNS && animals[row][col] != null) {
            col++;
        }
        return col == MAX_COLUMNS;
    }

    /**
     * Returns a list of all adjacents positions, horizontally and vertically.
     *
     * @param position the original position
     * @return the list
     */
    public List<Coordinates> getAdjacents(Coordinates position) {
        List<Coordinates> adjacents = new ArrayList<>();
        int row = position.getRow();
        int col = position.getColumn();
        if ((row - 1) >= 0) {
            adjacents.add(new Coordinates(row - 1, col));
        }
        if ((row + 1) < MAX_ROWS) {
            adjacents.add(new Coordinates(row + 1, col));
        }
        if ((col - 1) >= 0) {
            adjacents.add(new Coordinates(row, col - 1));
        }
        if ((col + 1) < MAX_COLUMNS) {
            adjacents.add(new Coordinates(row, col + 1));
        }
        return adjacents;
    }

    /**
     * Gets an animal out of the reserve.
     *
     * @param row specified row
     * @param column specified column
     */
    public void remove(int row, int column) {
        animals[row][column] = null;
    }

    /**
     * Gives the sector that contains a given coordinate.
     *
     * @param coord the specified coordinate
     * @return the sector
     * @throws GameException if no sector contains the coordinate
     */
    public Sector getSector(Coordinates coord) {
        int nbSector = 0;
        while (nbSector < 6 && !sectors.get(nbSector).contains(coord)) {
            nbSector++;
        }
        if (nbSector == 6) {
            throw new GameException("Coordinate isn't in any sector");
        }
        return sectors.get(nbSector);
    }

    /**
     * Gives the current score of a player.
     *
     * @param color the color of the player
     * @return his score
     */
    public int getScore(Color color) {
        return sectors.parallelStream().filter((sector) -> (sector.hasMajority(color)))
                .map((sector) -> sector.getScore()).mapToInt(i -> i).sum();
    }
}
