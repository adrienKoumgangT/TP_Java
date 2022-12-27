package com.github.adrienKoumgangT.ordonnancement.test;

import com.github.adrienKoumgangT.ordonnancement.main.Graph;


public class TestOrdonnancement {

    public static String PATH_DIR = "src/com/github/adrienKoumgangT/ordonnancement/test/files/input/";

    public static void main(String[] args) {
        String filename = PATH_DIR + "file01.txt";

        try {
            Graph g = new Graph(filename);
            g.affichageJeuDetriplets();
            System.out.println();
            System.out.println();
            System.out.println();
            g.affichageMatrice();
            System.out.println();
            System.out.println();
            g.afficheDetectionDeCircuit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
