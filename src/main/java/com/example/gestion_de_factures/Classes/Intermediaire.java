package com.example.gestion_de_factures.Classes;

import java.util.ArrayList;
import java.util.List;

public class Intermediaire {
    public static List<Facture> factureList = new ArrayList<>();
    public static List<Facture> factureList_payee = new ArrayList<>();
    public static Compte compte = new Compte();

    public static List<Facture> getFactureList() {
        return factureList;
    }

    public static void setFactureList(List<Facture> factureList) {
        Intermediaire.factureList = factureList;
    }

    public static List<Facture> getFactureList_payee() {
        return factureList_payee;
    }

    public static void setFactureList_payee(List<Facture> factureList_payee) {
        Intermediaire.factureList_payee = factureList_payee;
    }

    public static Compte getCompte() {
        return compte;
    }

    public static void setCompte(Compte compte) {
        Intermediaire.compte = compte;
    }
}
