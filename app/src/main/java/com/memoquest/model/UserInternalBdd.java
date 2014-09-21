package com.memoquest.model;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class UserInternalBdd {

    private Integer idAi;
    private Integer id;
    private String email;
    private String password;
    private Boolean active;
    private int createUser;
    private String createTime;
    private int updateUser;
    private String updateTime;

    public UserInternalBdd(){}

    public UserInternalBdd(Integer idAi, String email, String password) {
        this.idAi = idAi;
        this.email = email;
        this.password = password;
    }

    public Integer getIdAi() {
        return idAi;
    }

    public void setIdAi(Integer idAi) {
        this.idAi = idAi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString(){

        return ("\nidAi:    " + this.getIdAi() +
                "\nid:        " + this.getId() +
                "\nemail:     " + this.getEmail() +
                "\npassword: " + this.getPassword() +
                "\nactive: " + this.getActive().toString() +
                "\n:createUser " + this.getCreateUser() +
                "\n:createTime " + this.getCreateTime()) +
                "\n:updateUser " + this.getUpdateUser() +
                "\n:updateTime " + this.getUpdateTime();
    }
}

