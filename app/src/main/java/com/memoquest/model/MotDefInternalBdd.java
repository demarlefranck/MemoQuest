package com.memoquest.model;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class MotDefInternalBdd {

    private Integer idAi;
    private int id;
    private String mot;
    private String definition;
    private Boolean mustDeleted;
    private Integer MotDefListId;
    private int createUser;
    private String createTime;
    private int updateUser;
    private String updateTime;

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

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean getMustDeleted() {
        return mustDeleted;
    }

    public void setMustDeleted(Boolean mustDeleted) {
        this.mustDeleted = mustDeleted;
    }

    public Integer getMotDefListId() {
        return MotDefListId;
    }

    public void setMotDefListId(Integer motDefListId) {
        MotDefListId = motDefListId;
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
                "\nmot:       " + this.getMot() +
                "\ndefinition:     " + this.getDefinition() +
                "\nmustDeleted: " + this.getMustDeleted().toString() +
                "\n:createUser " + this.getCreateUser() +
                "\n:createTime " + this.getCreateTime()) +
                "\n:updateUser " + this.getUpdateUser() +
                "\n:updateTime " + this.getUpdateTime();
    }
}
