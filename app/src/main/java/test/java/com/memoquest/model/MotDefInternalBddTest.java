package test.java.com.memoquest.model;

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
}
