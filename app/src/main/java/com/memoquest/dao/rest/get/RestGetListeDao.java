package com.memoquest.dao.rest.get;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListeRest;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/*
Classe pas testee mais doit etre OK

Elle doit s'appeler de cette facon:

RestGetListeDaoTest restGetListeDao = new RestGetListeDaoTest();
ListeRest liste = null;

restGetListeDao.setUserId(userId);
restGetListeDao.execute();

try {
    liste = restGetListeDao.get();
} catch (InterruptedException e) {
    e.printStackTrace();
} catch (ExecutionException e) {
    e.printStackTrace();
}
*/

public class RestGetListeDao extends AsyncTask<Void, Void, ListeRest>{

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }

    @Override
    protected ListeRest doInBackground(Void... params) {

        try {
            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            ListeRest liste = restTemplate.getForObject(url, ListeRest.class);

            return liste;

        } catch (Exception e) {
            Log.e("RestGetListesDaoTest", e.getMessage(), e);
        }

        return null;
    }
}