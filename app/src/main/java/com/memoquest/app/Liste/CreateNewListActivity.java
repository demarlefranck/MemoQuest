package com.memoquest.app.Liste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.model.Liste;
import com.memoquest.service.ListeService;


public class CreateNewListActivity extends Activity {



    private EditText titreListText;
    private EditText themeListText;
    private EditText cathegoryListText;
    private TextView cancelText;
    private TextView addListText;

    private String titreListTextStr;
    private String themeListTextStr;
    private String cathegoryListTextStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);



        titreListText = (EditText) this.findViewById(R.id.titreListText);
        themeListText = (EditText) this.findViewById(R.id.themeListText);
        cathegoryListText = (EditText) this.findViewById(R.id.cathegoryListText);

        addListText = (TextView) this.findViewById(R.id.addListText);
        addListText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isValidate()){

                    createListe();

                    /*
                    CHANGER LA REDIRECTION VERS L'AFFICHAGE DES LISTES POUR ENSUITE AJOUTER DES MOTS / DEF

                    CAR NOUS NE RECUPERONS PAS L'ID DE LA LISTE CREEE
                     */


                    Intent intent = new Intent(CreateNewListActivity.this, ModifyListesActivity.class);
                    startActivity(intent);
                }
            }
        });

        cancelText = (TextView) this.findViewById(R.id.cancelText);
        cancelText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onStop();
            }
        });
    }

    public Boolean isValidate(){

        titreListTextStr = "";
        themeListTextStr = "";
        cathegoryListTextStr ="";

        titreListTextStr = String.valueOf(titreListText.getText());
        themeListTextStr = String.valueOf(themeListText.getText());
        cathegoryListTextStr = String.valueOf(cathegoryListText.getText());

        if(titreListTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir le titre de la liste", this);
            return false;
        }
        else if(themeListTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir le thême de la liste", this);
            return false;
        }
        else if(cathegoryListTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir la catégorie de la liste", this);
            return false;
        }
        return true;
    }

    public void createListe(){

        ListeService listeService = new ListeService();
        Liste liste = new Liste();

        liste.setNom(titreListTextStr);
        liste.setTheme(themeListTextStr);
        liste.setCategory(cathegoryListTextStr);

        try {
            listeService.addListe(liste, this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Probleme", e.getText(), this);
        }
    }

    @Override
    protected void onStop() {
        Log.i("","CreateNewListActivity.class: onStop()");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_new_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
