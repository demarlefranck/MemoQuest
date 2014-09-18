package com.memoquest.model;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class ListeInternalBdd {

    private Integer idTemp;
    private int id;
    private String nom;
    private String theme;
    private String cathegory;
    private Boolean willDelete;
    private Boolean synchro;

    public ListeInternalBdd() {}

    public ListeInternalBdd(Integer idTemp, int id, String nom, String theme, String cathegory, Boolean willDelete, Boolean synchro) {
        this.idTemp = idTemp;
        this.id = id;
        this.nom = nom;
        this.theme = theme;
        this.cathegory = cathegory;
        this.willDelete = willDelete;
        this.synchro = synchro;
    }



    public Integer getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(Integer idTemp) {
        this.idTemp = idTemp;
    }

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

    public String getCathegory() {
        return cathegory;
    }

    public void setCathegory(String cathegory) {
        this.cathegory = cathegory;
    }

    public Boolean getwillDelete() {
        return willDelete;
    }

    public void setwillDelete(Boolean willDelete) {
        this.willDelete = willDelete;
    }

    public Boolean getSynchro() {
        return synchro;
    }

    public void setSynchro(Boolean synchro) {
        this.synchro = synchro;
    }

    @Override
    public String toString(){

        return ("\nidTemp:    " + this.getIdTemp() +
                "\nid:        " + this.getId() +
                "\nnom:       " + this.getNom() +
                "\ntheme:     " + this.getTheme() +
                "\ncathegory: " + this.getCathegory() +
                "\nwillDelete:   " + this.getwillDelete() +
                "\nsynchro:   " + this.getSynchro()
        );
    }

}



