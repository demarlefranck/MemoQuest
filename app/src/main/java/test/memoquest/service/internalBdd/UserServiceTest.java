package test.memoquest.service.internalBdd;

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
        assertEquals(userExpected.getId(), userReality.getId());
        assertEquals(userExpected.getEmail(), userReality.getEmail());
        assertEquals(userExpected.getPassword(), userReality.getPassword());
        assertEquals(userExpected.getActif(), userReality.getActif());
        assertEquals(userExpected.getCreateUser(), userReality.getCreateUser());
        assertEquals(userExpected.getCreateTime(), userReality.getCreateTime());
        assertEquals(userExpected.getUpdateUser(), userReality.getUpdateUser());
        assertEquals(userExpected.getUpdateTime(), userReality.getUpdateTime());
    }

    public void testGetUserActive() throws TechnicalAppException, FonctionalAppException {


        assertEquals(0, userService.getAllUserInternalBdd().size());

        UserInternalBdd userInternalBdd = userTest.createOneUser(1);
        userService.addUserInternalBddActive(userInternalBdd);
        assertEquals(1, userService.getAllUserInternalBdd().size());

        UserInternalBdd userInternalBdd2 = userTest.createOneUser(2);
        userService.addUserInternalBddActive(userInternalBdd2);
        assertEquals(2, userService.getAllUserInternalBdd().size());
        compareAttributesOfTwoUser(userInternalBdd2, userService.getUserInternalBddActive());


        UserInternalBdd userInternalBdd3 = userTest.createOneUser(3);
        userService.addUserInternalBddActive(userInternalBdd3);
        assertEquals(3, userService.getAllUserInternalBdd().size());
        compareAttributesOfTwoUser(userInternalBdd3, userService.getUserInternalBddActive());
    }
}
