package com.memoquest.dao.rest.get;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.Liste;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/*
Classe pas testee mais doit etre OK

Elle doit s'appeler de cette facon:

RestGetListeDao restGetListeDao = new RestGetListeDao();
Liste liste = null;

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

public class RestGetListeDao extends AsyncTask<Void, Void, Liste>{

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }

    @Override
    protected Liste doInBackground(Void... params) {

        try {
            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Liste liste = restTemplate.getForObject(url, Liste.class);

            return liste;

        } catch (Exception e) {
            Log.e("RestGetListesDao", e.getMessage(), e);
        }

        return null;
    }
}