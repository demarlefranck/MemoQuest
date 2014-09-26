package com.memoquest.model;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class MotDefInternalBdd {

    private Integer id;
    private Integer motDefServerId;
    private Integer MotDefListeInternalBddId;
    private Integer MotDefListeServerId;
    private String mot;
    private String definition;
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

    public Integer getMotDefServerId() {
        return motDefServerId;
    }

    public void setMotDefServerId(Integer motDefServerId) {
        this.motDefServerId = motDefServerId;
    }

    public Integer getMotDefListeInternalBddId() {
        return MotDefListeInternalBddId;
    }

    public void setMotDefListeInternalBddId(Integer motDefListeInternalBddId) {
        MotDefListeInternalBddId = motDefListeInternalBddId;
    }

    public Integer getMotDefListeServerId() {
        return MotDefListeServerId;
    }

    public void setMotDefListeServerId(Integer motDefListeServerId) {
        MotDefListeServerId = motDefListeServerId;
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

        return ("\nid:                      "   + this.getId() +
                "\nmotDefServerId:          "   + this.getMotDefServerId() +
                "\nMotDefListeInternalBddId:"   + this.getMotDefListeInternalBddId() +
                "\nMotDefListeServerId:     "   + this.getMotDefListeServerId() +
                "\nmot:                     "   + this.getMot() +
                "\n:definition              "   + this.getDefinition() +
                "\nmustDeleted:             "   + this.getMustDeleted().toString() +
                "\n:createUser:             "   + this.getCreateUser() +
                "\n:createTime:             "   + this.getCreateTime()) +
                "\n:updateUser:             "   + this.getUpdateUser() +
                "\n:updateTime:             "   + this.getUpdateTime();
    }
}
