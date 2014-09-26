package com.memoquest.service;

import android.content.Context;

import com.memoquest.dao.ConnexionDao;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.utils.MyDateUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConnexionService {

    private ConnexionDao connexionDao;
    private UserService userService;
    private	static	String saltWord	= "PourSecuriserLeMotDePasse";

    public ConnexionService(Context context){
        connexionDao = new ConnexionDao();
        userService = new UserService(context);
    }

    public Boolean isAuthentifiateByServeur(String loginTextStr, String passwordTextStr) throws TechnicalAppException, FonctionalAppException {

        Integer userId = connexionDao.isAuthentifiateByServeur(loginTextStr, passwordTextStr);

        if(userId != null){
            UserInternalBdd userInternalBdd = new UserInternalBdd();
            userInternalBdd.setId(userId);
            userInternalBdd.setEmail(loginTextStr);
            userInternalBdd.setPassword(toMD5(passwordTextStr));
            userInternalBdd.setActif(true);
            userInternalBdd.setCreateUser(userId);
            userInternalBdd.setCreateTime(MyDateUtils.getDateTime());
            userInternalBdd.setCreateUser(userId);
            userInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
            userService.addUserInternalBddActive(userInternalBdd);

            return true;
        }
        else
            return false;
    }

    public Boolean isConnected(Context context){
        return connexionDao.isConnected(context);
    }



    //Pris de epimarket A VOIR si utilise
    private String toMD5( String password ){

        MessageDigest messageDigest;

        StringBuffer 	stringBuffer 		= 	new StringBuffer();

        StringBuffer 	stringBufferTemp 	= 	new StringBuffer();

        try {

            messageDigest = MessageDigest.getInstance( "MD5" );

            stringBufferTemp.append( password );

            stringBufferTemp.append( saltWord );

            messageDigest.update( stringBufferTemp.toString().getBytes() );

            byte 	byteData[]	 	= 		messageDigest.digest();

            for (int i = 0; i < byteData.length; i++) {

                stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            }
        }
        catch ( NoSuchAlgorithmException e )	{	System.out.println("CustomersService.class: \t 	toMD5():\t     NoSuchAlgorithmException: "	+	e.toString());	e.printStackTrace();	}

        return stringBuffer.toString();
    }

}
