package com.github.adrienKoumgangT.contacts.main;

import java.util.Arrays;

/**
 * Cette classe modélise un contact téléphonique.
 *
 * @author Adrien Koumgang Tegantchouang
 * @version décembre 2022.
 */
public class Contact {

    //--------------
    //CONSTANTES
    //--------------

    //Valeurs par défaut pour les attributs

    public final static String NOM_DEFAUT = "Nom"; // Nom par défaut d'un contact
    public final static String PRENOM_DEFAUT = "Prenom"; // Prénom par déFaut d'un contact


    //--------------------
    //ATTRIBUTS DE CLASSE
    //--------------------


    public static int nbrContactsFavoris = 0; // Nombre de contacts créés qui ont été signalés come étant des contacts favoris.

    //--------------------
    //ATTRIBUTS D'INSTANCE
    //--------------------

    private String nom = NOM_DEFAUT; // Nom du contact.
    private String prenom = PRENOM_DEFAUT; // Prénom du contact.
    private Telephone[] telephones = new Telephone[0]; // Différents téléphones du contact.
    private Adresse adresse = null; // L'adresse du contact.
    private String[] courriels = new String[0]; // Différents courriels du contact.
    private boolean favori = false; // true si ce contact est un favori, false sinon.

    //--------------
    //CONSTRUCTEURS
    //--------------

    /**
     * Construit un object Contact en initialisant ses attributs avec les valeurs passées en paramètre.
     *
     * Si le paramètre nom est null ou vide, l'attribut nom sera plutôt initialisé avec sa valeur par défaut NOM_DEFAUT
     * Si le paramètre prenom est null ou vide, l'attribut prenom sera plutôt initialisé avec sa valeur par défaut PRENOM_DEFAUT
     *
     * Si le paramètre tel est null, aucun téléphone n'est ajouté à ce contact
     *
     * Si le paramètre courriel est null ou vide, aucun courriel n'est ajouté à ce contact
     *
     * @param nom le nom de ce contact
     * @param prenom le prénom de ce contact
     * @param tel un premier téléphone à ajouter aux telephones de ce contact
     * @param adresse l'adresse de ce contact
     * @param courriel un premier courriel à ajouter aux courriels
     * @param favori true si ce contact est un favori, false sinon
     */
    public Contact (String nom, String prenom, Telephone tel, Adresse adresse, String courriel, boolean favori) {
        if (estNomValide(nom))
            this.nom = nom;

        if(estNomValide(prenom))
            this.prenom = prenom;

        if(tel != null){
            this.telephones = new Telephone[1];
            this.telephones[0] = tel;
        }

        this.adresse = adresse;

        if(courriel != null && !courriel.isEmpty()) {
            this.courriels = new String[1];
            this.courriels[0] = courriel;
        }

        if(favori) Contact.nbrContactsFavoris++;
        this.favori = favori;
    }

    /**
     * Ce constructeur construit un object contact en initialisant ses attributs nom,
     * prenom et adresse avec les valeurs passées en paramètre.
     *
     * Si le paramètre nom est null ou vide, l'attribut nom de ce contact est initialisé à NOM_DEFAUT.
     *
     * Si le paramètre prenom est null ou vide, l'attribut prenom de ce contact dest initialisé à PRENOM_DEFAUT.
     *
     * L'attribut favori est initialisé à false
     *
     * Aucun téléphone n'est associé à ce contact
     *
     * Aucun courriel n'est associé à ce contact
     *
     * @param nom le nom de ce contact
     * @param prenom le prénom de ce contact
     * @param adresse l'adresse de ce contact
     */
    public Contact (String nom, String prenom, Adresse adresse) {
        this(nom, prenom, null, adresse, null, false);
    }

    //------------------------------------
    //METHODES D'INSTANCE PUBLIQUES
    //------------------------------------

    //------------------------------------
    //OBSERVATEURS (getters)
    //------------------------------------

    /**
     * Permet d'obtenir le nom de ce contact.
     * @return le nom de ce contact.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Permet d'obtenir le prénom de ce contact.
     * @return le prénom de ce contact.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Permet d'obtenir l'adresse de ce contact.
     * @return l'adresse de ce contact.
     */
    public Adresse getAdresse() {
        return this.adresse;
    }

    /**
     * Permet de savoir si le contact est un favori.
     * @return true si c'est un favori, false sinon.
     */
    public boolean isFavori() {
        return this.favori;
    }

