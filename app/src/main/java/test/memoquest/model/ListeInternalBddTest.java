package test.memoquest.model;

import com.memoquest.model.ListeInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class ListeInternalBddTest {

    public ListeInternalBdd createOneListeInternalBdd(int i){
        ListeInternalBdd listeInternalBdd = new ListeInternalBdd();
        listeInternalBdd.setId(null);
        listeInternalBdd.setServerId(i);
        listeInternalBdd.setNom("listeInternalBddNom" + i);
        listeInternalBdd.setTheme("listeInternalBddTheme" + i);
        listeInternalBdd.setCategory("listeInternalBddCategory" + i);
        listeInternalBdd.setShared(false);
        listeInternalBdd.setMustDeleted(false);
        listeInternalBdd.setCreateUser(i);
        listeInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        listeInternalBdd.setUpdateUser(i);
        listeInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
        return listeInternalBdd;
    }

    public void compareAttributesOfTwoListes(ListeInternalBdd listeExpected, ListeInternalBdd listeReality){
        assertEquals(listeExpected.getId(), listeReality.getId());
        assertEquals(listeExpected.getServerId(), listeReality.getServerId());
        assertEquals(listeExpected.getNom(), listeReality.getNom());
        assertEquals(listeExpected.getTheme(), listeReality.getTheme());
        assertEquals(listeExpected.getCategory(), listeReality.getCategory());
        assertEquals(listeExpected.getShared(), listeReality.getShared());
        assertEquals(listeExpected.getMustDeleted(), listeReality.getMustDeleted());
        assertEquals(listeExpected.getCreateUser(), listeReality.getCreateUser());
        assertEquals(listeExpected.getCreateTime(), listeReality.getCreateTime());
        assertEquals(listeExpected.getUpdateUser(), listeReality.getUpdateUser());
        assertEquals(listeExpected.getUpdateTime(), listeReality.getUpdateTime());
    }
}
