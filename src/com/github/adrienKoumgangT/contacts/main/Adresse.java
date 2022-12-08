package com.github.adrienKoumgangT.contacts.main;


/**
 * Cette classe modélise une adresse postale.
 * Classe fournie dans le cadre du TP3 INF1120 A22.
 * @author Melanie Lord
 * @version Novembre 2022
 */
public class Adresse {

    //--------------------
    //CONSTANTES DE CLASSE
    //--------------------
    public final static String NO_PORTE_DEFAUT = "NO";
    public final static String NOM_RUE_DEFAUT = "RUE";
    public final static String APT_DEFAUT = "";
    public final static String VILLE_DEFAUT = "VILLE";
    public final static String PROVINCE_DEFAUT = "PROVINCE";
    public final static String PAYS_DEFAUT = "PAYS";
    public final static String CODE_POSTAL_DEFAUT = "";

    //--------------------
    //ATTRIBUTS D'INSTANCE
    //--------------------

    //valeurs par defaut pour les differents attributs
    private String noPorte = NO_PORTE_DEFAUT;       //le no de porte
    private String nomRue = NOM_RUE_DEFAUT;         //le nom de la rue
    private String apt = APT_DEFAUT;                //le no d'apartement
    private String ville = VILLE_DEFAUT;            //la ville
    private String province = PROVINCE_DEFAUT;      //la province
    private String pays = PAYS_DEFAUT;              //le pays
    private String codePostal = CODE_POSTAL_DEFAUT; //le code postal

    //--------------
    //CONSTRUCTEURS
    //--------------

    /**
     * Construit un objet Adresse en initialisant ses attributs avec les valeurs
     * passees en parametres.
     *
     * Si l'un des parametres donnnes est null ou vide, l'attribut correspondant
     * est plutot initialise avec sa valeur par defaut (voir constantes de classe).
     *
     * @param noPorte le numero de porte de cette adresse.
     * @param nomRue le nom de rue de cette adresse.
     * @param apt l'appartement de cette adresse.
     * @param ville la ville de cette adresse.
     * @param province la province de cette adresse.
     * @param pays le pays de cette adresse.
     * @param codePostal le code postal de cette adresse.
     */
    public Adresse (String noPorte, String nomRue, String apt, String ville,
                    String province, String pays, String codePostal) {

        if (noPorte != null && !noPorte.isEmpty())
            this.noPorte = noPorte;

        if (nomRue != null && !nomRue.isEmpty())
            this.nomRue = nomRue;

        if (apt != null && !apt.isEmpty())
            this.apt = apt;

        if (ville != null && !ville.isEmpty())
            this.ville = ville;

        if (province != null && !province.isEmpty())
            this.province = province;

        if (pays != null && !pays.isEmpty())
            this.pays = pays;

        if (codePostal != null && !codePostal.isEmpty())
            this.codePostal = codePostal;

    }

    //---------------------
    //METHODES D'INSTANCE
    //---------------------

    /**
     * Permet d'obtenir le numero de porte de cette adresse.
     * @return le numero de porte de cette adresse.
     */
    public String getNoPorte() {
        return noPorte;
    }

    /**
     * Modifie le no de porte de cette adresse par le noPorte donne en parametre.
     * Si le noPorte donne est null ou vide, l'attribut noPorte de cet objet sera
     * modifie avec la chaine NO_PORTE_DEFAUT.
     *
     * @param noPorte le nouveau numero de porte a assigner a cette adresse.
     */
    public void setNoPorte(String noPorte) {
        if (noPorte == null || noPorte.isEmpty())
            noPorte = NO_PORTE_DEFAUT;
        this.noPorte = noPorte;
    }

    /**
     * Permet d'obtenir le pays de cette adresse.
     * @return le pays de cette adresse.
     */
    public String getPays() {
        return pays;
    }

