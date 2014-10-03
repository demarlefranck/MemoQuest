package com.memoquest.app.manage.liste;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.manage.menu.MenuActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.service.CompleteListeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ModifyListesActivity extends Activity implements View.OnClickListener {

    Context context = this;

    private CompleteListeService completeListeService;
    private CompleteListe completeListe;

    private TextView titleListeView;
    private TextView addWordsAndDefView;
    private TextView saveWordsAndDefView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_listes);

        titleListeView = (TextView) this.findViewById(R.id.titleListeTextView);

        addWordsAndDefView = (TextView) this.findViewById(R.id.addWordsAndDef);
        addWordsAndDefView.setOnClickListener(this);

        saveWordsAndDefView = (TextView) this.findViewById(R.id.saveWordsAndDef);
        saveWordsAndDefView.setOnClickListener(this);

        completeListeService = new CompleteListeService(this);

        showListeComplete();

        titleListeView.setText(completeListe.getListeInternalBdd().getNom());
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addWordsAndDef:
                showAlertBoxWordAndDef(null);
            break;

            case R.id.saveWordsAndDef:
                Intent intent = new Intent(ModifyListesActivity.this, MenuActivity.class);
                startActivity(intent);
            break;

            default:
                Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....", this);
            break;
        }
    }

    private void showListeComplete() {

        loadListeByInternalBddId();

        final ListView listView = (ListView) findViewById(R.id.listMotDefView);

        String[] values = getWordListValues();

        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MotDefInternalBdd motDefInternalBdd = completeListe.getMotDefInternalBdds().get(position);
                showAlertBoxWordAndDef(motDefInternalBdd);
            }
        });
    }

    /*
        Recuperation de la liste grace a son id
        passé par l'activité prédente
     */
    private void loadListeByInternalBddId() {

        int listeInternalBddId = -1;
        String bundleKey = "listeInternalBddId";

        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)){

            listeInternalBddId = this.getIntent().getIntExtra(bundleKey, -1);

        } else{

            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);
        }

        if (listeInternalBddId == -1){

            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);

        } else {
            try {

                completeListe = completeListeService.getCompleteListeByListeId(listeInternalBddId);

            } catch (FonctionalAppException e) {
                Log.e("ERROR", e.toString());
                Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "modifyListe(): " + "Probleme de recuperatoion de la liste", this);
            }
        }
    }


    public Boolean isValidate(String wordTextStr, String defTextStr){
        String empty = "";

        if(wordTextStr.equals(empty)) {

            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir un mot", this);
            return false;

        } else if(defTextStr.equals(empty)){

            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir une définition", this);
            return false;
        }
        return true;
    }


    private String[] getWordListValues() {
        List<String> worlList = new ArrayList<String>();

        for (MotDefInternalBdd mot : completeListe.getMotDefInternalBdds()) {
            worlList.add(mot.getMot());
        }

        return worlList.toArray(new String[0]);
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        Map<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }


    /*
        Fenetre de dialogue permettant la saisie d'un mot et de sa definition
     */
    private void showAlertBoxWordAndDef(final MotDefInternalBdd motDefInput) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.word_def_edit_alerte_box, null);

        final EditText editWordText = (EditText) promptsView.findViewById(R.id.editTextWord);
        final EditText editDefText = (EditText) promptsView.findViewById(R.id.editTextDef);

        if(motDefInput != null){
            editWordText.setText(motDefInput.getMot());
            editDefText.setText(motDefInput.getDefinition());
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Mot / Définition");
        alertDialogBuilder.setIcon(R.drawable.ic_launcher);

        alertDialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            }
        );
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                String wordTextStr = "";
                String defTextStr = "";
                wordTextStr =String.valueOf(editWordText.getText());
                defTextStr = String.valueOf(editDefText.getText());

                if (isValidate(wordTextStr, defTextStr)) {

                    MotDefInternalBdd motDef = null;

                    if(motDefInput != null){

                        motDefInput.setMot(wordTextStr);
                        motDefInput.setDefinition(defTextStr);
                        motDef = motDefInput;

                    } else {

                        motDef = new MotDefInternalBdd();
                        motDef.setMot(wordTextStr);
                        motDef.setDefinition(defTextStr);
                    }

                    completeListe.getMotDefInternalBdds().add(motDef);

                    try {
                        completeListeService.updateCompleteListe(completeListe);

                    } catch (FonctionalAppException e) {

                        Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "showAlertBoxWordAndDef(): " + "Probleme d'enregistrement de la liste", context);

                    }
                    showListeComplete();
                }
                }
            }
        );

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}