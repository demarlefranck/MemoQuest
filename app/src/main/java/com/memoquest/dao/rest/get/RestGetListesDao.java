package com.memoquest.dao.rest.get;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListOfListe;
import com.memoquest.model.Liste;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.List;



public class RestGetListesDao extends AsyncTask<Void, Void, List<Liste> >{

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }












    protected List<Liste> doInBackground(Void... params) {

        final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";

        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);
        StringBuilder builder = new StringBuilder();


        // Log.i("DEBUG", String.valueOf(httpResponse.getStatusLine().getStatusCode()));


        try {

            HttpResponse httpResponse = httpclient.execute(httpGet);


            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = httpResponse.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {


                Log.e("RestGetListesDao", "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String readTwitterFeed = builder.toString();
        try {

            JSONObject envellopeListesJson;
            envellopeListesJson = new JSONObject(readTwitterFeed);

            JSONArray listesJson = new JSONArray();
            listesJson = envellopeListesJson.getJSONArray("entities");

            Log.i("DEBUG", "envellopeListesJson3:      " + listesJson.toString());

            Liste liste;

            for (int i = 0; i < listesJson.length(); i++) {

                JSONObject jsonObject = listesJson.getJSONObject(i);


                liste = new Liste();
                liste.setId(Integer.parseInt(jsonObject.getString("id")));
                liste.setNom(jsonObject.getString("nom"));
                liste.setCategory(jsonObject.getString("category"));
                liste.setTheme(jsonObject.getString("theme"));


               Log.i("DEBUG", "liste : " + liste.toString());
            }

            /*
            Log.i("DEBUG", "Number of entries " + jsonArray.length());


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);


                Log.i("DEBUG", "NjsonObject.getString(\"text\") : " + jsonObject.getString("text"));
            }

            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    }

/*
            try {

            JSONArray jarr = new JSONArray(response);
                    for (int i = 0; i < jarr.length(); ++i) {

                            JSONObject jobj = jarr.getJSONObject(i);

                        // do your things...
                    }
                } catch (JSONException e) {
                e.printStackTrace();
                }
  */

        //    Log.i("DEBUG", httpResponse.getEntity().getContent().toString());

/*
            if (httpResponse.getStatusLine().getStatusCode() == 200)
                return true;
*/
/*

        } catch (UnknownHostException e) {
            Log.i("DEBUG", "Pas de connexion");

        } catch (IOException e) {
            Log.e("RestGetConnectionDao.class, doInBackground(): ", e.getMessage(), e);
        }
        return null;
    }
    /*
    @Override
    protected List<Liste> doInBackground(Void... params) {

        try {
            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


            List<Liste> entities;



            ListOfListe listOfListe = restTemplate.getForObject(url, ListOfListe.class);



            return listOfListe;

        } catch (Exception e) {
            Log.e("RestGetListesDao", e.getMessage(), e);
        }

        return null;
    }
    */


/*
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
*/