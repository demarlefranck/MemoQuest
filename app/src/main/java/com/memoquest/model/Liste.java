package com.memoquest.model;

public class Liste {

    private int id;
    private String nom;
    private String theme;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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