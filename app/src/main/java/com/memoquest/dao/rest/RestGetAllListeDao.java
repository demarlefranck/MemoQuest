package com.memoquest.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListOfListe;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestGetAllListeDao  extends AsyncTask<Void, Void, ListOfListe> {

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
            Log.e("ActivityTestSpringRest", e.getMessage(), e);
        }

        return null;
    }
/*
    @Override
    protected void onPostExecute(ListOfListe listOfListe) {


        Log.e("INFO", "CECI EST UNE TRACE:   RestGetAllListe.class / onPostExecute()");

        Log.e("INFO", "listOfListe.getEntities().get(1).getNom():                " + listOfListe.getEntities().get(1).getNom());

*/
            /*
            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            greetingIdText.setText(listOfListe.getListOfListe().toString());
            greetingContentText.setText(listOfListe.getListOfListe().toString());
        */
/*
    }
*/
}