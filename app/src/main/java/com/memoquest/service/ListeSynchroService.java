package com.memoquest.service;

import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by franck on 20/09/2014.
 */
public class ListeSynchroService {

    //retourne la liste la plus recente
    public ListeInternalBdd compareListes(ListeInternalBdd liste1, ListeInternalBdd liste2) throws TechnicalAppException {
        Date date1;
        Date date2;
        try {
            date1 = MyDateUtils.convertDateStringToDate(liste1.getUpdateTime());
            date2 = MyDateUtils.convertDateStringToDate(liste2.getUpdateTime());
        } catch (ParseException e) {
            throw new TechnicalAppException(this.getClass().getSimpleName() + ": compareListes(): " + "Probleme lors de la conversion des date");
        }

        if(date1.compareTo(date2) > 0) {
            return liste1;
        }
        else
            return liste2;
    }

}