    /**
     * Modifie le pays de cette adresse par le pays donne en parametre.
     * Si le pays donne est null ou vide, l'attribut pays de cet objet sera
     * modifie avec la chaine PAYS_DEFAUT.
     *
     * @param pays le nouveau pays a assigner a cette adresse.
     */
    public void setPays(String pays) {
        if (pays == null || pays.isEmpty())
            pays = PAYS_DEFAUT;
        this.pays = pays;
    }

    /**
     * Permet d'obtenir le nom de rue de cette adresse.
     * @return le nom de rue de cette adresse.
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Modifie le nom de rue de cette adresse par le nomRue donne en parametre.
     * Si le nomRue donne est null ou vide, l'attribut nomRue de cet objet sera
     * modifie avec la chaine NOM_RUE_DEFAUT.
     *
     * @param nomRue le nouveau nom de rue a assigner a cette adresse
     */
    public void setNomRue(String nomRue) {
        if (nomRue == null || nomRue.isEmpty())
            nomRue = NOM_RUE_DEFAUT;
        this.nomRue = nomRue;
    }

    /**
     * Permet d'obtenir l'appartement de cette adresse.
     * @return l'appartement de cette adresse.
     */
    public String getApt() {
        return apt;
    }

    /**
     * Modifie l'appartement de cette adresse par l'apt donne en parametre.
     * Si l'apt donne est null ou vide, l'attribut apt de cet objet sera modifie
     * avec la chaine APT_DEFAUT.
     *
     * @param apt le nouvel appartement a assigner a cette adresse.
     */
    public void setApt(String apt) {
        if (apt == null || apt.isEmpty())
            apt = APT_DEFAUT;
        this.apt = apt;
    }

    /**
     * Permet d'obtenir la ville de cette adresse.
     * @return la ville de cette adresse.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Modifie la ville de cette adresse par la ville donnee en parametre.
     * Si la ville donnee est null ou vide, l'attribut ville de cet objet sera
     * modifie avec la chaine VILLE_DEFAUT.
     *
     * @param ville la nouvelle ville a assigner a cette adresse.
     */
    public void setVille(String ville) {
        if (ville == null || ville.isEmpty())
            ville = VILLE_DEFAUT;
        this.ville = ville;
    }

    /**
     * Permet d'obtenir la province de cette adresse.
     * @return la province de cette adresse.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Modifie la province de cette adresse par la province donnee en parametre.
     * Si la province donnee est null ou vide, l'attribut province de cet objet
     * sera modifie avec la chaine PROVINCE_DEFAUT.
     *
     * @param province la nouvelle province a assigner a cette adresse.
     */
    public void setProvince(String province) {
        if (province == null || province.isEmpty())
            province = PROVINCE_DEFAUT;
        this.province = province;
    }

    /**
     * Permet d'obtenir le code postal de cette adresse.
     * @return le code postal de cette adresse.
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Modifie le code postal de cette adresse par le codePostal donne en
     * parametre.
     *
     * Si le codePostal donne est null ou vide, l'attribut codePostal de cet objet
     * sera modifie avec la chaine CODE_POSTAL_DEFAUT.
     *
     * @param codePostal le nouveau code postal a assigner a cette adresse.
     */
    public void setCodePostal(String codePostal) {
        if (codePostal == null || codePostal.isEmpty())
            codePostal = CODE_POSTAL_DEFAUT;
        this.codePostal = codePostal;
    }

    //------------------------------------
    //AUTRES METHODES D'INSTANCE PUBLIQUES
    //------------------------------------

    /**
     * Construit une representation de cette adresse sous forme de chaine de
     * caracteres.
     *
     * @return une representation de cette adresse sous forme de chaine de
     *         caracteres.
     */
    public String toString () {

        String cp;
        String adr = noPorte + " " + nomRue;

        if (!apt.isEmpty())
            adr = adr + ", app. " + apt;

        adr = adr + ", " + ville + ", " + province + ", " + pays.toUpperCase();

        if (!codePostal.isEmpty())
            adr = adr + ", " + codePostal.toUpperCase();

        return adr;
    }


}



