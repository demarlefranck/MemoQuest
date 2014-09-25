package com.memoquest.app.manageListe;

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
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.service.InternalBdd.ListeService;


public class CreateNewListesActivity extends Activity {

    private EditText titreListText;
    private EditText themeListText;
    private EditText cathegoryListText;
    private TextView addListText;

    private String titreListTextStr;
    private String themeListTextStr;
    private String cathegoryListTextStr;

    private ListeService listeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);

        listeService = new ListeService(this);

        titreListText = (EditText) this.findViewById(R.id.titreListText);
        themeListText = (EditText) this.findViewById(R.id.themeListText);
        cathegoryListText = (EditText) this.findViewById(R.id.cathegoryListText);

        addListText = (TextView) this.findViewById(R.id.addWordsAndDef);
        addListText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            if(isValidate()){

                ListeInternalBdd listeInternalBdd = new ListeInternalBdd();
                listeInternalBdd.setNom(titreListTextStr);
                listeInternalBdd.setTheme(themeListTextStr);
                listeInternalBdd.setCategory(cathegoryListTextStr);

                CompleteListe completeListe = new CompleteListe();
                completeListe.setListeInternalBdd(listeInternalBdd);

                int newListeId = listeService.addListe(completeListe);
                Intent intent = new Intent(CreateNewListesActivity.this, ModifyListesActivity.class);
                intent.putExtra("listeInternalBddIdAi", newListeId);
                startActivity(intent);
            }
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

    /*

    public void createListe(){

        ListeService listeService = new ListeService();
        ListeRest liste = new ListeRest(titreListTextStr, themeListTextStr, cathegoryListTextStr);

        try {
            listeService.addListe(liste, this);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Probleme", e.getText(), this);
        }
    }
*/
}