    //------------------------------------
    //MODIFICATEURS (setters)
    //------------------------------------

    /**
     * Modifie le nom de ce contact
     *
     * Si nom est null ou vide, la modification n'est pas effectuée
     *
     * @param nom le nouvel nom de ce contact
     */
    public void setNom(String nom) {
        if (estNomValide(nom))
            this.nom = nom;
    }

    /**
     * Modifie le prénom de ce contact
     *
     * Si prenom est null ou vide, la modification n'est pas effectuée
     *
     * @param prenom le nouvel prénom de ce contact
     */
    public void setPrenom(String prenom) {
        if(estNomValide(prenom))
            this.prenom = prenom;
    }

    /**
     * Modifie l'adresse de ce contact
     *
     * @param adresse le nouvel adresse de ce contact
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    /**
     * Modifie le favori de ce contact
     * @param favori le nouvel favori
     */
    public void setFavori(boolean favori) {
        if(this.favori != favori) {
            if(favori) Contact.nbrContactsFavoris++;
            else Contact.nbrContactsFavoris--;

            this.favori = favori;
        }
    }

    /**
     * Permet d'obtenir une representation sous forme de chaine de caracteres de
     * ce contact.
     *
     * @return une representation sous forme de chaine de caracteres de
     *         ce contact.
     */
    public String toString() {
        StringBuilder strTel = new StringBuilder();
        for (Telephone tel : this.telephones)
            strTel.append(tel.toString()).append("\n");
        StringBuilder strCourriel = new StringBuilder();
        for (String cour : this.courriels)
            strCourriel.append(cour).append("\n");

        return nom.toUpperCase() + " " + prenom + (this.favori ? "[FAVORI]" : "") + "\n"
                + "\n"
                + "TELEPHONE(S) :\n"
                + strTel
                + "\n"
                + "ADRESSE :\n"
                + adresse + "\n"
                + "\n"
                + "COURRIEL(S) :\n"
                + strCourriel
                ;
    }

    /**
     * Permet d'ajouter le tel donné en paramètre aux téléphones de ce contact.
     *
     * @param tel le téléphone à ajouter aux téléphones de ce contact
     */
    public void ajouterTelephone(Telephone tel) {
        if(tel != null) {
            Telephone[] newTelephones = new Telephone[this.telephones.length+1];
            for(int i=0; i<this.telephones.length; i++)
                newTelephones[i] = this.telephones[i];
            newTelephones[this.telephones.length] = tel;
            this.telephones = newTelephones;
        }
    }

    /**
     * Permet d'ajouter le courriel donné en paramètre aux courriels de ce contact.
     *
     * @param courriel le courriel à ajouter aux courriels de ce contact
     */
    public void ajouterCourriel(String courriel) {
        if(courriel != null && !courriel.isEmpty()) {
            String[] newCourriels = new String[this.courriels.length+1];
            for(int i=0; i<this.courriels.length; i++)
                newCourriels[i] = this.courriels[i];
            newCourriels[this.courriels.length] = courriel;
            this.courriels = newCourriels;
        }
    }

    /**
     * Permet d'obtenir le téléphone des téléphones de ce contact qui se trouve à la position donnée.
     *
     * Si la position donnée ne correspond ps à u indice valide, la méthode retourne null.
     *
     * @param position la position dans le tableau téléphones de ce contact.
     * @return le téléphone de ce contact si l'indice est valide, null sinon.
     */
    public Telephone obtenirTelephone(int position) {
        return position < this.telephones.length ? this.telephones[position] : null;
    }

    /**
     * Permet d'obtenir le courriel des courriels de ce contact qui se trouve à la position donnée.
     *
     * Si la position donnée ne correspond ps à u indice valide, la méthode retourne null.
     *
     * @param position la position dans le tableau courriels de ce contact.
     * @return le courriel de ce contact si l'indice est valide, null sinon.
     */
    public String obtenirCourriel(int position) {
        return position < this.courriels.length ? this.courriels[position] : null;
    }

    /**
     * Permet de supprimer le téléphone se trouvant à la position donnée du tableau de téléphones de ce contact.
     *
     * Si la position donnée ne correspond pas à un indice valide dans le tableau téléphones, la suppression n'a pas lieu.
     *
     * @param position la position dans le tableau téléphones de ce contact du téléphone à supprimer.
     * @return le téléphone supprimé.
     */
    public Telephone supprimerTelephone(int position) {
        if(position < this.telephones.length) {
            Telephone[] newTelephones = new Telephone[this.telephones.length-1];
            for(int i=0, j=0; i<this.telephones.length; i++) {
                if(i!=position) {
                    newTelephones[j] = this.telephones[i];
                    j++;
                }
            }
            Telephone t = this.telephones[position];
            this.telephones = newTelephones;
            return t;
        }
        return null;
    }

