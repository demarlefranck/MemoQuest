package com.memoquest.app.manageListe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.memoquest.app.R;
import com.memoquest.app.util.Alerte;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.service.CompleteListeService;
import com.memoquest.service.InternalBdd.ListeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectOrDeleteListesActivity extends ActionBarActivity {

    private Context context;
    private ListeService listeService;
    private CompleteListeService completeListeService;
    private List<ListeInternalBdd> listes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list);

        context = this;

        listeService = new ListeService(this);

        completeListeService = new CompleteListeService(this);

        showListOfListe();
    }


    private void showListOfListe() {

        getAllListeInternalBddByUser();

        final ListView listView = (ListView) findViewById(R.id.listeListview);
        String[] values = getListeTitleValues();
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ListeInternalBdd listeInternalBdd = listes.get(position);
                redirectIntent(listeInternalBdd.getId());

                /*
                ListeInternalBdd listeInternalBdd = listes.get(position);
                Intent intent = new Intent(SelectOrDeleteListesActivity.this, ModifyListesActivity.class);
                intent.putExtra("listeInternalBddId", listeInternalBdd.getId());
                startActivity(intent);
                */
            }
        });

    }



    private void redirectIntent(Integer listeId){

        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey("modifyMode") && objetbunble.containsKey("deleteMode")){

            Boolean modifyMode = (Boolean) objetbunble.get("modifyMode");
            Boolean deleteMode = (Boolean) objetbunble.get("deleteMode");

            if(modifyMode){
                Intent intent = new Intent(SelectOrDeleteListesActivity.this, ModifyListesActivity.class);
                intent.putExtra("listeInternalBddId", listeId);
                startActivity(intent);
            }
            else if(deleteMode){
                showAlertBoxDeleteListe(listeId);
            }
        }
    }

    private void getAllListeInternalBddByUser() {
        try {

            listes = listeService.getListeInternalBddByUser();

        } catch (FonctionalAppException e) {
            Alerte.showAlertDialog("Fonctional Problem", this.getClass().getSimpleName() + "getAllListeInternalBddByUser(): " + e.toString(), this);
        }
    }

    private String[] getListeTitleValues() {
        List<String> titleListeList = new ArrayList<String>();

        for (ListeInternalBdd liste : listes) {

            if(liste.getMustDeleted() == false) {
                titleListeList.add(liste.getNom());
            }
        }

        return titleListeList.toArray(new String[0]);
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

    /*
     Fenetre de dialogue permettant la saisie d'un mot et de sa definition
  */
    private void showAlertBoxDeleteListe(Integer listeId) {
        new AlertDialog.Builder(context).setTitle("Confirmation")
                                        .setTitle("Fonctionnalité momentanément indisponible")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }
                )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                            /*
                                   Doit juste mettre ListeInternalBdd.setMustDeleted = true

                                   pour etre delete sur le serveur avant de le delete dans la base
                             */

                        showListOfListe();
                    }
                })
                .setIcon(R.drawable.ic_launcher)
                .show();

    }
}