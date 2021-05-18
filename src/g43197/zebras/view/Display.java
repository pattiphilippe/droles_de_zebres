package g43197.zebras.view;

import g43197.zebras.model.*;

/**
 * Class with all methods used for the display. toString methods with basic
 * display aren't included and shouldn't be used anymore.
 *
 * @author Philippe
 */
public class Display {

    private static final String SEPARATOR = "\n";
    private static final String COLOR_DEFAULT = "\033[0m";
    private static final String BKG_DEFAULT = "\033[049m";
    private static final String BOLD_ON = "\033[2m";
    private static final String BOLD_OFF = "\033[22m";
    
    

    /**
     * Changes the color of a string. Only color of this enum are accepted.
     *
     * @param msg specified string as msg
     * @param color specified color
     * @return the message in the specified Color.
     */
    public static String toColor(String msg, Color color) {
        switch (color) {
            case RED:
                return "\033[31m" + msg + COLOR_DEFAULT;
            case GREEN:
                return "\033[32m" + msg + COLOR_DEFAULT;
            default:
                return msg;
        }
    }

    public static String toBold(String msg){
        return BOLD_ON + msg + BOLD_OFF;
    }
    public static String backgroundColor(String msg, int nbSector) {
        switch (nbSector) {
            case 1:
            case 3:
                return "\033[043m" + msg + BKG_DEFAULT;
            case 2:
            case 4:
            case 5:
                return "\033[044m" + msg + BKG_DEFAULT;
            case 6:
                return "\033[045m" + msg + BKG_DEFAULT;
            default:
                throw new GameException("Sector nb invalid");
        }
    }

    /**
     * Display the intro and a link to the rules if he wants it.
     */
    public static void displayIntro() {
        System.out.println("Bienvenue! Vous êtes prèts pour une partie de "
                + "Droles de Zèbres?");
        System.out.println("Voici une adresse si vous voulez revoir les règles : ");
        System.out.println("http://www.boiteajeux.net/ludotheque/regles/ddz/ddz_regles.html");

        System.out.println("Et c'est parti!");
    }

    /**
     * Display who's turn it is in the game.
     *
     * @param currentColor of the player in game
     */
    public static void displayCurrentPlayer(Color currentColor) {
        String msg = "C'est au tour du joueur ";
        switch (currentColor) {
            case RED:
                msg += "rouge";
                break;
            case GREEN:
                msg += "vert";
                break;
            default:
                msg += "qui joue maintenant";
                break;
        }
        msg += ".";
        System.out.println(msg);
    }

    /**
     * Display the stock of the current player and the bord.
     *
     * @param game current game
     */
    public static void displayGame(Game game) {
        displayStock(game);
        displayBord(game.getReserve(), game.getImpalaJones());
    }

    /**
     * Nice display of the stock of the current player.
     *
     * @param game current game
     */
    public static void displayStock(Game game) {
        String stock = "";
        stock += "   Stock" + SEPARATOR;
        stock += "   -----" + SEPARATOR;
        int nbAnimals;
        for (Species species : Species.values()) {
            nbAnimals = game.getNb(species);
            if (species != Species.ZEBRA) {
                stock += nbAnimals + " " + species;
            } else {
                stock += nbAnimals + " ZEBRE";
            }
            if (nbAnimals > 1) {
                stock += "S";
            }
            stock += SEPARATOR;
        }
        System.out.println(stock);
    }

    /**
     * Nice display of the bord.
     *
     * @param impala game's impala
     * @param reserve game's reserve
     */
    public static void displayBord(Reserve reserve, ImpalaJones impala) {
        String[] bord = makeBord(reserve, impala);
        String msg = "";
        for (String line : bord) {
            msg += line + SEPARATOR;
        }
        System.out.println(msg);
    }

    private static String[] makeBord(Reserve reserve, ImpalaJones impala) {
        //upper part 
        String[] bord = new String[5 + Reserve.MAX_ROWS];
        bord[0] = "   123456";
        bord[1] = displaySidesUD(impala, "up");
        bord[2] = "   ======";

        //core of the bord
        Coordinates pos;
        for (int i = 0; i < Reserve.MAX_ROWS; i++) {
            //left side
            bord[i + 3] = "";
            bord[i + 3] += (i + 1);
            bord[i + 3] += displaySidesLR(impala, i, "left");
            bord[i + 3] += "|";
            //center animals
            for (int j = 0; j < Reserve.MAX_COLUMNS; j++) {
                pos = new Coordinates(i, j);
                bord[i + 3] += tileToString(reserve, pos);
            }
            //right side
            bord[i + 3] += "|";
            bord[i + 3] += displaySidesLR(impala, i, "right");
        }

        //bottom part
        bord[bord.length - 2] = "   ======";
        bord[bord.length - 1] = displaySidesUD(impala, "down");

        return bord;
    }

    private static String displaySidesUD(ImpalaJones impala, String side) {
        int col;
        String msg = "   ";
        boolean doUp = impala.isUp() && side.equals("up");
        boolean doDown = impala.isDown() && side.equals("down");
        if (doUp || doDown) {
            col = impala.getColumn();
            for (int i = 0; i < col; i++) {
                msg += ".";
            }
            msg += "I";
            for (int i = col + 1; i < Reserve.MAX_COLUMNS; i++) {
                msg += ".";
            }
        } else {
            msg += "......";
        }
        return msg;
    }

    private static String displaySidesLR(ImpalaJones impala, int row, String side) {
        String msg = ".";
        if (row == impala.getRow()) {
            boolean doLeft = impala.isLeft() && side.equals("left");
            boolean doRight = impala.isRight() && side.equals("right");
            if (doLeft || doRight) {
                msg = "I";
            }
        }
        return msg;
    }

    private static String tileToString(Reserve reserve, Coordinates position) {
        String msg = ".";
        Animal animal = reserve.getAnimal(position);
        if (animal != null) {
            String letter = animal.getSpecies().name().charAt(0) + "";
            if (animal.getState() == AnimalState.HIDDEN) {
                msg = toColor("X", animal.getColor());
            } else {
                msg = toColor(letter, animal.getColor());
            }
            msg = toBold(msg);
        }
        msg = backgroundColor(msg, reserve.getSector(position).getNumber());
        return msg;
    }

    /**
     * Displays the score of the current player of the game.
     *
     * @param game
     */
    public static void displayScore(Game game) {
        String msg = "Votre score actuel = ";
        msg += game.getScore(game.getCurrentColor());
        System.out.println(msg);
    }

    public static void displayWinner(Game game) {
        int redScore, greenScore;
        String msg = "Le jeu est fini!" + SEPARATOR + SEPARATOR;
        msg += "Le joueur rouge a un score de : ";
        redScore = game.getScore(Color.RED);
        msg += redScore;
        msg += "Le joueur vert a un score de : ";
        greenScore = game.getScore(Color.GREEN);
        msg += greenScore;
        msg += SEPARATOR + SEPARATOR + SEPARATOR;
        if (redScore == greenScore) {
            msg += "Vous êtes à égalité! Félicitations, vous"
                    + " avez gagnez tous les deux!";
        } else {
            msg += "Le gagnant est donc le joueur ";
            if (redScore > greenScore) {
                msg += "vert!";
            } else {
                msg += "rouge!";
            }
            msg += " Félicitations!";
        }
        msg += SEPARATOR;
        System.out.println(msg);
    }
}
