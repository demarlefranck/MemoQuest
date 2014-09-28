package test.memoquest.model;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class UserInternalBddTest {

    public UserInternalBdd createOneUser(Integer i){
        UserInternalBdd user = new UserInternalBdd();

        //pas de set id car auto incremente de Sqlite
        user.setServerId(i);
        user.setEmail("test@test.test" + i);
        user.setPassword("passwordTest" + i);
        user.setPseudo("pseudoTest" + i);
        user.setActif(true);
        user.setCreateUser(i);
        user.setCreateTime(MyDateUtils.getDateTime());
        user.setUpdateUser(i);
        user.setUpdateTime(MyDateUtils.getDateTime());
        return user;
    }

    public void compareAttributesOfTwoUser(UserInternalBdd userExpected, UserInternalBdd userReality){
        assertEquals(userExpected.getId(), userReality.getId());
        assertEquals(userExpected.getEmail(), userReality.getEmail());
        assertEquals(userExpected.getPassword(), userReality.getPassword());
        assertEquals(userExpected.getPseudo(), userReality.getPseudo());
        assertEquals(userExpected.getActif(), userReality.getActif());
        assertEquals(userExpected.getCreateUser(), userReality.getCreateUser());
        assertEquals(userExpected.getCreateTime(), userReality.getCreateTime());
        assertEquals(userExpected.getUpdateUser(), userReality.getUpdateUser());
        assertEquals(userExpected.getUpdateTime(), userReality.getUpdateTime());
    }
}