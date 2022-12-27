package com.github.adrienKoumgangT.test;

public class TestMain {

    public static void main(String[] args) {
        Logement log4;
        Logement log0 = null;
        Logement log1 = new Logement(695, -2);
        Logement log2 = new Logement(465, 1);
        Logement log3 = log1;
        log1.setNbrLocataires(2);
        log3.setALouer(true);
        log0 = new Logement(log2.getPrixMensuel()+50,
                        log3.getNbrLocataires() - 1);
        log2.setPrixMensuel(850);
        log4 = log1;
        log2 = log4;
        log3 = log0;
        log0 = log2;

        System.out.println("log0 [prixMensuel] = "      + log0.getPrixMensuel());
        System.out.println("log0 [nbrLocataires] = "    + log0.getNbrLocataires());
        System.out.println("log0 [aLouer] = "           + log0.isaLouer());
        System.out.println();

        System.out.println("log1 [prixMensuel] = "      + log1.getPrixMensuel());
        System.out.println("log1 [nbrLocataires] = "    + log1.getNbrLocataires());
        System.out.println("log1 [aLouer] = "           + log1.isaLouer());
        System.out.println();

        System.out.println("log2 [prixMensuel] = "      + log2.getPrixMensuel());
        System.out.println("log2 [nbrLocataires] = "    + log2.getNbrLocataires());
        System.out.println("log2 [aLouer] = "           + log2.isaLouer());
        System.out.println();

        System.out.println("log3 [prixMensuel] = "      + log3.getPrixMensuel());
        System.out.println("log3 [nbrLocataires] = "    + log3.getNbrLocataires());
        System.out.println("log3 [aLouer] = "           + log3.isaLouer());
        System.out.println();

        System.out.println("log4 [prixMensuel] = "      + log4.getPrixMensuel());
        System.out.println("log4 [nbrLocataires] = "    + log4.getNbrLocataires());
        System.out.println("log4 [aLouer] = "           + log4.isaLouer());
    }

    static class Logement {
        private int prixMensuel;
        private int nbrLocataires;
        private boolean aLouer;

        public Logement(int prix, int nbrLoc) {
            if(nbrLoc < 0) {
                nbrLoc = 0;
            }
            aLouer = nbrLoc == 0;
            prixMensuel = prix;
            nbrLocataires = nbrLoc;
        }

        public int getPrixMensuel() {
            return prixMensuel;
        }

        public int getNbrLocataires() {
            return nbrLocataires;
        }

        public boolean isaLouer() {
            return aLouer;
        }

        public void setPrixMensuel(int prixMensuel) {
            this.prixMensuel = prixMensuel;
        }

        public void setNbrLocataires(int nbrLocataires) {
            if(nbrLocataires < 0) {
                nbrLocataires = 0;
            }
            this.nbrLocataires = nbrLocataires;
            this.aLouer = nbrLocataires > 0;
        }

        public void setALouer(boolean aLouer) {
            this.aLouer = aLouer;
        }
    }

}
