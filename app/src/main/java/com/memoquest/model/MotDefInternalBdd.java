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
                "\nmustDeleted:             "   + this.getMustDeleted() +
                "\n:createUser:             "   + this.getCreateUser() +
                "\n:createTime:             "   + this.getCreateTime()) +
                "\n:updateUser:             "   + this.getUpdateUser() +
                "\n:updateTime:             "   + this.getUpdateTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MotDefInternalBdd that = (MotDefInternalBdd) o;

        if (MotDefListeInternalBddId != null ? !MotDefListeInternalBddId.equals(that.MotDefListeInternalBddId) : that.MotDefListeInternalBddId != null)
            return false;
        if (MotDefListeServerId != null ? !MotDefListeServerId.equals(that.MotDefListeServerId) : that.MotDefListeServerId != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
            return false;
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null)
            return false;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null)
            return false;
        //if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mot != null ? !mot.equals(that.mot) : that.mot != null) return false;
        if (motDefServerId != null ? !motDefServerId.equals(that.motDefServerId) : that.motDefServerId != null)
            return false;
        if (mustDeleted != null ? !mustDeleted.equals(that.mustDeleted) : that.mustDeleted != null)
            return false;
        if (!updateTime.equals(that.updateTime)) return false;
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (motDefServerId != null ? motDefServerId.hashCode() : 0);
        result = 31 * result + (MotDefListeInternalBddId != null ? MotDefListeInternalBddId.hashCode() : 0);
        result = 31 * result + (MotDefListeServerId != null ? MotDefListeServerId.hashCode() : 0);
        result = 31 * result + (mot != null ? mot.hashCode() : 0);
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        result = 31 * result + (mustDeleted != null ? mustDeleted.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + updateTime.hashCode();
        return result;
    }
}
