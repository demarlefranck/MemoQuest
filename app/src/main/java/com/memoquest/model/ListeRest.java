package com.memoquest.model;

import java.util.List;

public class ListeRest {


    private Integer id;
    private String nom;
    private String theme;
    private String category;

    public ListeRest() {}

    public ListeRest(String nom, String theme, String category) {
        this.nom = nom;
        this.theme = theme;
        this.category = category;
    }

    public ListeRest(int idAi, String nom, String theme, String category) {
        this.id = id;
        this.nom = nom;
        this.theme = theme;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idAi) {
        this.id = idAi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString(){

        return ("\nliste id:       " + this.getId() +
                "\nliste nom:      " + this.getNom() +
                "\nliste theme:    " + this.getTheme() +
                "\nliste category: " + this.getCategory()
               );
    }
}