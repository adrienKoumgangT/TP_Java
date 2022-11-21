package com.github.adrienKoumgangT.INF1120A22TP2.v3;

import com.github.adrienKoumgangT.utils.Clavier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
 * @version 3.0
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
            contenuFic = lireFichierTexte(choix);
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
        String codeTrimer = trimer(code);

        System.out.println("code trimer : \n" + codeTrimer);

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
                // après un mot réservé, il y a un espace blanc
                // qui peut etre soit un simple espace ' ' soit un tab '\t'
                String s;
                String sLowerCase;
                int indexBlanc;
                int indexOfSpace = codeTmp.indexOf(" ");
                int indexOfTab = codeTmp.indexOf("\t");
                if(indexOfSpace == indexOfTab) {
                    indexBlanc = codeTmp.length();
                } else if ((indexOfSpace > 0 && indexOfTab == -1)
                        || (indexOfSpace > 0 && indexOfSpace < indexOfTab)) {
                    indexBlanc = indexOfSpace;
                } else if ((indexOfTab > 0 && indexOfSpace == -1)
                        || (indexOfTab > 0 && indexOfTab < indexOfSpace)) {
                    indexBlanc = indexOfTab;
                } else {
                    indexBlanc = codeTmp.length();
                }
                s = codeTmp.substring(0, indexBlanc);
                sLowerCase = s.toLowerCase(Locale.ROOT);


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
                            niveauIndentation -= 1;
                            debutOuFin = -1;
                            codeResult.append(getIndentation(niveauIndentation));

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
                            if (codeTmp.toString().toLowerCase(Locale.ROOT).equals("sinon")) {
                                // cas "sinon" simple
                                niveauIndentation -= 1;
                                debutOuFin = 1;
                                codeResult.append(getIndentation(niveauIndentation));
                                codeResult.append(majuscule ? "SINON" : "sinon").append("\n");
                            } else {
                                // cas "sinon" complexe = "sinon si"
                                niveauIndentation -= 1;
                                // if(debutOuFin == -1) niveauIndentation -= 1;
                                debutOuFin = 1;
                                codeResult.append(getIndentation(niveauIndentation));
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
                            if(debutOuFin == 1) niveauIndentation += 1;
                            codeResult.append(getIndentation(niveauIndentation));

                            codeResult.append(codeTmp).append("\n");
                        }
                    }
                } else {
                    if(debutOuFin == 1) niveauIndentation += 1;
                    debutOuFin = 0;
                    codeResult.append(getIndentation(niveauIndentation));

                    codeResult.append(codeTmp).append("\n");
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




    // utilitaire

    /**
     * Lit le fichier texte donne par cheminFic, et retourne son contenu sous
     * la forme d'une chaine de caracteres. Si le fichier donne par cheminFic
     * n'existe pas ou ne peut pas etre lu, la methode retourne null.
     *
     * @param cheminFic le chemin du fichier texte a lire.
     * @return le contenu du fichier texte donne par cheminFic ou null si ce
     *         fichier ne peut pas etre lu.
     */
    public static String lireFichierTexte (String cheminFic) {
        File fic = new File(cheminFic);
        StringBuilder texte = null;
        BufferedReader in;

        if (fic.exists() && fic.isFile() && fic.canRead()) {
            try {
                texte = new StringBuilder();
                in = new BufferedReader(new FileReader(fic));

                while (in.ready()) {
                    texte.append(in.readLine()).append("\n");
                }
            } catch (IOException e) {
                System.err.println("ERREUR INATTENDUE, NE DEVRAIT PAS SE PRODUIRE");
            }
        }

        return texte != null ? texte.toString() : null;
    }


    /**
     * Cette methode retourne une nouvelle chaine de caracteres qui represente
     * le texte donne en parametre, auquel on a enleve tous les caracteres
     * blancs au debut de chaque ligne, et tous les caracteres blancs superflus
     * a la fin de chaque ligne (en conservant cependant le caractere '\n' qui
     * marque la fin d'une ligne).
     *
     * @param texte le texte a traiter.
     * @return une nouvelle chaine representant le texte donne en parametre,
     * auquel on a enleve tous les caracteres blancs au debut et a la fin
     * de chaque ligne (en conservant le caractere '\n' a la fin de chaque
     * ligne).
     */
    public static String trimer (String texte) {
        StringBuilder sTrim = new StringBuilder(); //texte a retourner
        String sTmp;
        int debut = 0; //indice du debut de la ligne courante
        int fin; //indice de la fin de la ligne courante
        //parcourir les lignes, et enlever les caracteres blancs avant et après
        //chaque ligne sauf le \n a la fin de la ligne.
        texte = texte.trim() + "\n";
        fin = texte.indexOf("\n", debut);
        while (fin != -1) {
            //extraire la ligne courante et enlever tous les caracteres blancs
            //en debut et fin de ligne
            sTmp = texte.substring(debut, fin).trim();
            //concatener la ligne courante a la chaine a retourner, en ajoutant
            //le saut de ligne a la fin.
            sTrim.append(sTmp).append("\n");
            //ajuster le debut de la ligne courante suivante
            debut = fin + 1;
            //trouver la fin de la ligne courante suivante
            fin = texte.indexOf("\n", debut);
        }
        return sTrim.toString();
    }
}
