package com.github.adrienKoumgangT.INF1120A22TP2.test;

import com.github.adrienKoumgangT.INF1120A22TP2.v3.IndenteurPseudocode;
import com.github.adrienKoumgangT.utils.Utils;


public class TestINF1120A22TP2 {
    public static void main(String[] args) {
        String fichier = "src/com/github/adrienKoumgangT/INF1120A22TP2/test/fichiers/test2.psdo";
        test(fichier);
    }

    public static void test(String file) {
        System.out.println("fichier choisi == " + file);
        String contenuFic = Utils.lireFichierTexte(file);
        System.out.println("contenu du fichier choisi :");
        System.out.println("-----------------------------------");
        System.out.println(contenuFic);
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("Code indenter :");
        System.out.println("-----------------------------------");
        System.out.println(IndenteurPseudocode.indenterPseudocode(contenuFic, true));
        System.out.println("-----------------------------------");
    }
}
