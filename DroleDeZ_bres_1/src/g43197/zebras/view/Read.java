package g43197.zebras.view;

import g43197.zebras.model.*;
import java.util.Scanner;

/**
 * Class to read different objects from the keyboard.
 *
 * @author Philippe
 */
public class Read {

    public static Scanner clavier = new Scanner(System.in);

    /**
     * Reads an integer on the keyboard. Continues until it has a good answer.
     *
     * @param msg to explain what the user has to put in
     * @return the integer
     */
    public static int readInt(String msg) {
        System.out.println(msg);

        while (!clavier.hasNextInt()) {
            System.out.println("Entrez un entier!");
            clavier.next();
        }

        return clavier.nextInt();
    }

    /**
     * Reads an integer between limits. Continues until it has a good answer.
     * Limits can be selected as well.
     *
     * @param msg to explain what the user has to put in
     * @param min the minimum limit
     * @param max the maximum limit
     * @return the good nb inserted by the user
     */
    public static int readIntBetween(String msg, int min, int max) {
        int nb;
        nb = readInt(msg);
        while (nb < min || max < nb) {
            System.out.println("Entrez un nombre entre les limites!");
            System.out.print("Le minimum est " + min);
            System.out.println(" et le maximum est " + max + ".");
            nb = readInt(msg);
        }
        return nb;
    }
    
    public static boolean readBoolean(){
        String reponse = clavier.next().toUpperCase();
        while(!reponse.equals("OUI") && !reponse.equals("NON")){
            System.out.println("Entrez oui ou non!");
            reponse = clavier.next().toUpperCase();
        }
        return reponse.equals("OUI");
    }

    /**
     * Reads a specie on the keyboard. Continues until it has a good answer.
     *
     * @return the specie
     */
    public static Species readSpecies() {
        String msg = "Quelle espèce d'animal voulez-vous placez dans la réserve?";
        System.out.println(msg);
        boolean error;
        Species species = null;
        String s;

        do {
            try {
                error = false;
                s = clavier.next().toUpperCase();
                if (s.equals("ZEBRE")) {
                    s = "ZEBRA";
                }
                species = Species.valueOf(s);
            } catch (IllegalArgumentException e) {
                error = true;
                System.out.println("Ce n'est pas une bonne espèce!");
                System.out.println("Choisissez parmi : zebre, gazelle,"
                        + " lion, crocodile, elephant.");
            }
        } while (error);
        return species;
    }
    
    public static boolean switchCrocoGaz(){
        System.out.println("Voulez-vous échanger la gazelle et "
                + "le crocodile de place?");
        return readBoolean();
    }

    /**
     * Read a position on the bord. Limits of the bord are 5 rows and 6 columns.
     * Asks the position in relation with impala's position. For example, if
     * impala is on column 4, only the row will be asked, and column 4 will be
     * used.
     *
     * @param impala game's impala
     * @return the inserted Coordinate
     */
    public static Coordinates readAnimalPosition(ImpalaJones impala) {
        String msg = "Où voulez-vous le placez?";
        System.out.println(msg);

        int col = impala.getColumn(), row = impala.getRow();
        if (col != -1) {
            String rowMsg = "Entrez la ligne.";
            row = readIntBetween(rowMsg, 1, Reserve.MAX_ROWS) - 1;
        } else {
            String colMsg = "Entrez la colone.";
            col = readIntBetween(colMsg, 1, Reserve.MAX_COLUMNS) - 1;
        }
        return new Coordinates(row, col);
    }

    public static int readImpalaFirstPosition() {
        String msg = "Où doit commencer Impala Jones?";
        return Read.readIntBetween(msg, 1, 22) - 1;
    }

    public static int readImpalaDistance() {
        String msg;
        msg = "De combien de pas Impala doit-il se déplacer?";
        return Read.readInt(msg);
    }
}
