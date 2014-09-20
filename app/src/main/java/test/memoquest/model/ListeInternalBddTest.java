package test.memoquest.model;

import com.memoquest.model.ListeInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class ListeInternalBddTest {

    public ListeInternalBdd createOneListeInternalBdd(int i){
        ListeInternalBdd listeInternalBdd = new ListeInternalBdd();
        listeInternalBdd.setId(i);
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

    public List<ListeInternalBdd> createNListeInternalBdd(int i){
        List<ListeInternalBdd> listeInternalBdds = new ArrayList<ListeInternalBdd>();

        for(int j = 0; j != i; j++){
            listeInternalBdds.add(createOneListeInternalBdd(j));
        }
        return listeInternalBdds;
    }
}
