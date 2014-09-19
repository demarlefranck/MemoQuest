package test.memoquest.model;

import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class MotDefInternalBddTest {

    public MotDefInternalBdd createOneMotDefInternalBdd(int i){
        MotDefInternalBdd motDefInternalBdd = new MotDefInternalBdd();
        motDefInternalBdd.setId(-1);
        motDefInternalBdd.setMot("MotDefInternalBddMot" + i);
        motDefInternalBdd.setDefinition("MotDefInternalBddDefiniton" + i);
//        motDefInternalBdd.setMustDeleted(false);
        motDefInternalBdd.setCreateUser(i);
        motDefInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        motDefInternalBdd.setUpdateUser(i);
        motDefInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
        return motDefInternalBdd;
    }

    public List<MotDefInternalBdd> createNMotDefInternalBdd(int i){
        List<MotDefInternalBdd> motDefInternalBdds = new ArrayList<MotDefInternalBdd>();

        for(int j = 0; j != i; j++){
            motDefInternalBdds.add(createOneMotDefInternalBdd(j));
        }
        return motDefInternalBdds;
    }
}
