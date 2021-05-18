package g43197.zebras;

import g43197.zebras.model.*;
import g43197.zebras.view.*;
import java.util.List;

/**
 * Class to play the game and do basic tests with model methods.
 *
 * @author Philippe
 */
public class Zebras {

    //TODO @mention score avant changement joueur, calculé dynamiquement (méthode get Score doit juste prendre la valeur d'un attribut)
    //TODO @mention trouver idée pour gérer la langue... Tout le code doit être en anglais!
    //TODO @mention fichier à part pour message anglais français,... , message correspondant lié à un numéro, numéro cherché par une méthode
    //TODO @mention affichage uniquement des rivières pour séparer les secteurs
    //TODO @mention affichage plus beau des erreurs, textes court, domaine : (..,..,..)
    //TODO @mention possibles nouveaux secteurs, changement les plus dynamiques possibles.
    //TODO @mention optimiser mouvement impala, si pas disp < 3, direct premier possible.
    //TODO @mention le/la gazelle
    public static void play() {

        Game game = new Game();
        startGame(game);

        while (!game.isOver()) {
            Display.displayCurrentPlayer(game.getCurrentColor());
            Display.displayGame(game);

            if (game.getStatus() == GameStatus.ANIMAL) {
                putAnimal(game);
            } else if (game.getStatus() == GameStatus.IMPALA) {
                moveImpala(game);
            }

            Display.displayScore(game);
            System.out.println("");
        }

        Display.displayWinner(game);
    }

    /*
    * Creates a new game, displays introduction text and rules if needed
    * and initialize Impala's position
     */
    private static void startGame(Game game) {
        Display.displayIntro();
        int initImp = Read.readImpalaFirstPosition();
        try {
            game.setImpalaJonesFirstPosition(initImp);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    * Puts an animal in if good status and can play.
     */
    private static void putAnimal(Game game) {
        boolean error;
        Species species;
        Coordinates pos = null;
        do {
            try {
                error = false;
                species = Read.readSpecies();

                pos = Read.readAnimalPosition(game.getImpalaJones());

                game.putAnimal(pos, species);
            } catch (GameException e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while (error);

        if (game.getStatus() == GameStatus.SWITCH) {
            switchCG(game, pos);
        }
    }

    private static void switchCG(Game game, Coordinates crocoPos) {
        List<Coordinates> gazPoss = game.switchableGaz(crocoPos);
        for (Coordinates gazPos : gazPoss) {
            //TODO display tiles to change here or in read.switchCrocoGaz
            if (Read.switchCrocoGaz()) {
                game.switchCrocoGaz(crocoPos, gazPos);
                break;
            }
        }
    }

    /*
    si switch
    pour chaque switchable pos TANT QUE nbSwitch < 1
        proposer switch
    fin pour
    remettre state
     */

 /*
    * Moves Impala if good status.
     */
    private static void moveImpala(Game game) {
        boolean error;
        int distance;
        do {
            try {
                error = false;
                distance = Read.readImpalaDistance();
                game.moveImpalaJones(distance);
            } catch (GameException e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while (error);
    }

    public static void main(String[] args) {
        play();
    }
}
