package com.memoquest.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListOfListe;
import com.memoquest.model.Liste;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestPostListeDao extends AsyncTask<Void, Void, String> {

    private Liste liste;

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Liste getListe() {
        return liste;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }

    @Override
    protected String doInBackground(Void... params) {

        InputStream inputStream = null;

        try {

            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);


            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("nom", liste.getNom());
            jsonObject.accumulate("theme", liste.getTheme());
            jsonObject.accumulate("category", liste.getCategory());


            JSONObject jsonObjectEnveloppe = new JSONObject();
            jsonObjectEnveloppe.accumulate("liste", jsonObject);


            String json = "";
            json = jsonObjectEnveloppe.toString();

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream == null)
                throw new Exception("L'ajout de la liste n'a pas fonctionne:  "  );



        } catch (Exception e) {
            Log.e("ActivityTestSpringRest", e.getMessage(), e);
        }

        return null;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}