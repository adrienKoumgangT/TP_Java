package com.github.adrienKoumgangT.ordonnancement.main;

import com.github.adrienKoumgangT.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Graph est un obj
 *
 * @author Adrien Koumgang Tegantchouang
 * @version 1.0
 * @since 11
 */
public class Graph {
    // liste des taches
    public List<Long> taches;

    // tache --> sa durée
    public Map<Long, Long> tachesDuree;

    // tache --> sa liste de successeurs
    public Map<Long, List<Long>> predecesseurs;

    // tache --> sa liste de successeurs
    public Map<Long, List<Long>> successeurs;

    /**
     * Unique constructeur du graph qui initialise le graph
     *
     * @param pathFile : le nom du fichier contenant les informations
     *                 permettant d'initialiser le graph
     * @throws Exception si le nom du fichier n'est pas valide
     */
    public Graph(String pathFile) throws Exception {
        if(!Utils.isGoodPathFile(pathFile)) throw new Exception("mauvais fichiers");

        this.taches = new ArrayList<>();
        this.tachesDuree = new HashMap<>();
        this.predecesseurs = new HashMap<>();
        this.successeurs = new HashMap<>();
        this.taches.add(0L);
        this.tachesDuree.put(0L, 0L);
        this.predecesseurs.put(0L, new ArrayList<>());
        this.successeurs.put(0L, new ArrayList<>());
        this.readTextFile(pathFile);
        this.setAlpha();
        this.setOmega();
    }

    /**
     * Méthode qui me permet d'initialiser le nœud alpha
     * qui représente le nœud d'entrée (avec comme valeur 0).
     * Ce nœud n'a pas de prédécesseur et se connecte aux nœuds
     * qui n'ont pas de prédécesseurs dans le graph en leur
     * prenant comme successeurs.
     */
    private void setAlpha() {
        if(!this.taches.contains(0L)) this.taches.add(0L);

        for(Long tache: this.taches) {
            if(tache == 0L) continue;
            if(this.predecesseurs.get(tache).isEmpty()) {
                this.predecesseurs.get(tache).add(0L);
                this.successeurs.get(0L).add(tache);
            }
        }
    }

    /**
     * Méthode qui me permet d'initialiser le nœud omega
     * qui représente le nœud de sortie (avec comme valeur N+1,
     * avec N le nombre de nœuds du graph sans le nœud alpha).
     * Ce nœud n'a pas de successeurs et se connecte au nœud
     * qui n'ont pas de successeurs et prend comme prédécesseur
     * les nœuds qui n'ont pas de successeurs.
     */
    private void setOmega() {
        Long last = (long) (this.taches.size());
        this.taches.add(last);
        this.predecesseurs.put(last, new ArrayList<>());
        this.successeurs.put(last, new ArrayList<>());

        for(Long tache: this.taches) {
            if(Objects.equals(tache, last)) continue;
            if(this.successeurs.get(tache).isEmpty()) {
                this.predecesseurs.get(last).add(tache);
                this.successeurs.get(tache).add(last);
            }
        }
    }


    /**
     * Méthode qui donne le nombre de sommets du graph
     * en incluant les sommets alpha et omega.
     *
     * @return numéro total de sommets du graph
     */
    public int numSommets() {
        return this.taches.size();
    }

    /**
     * Méthode qui donne le nombre d'arcs du graph
     * en incluant les arcs qui connectent les nœuds
     * internes avec les sommets alpha et omega.
     *
     * @return nombre total d'arcs du graph
     */
    public int numArcs() {
        int total = 0;
        for(List<Long> pre : this.successeurs.values()) {
            total += pre.size();
        }
        return total;
    }

    /**
     * Méthode qui me permet d'afficher le jeu de triplets
     * qui représente le graph.
     */
    public void affichageJeuDetriplets() {
        System.out.println("Affichage du graphe comme un jeu de triplets :");
        System.out.println(this.numSommets() + " sommets");
        System.out.println(this.numArcs() + " arcs");
        for(Long tache: this.taches) {
            if(tache == this.taches.size()) continue;
            for(Long successeur: this.successeurs.get(tache)) {
                System.out.println(tache + " -> " + successeur + " = " + this.tachesDuree.get(tache));
            }
        }
        System.out.println();
    }

