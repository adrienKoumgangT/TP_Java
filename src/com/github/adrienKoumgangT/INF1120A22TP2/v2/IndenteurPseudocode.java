package com.github.adrienKoumgangT.INF1120A22TP2.v2;

import com.github.adrienKoumgangT.utils.Clavier;
import com.github.adrienKoumgangT.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Programme permettant de corriger l'indentation d'un algorithme
 * écrit en pseudocode se trouvant dans un fichier texte.
 *
 * De plus, le programme permet de formater, soit en majuscules,
 * soit en minuscules, tous les mots réservés du langage pseudocode.
 *
 * INF1120 A22 TP2
 * @author Adrien Koumgang Tegantchouang
 * @version 2.0
 */
public class IndenteurPseudocode {

    private final static String MSG_DEBUT = "Ce programme permet de corriger l'indentation d'un algorithme ecrit en pseudocode.\n";
    private final static String MSG_MENU = "----\n" +
                                        "MENU\n" +
                                        "----\n" +
                                        "1. Indenter pseudocode\n" +
                                        "2. Quitter\n" +
                                        "\n" +
                                        "Entrez votre choix : ";
    private final static String MSG_ERREUR_CHOIX_MENU = "ERREUR ! Choix invalide. Recommencez...\n";
    private final static String MSG_MENU_FICHIER = "Entrez le chemin du fichier de pseudocode : ";
    private final static String MSG_ERREUR_CHOIX_FICHIER = "ERREUR ! Chemin de fichier invalide. Recommencez...\n";
    private final static String MSG_MOTS_RESERVES = "Mots reserves : en (m)inuscules ou en (M)ajuscules : ";
    private final static String MSG_ERREUR_CHOIX_MOTS_RESERVES = "ERREUR ! Entrez m ou M. Recommencez...";
    private final static String LIGNE_TIRETS = "--------------------\n";
    private final static String MSG_ENTREE_CONTINUER = "Tapez <ENTREE> pour continuer...";
    private final static String MSG_FIN = "\nF I N   N O R M A L E   D U   P R O G R A M M E";

    private final static List<String> MOTS_RESERVES = Arrays.asList(
                                                            "debut",
                                                            "fin",
                                                            "tant",
                                                            "que",
                                                            "si",
                                                            "alors",
                                                            "sinon",
                                                            "faire",
                                                            "afficher",
                                                            "ecrire",
                                                            "lire",
                                                            "saisir"
                                                        );

    public static void work() {
        String choixMenu;
        String choix;
        String contenuFic;

        System.out.println(MSG_DEBUT);
        System.out.println();
        do {
            System.out.println();
            // validation du choix du menu
            choixMenu = validationChoixMenu();
            if (choixMenu.equals("1")) {
                // Validation du fichier
                contenuFic = validationFichier();

                // validation du choix de indentation
                choix = validationChoixIndentation();

                // indention du pseudocode
                // TODO: à finir
                if (choix.equals("m")) {
                    contenuFic = contenuFic.toLowerCase(Locale.ROOT);
                } else {
                    contenuFic = contenuFic.toUpperCase(Locale.ROOT);
                }

                // affiche du résultat
                System.out.println();
                System.out.println(LIGNE_TIRETS);
                System.out.println(contenuFic);
                System.out.println(LIGNE_TIRETS);
                System.out.println();
                System.out.println(MSG_ENTREE_CONTINUER);
                Clavier.lireFinLigne();
            }

        } while (!choixMenu.equals("2"));
        System.out.println(MSG_FIN);
    }

    public static String validationChoixMenu() {
        String choixMenu;
        do {
            System.out.println(MSG_MENU);
            choixMenu = Clavier.lireString();
            if (choixMenu == null || !(choixMenu.equals("1") || choixMenu.equals("2"))) {
                System.out.println();
                System.out.println(MSG_ERREUR_CHOIX_MENU);
            }
        } while (choixMenu == null || !(choixMenu.equals("1") || choixMenu.equals("2")));
        return choixMenu;
    }

    public static String validationFichier() {
        String choix;
        String contenuFic;
        do {
            System.out.println(MSG_MENU_FICHIER);
            choix = Clavier.lireString();
            contenuFic = Utils.lireFichierTexte(choix);
            if (contenuFic == null) {
                System.out.println();
                System.out.println(MSG_ERREUR_CHOIX_FICHIER);
            }
        } while (contenuFic == null);
        return contenuFic;
    }

    public static String validationChoixIndentation() {
        String choix;
        do {
            System.out.println(MSG_MOTS_RESERVES);
            choix = Clavier.lireString();
            if (!(choix.equals("m") || choix.equals("M") )) {
                System.out.println();
                System.out.println(MSG_ERREUR_CHOIX_MOTS_RESERVES);
            }
        } while (!(choix.equals("m") || choix.equals("M")));
        return choix;
    }

}
