package com.memoquest.model;

import java.util.List;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class CompleteList {
    private int idAi;
    private String nom;
    private String theme;
    private String category;
    private List<MotDefInternalBdd> wordAndDefinitions;

    public CompleteList() {
    }

    public CompleteList(String nom, String theme, String category) {
        this.nom = nom;
        this.theme = theme;
        this.category = category;
    }

    public CompleteList(int idAi, String nom, String theme, String category) {
        this.idAi = idAi;
        this.nom = nom;
        this.theme = theme;
        this.category = category;
    }

    public CompleteList(int idAi, String nom, String theme, String category, List<MotDefInternalBdd> wordAndDefinitions) {
        this.idAi = idAi;
        this.nom = nom;
        this.theme = theme;
        this.category = category;
        this.wordAndDefinitions = wordAndDefinitions;
    }

    public int getIdAi() {
        return idAi;
    }

    public void setIdAi(int idAi) {
        this.idAi = idAi;
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

    public List<MotDefInternalBdd> getWordAndDefinitions() {
        return wordAndDefinitions;
    }

    public void setWordAndDefinitions(List<MotDefInternalBdd> wordAndDefinitions) {
        this.wordAndDefinitions = wordAndDefinitions;
    }

    @Override
    public String toString() {

        return ("\nliste id:       " + this.getIdAi() +
                "\nliste nom:      " + this.getNom() +
                "\nliste theme:    " + this.getTheme() +
                "\nliste category: " + this.getCategory()
        );
    }
}