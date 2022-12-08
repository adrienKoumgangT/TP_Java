package com.github.adrienKoumgangT.contacts.main;


/**
 * Cette classe modelise un numero de telephone.
 * Classe fournie dans le cadre du TP3 INF1120 A22.
 * @author Melanie Lord
 * @version novembre 2022.
 */
public class Telephone {

    //--------------
    //CONSTANTES
    //--------------

    //Valeurs par defaut pour les attributs
    public final static String IND_REG_DEFAUT = "000";
    public final static String NUMERO_DEFAUT = "0000000";
    public final static String POSTE_DEFAUT = "";
    public final static String TYPE_DEFAUT = "Domicile";

    //Pour validation
    private final static int LNG_NUM = 7;       //lng valide pour le numero
    private final static int LNG_IND_REG = 3;   //lng valide pour l'ind. reg.

    //--------------------
    //ATTRIBUTS D'INSTANCE
    //--------------------

    private String indReg = IND_REG_DEFAUT; //l'indicatif regional (3 car num)
    private String numero = NUMERO_DEFAUT;  //le numero de telephone (7 car num)
    private String poste = POSTE_DEFAUT;    //le poste telephonique

    //type de telephone (portable, domicile, etc...)
    private String type = TYPE_DEFAUT;

    //--------------
    //CONSTRUCTEURS
    //--------------

    /**
     * Construit un objet Telephone en initialisant son indicatif regional, son
     * numero, son poste, et son type avec les valeurs passees en parametres.
     *
     * Si l'un des parametres donnnes est null ou vide, l'attribut correspondant
     * est plutot initialise avec sa valeur par defaut (voir constantes de classe).
     *
     * Si l'indReg donne est invalide (n'est pas une chaine de LNG_IND_REG
     * caracteres numeriques), cet attribut sera plutot initialise avec sa valeur
     * par defaut.
     *
     * Si le numero donne est invalide (n'est pas une chaine de LNG_NUM caracteres
     * numeriques), cet attribut sera plutot initialise avec sa valeur par defaut.
     *
     * @param indReg l'indicatif regional de ce telephone
     * @param numero le numero de ce telephone.
     * @param poste le poste telephonique de ce telephone.
     * @param type le type de ce telephone (portable, domicile, bureau...).
     */
    public Telephone (String indReg, String numero, String poste, String type) {

        if (estNumValide(indReg, LNG_IND_REG))
            this.indReg = indReg;

        if (estNumValide(numero, LNG_NUM))
            this.numero = numero;

        if (poste != null && !poste.isEmpty())
            this.poste = poste;

        if (type != null && !type.isEmpty())
            this.type = type;
    }

    //------------------------------------
    //METHODES D'INSTANCE PUBLIQUES
    //------------------------------------

    /**
     * Permet d'obtenir l'indicatif regional de ce telephone.
     * @return l'indicatif regional de ce telephone.
     */
    public String getIndReg() {
        return indReg;
    }

    /**
     * Modifie l'indicatif regional de ce telephone par celui passe en parametre.
     *
     * Si l'indReg donne n'est pas une chaine numerique de LNG_IND_REG caracteres,
     * l'indicatif regional de ce telephone ne sera pas modifie.
     *
     * @param indReg le nouvel indicatif regional a assigner a ce telephone.
     */
    public void setIndReg(String indReg) {
        if (estNumValide(indReg, LNG_IND_REG))
            this.indReg = indReg;
    }

    /**
     * Permet d'obtenir le numero de ce telephone.
     * @return le numero de ce telephone.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Modifie le numero de ce telephone par celui passe en parametre.
     *
     * Si le numero donne n'est pas une chaine numerique de LNG_NUM caracteres,
     * le numero de ce telephone ne sera pas modifie.
     *
     * @param numero le nouveau numero a assigner a ce telephone.
     */
    public void setNumero(String numero) {
        if (estNumValide(numero, LNG_NUM))
            this.numero = numero;
    }

    /**
     * Permet d'obtenir le poste de ce telephone.
     * @return le poste de ce telephone.
     */
    public String getPoste() {
        return poste;
    }

    /**
     * Modifie le poste de ce telephone par celui passe en parametre.
     *
     * Si le poste donne est null, le poste de ce telephone sera modifie
     * avec la chaine POSTE_DEFAUT.
     *
     * @param poste le nouveau poste a assigner a ce telephone.
     */
    public void setPoste(String poste) {
        if (poste == null)
            poste = POSTE_DEFAUT;

        this.poste = poste;
    }

    /**
     * Permet d'obtenir la valeur du type de ce telephone.
     * @return le type de ce telephone.
     */
    public String getType() {
        return type;
    }

    /**
     * Modifie le type de ce telephone par celui passe en parametre.
     *
     * Si le type donne est null ou vide, le type de ce telephone ne sera pas
     * modifie.
     *
     * @param type le nouveau type a assigner a ce telephone.
     */
    public void setType(String type) {
        if (type != null && !type.isEmpty())
            this.type = type;
    }

    /**
     * Permet d'obtenir une representation sous forme de chaine de caracteres de
     * ce telephone.
     *
     * @return une representation sous forme de chaine de caracteres de
     *         ce telephone.
     */
    public String toString() {
        String num = "(" + indReg + ") " + numero.substring(0, 3) + "-"
                + numero.substring(3);

        if (!poste.isEmpty())
            num = num + ", Poste " + poste;

        num = num + " [" + type + "]";

        return num;
    }


    //--------------------------
    //METHODE D'INSTANCE PRIVEE
    //--------------------------

    /**
     * Permet de verifier si le num passe en parametre est valide.
     * Un numero valide doit etre compose de lngValide caracteres numeriques.
     * @param num le numero a verifier.
     * @param lngValide la longueur valide de la chaine num.
     * @return true si num est valide, false sinon.
     */
    private static boolean estNumValide (String num, int lngValide) {

        boolean valide = false;

        try {
            Integer.parseInt(num);
            valide = num.length() == lngValide;
        } catch (NumberFormatException | NullPointerException e) {
            //ne rien faire
        }

        return valide;
    }
}
