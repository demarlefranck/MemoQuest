package com.memoquest.service;

import android.content.Context;

import com.memoquest.dao.ListeDao;
import com.memoquest.dao.rest.post.RestPostListeDao;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.Liste;

import java.util.List;

/**
 * Created by franck on 30/08/2014.
 */
public class ListeService {

    private UserService userService;

    private ListeDao listeDao;

    public ListeService(){
        userService = new UserService();
        listeDao = new ListeDao();
    }

    public Boolean addListe(Liste liste, Context context) throws FonctionalAppException {

        Integer userId = userService.getId();

        try {
            if(listeDao.restPostListe(liste, userId)){
                listeDao.reloadBddListTable(context, userId);
                return true;
            }
        } catch (TechnicalAppException e) {
            throw new FonctionalAppException("Un probleme est surevenu lors de la creation de la liste");
        }
        return false;
    }


    public List<Liste> getListes(Context context) {

        return listeDao.getListes(context);
    }

}