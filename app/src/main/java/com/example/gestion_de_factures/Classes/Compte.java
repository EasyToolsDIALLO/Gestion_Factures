package com.example.gestion_de_factures.Classes;

public class Compte {
    private String login,password;
    private int id;

    //03 types de constructeurs
    public Compte(int id,String login, String password) {
        this.login = login;
        this.password = password;
        this.id = id;
    }
    public Compte(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Compte() {
    }

    //On génère les getters et les setters

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdCompte() {
        return id;
    }
    public void setIdCompte(int id) {
        this.id = id;
    }
}
