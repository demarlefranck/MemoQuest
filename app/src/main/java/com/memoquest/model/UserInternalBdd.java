package com.memoquest.model;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class UserInternalBdd {

    private Integer id;
    private Integer serverId;
    private String pseudo;
    private String email;
    private String password;
    private Boolean actif;
    private Integer createUser;
    private String createTime;
    private Integer updateUser;
    private String updateTime;

    public UserInternalBdd(){}


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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
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
                "\npseudo:      " + this.getEmail() +
                "\nemail:       " + this.getEmail() +
                "\npassword:    " + this.getPassword() +
                "\nactif:       " + this.getActif().toString() +
                "\n:createUser: " + this.getCreateUser() +
                "\n:createTime: " + this.getCreateTime()) +
                "\n:updateUser: " + this.getUpdateUser() +
                "\n:updateTime: " + this.getUpdateTime();
    }
}

