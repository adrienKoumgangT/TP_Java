package com.github.adrienKoumgangT.ordonnancement.main;

import com.github.adrienKoumgangT.utils.Clavier;

/**
 * <h1>French version</h1>
 * <p>
 * Source de la documentation : @link <a href="https://fr.wikipedia.org/wiki/Théorie_de_l%27ordonnancement">Wikipedia Ordonnancement</a>
 * </p>
 *  <br>
 * <h2> Introduction </h2>
 * <p>
 * La théorie de l'ordonnancement est une branche de la recherche opérationelle
 * qui s'intéresse au calcul de dates d'exécution optimales de taches. Pour cela,
 * il est très souvent nécessaire d'affecter en meme temps les ressources nécessaires
 * à l'exécution de ces taches. Un problème d'ordonnancement peut etre considéré comme
 * un sous-problème de planification dans lequel il s'agit de décider de l'exécution
 * opérationellelle des taches planifiées.
 * </p>
 *  <br>
 * <h2> Définition </h2>
 * <p>
 * Un problème d'ordonnancement consiste à organiser dans le temps la réalisation de taches,
 * compte tenu de contraintes temporelles (délais, contraintes d'enchainement)
 * et de contraintes portant sur la disponibilité des ressources requises.
 * </p>
 *  <br>
 * <h2> Les taches </h2>
 * <p>
 * Une tache est une entité élémentaire localisée dans le temps par une date de début
 * et/ou de fin, dont la réalisation nécessite une durée, et qui consomme en moyen selon
 * une certaine intensité.
 * Certains modèles intègrent la notion de date d'échéance, une date à laquelle la tache doit etre finie;
 * dans ces cas, le retard induit une pénalité.
 * </p>
 *  <br>
 * <h2> Les ressources </h2>
 * <p>
 * La ressource est un moyen technique ou humain destiné à etre utilisé pour la réalisation
 * d'une tache et disponible en quantité limitée, sa capacité.
 * </p>
 *  <br>
 * <h2> Les contraintes </h2>
 * <p>
 * Les contraintes expriment des restrictions sur les valeurs que peuvent prendre simultanément
 * les variables de décision. On distingue :
 * <ul>
 *     <li>
 *         des contraintes temporelles :
 *         <ul>
 *             <li>
 *                 les contraintes de temps alloué, issues généralement d'impératifs de gestion
 *                 et relatives aux dates limites des taches (délais de livraison, disponibilité des
 *                 approvisionnements) ou à la durée totale d'un projet;
 *             </li>
 *             <li>
 *                 les contraintes de cohérence technologique, ou contraintes de gammes,
 *                 qui décrivent des relations d'ordre entre les différentes taches;
 *             </li>
 *         </ul>
 *     </li>
 *     <li>
 *         des contraintes de ressources :
 *         <ul>
 *             <li>
 *                 les contraintes d'utilisation de ressources qui expriment la nature et la quantité
 *                 des moyens utilisés par les taches, ainsi que les caractéristiques d'utilisation
 *                 de ces moyens;
 *             </li>
 *             <li>
 *                 les contraintes de disponibilités des ressources qui précisent la nature et la quantité
 *                 des moyens disponibles au cours du temps. Toutes ces contraintes peuvent etre
 *                 formalisées sur la base des distances entre débuts de taches ou potentiels.
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 * </p>
 *
 * <h1>English version</h1>
 *
 * @author Adrien Koumgang Tegantchouang
 * @version 1.0
 * @since 11
 */
public class Ordonnancement {

    /**
     * Chemin d'accès au dossier contenant les fichiers sources
     */
    public static String PATH_DIR = "src/com/github/adrienKoumgangT/ordonnancement/test/files/input/";

    /**
     * Fonction qui me permet de lancer mon programme d'ordonnancement
     */
    public static void run() {
        boolean finish = false;
        String file = "";
        do {
            System.out.print("Si vous voulez lire un graphe Appuyez sur entrer 'oui' sinon entrez 'non' (default) et le programme s'arrêtera : ");
            file = Clavier.lireString();
            if(file == null || file.isEmpty()) {
                System.out.println("Valeur par défaut considérer : 'non'");
                finish = true;
            } else {
                finish = file.equalsIgnoreCase("oui");
            }
            if(finish) continue;

            file = "";

            while(file.isEmpty()) {
                System.out.println("Entrez le nom du fichier : ");
                file = Clavier.lireString();
                if(file.isEmpty()) System.out.println("Fichier invalide.");
            }

            try {
                Graph graph = new Graph(PATH_DIR + file);
                graph.affichageJeuDetriplets();
                graph.affichageMatrice();
                graph.afficheDetectionDeCircuit();
            } catch (Exception e) {
                e.printStackTrace();
                finish = true;
                continue;
            }
            System.out.println();
        } while(finish);
    }

}