    public void affichageMatrice() {
        System.out.println("Affichage du graph comme matrice des valeurs");
        System.out.format("%4s", " ");
        for(Long tache: this.taches) {
            System.out.format("%4d", tache);
        }
        System.out.println();
        for(Long u: this.taches) {
            System.out.format("%4d", u);
            for(Long v: this.taches) {
                if(this.successeurs.get(u).contains(v)) {
                    System.out.format("%4d", this.tachesDuree.get(u));
                } else {
                    System.out.format("%4s", "*");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public void afficheDetectionDeCircuit() {
        List<Long> t = new ArrayList<>(this.taches);
        // Map<Long, Long> td = new HashMap<>(this.tachesDuree);
        Map<Long, List<Long>> p = new HashMap<>(this.predecesseurs);
        Map<Long, List<Long>> s = new HashMap<>(this.successeurs);

        System.out.println("* Détection de circuit");
        System.out.println("* Méthode d'élimination des points d'entrée");
        boolean finish = false;
        do {
            System.out.print("Points d'entrée :");
            Set<Long> points = new HashSet<>();
            for(Long point : t) {
                if(p.get(point).isEmpty()) {
                    points.add(point);
                    System.out.print(" " + point);
                }
            }
            System.out.println();
            if(points.isEmpty()) {
                System.out.println("Aucun point d'entrée n'a pu etre sélectionné : Il y a un circuit...!");
                finish = true;
                continue;
            }
            System.out.println("Suppression des points d'entrée");
            for(Long point : t) {
                if(points.contains(point)) {
                    p.remove(point);
                    s.remove(point);
                } else {
                    p.get(point).removeAll(points);
                    // étant des points d'entrée, ils ne devraient
                    // pas avoir de prédecesseurs (ils ne peuvent faire
                    // partie des successeurs)
                    // this.successeurs.get(point).removeAll(points);
                }
            }
            t.removeAll(points);
            System.out.print("Sommets restant :");
            if(t.isEmpty()) {
                System.out.print(" Aucun");
                finish = true;
            } else {
                for(Long point : t) {
                    System.out.print(" " + point);
                }
            }
            System.out.println();
        } while (!finish);
        System.out.println("-> Il n'y a pas de circuit");
    }



    /**
     * Fonction qui me permet de lire un fichier txt
     * et me retourne le graph correspondant aux données
     * contenu dans le fichier
     *
     */
    private void readTextFile(String namefile) throws Exception {
        if(!Utils.isGoodPathFile(namefile)) throw new Exception("mauvais fichier!");

        String ligne = "";
        BufferedReader in;

        try {
            in = new BufferedReader(new FileReader(namefile));

            while(in.ready()) {
                ligne = in.readLine().trim();

                if(!ligne.isEmpty()) {
                    String[] values =  ligne.split(" ");
                    if(values.length >= 2) {
                        // je prends le tout premier élément de la ligne : c'est la tache
                        Long t = Long.valueOf(values[0]);
                        this.taches.add(t);

                        // je prends le deuxième élément de la ligne : c'est la durée de la tache
                        this.tachesDuree.put(t, Long.valueOf(values[1]));

                        // je crée une liste vide de successeurs et de predecesseurs de la tache courante
                        this.successeurs.putIfAbsent(t, new ArrayList<>());
                        this.predecesseurs.putIfAbsent(t, new ArrayList<>());

                        // s'il y a d'autres chiffres, ce sont des contraintes
                        List<Long> predecesseurs = new ArrayList<>();
                        for(int i=2; i<values.length; i++) {
                            Long value = Long.valueOf(values[i]);
                            this.predecesseurs.get(t).add(value);
                            if(this.successeurs.containsKey(value)){
                                this.successeurs.get(value).add(t);
                            } else {
                                List<Long> l = new ArrayList<>();
                                l.add(t);
                                this.successeurs.put(value, l);
                            }
                        }
                    } else {
                        // j'ai une ligne avec moi de deux éléments
                        // TODO: à choisir comment traier ce cas
                        throw new Exception("Ligne invalide : " + ligne);
                    }
                } else {
                    // j'ai une ligne vide dans le fichier
                    // TODO: à choisir comment traiter ce cas
                    throw new Exception("Ligne vide invalide.");
                }
            }
        } catch (IOException e) {
            // TODO: à voir (ne devrait pas normalement arriver)
            e.printStackTrace();
        }
    }
}
