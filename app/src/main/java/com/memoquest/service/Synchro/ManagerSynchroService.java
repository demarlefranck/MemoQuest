package com.memoquest.service.Synchro;

import android.content.Context;
import android.os.AsyncTask;

import com.memoquest.service.ConnexionService;

/**
 * Created by franck on 21/09/2014.
 */
public class ManagerSynchroService {

    private ConnexionService connexionService;

    public void updateAllData(Context context) {
        connexionService = new ConnexionService(context);

        if(connexionService.isConnected(context)){
            ListeSynchroService listeSynchroService = new ListeSynchroService(context);
            MotDefSynchroService motDefSynchroService = new MotDefSynchroService(context);

            listeSynchroService.updateListe();
            motDefSynchroService.updateMotDef();

        }
    }
}