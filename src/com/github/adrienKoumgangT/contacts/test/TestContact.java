package com.github.adrienKoumgangT.contacts.test;


import com.github.adrienKoumgangT.contacts.main.Adresse;
import com.github.adrienKoumgangT.contacts.main.Contact;
import com.github.adrienKoumgangT.contacts.main.Telephone;

/**
 * Tests PARTIELS pour tester la classe Contact.
 * Cette classe fournit quelques tests pour tester les methodes de la classe
 * Contact. Ces tests sont PARTIELS, donc ils ne testent pas tous les cas.
 * Vous devez donc faire aussi vos propres tests.
 *
 * Importez cette classe dans votre projet (dans le paquetage par defaut), et
 * executez la methode main pour demarrer les tests.
 *
 * @author Melanie Lord
 * @version A22
 */
public class TestContact {

    /**
     * IMPORTANT :
     *
     * 1. Si les tests plantent, c'est que votre classe Contact comporte
     *    une / des erreur(s)
     *
     * 2. Corriger les erreurs des premiers tests en premier, puisque ceux-ci peuvent
     *    influencer les tests subsequents.
     *
     * @param args non utilise
     */
    public static void main(String[] args) {

      /*
      L'execution de cette methode main devrait afficher ce qui suit, si
      votre code respecte les specifications.

      ****************
      TESTS 1
      ****************

      BARBIN, Stephanie

      TELEPHONE(S) :

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      ------
      NOM, Prenom

      TELEPHONE(S) :

      ADRESSE :

      COURRIEL(S) :
      ------
      0
      0
      0
      Nom
      Prenom
      null
      false
      ------
      LAFOND, Isabelle

      TELEPHONE(S) :

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      ------
      NOM, Prenom [FAVORI]

      TELEPHONE(S) :
      (111) 111-1111, Poste 11 [cellulaire]

      ADRESSE :
      3434 Sherbrooke, app. 2, Montreal, QC, CANADA, B4T6UF

      COURRIEL(S) :
      bob@gmail.com
      ------
      1
      1
      1
      Nom
      Prenom
      3434 Sherbrooke, app. 2, Montreal, QC, CANADA, B4T6UF
      true

      ****************
      TESTS 2
      ****************

      NOM, Prenom [FAVORI]

      TELEPHONE(S) :
      (111) 111-1111, Poste 11 [cellulaire]
      (222) 222-2222, Poste 22 [bureau]
      (333) 333-3333 [maison]
      (444) 444-4444, Poste 44 [Domicile]

      ADRESSE :
      3434 Sherbrooke, app. 2, Montreal, QC, CANADA, B4T6UF

      COURRIEL(S) :
      bob@gmail.com
      ------
      4
      (222) 222-2222, Poste 22 [bureau]
      (444) 444-4444, Poste 44 [Domicile]
      (333) 333-3333 [maison]
      3
      null
      3

      ****************
      TESTS 3
      ****************

      DOE, John [FAVORI]

      TELEPHONE(S) :
      (111) 111-1111, Poste 11 [cellulaire]

      ADRESSE :
      3434 Sherbrooke, app. 2, Montreal, QC, CANADA, B4T6UF

      COURRIEL(S) :
      bob@gmail.com
      ------
      DOE, John

      TELEPHONE(S) :
      (111) 111-1111, Poste 11 [cellulaire]

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      bob@gmail.com
      ------
      LAPIERRE, Charlotte [FAVORI]

      TELEPHONE(S) :
      (111) 111-1111, Poste 11 [cellulaire]

      ADRESSE :

      COURRIEL(S) :
      bob@gmail.com
      ------
      2

      ****************
      TESTS 4
      ****************

      DOE, John

      TELEPHONE(S) :
      (333) 333-3333 [maison]

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      ------
      DOE, John

      TELEPHONE(S) :
      (333) 333-3333 [maison]

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      doe.john@yahoo.ca
      doe.john@uqam.ca
      johnny@gmail.com
      johndoe@hotmail.com
      ------
      4
      doe.john@yahoo.ca
      johnny@gmail.com
      null
      johndoe@hotmail.com
      doe.john@yahoo.ca
      null
      2

      ****************
      TESTS 5
      ****************

      DOE, John

      TELEPHONE(S) :
      (333) 333-3333 [maison]
      (555) 555-5555, Poste 55 [travail]
      (444) 444-4444, Poste 44 [Domicile]
      (111) 111-1111, Poste 11 [cellulaire]

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      yoyo@gmail.com
      titi@gmail.com
      momo@gmail.com
      ------
      DOE, John

      TELEPHONE(S) :
      (333) 333-3333 [maison]
      (555) 555-5555, Poste 55 [travail]
      (444) 444-4444, Poste 44 [Domicile]
      (450) 111-1111, Poste 11 [maison]

      ADRESSE :
      55 Laurier Est, Montreal, QC, CANADA, H2R5H4

      COURRIEL(S) :
      yoyo@gmail.com
      toto@gmail.com
      momo@gmail.com
      */

        //variables
        Contact c1, c2;
        Telephone t1 = new Telephone("111", "1111111", "11", "cellulaire");
        Telephone t2 = new Telephone("222", "2222222", "22", "bureau");
        Telephone t3 = new Telephone("333", "3333333", "", "maison");
        Telephone t4 = new Telephone("444", "4444444", "44", "");
        Telephone t5 = new Telephone("555", "5555555", "55", "travail");

        Adresse a1 = new Adresse("3434", "Sherbrooke", "2", "Montreal", "QC", "Canada",
                "B4T6UF");
        Adresse a2 = new Adresse("55", "Laurier Est", "", "Montreal", "QC", "Canada",
                "H2R5H4");

        String m1 = "bob@gmail.com";

        //------------------------- DEBUT DES TESTS -----------------------------


        System.out.println("\n****************\nTESTS 1\n****************\n");

        c1 = new Contact("Barbin", "Stephanie", a2);
        System.out.println(c1);

        System.out.println("------");

        c1 = new Contact(null, "", null, null, null, false);
        System.out.println(c1);

        System.out.println("------");

        System.out.println(c1.nbrCourriels());
        System.out.println(c1.nbrTelephones());
        System.out.println(Contact.obtenirNbrContactsFavoris());
        System.out.println(c1.getNom());
        System.out.println(c1.getPrenom());
        System.out.println(c1.getAdresse());
        System.out.println(c1.isFavori());

        System.out.println("------");

        c1 = new Contact("Lafond", "Isabelle", a2);
        System.out.println(c1);

        System.out.println("------");

        c2 = new Contact("", "", t1, a1, m1, true);
        System.out.println(c2);

        System.out.println("------");

        System.out.println(c2.nbrCourriels());
        System.out.println(c2.nbrTelephones());
        System.out.println(Contact.obtenirNbrContactsFavoris());
        System.out.println(c2.getNom());
        System.out.println(c2.getPrenom());
        System.out.println(c2.getAdresse());
        System.out.println(c2.isFavori());

        System.out.println("\n****************\nTESTS 2\n****************\n");

        c2.ajouterTelephone(t2);
        c2.ajouterTelephone(t3);
        c2.ajouterTelephone(null);
        c2.ajouterTelephone(t4);

        System.out.println(c2);

        System.out.println("------");

        System.out.println(c2.nbrTelephones());
        System.out.println(c2.obtenirTelephone(1));
        System.out.println(c2.obtenirTelephone(3));

        System.out.println(c2.supprimerTelephone(2));
        System.out.println(c2.nbrTelephones());

        System.out.println(c2.supprimerTelephone(3));
        System.out.println(c2.nbrTelephones());

        System.out.println("\n****************\nTESTS 3\n****************\n");

        c2 = new Contact("Doe", "John", t1, a1, m1, true);
        System.out.println(c2);

        System.out.println("------");

        c2.setAdresse(a2);
        c2.setFavori(false);
        c2.setNom("");
        c2.setPrenom(null);
        System.out.println(c2);

        System.out.println("------");

        c2.setAdresse(null);
        c2.setFavori(true);
        c2.setNom("Lapierre");
        c2.setPrenom("Charlotte");

        System.out.println(c2);

        System.out.println("------");

        System.out.println(Contact.obtenirNbrContactsFavoris());

        System.out.println("\n****************\nTESTS 4\n****************\n");

        c2 = new Contact("Doe", "John", t3, a2, "", false);
        System.out.println(c2);

        System.out.println("------");

        c2.ajouterCourriel("doe.john@yahoo.ca");
        c2.ajouterCourriel("doe.john@uqam.ca");
        c2.ajouterCourriel("johnny@gmail.com");
        c2.ajouterCourriel("johndoe@hotmail.com");

        System.out.println(c2);

        System.out.println("------");

        System.out.println(c2.nbrCourriels());
        System.out.println(c2.obtenirCourriel(0));
        System.out.println(c2.obtenirCourriel(2));
        System.out.println(c2.obtenirCourriel(4));

        System.out.println(c2.supprimerCourriel(3));
        System.out.println(c2.supprimerCourriel(0));
        System.out.println(c2.supprimerCourriel(6));
        System.out.println(c2.nbrCourriels());

        System.out.println("\n****************\nTESTS 5\n****************\n");
        c2 = new Contact("Doe", "John", t3, a2, "", false);
        c2.ajouterTelephone(t5);
        c2.ajouterTelephone(t4);
        c2.ajouterTelephone(t1);
        c2.ajouterCourriel("yoyo@gmail.com");
        c2.ajouterCourriel("titi@gmail.com");
        c2.ajouterCourriel("momo@gmail.com");

        System.out.println(c2);

        System.out.println("------");

        c2.modifierCourriel(1, "toto@gmail.com");
        c2.modifierTelephone(3, "450", "", null, "maison");

        System.out.println(c2);

    }

}
