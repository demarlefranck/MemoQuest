package com.memoquest.app.manageListe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.menu.ConnectionActivity;
import com.memoquest.app.menu.MenuActivity;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.CompleteListeService;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.service.InternalBdd.MotDefService;
import com.memoquest.service.InternalBdd.UserService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ModifyListesActivity extends Activity {



    final Context context = this;

    private CompleteListeService completeListeService;
    private CompleteListe completeListe;

    private EditText wordTextValue;
    private EditText defTextValue;

    private TextView titleListeView;
    private TextView addWordsAndDefView;
    private TextView saveWordsAndDefView;

    private String wordTextStr;
    private String defTextStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_listes);

        titleListeView = (TextView) this.findViewById(R.id.titleListeTextView);
/*
        wordTextValue = (EditText) this.findViewById(R.id.wordText);
        defTextValue = (EditText) this.findViewById(R.id.defText);
*/
        addWordsAndDefView = (TextView) this.findViewById(R.id.addWordsAndDef);
        saveWordsAndDefView = (TextView) this.findViewById(R.id.saveWordsAndDef);

        completeListeService = new CompleteListeService(this);

        showListeComplete();

        titleListeView.setText(completeListe.getListeInternalBdd().getNom());

        addWordsAndDefView.setOnClickListener(new View.OnClickListener() {


       //     if(isValidate()){
/*
                MotDefInternalBdd motDefInternalBdd = new MotDefInternalBdd();
                motDefInternalBdd.setMot(wordTextStr);
                motDefInternalBdd.setDefinition(defTextStr);
                motDefInternalBdd.setMotDefListId(completeListe.getListeInternalBdd().getId());
*/
@Override
			public void onClick(View arg0) {

				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.word_def_edit_alerte_box, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.editTextDialogUserInput);

				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text


						//result.setText(userInput.getText());
					    }
					  })
					.setNegativeButton("Cancel",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					    }
					  });

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}
		});
              //  motDefService.addMotDefInternalBdd(motDefInternalBdd);


                //showListeComplete();


        saveWordsAndDefView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(ModifyListesActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


    }




    private void showListeComplete() {


        loadListeByInternalBddId();

        final ListView listView = (ListView) findViewById(R.id.listMotDefView);

        String[] values = getWordListValues();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




            /*
                MotDefInternalBdd motDefInternalBdd = completeListe.getMotDefInternalBdds().get(position);
                wordTextValue.setText(motDefInternalBdd.getMot());
                defTextValue.setText(motDefInternalBdd.getDefinition());

                motDefService.deleteMotDefInternalBdd(motDefInternalBdd);
*/
            }
        });
    }

    private void loadListeByInternalBddId() {

        int listeInternalBddId = -1;

        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey("listeInternalBddId"))
            listeInternalBddId = this.getIntent().getIntExtra("listeInternalBddId", -1);
        else
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);

        if (listeInternalBddId == -1)
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de la liste", this);
        else {
            try {

                completeListe = completeListeService.getCompleteListeByListeId(listeInternalBddId);

            } catch (TechnicalAppException e) {
                Alerte.showAlertDialog("Technical Problem", this.getClass().getSimpleName() + "modifyListe(): " + "Probleme de recuperation de la liste", this);
            } catch (FonctionalAppException e) {
                Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "modifyListe(): " + "Probleme de recuperatoion de la liste", this);
            }
        }
    }

    private String[] getWordListValues() {
        List<String> worlList = new ArrayList<String>();

        for (MotDefInternalBdd mot : completeListe.getMotDefInternalBdds()) {
            worlList.add(mot.getMot());
        }

        return worlList.toArray(new String[0]);
    }

    public Boolean isValidate(){

        wordTextStr = "";
        defTextStr = "";

        wordTextStr = String.valueOf(wordTextValue.getText());
        defTextStr = String.valueOf(defTextValue.getText());

        if(wordTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir un mot", this);
            return false;
        }
        else if(defTextStr.equals("")){
            Alerte.showAlertDialog("erreur de saisie", "Veuillez saisir une définition", this);
            return false;
        }
        return true;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
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
}