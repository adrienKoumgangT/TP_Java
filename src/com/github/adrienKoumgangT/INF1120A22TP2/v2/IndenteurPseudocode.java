package com.github.adrienKoumgangT.INF1120A22TP2.v2;

import com.github.adrienKoumgangT.utils.Clavier;
import com.github.adrienKoumgangT.utils.Utils;

import java.util.*;

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

    private final static String INDENTATION = "\t";


    private final static String MSG_INCORRECT_PSEUDO_CODE = "Pseudocode incorrect\n";

    public static Boolean isMotsReserve(String mot) {
        if(mot == null) return false;
        switch (mot) {
            case "debut":
            case "fin":
            case "tant":
            case "tant que":
            case "si":
            case "sinon":
            case "sinon si":
            case "alors":
            case "faire":
            case "afficher":
            case "ecrire":
            case "lire":
            case "saisir":
                return true;
            default:
                return false;
        }
    }

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
                indenterPseudocode(contenuFic, choix);

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

    public static String indenterPseudocode(String code, String indentation) {
        if(code == null || indentation == null) return null;
        if ("m".equals(indentation)) {
            return indenterPseudocode(code, false);
        }
        return indenterPseudocode(code, true);
    }

    public static String indenterPseudocode(String code, Boolean majuscule) {
        if(code == null) return null;
        if(majuscule == null) majuscule = true;
        String codeTrimer = Utils.trimer(code);

        StringBuilder codeResult = new StringBuilder(); //code a retourner
        StringBuilder codeTmp;
        int debut = 0; // indice du début de la ligne courante
        int fin; // indice de la fin de la ligne courante

        long niveauIndentation = 0; // niveau d'indentation courante
        fin = codeTrimer.indexOf("\n", debut);

        // debutOuFin :
        // si l'instruction précédente contenait une instruction de fin de block = -1
        // si l'instruction précédente contenait une instruction de début de block = 1
        // autrement = 0
        int debutOuFin = 0;
        while (fin != -1) {
            // extraire la ligne courante en indenter
            codeTmp = new StringBuilder(codeTrimer.substring(debut, fin));
            if (codeTmp.toString().isEmpty()) {
                // cas où on a affaire à une ligne vide
                // on ajoute la ligne vide à notre résultat
                codeResult.append("\n");
            } else if (codeTmp.toString().substring(0, 3).contains("//")) {
                // cas où on a affaire à un commentaire:
                // les commentaires sont mis seul sur une ligne donc cette doit commencer avec '//'
                // on indente et on ajoute le commentaire
                // si l'instruction précédente était une instruction de début de block
                // j'indente + 1
                // sinon on reste au meme niveau d'indentation précédente
                if(debutOuFin == 1) niveauIndentation += 1;
                debutOuFin = 0;
                codeResult.append(getIndentation(niveauIndentation));
                codeResult.append(codeTmp)
                        .append("\n");
            } else{
                // autres cas : instructions
                String s = codeTmp.substring(0, codeTmp.toString().contains(" ") ? codeTmp.indexOf(" ") : codeTmp.length());
                String sLowerCase = s.toLowerCase(Locale.ROOT);
                if (isMotsReserve(sLowerCase)) {
                    // si le premier mot est un mot reserve du langage
                    String replacement = majuscule ? s.toUpperCase(Locale.ROOT) : s.toLowerCase(Locale.ROOT);
                    switch (sLowerCase) {
                        case "faire":
                        case "debut": {
                            // 'faire' et 'debut' sont des instructions de début de block
                            // si l'instruction précédente était une instruction de début de block
                            // l'indentation augmente
                            if(debutOuFin == 1) niveauIndentation += 1;
                            debutOuFin = 1;
                            codeResult.append(getIndentation(niveauIndentation));

                            codeResult.append(replacement)
                                    .append("\n");
                        } break;
                        case "afficher":
                        case "ecrire":
                        case "lire":
                        case "saisir":{
                            // si l'instruction précédente était une instruction de début de block
                            // alors on augmente l'indentation
                            if(debutOuFin == 1) niveauIndentation += 1;
                            debutOuFin = 0;
                            codeResult.append(getIndentation(niveauIndentation));
                            codeResult.append(codeTmp.toString().replaceFirst(s, replacement))
                                    .append("\n");
                        } break;
                        case "fin": {
                            // si l'instruction précédente était une instruction de fin de block
                            // alors on diminue l'indentation
                            if(debutOuFin == -1) niveauIndentation -= 1;
                            debutOuFin = -1;
                            codeResult.append(getIndentation(niveauIndentation-1));

                            // il n'existe que 3 types d'instruction ayant 'fin'
                            if (codeTmp.toString().substring(3).isEmpty()) {
                                // case "Fin"
                                codeResult.append(majuscule ? "FIN" : "fin");
                            } else if (codeTmp.toString().toLowerCase(Locale.ROOT).contains("si")) {
                                // case "Fin Si"
                                codeResult.append(majuscule ? "FIN SI" : "fin si");
                            } else {
                                // case "Fin Tant Que"
                                codeResult.append(majuscule ? "FIN TANT QUE" : "fin tant que");
                            }
                        } break;
                        case "si": {
                            // si l'instruction précédente était une instruction de début de block
                            // alors on augmente l'indentation
                            if(debutOuFin == 1) niveauIndentation += 1;
                            debutOuFin = 1;
                            codeResult.append(getIndentation(niveauIndentation));

                            codeResult.append(handlerSi(codeTmp, majuscule, s)).append("\n");
                        } break;
                        case "sinon": {
                            // dans ce cas, l'instruction précédente est forcément une interne à un block
                            // donc je recale en arrière
                            niveauIndentation -= 1;
                            codeResult.append(getIndentation(niveauIndentation));
                            debutOuFin = 1;
                            if (codeTmp.toString().toLowerCase(Locale.ROOT).equals("sinon")) {
                                // cas "sinon" simple
                                codeResult.append(majuscule ? "SINON" : "sinon").append("\n");
                            } else {
                                // cas "sinon" complexe = "sinon si"
                                int index = codeTmp.toString().toLowerCase(Locale.ROOT).indexOf("si", 3);
                                codeResult.append(majuscule ? "SINON " : "sinon ")
                                        .append(handlerSi(new StringBuilder(codeTmp.toString().substring(index)), majuscule, codeTmp.substring(index, index+2)))
                                        .append("\n");
                            }
                        } break;
                        case "tant": {
                            String codeTmpString = codeTmp.toString().toLowerCase(Locale.ROOT);
                            if (codeTmpString.substring(codeTmpString.length()-5).contains("faire")) {
                                // cas "Tant que condition faire"
                                // si l'instruction précédente était une instruction de début de block
                                // alors on augmente l'indentation
                                if(debutOuFin == 1) niveauIndentation += 1;
                                debutOuFin = 1;
                                codeResult.append(getIndentation(niveauIndentation));

                                int index = codeTmpString.indexOf("que");
                                int indexFaire = codeTmpString.lastIndexOf("faire");
                                codeResult.append(majuscule ? "TANT QUE " : "tant que ")
                                        .append(codeTmp.substring(index+3, indexFaire))
                                        .append(majuscule ? "FAIRE" : "faire")
                                        .append("\n");

                            } else {
                                // cas "Tant que condition"
                                // dans ce cas, on a affaire à une condition de fin de block
                                // donc on retourne en arrière
                                if(debutOuFin == -1) niveauIndentation -= 1;
                                debutOuFin = -1;
                                codeResult.append(getIndentation(niveauIndentation));

                                int index = codeTmpString.indexOf("que");
                                codeResult.append(majuscule ? "TANT QUE " : "tant que ")
                                        .append(codeTmp.toString().substring(index+3))
                                        .append("\n");
                            }
                        } break;
                        default: {
                            // TODO: à reféchir dessus
                        }
                    }
                } else {
                    // TODO: à reféchir dessus
                }
            }

            debut = fin + 1;
            fin = codeTrimer.indexOf("\n", debut);
        }

        return codeResult.toString();
    }

    public static StringBuilder handlerSi(StringBuilder codeTmp, Boolean majuscule, String s) {
        int index = codeTmp.toString().toLowerCase(Locale.ROOT).lastIndexOf("alors");
        codeTmp = new StringBuilder(codeTmp.toString().replaceFirst(s, majuscule ? "SI" : "si"));
        codeTmp.replace(index, index + 5, majuscule ? "ALORS" : "alors");
        return codeTmp;
    }

    public static String getIndentation(long nb) {
        if(nb <= 0) return "";
        StringBuilder result = new StringBuilder();
        for(int i=0; i<nb; i++) {
            result.append(INDENTATION);
        }
        return result.toString();
    }
}
