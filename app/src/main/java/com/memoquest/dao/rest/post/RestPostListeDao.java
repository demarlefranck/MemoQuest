package com.memoquest.dao.rest.post;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.Liste;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RestPostListeDao extends AsyncTask<Void, Void, Boolean> {

    private Liste liste;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {

            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

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

            if (httpResponse.getStatusLine().getStatusCode() == 201){

                return true;
            }

        } catch (JSONException e) {
            Log.e("RestPostListeDaoTest.class, doInBackground(): ", e.getMessage(), e);
        } catch (ClientProtocolException e) {
            Log.e("RestPostListeDaoTest.class, doInBackground(): ", e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            Log.e("RestPostListeDaoTest.class, doInBackground(): ", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("RestPostListeDaoTest.class, doInBackground(): ", e.getMessage(), e);
        }

        return false;
    }
}