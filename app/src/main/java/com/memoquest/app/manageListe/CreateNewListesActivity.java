package com.memoquest.app.manageListe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.service.CompleteListeService;

public class CreateNewListesActivity extends Activity implements View.OnClickListener {

    private EditText titreListText;
    private EditText themeListText;
    private EditText cathegoryListText;
    private TextView addListText;

    private String titreListTextStr;
    private String themeListTextStr;
    private String cathegoryListTextStr;
    private CompleteListe completeListe;

    private CompleteListeService completeListeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);

        /*
            Penser a ajouter l'attribut shared
         */

        completeListe = null;

        completeListeService = new CompleteListeService(this);
        titreListText = (EditText) this.findViewById(R.id.titreListText);
        themeListText = (EditText) this.findViewById(R.id.themeListText);
        cathegoryListText = (EditText) this.findViewById(R.id.cathegoryListText);

        addListText = (TextView) this.findViewById(R.id.addWordsAndDef);
        addListText.setOnClickListener(this);

        initActivity();
    }

    private void initActivity() {

        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey("listeInternalBddId")){

            Integer listeId = (Integer) objetbunble.get("listeInternalBddId");

            try {
                completeListe = completeListeService.getCompleteListeByListeId(listeId);
            } catch (FonctionalAppException e) {
                Alerte.showAlertDialog("erreur technique", "Lors de la recuperation des valeurs de la liste", this);
            }
            titreListTextStr = completeListe.getListeInternalBdd().getNom();
            themeListTextStr = completeListe.getListeInternalBdd().getTheme();
            cathegoryListTextStr = completeListe.getListeInternalBdd().getCategory();

            titreListText.setText(titreListTextStr);
            themeListText.setText(themeListTextStr);
            cathegoryListText.setText(cathegoryListTextStr);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addWordsAndDef:
                if(isValidate()){
                    if (completeListe == null) {
                        createNewCompleteListe();
                    }
                    else{
                        try {
                            completeListe.getListeInternalBdd().setNom(titreListTextStr);
                            completeListe.getListeInternalBdd().setTheme(themeListTextStr);
                            completeListe.getListeInternalBdd().setCategory(cathegoryListTextStr);
                            completeListeService.updateCompleteListe(completeListe);
                        } catch (FonctionalAppException e) {
                            Alerte.showAlertDialog("erreur technique", "Lors de la recuperation des valeurs de la liste", this);
                        }
                    }
                    Intent intent = new Intent(CreateNewListesActivity.this, ModifyListesActivity.class);
                    intent.putExtra("listeInternalBddId", completeListe.getListeInternalBdd().getId());
                    startActivity(intent);
                }
            break;

            default:
                Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....", this);
            break;
        }
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

    public void createNewCompleteListe(){

        int listeId = -1;
        ListeInternalBdd listeInternalBdd = new ListeInternalBdd();
        listeInternalBdd.setNom(titreListTextStr);
        listeInternalBdd.setTheme(themeListTextStr);
        listeInternalBdd.setCategory(cathegoryListTextStr);
        listeInternalBdd.setShared(false);

        completeListe = new CompleteListe();
        completeListe.setListeInternalBdd(listeInternalBdd);

        try {
            listeId = completeListeService.addCompleteListe(completeListe);
        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Problème technique", "Une erreur s'est produite lors de la création de la liste", this);
        }

        if(listeId != -1){
            completeListe.getListeInternalBdd().setId(listeId);
        }
    }
}
