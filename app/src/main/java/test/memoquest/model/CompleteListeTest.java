package test.memoquest.model;

import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import static junit.framework.Assert.assertEquals;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class CompleteListeTest {

    public void compareAttributesOfTwoMotDef(MotDefInternalBdd motDefExpected, MotDefInternalBdd motDefReality){
        //PB pour tester l'ID
        //assertEquals(motDefExpected.getId(), motDefReality.getId());
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
