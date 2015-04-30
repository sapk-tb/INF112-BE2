/**
 *
 */
package test;

import exception.BadEntry;
import exception.NotMember;
import avis.SocialNetwork;
import exception.NotItem;

/**
 * @author Antoine GIRARD, Simon LILLE
 *
 */
public class TestsReviewItemFilm {

    static class Moyenne {

        public float value;
    }

    //TODO ajouter verification la modification de la note ?
    public static int reviewItemFilmBadEntryTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
        float moy = moyenne.value;
        int nbFilms = sn.nbFilms();
        try {
            moy = sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (BadEntry e) {
            if (moyenne.value != moy) {
                System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais la moyenne a été modifié");
                return 1;
            }
            if (sn.nbFilms() != nbFilms) {
                System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    //TODO ajouter verification la modification de la note ?
    public static int reviewItemFilmNotMemberTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
        float moy = moyenne.value;
        int nbFilms = sn.nbFilms();
        try {
            moy = sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotMember e) {
            if (moyenne.value != moy) {
                System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais la moyenne a été modifié");
                return 1;
            }
            if (sn.nbFilms() != nbFilms) {
                System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    //TODO ajouter verification la modification de la note ?
    public static int reviewItemFilmNotItemTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
        float moy = moyenne.value;
        int nbFilms = sn.nbFilms();
        try {
            moy = sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotItem e) {
            if (moyenne.value != moy) {
                System.out.println("Test " + idTest + " : l'exception NotItem a bien été levée mais la moyenne a été modifié");
                return 1;
            }
            if (sn.nbFilms() != nbFilms) {
                System.out.println("Test " + idTest + " : l'exception NotItem a bien été levée mais le nombre de films a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    //TODO ajouter verification la modification de la note ? et ajour du comentaire
    public static int reviewItemFilmOKTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest) {
        int nbFilms = sn.nbFilms();
        try {
            moyenne.value = sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
            if (moyenne.value != note) {
                System.out.println("Test " + idTest + " : Le review semble etre ajouté mais la moyenne n'a pas été correctement modifié");
                return 1;
            }
            if (sn.nbFilms() != nbFilms) {
                System.out.println("Test " + idTest + " : Le review semble etre ajouté mais le nombre de films a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        int nbTests = 0;
        int nbErreurs = 0;

        System.out.println("Tests de review des films au réseau social  ");
        SocialNetwork sn = new SocialNetwork();


        //On ajout un utilisateur qui nous servira pour les reviews
        try {
            sn.addMember("UtilisateurReviewTest", "password", "grand amoureux de critique");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de la création de l'utilisateur servant au test. " + e);
            e.printStackTrace();
        }

        //On ajout des films qui nous serviront pour les reviews
        try {
            sn.addItemFilm("UtilisateurReviewTest", "password", "Pulp Fiction", "Policier", "Quentin Tarantino", "Quentin Tarantino", 168);
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de film servant au test. " + e);
            e.printStackTrace();
        }

        int nbLivres = sn.nbBooks();
        int nbFilms = sn.nbFilms();
        Moyenne moyenne = new Moyenne();
        moyenne.value = 0;
        // <=> fiche numéro 6
        // Tentative d'ajout de review avec entrées "correctes"
        //Aillant un seul utilisateru de test la moyenne des reviwe doivent etre la note du dernier review de ceet utilisateur
        nbTests++;
        nbErreurs += reviewItemFilmOKTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 5, "un commentaire", "1.1a");
        //System.out.printf("%f\n", moyenne.value);
        nbTests++;
        nbErreurs += reviewItemFilmOKTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 3, "un commentaire", "1.1b");
        //System.out.printf("%f\n", moyenne.value);

        // <=> fiche numéro 5
        // tentative d'ajout de review avec entrées "incorrectes"
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, null, "password", "Pulp Fiction", 0, "un commentaire", "2.1", "L'ajout d'une review dont le pseudo n'est pas instancié est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, " ", "password", "Pulp Fiction", 0, "un commentaire", "2.2", "L'ajout d'une review  dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, "UtilisateurReviewTest", null, "Pulp Fiction", 0, "un commentaire", "2.3", "L'ajout d'une review dont le password n'est pas instancié est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "   qwd ", "Pulp Fiction", 0, "un commentaire", "2.4", "L'ajout d'un film dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", null, 0, "un commentaire", "2.5", "L'ajout d'une review dont le titre n'est pas instancié est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", " ", 0, "un commentaire", "2.6", "L'ajout d'une review  dont le titre ne contient pas un caracteres, autre que des espaces, est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 10, "un commentaire", "2.7", "L'ajout d'une review  dont la note n'est pas comprise entre 0.0 et 5.0 est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 0, null, "2.8", "L'ajout d'une review dont le commentaire n'est pas instancié est accepté");

        //Tentative d'ajout de de review avec des informations utilisateurs invalides (pseudo, password)
        nbTests++;
        nbErreurs += reviewItemFilmNotMemberTest(sn, moyenne, "NotAMenber", "password", "Pulp Fiction", 0, "un commentaire", "3.1", "L'ajout d'une review dont l'utilisateur n'existe pas est accepté");
        nbTests++;
        nbErreurs += reviewItemFilmNotMemberTest(sn, moyenne, "UtilisateurAddItemFilmTest", "badpassword", "Pulp Fiction", 0, "un commentaire", "3.2", "L'ajout d'une review dont le mot de passe ne correspond pas à celui de l'utilisateur n'existe pas est accepté");

        //Tentative d'ajout de review avec des informations film invalides (titre)
        nbTests++;
        nbErreurs += reviewItemFilmNotItemTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pas Fiction", 0, "un commentaire", "4.1", "L'ajout d'une review dont le film n'existe pas est accepté");

        nbTests++;
        if (nbFilms != sn.nbFilms()) {
            System.out.println("Erreur  :  le nombre de films après utilisation de reviewItemFilm a été modifié");
            nbErreurs++;
        }
        nbTests++;
        if (nbLivres != sn.nbBooks()) {
            System.out.println("Erreur  :  le nombre de livres après utilisation de reviewItemFilm a été modifié");
            nbErreurs++;
        }

        // bilan du test de addItemFilm
        System.out.println("TestsReviewItemFilm :   " + nbErreurs + " erreur(s) / " + nbTests + " tests effectués");

        // ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {
            nbTests = nbTests + new Integer(args[0]);
            nbErreurs = nbErreurs + new Integer(args[1]);
            args[0] = "" + nbTests;
            args[1] = "" + nbErreurs;
        }
    }

}
