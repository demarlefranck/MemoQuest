package com.memoquest.model;

import org.apache.http.impl.cookie.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class ListeInternalBdd {

    private Integer idAi;
    private int id;
    private String nom;
    private String theme;
    private String category;
    private Boolean shared;
    private Boolean mustDeleted;
    private int createUser;
    private String createTime;
    private int updateUser;
    private String updateTime;

    public ListeInternalBdd() {}

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIdAi() {
        return idAi;
    }

    public void setIdAi(Integer idAi) {
        this.idAi = idAi;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Boolean getMustDeleted() {
        return mustDeleted;
    }

    public void setMustDeleted(Boolean mustDeleted) {
        this.mustDeleted = mustDeleted;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString(){

        return ("\nidAi:    " + this.getIdAi() +
                "\nid:        " + this.getId() +
                "\nnom:       " + this.getNom() +
                "\ntheme:     " + this.getTheme() +
                "\ncathegory: " + this.getCategory() +
                "\n:shared "    + this.getShared() +
                "\nmustDeleted: " + this.getMustDeleted().toString() +
                "\n:createUser " + this.getCreateUser() +
                "\n:createTime " + this.getCreateTime()) +
                "\n:updateUser " + this.getUpdateUser() +
                "\n:updateTime " + this.getUpdateTime();
    }
}



