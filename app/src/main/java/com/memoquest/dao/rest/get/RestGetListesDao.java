package com.memoquest.dao.rest.get;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListOfListe;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestGetListesDao extends AsyncTask<Void, Void, ListOfListe> {

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }

    @Override
    protected ListOfListe doInBackground(Void... params) {

        try {
            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            ListOfListe listOfListe = restTemplate.getForObject(url, ListOfListe.class);

            return listOfListe;

        } catch (Exception e) {
            Log.e("RestGetListesDao", e.getMessage(), e);
        }

        return null;
    }
}