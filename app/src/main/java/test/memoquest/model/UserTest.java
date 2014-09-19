package test.memoquest.model;

import com.memoquest.model.User;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class UserTest {

    public User createOneUser(){
        User user = new User();
        user.setId(42);
        user.setEmail("test@test.test");
        user.setPassword("passwordTest");
        return user;
    }
}
