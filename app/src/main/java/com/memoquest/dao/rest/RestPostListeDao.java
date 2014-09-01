package com.memoquest.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.ListOfListe;
import com.memoquest.model.Liste;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestPostListeDao extends AsyncTask<Void, Void, String> {

    private int userId;

    private Liste liste;

    public void setUserId(int id) {
        this.userId = id;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }

    @Override
    protected String doInBackground(Void... params) {

        try {

            //  POST http://memoquest.fr/MemoQuest/app_dev.php/api/users/{userId}/listes
            final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/" + this.userId + "/listes";


            RestTemplate restTemplate = new RestTemplate();

            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

            restTemplate.getMessageConverters().add(formHttpMessageConverter);
            restTemplate.getMessageConverters().add(stringHttpMessageConverternew);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("nom", this.liste.getNom());
            map.add("theme", this.liste.getTheme());
            map.add("category", this.liste.getCategory());



            return restTemplate.postForObject(url, map, String.class);

/*
JE N ARRIVE PAS A POSTER

EST CE COMME CA

SOUS REST CONSOLE

{
        "row_id": 11,
        "nom": "titre_liste_post",
        "theme": "theme_liste_post",
        "category": "category_liste_post",
        "user_id": 4
}
 */



        } catch (Exception e) {
            Log.e("ActivityTestSpringRest", e.getMessage(), e);
        }

        return null;
    }

}