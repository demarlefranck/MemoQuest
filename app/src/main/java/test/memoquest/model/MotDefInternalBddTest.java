package test.memoquest.model;

import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class MotDefInternalBddTest {

    public MotDefInternalBdd createOneMotDefInternalBdd(int i){
        MotDefInternalBdd motDefInternalBdd = new MotDefInternalBdd();
        motDefInternalBdd.setMotDefServerId(i);
        motDefInternalBdd.setMotDefListeInternalBddId(i);
        motDefInternalBdd.setMotDefListeServerId(i);
        motDefInternalBdd.setMot("MotDefInternalBddMot" + i);
        motDefInternalBdd.setDefinition("MotDefInternalBddDefiniton" + i);
        motDefInternalBdd.setMustDeleted(false);
        motDefInternalBdd.setCreateUser(i);
        motDefInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        motDefInternalBdd.setUpdateUser(i);
        motDefInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
        return motDefInternalBdd;
    }

    public void compareAttributesOfTwoMotDef(MotDefInternalBdd motDefExpected, MotDefInternalBdd motDefReality){
        assertEquals(motDefExpected.getId(), motDefReality.getId());
        assertEquals(motDefExpected.getMotDefServerId(), motDefReality.getMotDefServerId());
        assertEquals(motDefExpected.getMotDefListeInternalBddId(), motDefReality.getMotDefListeInternalBddId());
        assertEquals(motDefExpected.getMotDefListeServerId(), motDefReality.getMotDefListeServerId());
        assertEquals(motDefExpected.getMot(), motDefReality.getMot());
        assertEquals(motDefExpected.getDefinition(), motDefReality.getDefinition());
        assertEquals(motDefExpected.getMustDeleted(), motDefReality.getMustDeleted());
        assertEquals(motDefExpected.getCreateUser(), motDefReality.getCreateUser());
        assertEquals(motDefExpected.getCreateTime(), motDefReality.getCreateTime());
        assertEquals(motDefExpected.getUpdateUser(), motDefReality.getUpdateUser());
        assertEquals(motDefExpected.getUpdateTime(), motDefReality.getUpdateTime());
    }

}
