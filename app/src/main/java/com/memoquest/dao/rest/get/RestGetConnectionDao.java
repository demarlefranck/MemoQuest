package com.memoquest.dao.rest.get;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListOfListe;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

public class RestGetConnectionDao extends AsyncTask<Void, Void, Boolean> {

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            final String url = "http://memoquest.fr";

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpclient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == 200)
                return true;

        } catch (UnknownHostException e) {
            Log.i("DEBUG", "Pas de connexion");
            return false;
        } catch (IOException e) {
            Log.e("RestGetConnectionDao.class, doInBackground(): ", e.getMessage(), e);
        }
        return false;
    }

}