    /**
     * Permet de supprimer le courriel se trouvant à la position donnée du tableau de courriels de ce contact.
     *
     * Si la position donnée ne correspond pas à un indice valide dans le tableau courriels, la suppression n'a pas lieu.
     *
     * @param position la position dans le tableau courriels de ce contact du courriel à supprimer.
     * @return le courriel supprimé.
     */
    public String supprimerCourriel(int position) {
        if(position < this.courriels.length) {
            String[] newCourriels = new String[this.courriels.length-1];
            for(int i=0, j=0; i<this.courriels.length; i++) {
                if(i!=position) {
                    newCourriels[j] = this.courriels[i];
                    j++;
                }
            }
            String c = this.courriels[position];
            this.courriels = newCourriels;
            return c;
        }
        return null;
    }

    /**
     * Permet de modifier les attributs du téléphone se trouvant à la position donnée dans le tableau téléphones de ce contact.
     *
     * Si la position donnée ne correspond pas à un indice valide dans le tableau de téléphones, aucune modification n'a lieu.
     *
     * Si le paramètre indReg est null, aucune modification n'est effectuée.
     *
     * Si le paramètre numero est null, aucune modification n'est effectuée.
     *
     * Si le paramètre poste est null, aucune modification n'est effectuée.
     *
     * Si le paramètre type est null, aucune modification n'est effectuée.
     *
     * @param position la position dans le tableau telephones de ce contact du téléphone à modifier.
     * @param indReg la nouvelle valeur pour l'indicatif régional du téléphone.
     * @param numero la nouvelle valeur pour le numéro du téléphone.
     * @param poste la nouvelle valeur pour le poste du téléphone à modifier.
     * @param type la nouvelle valeur pour le type du téléphone à modifier.
     */
    public void modifierTelephone(int position, String indReg, String numero, String poste, String type) {
        if(position < this.telephones.length) {
            if(indReg != null) this.telephones[position].setIndReg(indReg);
            if(numero != null) this.telephones[position].setNumero(numero);
            if(poste != null) this.telephones[position].setPoste(poste);
            if(type != null) this.telephones[position].setType(type);
        }
    }

    /**
     * Permet de remplacer le courriel se trouvant à la position donnée dans le tableau courriels de ce contact par celui passé en paramètre.
     *
     * Si la position donnée ne correspond pas à un indice valide dans le tableau courriels, aucune modification n'a lieu.
     *
     * Si le courriel donnée est null ou vide, aucune modification n'a lieu.
     *
     * @param position la position dans le tableau courriels de ce contact du courriel à modifier.
     * @param courriel la nouvelle valeur pour le courriel à modifier.
     */
    public void modifierCourriel(int position, String courriel) {
        if(position < this.courriels.length
            && courriel != null
            && !courriel.isEmpty())
        {
            this.courriels[position] = courriel;
        }
    }

    /**
     * Permet d'obtenir le nombre de téléphones associés à ce contact.
     *
     * @return le nombre de téléphones associés à ce contact.
     */
    public int nbrTelephones() {
        return this.telephones.length;
    }

    /**
     * Permet d'obtenir le nombre de courriels associés à ce contact.
     *
     * @return le nombre de courriels associés à ce contact.
     */
    public int nbrCourriels() {
        return this.courriels.length;
    }

    //--------------------------
    //METHODE DE CLASSE PRIVEE
    //--------------------------

    /**
     * Permet de valider si le nom passé en paramètre est valide.
     * Un nom ne doit pas etre null et vide.
     *
     * @param nom le nom à vérifier.
     * @return true si le nom est valide et false sinon.
     */
    private static boolean estNomValide (String nom) {
        return nom != null && !nom.isEmpty();
    }

    //--------------------------
    //METHODE DE CLASSE PUBLIC
    //--------------------------

    /**
     * Permet d'obtenir la valeur de l'attribut de classe nbrContactsFavoris
     *
     * @return le nombre de contacts favoris
     */
    public static int obtenirNbrContactsFavoris() {
        return Contact.nbrContactsFavoris;
    }
}
