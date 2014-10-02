package com.memoquest.model;

import org.apache.http.impl.cookie.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class ListeInternalBdd {

    private Integer id;
    private Integer serverId;
    private String nom;
    private String theme;
    private String category;
    private Boolean shared;
    private Boolean mustDeleted;
    private Integer createUser;
    private String createTime;
    private Integer updateUser;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
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

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){

        return ("\nid:          " + this.getId() +
                "\nserverId:    " + this.getServerId() +
                "\nnom:         " + this.getNom() +
                "\ntheme:       " + this.getTheme() +
                "\ncathegory:   " + this.getCategory() +
                "\n:shared:      " + this.getShared() +
                "\nmustDeleted: " + this.getMustDeleted().toString() +
                "\n:createUser: " + this.getCreateUser() +
                "\n:createTime: " + this.getCreateTime()) +
                "\n:updateUser: " + this.getUpdateUser() +
                "\n:updateTime: " + this.getUpdateTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        ListeInternalBdd that = (ListeInternalBdd) o;

        if (category != null ? !category.equals(that.category) : that.category != null){
            return false;
        }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null){
            return false;
        }
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null){
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null){
            return false;
        }
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) {
            return false;
        }
        if (serverId != null ? !serverId.equals(that.serverId) : that.serverId != null){
            return false;
        }
        if (shared != null ? !shared.equals(that.shared) : that.shared != null){
            return false;
        }
        if (theme != null ? !theme.equals(that.theme) : that.theme != null){
            return false;
        }
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null){
            return false;
        }
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serverId != null ? serverId.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (shared != null ? shared.hashCode() : 0);
        result = 31 * result + (mustDeleted != null ? mustDeleted.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}



