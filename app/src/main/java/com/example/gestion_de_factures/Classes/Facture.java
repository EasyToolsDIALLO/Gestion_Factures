package com.example.gestion_de_factures.Classes;

public class Facture {
    private String type_facture, date;
    private int prix, numb_fact, idFacture;

    //03types de constructeurs
    public Facture(int idFacture,String type_facture, String date, int prix, int numb_fact) {
        this.type_facture = type_facture;
        this.date = date;
        this.prix = prix;
        this.numb_fact = numb_fact;
        this.idFacture = idFacture;
    }
    public Facture(String type_facture, String date, int prix, int numb_fact) {
        this.type_facture = type_facture;
        this.date = date;
        this.prix = prix;
        this.numb_fact = numb_fact;
    }
    public Facture() {
    }

    //On génère les setters et les getters
    public String getType_facture() {
        return type_facture;
    }
    public void setType_facture(String type_facture) {
        this.type_facture = type_facture;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNumb_fact() {
        return numb_fact;
    }
    public void setNumb_fact(int numb_fact) {
        this.numb_fact = numb_fact;
    }

    public int getIdFacture() {
        return idFacture;
    }
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
}
