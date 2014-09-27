package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.InternalBdd.UserService;

import test.memoquest.model.UserTest;

/**
 * Created by franck on 20/09/2014.
 */
public class UserServiceTest extends AndroidTestCase {


    private UserService userService;
    private UserTest userTest;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        userService = new UserService(context);
        userTest = new UserTest();
    }

    public void compareAttributesOfTwoUser(UserInternalBdd userExpected, UserInternalBdd userReality){

        //pas de comparaison de id car auto incremente de Sqlite
        assertEquals(userExpected.getServerId(), userReality.getServerId());
        assertEquals(userExpected.getEmail(), userReality.getEmail());
        assertEquals(userExpected.getPassword(), userReality.getPassword());
        assertEquals(userExpected.getPseudo(), userReality.getPseudo());
        assertEquals(userExpected.getActif(), userReality.getActif());
        assertEquals(userExpected.getCreateUser(), userReality.getCreateUser());
        assertEquals(userExpected.getCreateTime(), userReality.getCreateTime());
        assertEquals(userExpected.getUpdateUser(), userReality.getUpdateUser());
        assertEquals(userExpected.getUpdateTime(), userReality.getUpdateTime());
    }

    public void AllTestUserService() throws TechnicalAppException, FonctionalAppException {


        assertEquals(0, userService.getAllUserInternalBdd().size());

        UserInternalBdd userInternalBdd = userTest.createOneUser(1);
        Integer id = userService.addUserInternalBddActive(userInternalBdd);
        assertEquals(1, userService.getAllUserInternalBdd().size());


        assertEquals(id, userService.getCurrentUserId());

        UserInternalBdd userInternalBdd2 = userTest.createOneUser(2);
        userService.addUserInternalBddActive(userInternalBdd2);
        assertEquals(2, userService.getAllUserInternalBdd().size());
        compareAttributesOfTwoUser(userInternalBdd2, userService.getUserInternalBddActive());

        assertTrue(userService.isAuthentifiate());


        UserInternalBdd userInternalBdd3 = userTest.createOneUser(3);
        userService.addUserInternalBddActive(userInternalBdd3);
        assertEquals(3, userService.getAllUserInternalBdd().size());
        compareAttributesOfTwoUser(userInternalBdd3, userService.getUserInternalBddActive());

        userService.updateAllUserInternalBddToNoActive();
        assertFalse(userService.isAuthentifiate());
    }
}
