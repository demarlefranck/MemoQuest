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

    public UserInternalBdd(){

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        UserInternalBdd that = (UserInternalBdd) o;

        if (actif != null ? !actif.equals(that.actif) : that.actif != null){
            return false;
        }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null){
            return false;
        }
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null){
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null){
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null){
            return false;
        }
        if (password != null ? !password.equals(that.password) : that.password != null){
            return false;
        }
        if (pseudo != null ? !pseudo.equals(that.pseudo) : that.pseudo != null){
            return false;
        }
        if (serverId != null ? !serverId.equals(that.serverId) : that.serverId != null){
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
        result = 31 * result + (pseudo != null ? pseudo.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (actif != null ? actif.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}

