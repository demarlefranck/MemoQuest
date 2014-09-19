package com.memoquest.dao.rest.get;

import android.os.AsyncTask;

import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeRest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class RestGetListesDao extends AsyncTask<Void, Void, List<ListeRest> > {

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }

    protected List<ListeRest> doInBackground(Void... params) {

        List<ListeRest> listes = null;

        final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpResponse = httpclient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {

                listes = httpEntityToObject(httpResponse.getEntity());

            } else {
                new TechnicalAppException("RestGetListesDaoTest.class: Probleme lors de la recuperation des listes");
            }
        } catch (IOException e) {
            new TechnicalAppException("RestGetListesDaoTest.class: Probleme lors de la recuperation des listes: " + e.toString());
        }
        return listes;
    }

    public List<ListeRest> httpEntityToObject(HttpEntity httpEntity){
        List<ListeRest> listes = null;

        StringBuilder builder = new StringBuilder();

        try {
            InputStream inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            new TechnicalAppException("RestGetListesDaoTest.class: HttpEntityToObject(): Probleme lors de la recuperation des listes: " + e.toString());
        }
        return stringToListes(builder.toString());
    }


    public List<ListeRest> stringToListes(String string){
        List<ListeRest> listes = new ArrayList<ListeRest>();

        JSONObject envellopeListesJson;
        try {
            envellopeListesJson = new JSONObject(string);

            JSONArray listesJson = envellopeListesJson.getJSONArray("entities");

            ListeRest liste;

            for (int i = 0; i < listesJson.length(); i++) {

                JSONObject jsonObject = listesJson.getJSONObject(i);

                liste = new ListeRest();
                liste.setId(Integer.parseInt(jsonObject.getString("id")));
                liste.setNom(jsonObject.getString("nom"));
                liste.setCategory(jsonObject.getString("category"));
                liste.setTheme(jsonObject.getString("theme"));
                listes.add(liste);
            }

        } catch (JSONException e) {
            new TechnicalAppException("RestGetListesDaoTest.class: stringToListes(): Probleme lors de la conversion Json to listes: " + e.toString());
        }
        return listes;
    }
}