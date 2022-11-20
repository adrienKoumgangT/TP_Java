package com.github.adrienKoumgangT.utils;

import java.io.*;
import java.util.ArrayList;


public class Utils {

   //nom du fichier dans lequel on lit la liste des livres en inventaire
   //Doit se trouver a la racine du projet.
   public final static String CHEMIN_FIC = "inventaire.txt";

   //separateur des infos sur un livre dans une ligne du fichier
   public final static String SEPARATEUR = "\t";

   /**
    * Lit le fichier texte donne par CHEMIN_FIC, et retourne son contenu sous
    * la forme d'un tableau à 2 dimensions (tableau de tableaux de String).
    *
    * Si le fichier donne par CHEMIN_FIC n'existe pas, la methode retourne
    * un tableau vide.
    *
    * @return le contenu du fichier texte donne par CHEMIN_FIC sous forme
    * d'un tableau de String a 2 dimensions.
    */
   public static String[][] lireFichierInventaire() {

      ArrayList<String[]> liste = new ArrayList<>();
      String ligne = "";
      BufferedReader in;
      String[] tabLigne;

      try {
         in = new BufferedReader(new FileReader(CHEMIN_FIC));

         while (in.ready()) {
            ligne = in.readLine().trim();

            if (!ligne.isEmpty() && ligne.charAt(0) != '#') {
               tabLigne = ligne.split(SEPARATEUR);
               liste.add(tabLigne);
            }
         }

      } catch (IOException e) {
         //ne rien faire, ne devrait pas se produire
      }

      return liste.toArray(new String[0][0]);
   }


   /**
    * Valide un entier entre min et max inclusivement.
    *
    * ANTÉCÉDENT : ON SUPPOSE QUE MIN <= MAX.
    *
    * @param msgSol le message de sollicitation a afficher
    * @param msgErr le message d'erreur a afficher
    * @param min la borne minimum valide
    * @param max la borne maximum valide
    * @return un entier entre min et max inclusivement.
    */
   public static int validerEntier(String msgSol, String msgErr,
                                   int min, int max) {
      int entier = 0;
      boolean valide;

      do {
         try {
            System.out.print(msgSol);
            entier = Clavier.lireInt();
            valide = entier >= min && entier <= max;

         } catch (NumberFormatException e) {
            valide = false;
         }

         if (!valide) {
            System.out.println(msgErr);
         }

      } while (!valide);

      return entier;
   }






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


   public static String repeatString(String text, long n) {
      if (text == null) return null;
      if (n <= 1) return text;
      StringBuilder result = new StringBuilder();
      for(int i=0; i<n; i++) {
         result.append(text);
      }
      return result.toString();
   }
}
